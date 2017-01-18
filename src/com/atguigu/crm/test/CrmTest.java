package com.atguigu.crm.test;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.dao.UserMapper;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.jpa.RoleService;
import com.atguigu.crm.service.mybatis.UserService;

public class CrmTest {


		private ApplicationContext ctx = null;
		private RoleService roleService = null;
		private UserMapper userMapper = null;
		
		{
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			roleService = ctx.getBean(RoleService.class);
			userMapper = ctx.getBean(UserMapper.class);
		}
		
		@Test
		public void testUserMapper(){
			User user = userMapper.getByName("bcde");
			System.out.println(user);
		}
		
		@Test
		public void testUserService() {
			UserService userService = ctx.getBean(UserService.class);
			User user = userService.login("bcde", "4f6ed9e4ab25a6dac05933a8a0c5822ada8177e5");
			
			System.out.println(user);
		}


		@Test
		public void testRoleService(){
			Role role = new Role();
			role.setDescription("自己创建的role角色");
			role.setEnabled(true);
			role.setName("test测试");
			
			roleService.save(role);
		}
		
		@Test
		public void testPrint(){
			InputStream in = System.in;
			System.out.println(in);
		}
}
