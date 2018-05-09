package com.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e3mall.content.service.ContentService;
import com.e3mall.pojo.TbContent;

@Controller
public class IndexController {
	
	@Value("${CONTENT_LUNBO_ID}")
	private String CONTENT_LUNBO_ID;
	
	@Autowired
	private ContentService contentService;
	
	
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//查询内容列表
		List<TbContent> ad1 = contentService.getContentList(89);
		System.out.println(CONTENT_LUNBO_ID);
		model.addAttribute("ad1List", ad1);
		return "index";
	}
}
