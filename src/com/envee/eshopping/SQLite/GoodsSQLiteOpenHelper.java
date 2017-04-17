package com.envee.eshopping.SQLite;

import java.util.List;

import com.envee.eshopping.goods.Evaluation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoodsSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String dbName = "goods.db";
	
    public GoodsSQLiteOpenHelper(Context context){
    	super(context, dbName, null, 1);
    }
    
	private int id;
	private int price;
	private int sales;
	private int counts;
	private String name;
	private String describe;
	private List<String> listPicShow;
	private List<Evaluation> listEvaluation;
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table goodstable(id integer primary key autoincrement," 
				+ "name varchar,"
				+ "price integer,"
				+ "describe vatchar,"
				+ "etcfile varchar");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
