package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;

public interface SalesChanceMapper {

	long selectTotalRecords(Map<String, Object> params);
	
	List<SalesChance> selectPageContent(Map<String, Object> params);

	void save(SalesChance chance);

	SalesChance get(Integer id);

	void update(SalesChance chance);

	@Delete("delete from sales_chances where id = #{id}")
	void delete(Integer id);
}
