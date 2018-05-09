package com.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3mall.search.service.PreSearchService;
import com.shop.pojo.SearchResult;


/**
 * 商品搜索controller
 * @author Administrator
 *
 */
@Controller
public class PreSearchController {
	
	@Autowired
	private PreSearchService preSearchService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	
	@RequestMapping("/search")
	public String searchItemList(String keyword, @RequestParam(defaultValue="1") Integer page,Model model) throws Exception{
		
		keyword = new String(keyword.getBytes("iso-8859-1"),"UTF-8");
		
		//查询商品列表
		SearchResult searchResult = preSearchService.search(keyword, page, SEARCH_RESULT_ROWS);
		
		//把结果传递到页面
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages",searchResult.getTotalPages());
		model.addAttribute("page",page);
		model.addAttribute("recourdCount",searchResult.getRecordCount());
		model.addAttribute("itemList",searchResult.getItemList());
		return "search";
		
	}
	
	
}
