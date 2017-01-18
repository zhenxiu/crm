package com.atguigu.crm.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.mybatis.CustomerDrainService;
import com.atguigu.crm.web.Servlets;

@Controller
@RequestMapping("/drain")
public class CustomerDrainHandler {

	@Autowired
	private CustomerDrainService customerDrainService;
	
	@RequestMapping(value="/delay",method=RequestMethod.POST)
	public String delay(@RequestParam("drainId") Integer id,
						@RequestParam(value="delay",required=false) String delay, 
						Map<String,Object> map){
		CustomerDrain customerDrain = customerDrainService.get(id);
		
		if(delay != null){
			customerDrain.setDelay(customerDrain.getDelay()+"`"+delay);
		}
		
		List<String> delays = customerDrainService.getDelays(customerDrain);
		
		map.put("drain", customerDrain);
		map.put("delays", delays);
		return "drain/delay";
	}
	
	@RequestMapping(value="/delay")
	public String delay(@RequestParam("drainId") Integer id,
						Map<String,Object> map){
		CustomerDrain customerDrain = customerDrainService.get(id);
		List<String> delays = customerDrainService.getDelays(customerDrain);
		
		map.put("drain", customerDrain);
		map.put("delays", delays);
		return "drain/delay";
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
		
		Page<CustomerDrain> page = customerDrainService.getPage(pageNo,5,params);
		
		request.setAttribute("queryString", queryString);
		request.setAttribute("page", page);
		
		return "drain/list";
	}
}
