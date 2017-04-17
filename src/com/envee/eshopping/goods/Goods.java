package com.envee.eshopping.goods;

import java.util.List;

public class Goods {

	private int id;
	private int price;
	private int sales;
	private int counts;
	private String name;
	private String describe;
	private String etcfile;
	private List<String> listPicShow;
	private List<Evaluation> listEvaluation;

	public Goods() {
		// TODO: init with json obj
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getEtcfile() {
		return etcfile;
	}

	public void setEtcfile(String etcfile) {
		this.etcfile = etcfile;
	}

	public List<String> getListPicShow() {
		return listPicShow;
	}

	public void setListPicShow(List<String> listPicShow) {
		this.listPicShow = listPicShow;
	}

	public List<Evaluation> getListEvaluation() {
		return listEvaluation;
	}

	public void setListEvaluation(List<Evaluation> listEvaluation) {
		this.listEvaluation = listEvaluation;
	}

}
