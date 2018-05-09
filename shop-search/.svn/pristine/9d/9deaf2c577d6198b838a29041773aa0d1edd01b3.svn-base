package com.e3mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3mall.search.mapper.SearchItemMapper;
import com.e3mall.search.service.SearchItemService;
import com.shop.pojo.SearchItem;
import com.shop.utils.E3Result;
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired 
	private SearchItemMapper searchItemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 向solr导入所有商品
	 */
	public E3Result imporAllItems() {
		//查询商品
		List<SearchItem> itemList = searchItemMapper.getItemList();
		try {
			//遍历商品列表
			for (SearchItem searchItem : itemList) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档对象中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				//把文档对象写入到索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回导入成功
			return E3Result.ok();
			
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "数据导入失败");
		}
		
	}

}
