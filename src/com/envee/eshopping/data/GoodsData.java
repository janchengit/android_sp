package com.envee.eshopping.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.envee.eshopping.SQLite.GoodsSQLiteOpenHelper;
import com.envee.eshopping.goods.Goods;

public class GoodsData {
	private Context context;
	
	private GoodsSQLiteOpenHelper helperGoodsDb;
	private SQLiteDatabase dbGoods;
	
	public GoodsData(Context context) {
		this.context = context;
		init();
	}
	
	private void init() {
		helperGoodsDb = new GoodsSQLiteOpenHelper(context);
	}
	
	private void insertData(Goods goods) {
		String name = goods.getName();
		String describe = goods.getDescribe();
		String etcfile = goods.getEtcfile();
		int price = goods.getPrice();
		dbGoods = helperGoodsDb.getWritableDatabase();
		dbGoods.execSQL("insert into goodstable(name, price, describe, etcfile) values('"
				+ name + "', " + price + ", '" + describe + "', '" + etcfile + "')");
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
		dbGoods.execSQL("delete from goodstable where name =?", new String[] { goods.getName() });
		dbGoods.close();
	}
	
}
