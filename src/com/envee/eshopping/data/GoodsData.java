package com.envee.eshopping.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.envee.eshopping.SQLite.GoodsSQLiteOpenHelper;
import com.envee.eshopping.goods.Goods;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GoodsData {
	private static String GOODS_JSONFILE = "goods/goods.json";

	private Context context;
	
	private static GoodsData goodsdata = null;

	private ArrayList<Goods> goodsList = new ArrayList<>();

	private GoodsSQLiteOpenHelper helperGoodsDb;
	
	private SQLiteDatabase dbGoods;

	public static GoodsData getInstance(Context context) {
		
		if (goodsdata == null && context != null) {
			goodsdata = new GoodsData(context);
		}
		
		return goodsdata;
	}
	
	public GoodsData(Context context) {
		this.context = context;
		init();
	}

	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(ArrayList<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	public Goods getGoodsById(int id) {
		if (goodsList != null) {
			return goodsList.get(id);
		}
		return null;
	}

	private void init() {
		helperGoodsDb = new GoodsSQLiteOpenHelper(context);
		parseJArrayGoods();
	}

	private void parseJArrayGoods() {
		String fileJson = ObbManager.getInstance(context).getObbMountPath()
				+ "/" + GOODS_JSONFILE;
		String strJson = "";
		Gson gson = new Gson();
		
		try {		
			FileInputStream is = new FileInputStream(fileJson);
	        byte[] buffer = new byte[is.available()];
	        while (is.read(buffer) != -1);
	        strJson = new String(buffer);
	        is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		JsonParser parser = new JsonParser();
		JsonArray jsonArray = parser.parse(strJson).getAsJsonArray();

		for (JsonElement user : jsonArray) {
			Goods goods = gson.fromJson(user, new TypeToken<Goods>() {
			}.getType());
			goodsList.add(goods);
			goods.setId(goodsList.indexOf(goods));
			insertData(goods);
		}
	}

	private void insertData(Goods goods) {
		String name = goods.getName();
		String describe = goods.getDescribe();
		String etcfile = goods.getEtcfile();
		int price = goods.getPrice();
		int id = goods.getId();
		dbGoods = helperGoodsDb.getWritableDatabase();
		dbGoods.execSQL("insert into goodstable(id, name, price, describe, etcfile) values("
				+ id
				+ ", '"
				+ name
				+ "', "
				+ price
				+ ", '"
				+ describe
				+ "', '"
				+ etcfile + "')");
		dbGoods.close();
	}

	private String queryData(Goods goods) {

		Cursor cursor = helperGoodsDb.getReadableDatabase().rawQuery(
				"select id as _id,name from goodstable where name like '%"
						+ goods.getName() + "%' order by id desc ", null);

		return cursor.getString(cursor.getColumnIndex("etcfile"));
	}

	private boolean hasData(Goods goods) {
		Cursor cursor = helperGoodsDb.getReadableDatabase().rawQuery(
				"select id as _id,name from goodstable where name =?",
				new String[] { goods.getName() });

		return cursor.moveToNext();
	}

	private void deleteData(Goods goods) {
		dbGoods = helperGoodsDb.getWritableDatabase();
		dbGoods.execSQL("delete from goodstable where name =?",
				new String[] { goods.getName() });
		dbGoods.close();
	}

	public ArrayList<Goods> queryGoods(String string) {
		ArrayList<Goods> queryGoodsList = new ArrayList<>();

		Cursor cursor = helperGoodsDb.getReadableDatabase().rawQuery(
				"select id as _id,name from goodstable where name like '%"
						+ string + "%' order by id desc ", null);

		do {
			queryGoodsList.add(goodsList.get(cursor.getInt(cursor
					.getColumnIndex("id"))));
		} while (cursor.moveToNext());

		return queryGoodsList;
	}
}
