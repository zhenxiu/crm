package com.atguigu.crm.service.mybatis;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.CustomerServiceMapper;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyBatisUtils;
@Service
public class CustomerServiceService {

	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	
	@Transactional(readOnly=false)
	public void save(CustomerService customerService) {
		customerServiceMapper.save(customerService);
	}

	@Transactional(readOnly=false)
	public void delete(Integer id) {
		customerServiceMapper.delete(id);
	}

	@Transactional
	public Page<SalesChance> getPage(int pageNo, int pageSize,
			Map<String, Object> params,HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		params = MyBatisUtils.parseReuqestParamsToMyBatisParams(params);

		Page<SalesChance> page = new Page<SalesChance>();
		page.setPageNumber(pageNo);
		page.setPageSize(pageSize);
		params.put("createId", user.getId());
		
		long totalNumber = customerServiceMapper.selectTotalRecords(params);

		page.setTotal(totalNumber);
		
		int fromIndex = (pageNo - 1)*pageSize;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		
		List<SalesChance> content = customerServiceMapper.selectPageContent(params);
		page.setContent(content);
		
		return page;
	}

	@Transactional
	public CustomerService getById(Integer id) {
		return customerServiceMapper.get(id);
	}

	@Transactional(readOnly=false)
	public void update(CustomerService customerService) {
		customerServiceMapper.update(customerService);
	}

}
