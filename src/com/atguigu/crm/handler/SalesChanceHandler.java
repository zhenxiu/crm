package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.mybatis.SalesChanceService;
import com.atguigu.crm.service.mybatis.UserService;
import com.atguigu.crm.web.Servlets;
@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/stop",method=RequestMethod.PUT)
	public String stop(@RequestParam("chanceId") Integer chanceId,
						Map<String,Object> map){
		SalesChance chance = salesChanceService.getBy(chanceId);
		chance.setStatus(4);
		salesChanceService.update(chance);
		map.put("chance",chance);
		return "redirect:/plan/chance/list";
	}
	
	@RequestMapping("/finish")
	public String finish(@RequestParam("chanceId") int id,
						Map<String,Object> map){
		SalesChance chance = salesChanceService.getBy(id);
		
		salesChanceService.finish(chance);
	    map.put("chance", chance);
		return "redirect:/plan/chance/list";
	}
	
	@RequestMapping(value="/dispatch/{id}",method=RequestMethod.PUT)
	public String dispatch(SalesChance chance){
		System.out.println("designeeName======="+chance.getDesignee().getName());
		salesChanceService.update(chance);
		return "redirect:/chance/list";
	}
	
	
	@RequestMapping(value="/dispatch/{id}",method=RequestMethod.GET)
	public String dispatch(@PathVariable(value="id") Integer id,Map<String,Object> map){
		SalesChance chance = salesChanceService.getBy(id);
		List<User> users = userService.getAll();
		
		map.put("chance", chance);
		map.put("users", users);
		return "chance/dispatch";
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id,RedirectAttributes attributes){
		salesChanceService.delete(id);
		attributes.addFlashAttribute("message", "操作成功");
		return "redirect:/chance/list";
	}
	
	
	@ModelAttribute
	public void getSalesChance(@RequestParam(value="id",required=false) Integer id,
								Map<String,Object> map){
		if(id != null){
			map.put("salesChance",salesChanceService.getBy(id));
		}
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(SalesChance chance){
		salesChanceService.update(chance);
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String update(@PathVariable("id") String idStr,Map<String,Object> map){
		Integer id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		SalesChance chance = salesChanceService.getBy(id);
		if(chance == null){
			//转向一个专门的页面
			return "";
		}
		map.put("chance",chance);
		return "chance/input";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(SalesChance chance,RedirectAttributes attributes){
		chance.setStatus(1);
		salesChanceService.save(chance);
		attributes.addFlashAttribute("message", "操作成功");
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Map<String,Object> map){
		SalesChance chance = new SalesChance();
		
		map.put("chance", chance);
		return "chance/input";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
						HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		//{LIKE_contact=, LIKE_custName=A, LIKE_title=}
		
		//把 params 转为一个查询的字符串, 再传回到页面上.
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		Page<SalesChance> page = salesChanceService.getPage1(pageNo,5,params);
		
		request.setAttribute("queryString", queryString);
		request.setAttribute("page", page);
		
		return "chance/list";
	}
}
