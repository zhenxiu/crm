package com.atguigu.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface DictMapper {

	@Select("select item from dicts "
			+ "where type = '地区'")
	List<String> getRegions();

	@Select("select item from dicts "
			+ "where type = '客户等级'")
	List<String> getLevels();

	@Select("select item from dicts "
			+ "where type = '信用度'")
	List<String> getCredits();
	
	@Select("select item from dicts "
			+ "where type = '满意度'")
	List<String> getSatify();

	@Select("select item from dicts "
			+ "where type = '服务类型'")
	List<String> getserviceTypes();
}
