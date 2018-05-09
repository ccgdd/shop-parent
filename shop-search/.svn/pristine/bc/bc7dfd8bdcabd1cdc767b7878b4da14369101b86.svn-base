package com.e3mall.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.e3mall.search.mapper.SearchItemMapper;
import com.shop.pojo.SearchItem;
/**
 * 监听添加商品信息。接收消息后。将消息的商品信息同步到索引库
 * @author Administrator
 *
 */
public class ItemChangeListener implements MessageListener {
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = null;
			Long itemId = null; 
			//取商品id
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				itemId = Long.parseLong(textMessage.getText());
			}
			//等待事务提交
			Thread.sleep(100);
			//向索引库添加文档
			// 1、根据商品id查询商品信息。
			SearchItem searchItem = searchItemMapper.getItemById(itemId);
			// 2、创建一SolrInputDocument对象。
			SolrInputDocument document = new SolrInputDocument();
			// 3、向文档对象中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			// 5、向索引库中添加文档对象。
			solrServer.add(document);
			solrServer.commit();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}