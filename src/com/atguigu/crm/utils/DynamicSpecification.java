package com.atguigu.crm.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.atguigu.crm.orm.SearchFilter;
import com.atguigu.crm.orm.SearchFilter.Operator;

public class DynamicSpecification {

	public static <T> Specification<T> buildSpecification(
			final List<SearchFilter> filters) {
		//Specification: SpringData ��װ JPA Criteria ��ѯ�����Ķ���. 
		//ʵ��ʹ�õ�������󷽷� toPredicate �ķ���ֵ. 
		Specification<T> specification = new Specification<T>() {
			/**
			 * Predicate: JPA Criteria ��ѯ�Ĳ�ѯ����
			 * Root: Ҫ��ѯ�ĸ�����. ����ѯ�Ķ���. ���Ե�������Ӧ��������. 
			 * CriteriaBuilder: JPA Criteria ��ѯ�Ĺ�����. ���ڴ��� JPA Criteria ��ѯ��Ҫ�Ķ���. �������ڴ��� 
			 * Predicate ����. 
			 */
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				Predicate predicate = null;
				if(filters == null || filters.size() == 0){
					return builder.conjunction();
				}
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				for(SearchFilter filter: filters){
					//������������п����Ǽ�������! ���紫����� manager.name
					String propertyNames = filter.getPropertyName();
					String [] names = propertyNames.split("\\.");
					Path path = root.get(names[0]);
					if(names.length > 1){
						for(int i = 1; i < names.length; i++){
							path = path.get(names[i]);
						}	
					}
					
					Operator operator = filter.getOperator();
					Object propertyValue = filter.getPropertyValue();
					
					Predicate pre = null;
					switch(operator){
					case LIKE:
						if(propertyValue != ""){
							pre = builder.like(path, "%" + propertyValue + "%");
						}
						break;
					}
					
					if(pre != null){
						predicates.add(pre);
					}
				}
				
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
		return specification;
	}

}
