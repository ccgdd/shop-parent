package com.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.e3mall.mapper.TbItemDescMapper;
import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemDesc;
import com.e3mall.pojo.TbItemExample;
import com.e3mall.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.jedis.utils.JedisClient;
import com.shop.pojo.EasyUIDataGridRestful;
import com.shop.utils.E3Result;
import com.shop.utils.IDUtils;
import com.shop.utils.JsonUtils;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper  itemMapper;
	@Autowired
	private TbItemDescMapper  itemDescMapper;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource
	private Destination topicDestination;
	
	@Autowired 
	private JedisClient jedisClient;
	
	@Value("${ITEM_INFO_PRE}")
	private String ITEM_INFO_PRE;
	
	
	@Value("${ITEM_INFO_EXPIRE}")
	private Integer ITEM_INFO_EXPIRE;
	
	public TbItem getItemById(Long id){
		
		try {
			//查询缓存
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + id + ":BASE");
			if (StringUtils.isNotBlank(json)) {
				//把json转换为java对象
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		
		try {
			//把数据保存到缓存
			jedisClient.set(ITEM_INFO_PRE + ":" + id + ":BASE", JsonUtils.objectToJson(id));
			//设置缓存的有效期
			jedisClient.expire(ITEM_INFO_PRE + ":" + id + ":BASE", ITEM_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return tbItem;
	}

	
	/*取出分页对象*/
	public EasyUIDataGridRestful getItemList(int page,int rows){
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取出分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridRestful result = new EasyUIDataGridRestful();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}


	/**
	 * 商品添加
	 */
	public E3Result addItem(TbItem item, String desc) {
		//生成一个商品id
		final long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//设置商品状态
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//向商品表中插入数据
		itemMapper.insert(item);
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		//向商品描述表中插入数据
		itemDescMapper.insert(tbItemDesc);
		
		//发生商品添加消息
		//发送一个商品添加消息
		jmsTemplate.send(topicDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});
		
		return E3Result.ok();
	}

	/**
	 * 根据商品id查询描述信息
	 */
	@Override
	public E3Result queryDescByID(Long id) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		
		return E3Result.ok(itemDesc);
	}

	
	/**
	 * 商品编辑
	 */
	
	public E3Result editItem(TbItem item, String itemDesc) {
		
		//更新商品表
		itemMapper.updateByPrimaryKeySelective(item);
		
		
		Long id = item.getId();
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(itemDesc);
		tbItemDesc.setUpdated(new Date());
		//更新商品描述表
		itemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
		
		
		return E3Result.ok(tbItemDesc);
	}


	@Override
	public TbItemDesc geTbItemDescById(Long itemId) {
		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId + ":DESC");
			//判断缓存是否命中
			if (StringUtils.isNotBlank(json) ) {
				//转换为java对象
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		try {
			jedisClient.set(ITEM_INFO_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			//设置过期时间
			jedisClient.expire(ITEM_INFO_PRE + ":" + itemId + ":DESC", ITEM_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return itemDesc;
	}
	
	
	
	
}


