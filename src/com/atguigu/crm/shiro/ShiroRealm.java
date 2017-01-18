package com.atguigu.crm.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.crm.dao.UserMapper;
import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.User;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserMapper userMapper;
	/*
	 * 认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		User user = userMapper.getByName(username);
		
		if(user == null){
			throw new UnknownAccountException("用户名[" + username + "]不存在.");
		}
		if(user.getEnabled() != 1){
			throw new LockedAccountException("用户名[" + username + "]被锁定.");
		}
		
		Object principal = user;
		String hashedCredentials = user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt().getBytes());
		String realmName = getName();
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		
		return info;
	}
	
	@PostConstruct
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		
		setCredentialsMatcher(credentialsMatcher);
	}
	

	/*
	 * 授权的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = (User) principalCollection.getPrimaryPrincipal();
		
		Set<String> roles = new HashSet<String>();
		
		for(Authority authority : user.getRole().getAuthorities()){
			roles.add(authority.getName());
		}
		System.out.println("[ROLES] = "+roles);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		
		return info;
	}

	
	public static void main(String[] args) {
		String algorithmName = "MD5";
		String credentials = "z";
		ByteSource salt = ByteSource.Util.bytes("a9fc5c76e788a021".getBytes());
		int hashIterations = 1024;
		//e2b87e6eced06509
		Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		
		System.out.println(result);
	}
}
