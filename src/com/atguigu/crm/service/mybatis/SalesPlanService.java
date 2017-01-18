package com.atguigu.crm.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.dao.SalesPlanMapper;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper salesPlanMapper;
	
	public Long save(SalesPlan plan) {
		salesPlanMapper.save(plan);
		
		return plan.getId();
	}

	public void delete(Integer id) {
		salesPlanMapper.delete(id);
	}

	public void update(SalesPlan plan) {
		salesPlanMapper.update(plan);
	}

	public SalesPlan get(Integer id) {
		return salesPlanMapper.get(id);
	}

	public SalesChance getByChanceId(Integer chanceId) {
		return salesPlanMapper.getChanceById(chanceId);
	}

}
