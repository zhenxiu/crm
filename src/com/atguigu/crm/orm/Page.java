package com.atguigu.crm.orm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> content = new ArrayList<T>();
	private int pageNumber;
	private int pageSize;
	private long total;
	

	
	public Page() {
	}

	public void setPageNumber(int pageNumber) {
		if(pageNumber < 1){
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}
	
	public void setTotal(long total) {
		this.total = total;
		
		if(this.pageNumber > getTotalPages()){
			this.pageNumber = getTotalPages();
		}
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getNumber() {
		return pageNumber;
	}

	
	public int getSize() {
		return pageSize;
	}

	
	public int getTotalPages() {
		return (((int)total + pageSize - 1) / pageSize);
	}

	public int getNumberOfElements() {
		return content.size();
	}

	
	public long getTotalElements() {
		return total;
	}

	
	public boolean hasPreviousPage() {
		return getNumber() > 1;
	}

	
	public boolean isFirstPage() {
		return !hasPreviousPage();
	}

	
	public boolean hasNextPage() {
		return getNumber() < getTotalPages();
	}

	
	public boolean isLastPage() {
		return !hasNextPage();
	}

	

	public Iterator<T> iterator() {
		return content.iterator();
	}


	public List<T> getContent() {
		return Collections.unmodifiableList(content);
	}


	public boolean hasContent() {
		return !content.isEmpty();
	}

	@Override
	public String toString() {

		String contentType = "UNKNOWN";

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}
}
