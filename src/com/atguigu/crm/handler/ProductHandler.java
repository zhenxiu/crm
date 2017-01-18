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
import com.atguigu.crm.entity.Product;
import com.atguigu.crm.service.jpa.ProductService;
import com.atguigu.crm.web.Servlets;

@RequestMapping("/product")
@Controller
public class ProductHandler {

	@Autowired
	private ProductService productService;
	
	/*
	 * 产品信息分页的删除功能
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Integer id){
		Product product = productService.get(id);
		
		productService.delete(product);
		
		return "redirect:/product/list";
	}
	
	/*
	 * 产品信息新建页面的保存功能
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String save(Product product){
		productService.save(product);
		
		return "redirect:/product/list";
	}
	
	/*
	 * 产品信息新建功能和编辑功能
	 */
	@RequestMapping("/create")
	public String input(@RequestParam(value="id",required=false) Integer id,
						Map<String,Object> map){
		if(id != null){
			Product product = productService.get(id);
			
			map.put("product", product);
		}
		
		return "product/input";
	}
	
	/*
	 * 查询产品信息和 待条件的查询
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="0") int pageNo, 
			HttpServletRequest request){
		//1. 获取查询条件请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//2. 调用 Service 方法得到 Page 对象
		Page<Product> page = productService.getPage(pageNo,params);
		request.setAttribute("page", page);
		
		//3. 把 params 转为查询字符串, 回传给页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		request.setAttribute("queryString", queryString);

		return "product/list";
	}
}
