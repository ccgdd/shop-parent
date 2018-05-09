package com.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3mall.content.service.ContentService;
import com.shop.pojo.EasyUIDataGridRestful;
import com.e3mall.pojo.TbContent;
import com.shop.utils.E3Result;

@Controller
public class ContentController {
		
		@Autowired
		private ContentService contentService;
		
		
		/**
		 * 添加内容信息
		 * @param content
		 * @return
		 */
		@RequestMapping("/content/save")
		@ResponseBody
		public E3Result addContent(TbContent content) {
			E3Result result = contentService.addContent(content);
			return result;
		}
		
		
		
		
		/**
		 * 商品分类
		 */
		@RequestMapping("/content/query/list")
		@ResponseBody
		public EasyUIDataGridRestful getContentCetList(Long categoryId ,int page, int rows){
			
			EasyUIDataGridRestful contentCetList = contentService.getContentCetList(categoryId,page, rows);
			
			return contentCetList;
			
			
		}

}
