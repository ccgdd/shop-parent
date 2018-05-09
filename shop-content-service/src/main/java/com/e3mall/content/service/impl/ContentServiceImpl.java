package com.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.e3mall.content.service.ContentService;
import com.e3mall.mapper.TbContentMapper;
import com.shop.jedis.utils.JedisClient;
import com.shop.pojo.EasyUIDataGridRestful;
import com.e3mall.pojo.TbContent;
import com.e3mall.pojo.TbContentExample;
import com.e3mall.pojo.TbContentExample.Criteria;
import com.shop.utils.E3Result;
import com.shop.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



/**
 * 内容管理Service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	
	/**
	 * 添加首页模块商品
	 */
	public E3Result addContent(TbContent content) {
		//补全属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据
		contentMapper.insert(content);
		//缓存同步
		jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());
		return E3Result.ok();
	}


	@Override
	public EasyUIDataGridRestful getContentCetList(Long categoryId, int page, int rows) {
		
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		//如果没有查询数据库
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(categoryId);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//取出分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIDataGridRestful result = new EasyUIDataGridRestful();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}


	/**
	 *前台页面动态管理商品分类
	 */
	public List<TbContent> getContentList(long cid) {
		//查询缓存
		try {
			String json = jedisClient.hget(CONTENT_KEY, cid + "");
			//判断json是否为空
			if (StringUtils.isNotBlank(json)) {
				//把json转换成list
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据分类id查询内容列表
		//设置查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		 List<TbContent> list = contentMapper.selectByExample(example);
		 
		 
		 
		//向缓存中添加数据
			try {
				jedisClient.hset(CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return list;
		 
	}


	


}
