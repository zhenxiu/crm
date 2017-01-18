package com.atguigu.crm.service.mybatis;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.ContactMapper;
import com.atguigu.crm.dao.CustomerMapper;
import com.atguigu.crm.dao.SalesChanceMapper;
import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyBatisUtils;
@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ContactMapper contactMapper;
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage1(int pageNo, int pageSize,
			Map<String, Object> params) {
		params = MyBatisUtils.parseReuqestParamsToMyBatisParams(params);

		
		Page<SalesChance> page = new Page<SalesChance>();
		page.setPageNumber(pageNo);
		page.setPageSize(pageSize);
		
		params.put("status", "1");
		long totalNumber = salesChanceMapper.selectTotalRecords(params);

		page.setTotal(totalNumber);
		
		int fromIndex = (pageNo - 1)*pageSize;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.selectPageContent(params);
		page.setContent(content);
		
		return page;
	}
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage2(int pageNo, int pageSize,
			Map<String, Object> params,
			HttpSession session) {
		params = MyBatisUtils.parseReuqestParamsToMyBatisParams(params);

		
		Page<SalesChance> page = new Page<SalesChance>();
		page.setPageNumber(pageNo);
		page.setPageSize(pageSize);
		
//		params.put("status", "2");
		params.put("designeeId", ""+((User) session.getAttribute("user")).getId());
		long totalNumber = salesChanceMapper.selectTotalRecords(params);
//		System.out.println("---------"+((User) session.getAttribute("user")).getId());
		page.setTotal(totalNumber);
		
		int fromIndex = (pageNo - 1)*pageSize;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		
		List<SalesChance> content = salesChanceMapper.selectPageContent(params);
		page.setContent(content);
		
		return page;
	}

	@Transactional(readOnly=false)
	public void save(SalesChance chance) {
		salesChanceMapper.save(chance);
	}

	@Transactional(readOnly=true)
	public SalesChance getBy(Integer id) {
		return salesChanceMapper.get(id);
	}

	@Transactional(readOnly=false)
	public void update(SalesChance chance) {
		salesChanceMapper.update(chance);
	}

	@Transactional(readOnly=false)
	public void delete(Integer id) {
		salesChanceMapper.delete(id);
	}

	@Transactional(readOnly=false)
	public void finish(SalesChance chance) {
		chance.setStatus(3);
		salesChanceMapper.update(chance);
		
		Customer customer = new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString());
		customer.setState("正常");
		customerMapper.save(customer);
		
		Contact contact = new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		contact.setCustomer(customer);
		contactMapper.save(contact);
	}

}
