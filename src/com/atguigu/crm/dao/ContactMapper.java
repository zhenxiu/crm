package com.atguigu.crm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {
	@SelectKey(before=true, keyProperty="id", resultType=Long.class, 
			statement="SELECT crm_seq.nextval FROM dual")
	@Insert("insert into contacts(id,name,tel,customer_id) "
			+ "values(#{id},#{name},#{tel},#{customer.id})")
	void save(Contact contact);

}
