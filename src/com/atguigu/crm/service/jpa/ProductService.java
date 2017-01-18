package com.atguigu.crm.service.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.orm.SearchFilter;
import com.atguigu.crm.repository.ProductRepository;
import com.atguigu.crm.utils.DynamicSpecification;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Page<Product> getPage(int pageNo, Map<String, Object> params) {
		List<SearchFilter> filters = SearchFilter.parseParamToFilters(params);
		
		Specification<Product> specification = DynamicSpecification.buildSpecification(filters);
		
		PageRequest pageable = new PageRequest(pageNo, 5);
		
		Page<Product> page = productRepository.findAll(specification, pageable);
		
		return page;
	}

	public void save(Product product) {
		productRepository.saveAndFlush(product);
	}

	public Product get(Integer id) {
		Product product = productRepository.findOne((long)id);
		return product;
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

}
