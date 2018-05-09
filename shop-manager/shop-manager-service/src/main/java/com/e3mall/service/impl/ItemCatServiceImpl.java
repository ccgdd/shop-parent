package com.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3mall.mapper.TbItemCatMapper;
import com.shop.pojo.EasyUITreeNode;
import com.e3mall.pojo.TbItemCat;
import com.e3mall.pojo.TbItemCatExample;
import com.e3mall.pojo.TbItemCatExample.Criteria;
import com.e3mall.service.ItemCatService;
/**
 * 商品分类管理
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	
	
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		//根据parentID查询子节点
		TbItemCatExample catExample = new TbItemCatExample();
		
		Criteria criteria = catExample.createCriteria();
		
		criteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = itemCatMapper.selectByExample(catExample);
		
		List<EasyUITreeNode> treeNodes = new ArrayList<EasyUITreeNode>();
		
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(tbItemCat.getId());
			easyUITreeNode.setText(tbItemCat.getName());
			easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			
			treeNodes.add(easyUITreeNode);
		}
		
		return treeNodes;
	}
	
	
	
	
	

}
