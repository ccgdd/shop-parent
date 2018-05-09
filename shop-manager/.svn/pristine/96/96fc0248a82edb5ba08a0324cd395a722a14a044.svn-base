package com.e3mall.service;

import com.shop.pojo.EasyUIDataGridRestful;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemDesc;
import com.shop.utils.E3Result;

public interface ItemService {

	
	public TbItem getItemById(Long id);
	
	public EasyUIDataGridRestful getItemList(int page,int rows);
	
	public E3Result addItem(TbItem item,String desc);
	
	public E3Result queryDescByID(Long id);
	
	public E3Result editItem(TbItem item , String itemDesc);
	
	public TbItemDesc geTbItemDescById(Long itemId);
}
