package com.atguigu.crm.handler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.mybatis.UserService;

@RequestMapping("/user")
@Controller
public class UserHandler {
	@Autowired
	private UserService userService;
	@Autowired//解析国际化资源文件
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("menu")
	public String menus(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		ServletContext application = request.getSession().getServletContext();
		String contextPath = application.getContextPath();
		
		//存放StrutsMenu 的容器
		MenuRepository repository = new MenuRepository();
		//从 application 域中获取显示样式
		MenuRepository defaultRepository = (MenuRepository)
		        application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		repository.setDisplayers(defaultRepository.getDisplayers());
		
		//代表一个真正的 Struts Menu
	    MenuComponent mc = new MenuComponent();
	    
	    String name = "CRM-MENU";
	    mc.setName(name);

	    String title = "客户关系管理系统";
	    mc.setTitle(title);
	    repository.addMenu(mc);
	    
	    Map<Long,MenuComponent> parentMenus = new HashMap<Long, MenuComponent>();
	    for(Authority authority : user.getRole().getAuthorities()){
	    	
	    	MenuComponent menu = new MenuComponent();
	    	name = authority.getId()+"";
	    	menu.setName(name);
	    	title = authority.getDisplayName();
	    	menu.setTitle(title);
	    	String location = contextPath + authority.getUrl();
	    	menu.setLocation(location);
	    	
	    	Authority parentAuthority = authority.getParentAuthority();
	    	Long parentAuthorityId = parentAuthority.getId();
	    	
	    	MenuComponent parentMenu = parentMenus.get(parentAuthorityId);
	    	if(parentMenu == null){
	    		parentMenu = new MenuComponent();
	    		parentMenu.setName(parentAuthority.getId()+"");
	    		parentMenu.setTitle(parentAuthority.getDisplayName());
	    		
	    		parentMenu.setParent(mc);
	    		parentMenus.put(parentAuthorityId, parentMenu);
	    	}
	    	menu.setParent(parentMenu);
	    }
	    
		request.setAttribute("repository", repository); 
		return "home/menu";
	}
	
	
	@RequestMapping("/shiro-login")
	public String shiroLogin(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			Map<String,Object> map,HttpSession session,
			Locale locale,RedirectAttributes redirectAttributes){
		String message = null;
		
		Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } 
            catch (UnknownAccountException uae) {
            	message = "用户名不存在或用户名和密码不匹配";
            } catch (IncorrectCredentialsException ice) {
            	message = "用户名不存在或用户名和密码不匹配";
            } catch (LockedAccountException lae) {
            	message = "用户被锁定";
            }
            catch (AuthenticationException ae) {
            	System.out.println("登陆失败: \n\n" + ae);
            	System.out.println("\n\n");
            	
            	message = "登录失败, 请联系管理员";
            }
        }

		if(message == null){
			
			session.setAttribute("user", SecurityUtils.getSubject().getPrincipal());
			return "redirect:/success";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/shiro-index";
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/index";
	}
	
	
	/**
	 * RedirectAttributes: 直译为 重定向的属性. 即可以在重定向的情况下, 把属性传回页面. 不过需要调用其
	 * addFlashAttribute 方法.
	 * 注意: 直接重定向到物理页面, 还不行. 重定向的页面, 必须经过 SpringMVC 处理. 可以是 SpringMVC Handler 的一个请求, 也可以是
	 * 配置在 SpringMVC 配置文件中的 <mvc:view-controller />
	 */
	@RequestMapping("/login")
	public String login(@RequestParam(value="username") String username,
						@RequestParam(value="password") String password,
						Map<String,Object> map,HttpSession session,
						Locale locale,RedirectAttributes redirectAttributes){
		User user = userService.login(username,password);
		
		if(user != null){
			session.setAttribute("user", user);
			return "redirect:/success";
		}
		
		//从资源文件中获取相应的信息
		String message = messageSource.getMessage("user.login.error", null, locale);
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/index";
	}
	
}
