package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.service.jpa.DictService;
import com.atguigu.crm.web.Servlets;

@Controller
@RequestMapping("/dict")
public class DictHandler {

	@Autowired
	private DictService dictService;
	
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") Integer id,Map<String,Object> map){
		Dict dict = dictService.getById(id);
		map.put("dict",dict);
		return "dict/input";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String save(Dict dict){
		dictService.save(dict);
		return "redirect:/dict/list";
	}
	
	/*
	 * 数据字典新建功能
	 */
	@RequestMapping("/create")
	public String input(Map<String,Object> map){
		
		Dict dict = new Dict();
		
		map.put("dict", dict);
		
		return "dict/input";
	}
	
	/*
	 * 基础数据数据字典连接和待条件查询
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="0") int pageNo, 
						HttpServletRequest request){
		//1. 获取查询条件请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//2. 调用 Service 方法得到 Page 对象
		Page<Dict> page = dictService.getPage(pageNo,params);
		request.setAttribute("page", page);
		
		//3. 把 params 转为查询字符串, 回传给页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		request.setAttribute("searchParams", queryString);
		
		return "dict/list";
	}
}
