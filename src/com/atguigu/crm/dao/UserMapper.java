package com.atguigu.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.crm.entity.User;

public interface UserMapper {

	@Select("select u.id,u.enabled,u.name,u.password,u.role_id as \"role.id\",salt,r.name as \"role.name\" "
			+ "from users u "
			+ "left outer join roles r "
			+ "on u.role_id = r.id "
			+ "where u.name=#{name}")
	User getByName(@Param("name") String username);

	@Select("select u.id,u.enabled,u.name,u.password,u.role_id as \"role.id\",salt,r.name as \"role.name\" "
			+ "from users u "
			+ "left outer join roles r "
			+ "on u.role_id = r.id")
	List<User> getAll();
	
	@Select("select u.id,u.enabled,u.name,u.password,u.role_id as \"role.id\",salt,r.name as \"role.name\" "
			+ "from users u "
			+ "left outer join roles r "
			+ "on u.role_id = r.id "
			+ "where u.id=#{id}")
	User getById(Integer id);
}
