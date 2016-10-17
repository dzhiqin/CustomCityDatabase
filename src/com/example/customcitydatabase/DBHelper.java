package com.example.customcitydatabase;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper {
	private DBManager dbManager;
	private SQLiteDatabase db;
	public DBHelper(Context context) {
		// TODO 自动生成的构造函数存根
		super();
		dbManager=new DBManager(context);
	}
	public ArrayList<String> getCityArea(){
		
		dbManager.openDatabase();
		db=dbManager.getDatabase();
		ArrayList<String> cityArea=new ArrayList<String>();		
		Cursor cursor=db.query("city_id",new String[]{"city_area"},null,null,null,null,null);
		while(cursor.moveToNext()){
			String area=cursor.getString(cursor.getColumnIndex("city_area"));
			cityArea.add(area);
		}
		dbManager.closeDatabase();
		db.close();
		return cityArea;
	}
	
}
