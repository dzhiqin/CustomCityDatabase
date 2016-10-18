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
 * ����DBManager�࣬�������ȴӹ�����Ŀ�е������ݿ⵽�ֻ�
 * �����ڷ���һ��SQLiteDatebase��ʵ����DBHelper
 * @author Administrator
 *
 */
public class DBManager {
	private Context context;
	private SQLiteDatabase database;
	public static final String DB_NAME="cityid.db";//���ݿ�����
	public static final String PACKAGE_NAME="com.example.customcitydatabase";//����
	public static final String DB_PATH="/data"+Environment.getDataDirectory().getAbsolutePath()
			+"/"+PACKAGE_NAME;//���ݿ����ֻ����·������û��������
	public DBManager(Context context) {
		this.context=context;
	}
	public SQLiteDatabase getDatabase(){
		return this.database;
	}
	public void openDatabase(){
		String dbFile=DB_PATH+"/"+DB_NAME;//���ݿ�����·��
		try{
			if(!new File(dbFile).exists()){//��dbFile�ļ�����������ڣ���ִ�е���
				InputStream in=context.getResources().openRawResource(R.raw.cityid);
				//���ݿ��ļ����ڹ�����Ŀ��res/raw�ļ�����
				FileOutputStream fos=new FileOutputStream(dbFile);
				byte[] buffer=new byte[1024];
				int count=0;
				while((count=in.read(buffer))>0){
					fos.write(buffer, 0, count);
					//����Ŀ��raw�ļ����¶�ȡ���ݿ���ԴȻ��д�뵽�ֻ���dbFile·����
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
