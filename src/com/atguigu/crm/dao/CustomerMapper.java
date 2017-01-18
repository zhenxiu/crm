package com.atguigu.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.atguigu.crm.entity.Customer;

public interface CustomerMapper {
	@SelectKey(before=true, keyProperty="id", resultType=Long.class, 
			statement="SELECT crm_seq.nextval FROM dual")
	@Insert("insert into customers(id,name,no,state) "
			+ "values(#{id},#{name},#{no},#{state})")
	void save(Customer customer);

	List<Customer> getAll();

}
