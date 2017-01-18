package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.mybatis.SalesChanceService;
import com.atguigu.crm.service.mybatis.SalesPlanService;
import com.atguigu.crm.web.Servlets;

@RequestMapping("/plan")
@Controller
public class SalesPlanHandler {

	@Autowired
	private SalesPlanService salesPlanService;
	@Autowired
	private SalesChanceService salesChanceService;
	
	/*
	 * 开发成功或失败，查看详情
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam("chanceId") Integer chanceId,
						Map<String,Object> map){
		SalesChance chance = salesPlanService.getByChanceId(chanceId);
		System.out.println(chance.getSalesPlans());
		map.put("chance", chance);
		return "plan/detail";
	}
	
	
	@ResponseBody
	@RequestMapping("/execute")
	public String execute(@RequestParam("id") Integer id,
						@RequestParam("result") String result,
						Map<String,Object> map){
		SalesPlan plan = salesPlanService.get(id);
		plan.setResult(result);
		salesPlanService.update(plan);
		
		int chanceId = (int)(long)plan.getChance().getId();
		SalesChance chance = salesChanceService.getBy(chanceId);
		map.put("chance", chance);
		
		return "1";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String execution(@RequestParam("chanceId") int id,
							Map<String,Object> map){
		SalesChance chance = salesChanceService.getBy(id);
		map.put("chance",chance);
//		System.out.println("size==="+chance.getSalesPlans().size());
		return "plan/execution";
	}
	
	@ResponseBody
	@RequestMapping(value="/make-ajax",method=RequestMethod.POST)
	public String save(@RequestParam("id") Integer id,
						@RequestParam("todo") String todo){
		try {
			SalesPlan plan = salesPlanService.get(id);
			plan.setTodo(todo);
			salesPlanService.update(plan);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete-ajax",method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		try {
			salesPlanService.delete(id);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public String create(SalesPlan plan,Map<String,Object> map,
						@RequestParam("chanceId") Integer chanceId){
		SalesChance chance = salesPlanService.getByChanceId(chanceId);
		plan.setChance(chance);
		Long data = salesPlanService.save(plan);
		return ""+data;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String makePlan(@RequestParam("chanceId") Integer chanceId,
							Map<String,Object> map){
		SalesChance chance = salesPlanService.getByChanceId(chanceId);
		map.put("chance",chance);
		
		return "plan/make";
	}
	
	
	@RequestMapping("/chance/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr ,
						HttpServletRequest request,
						Map<String,Object> map,
						HttpSession session){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		Page<SalesChance> page = salesChanceService.getPage2(pageNo, 5, params,session);
		map.put("page", page);
		map.put("queryString", queryString);
		
		return "plan/list";
	}
}
