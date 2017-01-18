package com.atguigu.crm.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SearchFilter {

	public enum Operator {
		LIKE, EQ, GT, GE, LT, LE, ISNULL, NOTNULL, GTE, LTE;
	}

	private String propertyName;
	private Operator operator;
	private Object propertyValue;

	public String getPropertyName() {
		return propertyName;
	}

	public Operator getOperator() {
		return operator;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}
	
	public SearchFilter(String propertyName, Operator operator,
			Object propertyValue) {
		this.propertyName = propertyName;
		this.operator = operator;
		this.propertyValue = propertyValue;
	}

	/**
	 * 	去除标记，只保留关键查询信息
	 * @param params
	 * @return
	 */
	public static List<SearchFilter> parseParamToFilters(Map<String, Object> params){
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		
		if(params != null && params.size() > 0){
			for(Map.Entry<String, Object> entry: params.entrySet()){
				String key = entry.getKey();
				
				Object propertyValue = entry.getValue();
				String operatorCode = StringUtils.substringBefore(key, "_");
				String propertyName = StringUtils.substringAfter(key, "_");
				Operator operator = Enum.valueOf(Operator.class, operatorCode);
				
				SearchFilter filter = new SearchFilter(propertyName, operator, propertyValue);
				filters.add(filter);
			}
		}
		
		return filters;
	}
}
