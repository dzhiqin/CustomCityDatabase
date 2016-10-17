package com.example.customcitydatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
/**
 * 定义DBManager类，用于事先从工程项目中导入数据库到手机
 * 和用于返回一个SQLiteDatebase的实例给DBHelper
 * @author Administrator
 *
 */
public class DBManager {
	private Context context;
	private SQLiteDatabase database;
	public static final String DB_NAME="cityid.db";
	public static final String PACKAGE_NAME="com.example.customcitydatabase";
	public static final String DB_PATH="/data"+Environment.getDataDirectory().getAbsolutePath()
			+"/"+PACKAGE_NAME;
	public DBManager(Context context) {
		this.context=context;
	}
	public SQLiteDatabase getDatabase(){
		return this.database;
	}
	public void openDatabase(){
		String dbFile=DB_PATH+DB_NAME;
		try{
			if(!new File(dbFile).exists()){
				InputStream in=context.getResources().openRawResource(R.raw.cityid);
				FileOutputStream fos=new FileOutputStream(dbFile);
				byte[] buffer=new byte[1024];
				int count=0;
				while((count=in.read(buffer))>0){
					fos.write(buffer, 0, count);
					//从项目的raw文件夹下读取数据库资源然后写入到手机里dbFile路径下
				}
				fos.close();
				in.close();
			}
			database=SQLiteDatabase.openOrCreateDatabase(dbFile, null);
		}catch(FileNotFoundException e){
			Log.v("customdatabase", "File not found!");
			e.printStackTrace();
		}catch(IOException e){
			Log.v("customdatabase", "IOException");
			e.printStackTrace();
		}
		
	}
	public void closeDatabase(){
		database.close();
	}
}
