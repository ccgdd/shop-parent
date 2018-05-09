package com.shop.pojo;

import java.io.Serializable;

public class SearchItem implements Serializable{
	
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*	http://192.168.25.133/group1/M00/00/00/wKgZhVrpUFyAHB_MAADa17pZ5qE994.jpg,
	http://192.168.25.133/group1/M00/00/00/wKgZhVrpUFyADP80AAErA-sFwhs743.jpg,
	http://192.168.25.133/group1/M00/00/00/wKgZhVrpUFyADXb6AADfJV3lRFw430.jpg
*/	   private String id; 
	   private String title; 
	   private String sell_point; 
	   private Long price; 
	   private String image; 
	   private String category_name;
	   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	} 
	   
	   
	public String[] getImages() {
		if (image !=null && !"".equals(image)) {
			String[] split = image.split(",");
			return split;
		}
		return null;
	}   
	   
}