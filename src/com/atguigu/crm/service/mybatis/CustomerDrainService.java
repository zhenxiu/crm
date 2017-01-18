package com.atguigu.crm.service.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.CustomerDrainMapper;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyBatisUtils;

@Service
public class CustomerDrainService {

	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	
	@Transactional
	public void save(){
		System.out.println("[CustomerDrainService#save]");
		customerDrainMapper.callProcedure();
	}

	@Transactional
	public Page<CustomerDrain> getPage(int pageNo, int pageSize,
			Map<String, Object> params) {
		params = MyBatisUtils.parseReuqestParamsToMyBatisParams(params);

		
		Page<CustomerDrain> page = new Page<CustomerDrain>();
		page.setPageNumber(pageNo);
		page.setPageSize(pageSize);
		
		long totalNumber = customerDrainMapper.selectTotalRecords(params);

		page.setTotal(totalNumber);
		
		int fromIndex = (pageNo - 1)*pageSize;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		
		
		List<CustomerDrain> content = customerDrainMapper.selectPageContent(params);
		page.setContent(content);
		
		return page;
	}

	public CustomerDrain get(Integer id) {
		
		return customerDrainMapper.get(id);
	}

	public List<String> getDelays(CustomerDrain customerDrain) {
		String delay = customerDrain.getDelay();
		String[] strs = delay.split("`");
		List<String> delays = new ArrayList<String>();
		for (String string : strs) {
			delays.add(string);
		}
		return delays;
	}
}
