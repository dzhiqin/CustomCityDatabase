package com.example.customcitydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
/**
 * ����DBManager�࣬�������ȴӹ�����Ŀ�е������ݿ⵽�ֻ�
 * �����ڷ���һ��SQLiteDatebase��ʵ����DBHelper
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
