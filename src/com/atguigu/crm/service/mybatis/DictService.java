package com.atguigu.crm.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.dao.DictMapper;
@Service
public class DictService {

	@Autowired
	private DictMapper dictMapper;
	
	public List<String> getRegion() {
		return dictMapper.getRegions();
	}

	public List<String> getLevel() {
		return dictMapper.getLevels();
	}

	public List<String> getCredit() {
		return dictMapper.getCredits();
	}
	
	public List<String> getServiceType(){
		return dictMapper.getserviceTypes();
	}
}
