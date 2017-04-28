package com.envee.eshopping.goods;

import java.util.List;

public interface GoodsInterface {

	public List<String> getGoodsImgList();
	
	public int getId();

	public void setId(int id);

	public int getPrice();

	public void setPrice(int price);

	public int getSales();

	public void setSales(int sales);

	public int getCounts();

	public void setCounts(int counts);

	public String getName();

	public void setName(String name);

	public String getCreatetime();

	public void setCreatetime(String createtime);

	public String getDescribe();

	public void setDescribe(String describe);

	public String getEtcfile();

	public void setEtcfile(String etcfile);
}
