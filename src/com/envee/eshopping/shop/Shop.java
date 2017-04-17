package com.envee.eshopping.shop;

import java.util.List;

public class Shop {
	private int id;
	private String name;
	private String icon;
	
	private int goodsCounts;
	private List<String> listPicShow;
	private List<GoodsClasses> listGoodsClasses;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getGoodsCounts() {
		return goodsCounts;
	}
	public void setGoodsCounts(int goodsCounts) {
		this.goodsCounts = goodsCounts;
	}
	public List<String> getListPicShow() {
		return listPicShow;
	}
	public void setListPicShow(List<String> listPicShow) {
		this.listPicShow = listPicShow;
	}
	public List<GoodsClasses> getListGoodsClasses() {
		return listGoodsClasses;
	}
	public void setListGoodsClasses(List<GoodsClasses> listGoodsClasses) {
		this.listGoodsClasses = listGoodsClasses;
	}
	
	
}
