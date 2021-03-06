package com.shop.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long recordCount;
	 
	 private Integer totalPages;
	
	 private List<SearchItem> itemList;

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	
	 
	 
}
