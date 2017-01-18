package com.atguigu.crm.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.crm.orm.SearchFilter;

public class MyBatisUtils {
	
	/**
	 * 最终输出%参数%=值 的格式
	 * ����� params �а����� key-value Ϊ: LIKE_title=a, LIKE_custName=b, LIKE_contact=c
	 * ϣ�������� key-value: title=%a%, custName=%b%, contact=%c%
	 */
	public static Map<String, Object> parseReuqestParamsToMyBatisParams(Map<String, Object> params){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SearchFilter> filters = SearchFilter.parseParamToFilters(params);
		
		if(filters != null && filters.size() > 0){
			for(SearchFilter filter: filters){
				switch(filter.getOperator()){
				case LIKE:
					if(filter.getPropertyValue() != ""){
						result.put(filter.getPropertyName(), "%" + filter.getPropertyValue() + "%");
					}
				case GTE:
					if(filter.getPropertyValue() != ""){
						result.put(filter.getPropertyName(), filter.getPropertyValue() + "");
					}
				case LTE:
					if(filter.getPropertyValue() != ""){
						result.put(filter.getPropertyName(), filter.getPropertyValue() + "");
					}	
				}
			}
		}
		
		return result;
	}
	
}
