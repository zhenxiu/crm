package com.atguigu.crm.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.UserMapper;
import com.atguigu.crm.entity.User;
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Transactional(readOnly=true)
	public User login(String username, String password) {
		User user = userMapper.getByName(username);
		
		if(user != null 
				&& user.getEnabled() == 1 
				&& user.getPassword().equals(password)){
			return user;
		}
		
		return null;
	}
	
	@Transactional(readOnly=true)
	public List<User> getAll() {
		
		return userMapper.getAll();
	}

	@Transactional
	public User getById(Integer userId) {
		return userMapper.getById(userId);
	}

}
