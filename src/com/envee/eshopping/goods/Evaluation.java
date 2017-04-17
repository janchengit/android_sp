package com.envee.eshopping.goods;

import java.util.List;

public class Evaluation {
	private int star;
	private String buyer;
	private String time;
	private String content;
	private List<String> picShow;
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getPicShow() {
		return picShow;
	}
	public void setPicShow(List<String> picShow) {
		this.picShow = picShow;
	}
}
