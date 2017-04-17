package com.envee.eshopping.shop;

import java.util.List;

public class GoodsClasses {
	private String name;
	private List<DisplayGoods> listGoods;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DisplayGoods> getListGoods() {
		return listGoods;
	}

	public void setListGoods(List<DisplayGoods> listGoods) {
		this.listGoods = listGoods;
	}




	private class DisplayGoods {
		private String icon;

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}
	}
}
