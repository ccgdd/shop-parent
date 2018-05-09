package com.e3mall.content.service;

import java.util.List;

import com.e3mall.pojo.EasyUIDataGridRestful;
import com.shop.pojo.EasyUITreeNode;
import com.shop.utils.E3Result;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCategoryList(Long parentId);
	E3Result addContentCategory(long parentId, String name);
	void updateCategoryName(Long id, String name);
	E3Result deleteNode(Object object, long id);
	
}
