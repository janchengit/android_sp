package com.envee.eshopping.goods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.envee.eshopping.data.ObbManager;

public class Goods implements GoodsInterface {

	private int id;
	private int price;
	private int sales;
	private int counts;
	private String name;
	private String createtime;
	private String describe;
	private String etcfile;

	// private String listPicShow;
	// private String listEvaluation;

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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	@Override
	public List<String> getGoodsImgList() {
		// TODO Auto-generated method stub
		List<String> listGoodsImgs = getImgsPathByEtc(etcfile);

		return listGoodsImgs;
	}

	private List<String> getImgsPathByEtc(String string) {

		List<String> imagePathList = new ArrayList<String>();
		// TODO: Here maybe from other PATH, not only OBB file
		String filePath = ObbManager.getInstance(null).getObbMountPath()
				+ File.separator + string;
		File fileAll = new File(filePath);
		File[] files = fileAll.listFiles();

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (checkIsImageFile(file.getPath())) {
				imagePathList.add(file.getPath());
			}
		}

		return imagePathList;
	}

	private boolean checkIsImageFile(String fName) {
		boolean isImageFile = false;

		String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
				fName.length()).toLowerCase();
		if (FileEnd.equals("jpg") || FileEnd.equals("png")
				|| FileEnd.equals("gif") || FileEnd.equals("jpeg")
				|| FileEnd.equals("bmp")) {
			isImageFile = true;
		} else {
			isImageFile = false;
		}
		return isImageFile;
	}
}
