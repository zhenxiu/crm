package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;

public interface CustomerDrainMapper {
	
	@Update("{call drain_procedure2()}")
	void callProcedure();

	long selectTotalRecords(Map<String, Object> params);

	List<CustomerDrain> selectPageContent(Map<String, Object> params);

	CustomerDrain get(Integer id);
	
}

