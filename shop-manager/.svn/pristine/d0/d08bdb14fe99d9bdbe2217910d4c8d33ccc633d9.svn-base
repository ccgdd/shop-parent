package com.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.utils.FastDFSClient;
import com.shop.utils.JsonUtils;


/**
 * 图片上传
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String imageserverURL;

	@RequestMapping(value="/pic/upload", produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource.properties");
			//获得文件扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			//截取的位置包含点
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			//得到图片的地址名和文件名
			
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(),extName);
			//补充完整的url
			url = imageserverURL+url;
			//封装到map中返回
			Map map = new HashMap<>();
			map.put("error", 0);
			map.put("url", url);
			String json = JsonUtils.objectToJson(map);
			return json;
		} catch (Exception e) {
			//封装到map中返回
			e.printStackTrace();
			Map map = new HashMap<>();
			// TODO: handle exception
			map.put("error", 1);
			map.put("message", "图片上传失败");
			String json = JsonUtils.objectToJson(map);
			return json;
		}
	}

}
