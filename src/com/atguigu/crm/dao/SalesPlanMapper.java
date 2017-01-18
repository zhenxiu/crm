package com.atguigu.crm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	@SelectKey(before=true, keyProperty="id", resultType=Long.class, 
			statement="SELECT crm_seq.nextval FROM dual")
	@Insert("INSERT INTO sales_plan(id, plan_date, todo, chance_id) "
			+ "VALUES(#{id}, #{date}, #{todo}, #{chance.id})")
	void save(SalesPlan plan);


	void delete(Integer id);

	void update(SalesPlan plan);

	SalesPlan get(Integer id);


	SalesChance getChanceById(Integer chanceId);

}
