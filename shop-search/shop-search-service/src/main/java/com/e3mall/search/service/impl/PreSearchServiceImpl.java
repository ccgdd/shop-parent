package com.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3mall.search.dao.SearchDao;
import com.e3mall.search.service.PreSearchService;
import com.shop.pojo.SearchResult;


/**
 * 前台商品搜索
 * @author Administrator
 *
 */
@Service
public class PreSearchServiceImpl implements PreSearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception  {
		// 创建一个solrquery对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(keyword);
		//分页条件
		if (page<=0) page = 1;
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		//设置默认搜索域
		solrQuery.set("df", "item_title");
		//开启高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		//调用dao执行查询
		SearchResult result = searchDao.search(solrQuery);	
		//计算总页数
		Long recordCount = result.getRecordCount();
		int totalPages = (int) Math.ceil(recordCount/rows);
		result.setTotalPages(totalPages);
		
		//返回结果
		return result;
	}

}
