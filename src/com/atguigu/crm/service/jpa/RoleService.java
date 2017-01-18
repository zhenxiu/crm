package com.atguigu.crm.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role){
		roleRepository.saveAndFlush(role);
	}
}
