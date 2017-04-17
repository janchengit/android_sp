package com.envee.eshopping.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShopSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String dbName = "shop.db";
	
    public ShopSQLiteOpenHelper(Context context){
    	super(context, dbName, null, 1);
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
