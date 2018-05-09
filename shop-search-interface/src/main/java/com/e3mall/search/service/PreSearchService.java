package com.e3mall.search.service;

import com.shop.pojo.SearchResult;

public interface PreSearchService {
	/**前台
	 * 查询所有商品
	 * @return
	 * @throws Exception 
	 */
	SearchResult search(String keyword, int page, int rows) throws Exception;
	
}
