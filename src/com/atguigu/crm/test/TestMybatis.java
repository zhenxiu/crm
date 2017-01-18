package com.atguigu.crm.test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.atguigu.crm.dao.SalesPlanMapper;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.service.mybatis.CustomersService;
import com.atguigu.crm.service.mybatis.DictService;


public class TestMybatis {

	private SalesPlanMapper salesPlanMapper = null;
	private ApplicationContext ioc = null;
	{
		ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		salesPlanMapper = ioc.getBean(SalesPlanMapper.class);
		
	}
	
	@Test
	public void testInsert() {
		
		SalesChance chance = salesPlanMapper.getChanceById(16);
		
		Set<SalesPlan> plans = chance.getSalesPlans();
		
		for (SalesPlan salesPlan : plans) {
			System.out.println(salesPlan);
		}
		
		SalesPlan plan = new SalesPlan();
//		System.out.println(chance.getSalesPlans().size());
		plan.setDate(new Date());
		plan.setResult("hello world");
		plan.setTodo("how are you");
	}

	@Test
	public void testCustomer(){
		CustomersService customersService = ioc.getBean(CustomersService.class);
		
		List<Customer> customers = customersService.getAll();
		
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
	
	
	@Test
	public void getRegion(){
		DictService dictService = ioc.getBean(DictService.class);
		List regions = dictService.getRegion();
		for (Object object : regions) {
			System.out.println(object);
		}
	}
}
