package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.SalesChance;

public interface CustomerServiceMapper {

	void save(CustomerService customerService);

	@Delete("delete from customer_services "
			+ "where id = #{id}")
	void delete(Integer id);

	
	long selectTotalRecords(Map<String, Object> params);

	List<SalesChance> selectPageContent(Map<String, Object> params);

	CustomerService get(Integer id);

	void update(CustomerService customerService);

}
