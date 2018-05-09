package com.e3mall.search.mapper;

import java.util.List;

import com.shop.pojo.SearchItem;

public interface SearchItemMapper {

	//查询所有商品列表
	List<SearchItem> getItemList();
	
	SearchItem getItemById(Long itemId);
}
