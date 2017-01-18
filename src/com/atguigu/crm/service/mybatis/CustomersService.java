package com.atguigu.crm.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.dao.CustomerMapper;
import com.atguigu.crm.entity.Customer;
@Service
public class CustomersService {

	@Autowired
	private CustomerMapper customerMapper;
	
	public List<Customer> getAll() {
		return customerMapper.getAll();
	}

}
