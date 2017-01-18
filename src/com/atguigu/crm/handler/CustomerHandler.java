package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.service.mybatis.CustomersService;
import com.atguigu.crm.service.mybatis.DictService;



@Controller
@RequestMapping("/customer")
public class CustomerHandler {

	@Autowired
	private CustomersService customersService;
	@Autowired
	private DictService dictService;
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map){
		List<Customer> customers = customersService.getAll();
		
		List<String> regions = dictService.getRegion();
		List<String> levels = dictService.getLevel();
		List<String> credits = dictService.getCredit();
		map.put("regions", regions);
		map.put("levels", levels);
		map.put("credits", credits);
		map.put("customers", customers);
		
		return "customer/list";
	}
}
