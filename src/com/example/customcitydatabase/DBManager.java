package com.example.customcitydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
/**
 * 定义DBManager类，用于事先从工程项目中导入数据库到手机
 * 和用于返回一个SQLiteDatebase的实例给DBHelper
 * @author Administrator
 *
 */
public class DBManager {
	private Context context;
	private SQLiteDatabase database;
	public DBManager(Context context) {
		this.context=context;
	}
	private SQLiteDatabase getDatabase(){
		return this.database;
	}
}
