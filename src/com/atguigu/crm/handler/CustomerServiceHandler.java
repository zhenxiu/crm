package com.atguigu.crm.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.mybatis.CustomerServiceService;
import com.atguigu.crm.service.mybatis.CustomersService;
import com.atguigu.crm.service.mybatis.DictService;
import com.atguigu.crm.service.mybatis.UserService;
import com.atguigu.crm.web.Servlets;

@Controller
@RequestMapping("/service")
public class CustomerServiceHandler {

	@Autowired
	private UserService userService;
	@Autowired
	private DictService dictService;
	@Autowired
	private CustomersService customersService;
	@Autowired
	private CustomerServiceService customerServiceService;
	
	/*
	 *archive/list.jsp 页面的查看功能
	 */
	@RequestMapping(value="/archive")
	public String archiveGet(@RequestParam("id") Integer id,
							Map<String,Object> map){
		CustomerService customerService = customerServiceService.getById(id);
		
		map.put("customerService", customerService);
		
		return "service/archive/archive";
	}
	
	/*
	 * 服务归档连接
	 */
	@RequestMapping("/archive/list")
	public String archiveList(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
							HttpServletRequest request,HttpSession session){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		//{LIKE_contact=, LIKE_custName=A, LIKE_title=}
		
		//把 params 转为一个查询的字符串, 再传回到页面上.
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		params.put("LIKE_serviceState", "已归档");
		
		Page<SalesChance> page = customerServiceService.getPage(pageNo,5,params,session);
		
		request.setAttribute("queryString", queryString);
		request.setAttribute("page", page);
		
		List<User> users = userService.getAll();
		request.setAttribute("users", users);
		
		return "service/archive/list";
	}
	
	/*
	 * feedback/feedback.jsp 的保存功能
	 * deal_result  satisfy    service_state 已归档
	 * 返回feedback/list.jsp 页面
	 */
	@RequestMapping(value="/feedback",method=RequestMethod.POST)
	public String feedbackSave(CustomerService customerService){
		customerService.setServiceState("已归档");
		customerServiceService.update(customerService);
		return "redirect:/service/feedback/list";
	}
	
	@RequestMapping("/feedback")
	public String feedbackInput(@RequestParam("id") Integer id,
								Map<String,Object> map){
		CustomerService customerService = customerServiceService.getById(id);
		
		map.put("customerService", customerService);
		
		return "service/feedback/feedback";
	}
	
	/*
	 * 服务反馈连接
	 */
	@RequestMapping("/feedback/list")
	public String feedbackList(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
							HttpServletRequest request,HttpSession session){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		//{LIKE_contact=, LIKE_custName=A, LIKE_title=}
		
		//把 params 转为一个查询的字符串, 再传回到页面上.
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		params.put("LIKE_serviceState", "已处理");
		
		Page<SalesChance> page = customerServiceService.getPage(pageNo,5,params,session);
		
		request.setAttribute("queryString", queryString);
		request.setAttribute("page", page);
		
		List<User> users = userService.getAll();
		request.setAttribute("users", users);
		
		return "service/feedback/list";
	}
	
	/*
	 * deal/deal.jsp 页面保存功能
	 * deal_date    service_deal  service_state 已处理
	 * 到deal/list.jsp 页面
	 */
	@RequestMapping(value="/deal",method=RequestMethod.POST)
	public String deal(CustomerService customerService){
//		customerService.setDealDate(new Date());
		customerService.setServiceState("已处理");
		
		customerServiceService.update(customerService);
		return "redirect:/service/deal/list";
	}
	
	
	@ModelAttribute
	public void getCustomerService(@RequestParam(value="id",required=false) Integer id,
								Map<String,Object> map){
		if(id != null){
			map.put("customerService",customerServiceService.getById(id));
		}
	}
	
	
	/*
	 * deal/list.jsp 页面 的处理连接
	 */
	@RequestMapping("/deal")
	public String dealInput(@RequestParam("id") Integer id,
							Map<String,Object> map){
		CustomerService customerService = customerServiceService.getById(id);
		map.put("customerService", customerService);
		return "service/deal/deal";
	}
	
	@RequestMapping("/deal/list")
	public String dealList(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
							HttpServletRequest request,HttpSession session){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		//{LIKE_contact=, LIKE_custName=A, LIKE_title=}
		
		//把 params 转为一个查询的字符串, 再传回到页面上.
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		params.put("LIKE_serviceState", "已分配");
		
		Page<SalesChance> page = customerServiceService.getPage(pageNo,5,params,session);
		
		request.setAttribute("queryString", queryString);
		request.setAttribute("page", page);
		
		List<User> users = userService.getAll();
		request.setAttribute("users", users);
		return "service/deal/list";
	}
	
	/*
	 * allot/list.jsp 的分配后 设置  allot_date   service_state 已分配  allot_id
	 */
	@ResponseBody
	@RequestMapping(value="/allot",method=RequestMethod.POST)
	public String allot(@RequestParam("id") Integer customerServiceId,
						@RequestParam("allotId") Integer userId){
		try {
			CustomerService customerService = customerServiceService.getById(customerServiceId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String allotDateStr = sdf.format(new Date());
			Date allotDate = sdf.parse(allotDateStr);
			
			customerService.setAllotDate(allotDate);
			customerService.setServiceState("已分配");
			customerService.setAllotTo(userService.getById(userId));
			
			customerServiceService.update(customerService);
		} catch (Exception e) {
			return "0";
		}
		
		return "1";
	}
	
	/*
	 * allot/list.jsp 页面的删除功能
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Integer id){
		customerServiceService.delete(id);
		return "redirect:/service/allot/list";
	}

	/*
	 * allot/list.jsp 带与不带条件查询分页
	 * 
	 */
	@RequestMapping(value="/allot/list",method={RequestMethod.GET,RequestMethod.POST})
	public String allot(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
						HttpServletRequest request,HttpSession session){
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		//{LIKE_contact=, LIKE_custName=A, LIKE_title=}
		
		//把 params 转为一个查询的字符串, 再传回到页面上.
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		params.put("LIKE_serviceState", "新创建");
		
		Page<SalesChance> page = customerServiceService.getPage(pageNo,5,params,session);
		
		request.setAttribute("queryString", queryString);
		request.setAttribute("page", page);
		
		List<User> users = userService.getAll();
		request.setAttribute("users", users);
		
		return "service/allot/list";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String save(CustomerService customerService,
						Map<String,Object> map,
						HttpSession session){
		User user = (User) session.getAttribute("user");
		customerService.setCreatedby(user);
		customerServiceService.save(customerService);
		
		return "redirect:/service/allot/list";
	}
	
	@RequestMapping(value="/create")
	public String create(Map<String,Object> map){
		//服务类型/客户/用户
		List<String> serviceTypes = dictService.getServiceType();
		List<Customer> customers = customersService.getAll();
		
		map.put("serviceTypes", serviceTypes);
		map.put("customers", customers);
		
		return "service/input";
	}
}
