package com.example.customcitydatabase;
/**
 * 这个类从DBManager类获得数据库的实例，然后定义了从数据库中获取city_spell,city_area,city_province的方法
 * 没有继承SQLiteOpenHelper，因为不需要创建新表
 */
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper {
	private DBManager dbManager;
	private SQLiteDatabase db;
	public DBHelper(Context context) {		
		super();
		dbManager=new DBManager(context);
	}
	public ArrayList<String>getCityProvince(){
		dbManager.openDatabase();//先导入数据库或者打开数据库
		db=dbManager.getDatabase();//从dbManager获得数据库实例
		ArrayList<String> cityProvince=new ArrayList<String>();
		//查询city_id表中city_province列
		Cursor cursor=db.query("city_id", new String[]{"city_province"},null, null, null, null, null);
		while(cursor.moveToNext()){
			String province=cursor.getString(cursor.getColumnIndex("city_province"));
			cityProvince.add(province);
		}
		cursor.close();
		dbManager.closeDatabase();
		db.close();
		return cityProvince;
	}
	public ArrayList<String>getCitySpellZH(){
		dbManager.openDatabase();
		db=dbManager.getDatabase();
		ArrayList<String> citySpellZH=new ArrayList<String>();
		Cursor cursor=db.query("city_id", new String[]{"city_spell_zh"},null,null,null,null,null);
		while(cursor.moveToNext()){
			String spellZH=cursor.getString(cursor.getColumnIndex("city_spell_zh"));
			citySpellZH.add(spellZH);
		}
		cursor.close();
		dbManager.closeDatabase();
		db.close();
		return citySpellZH;
	}
	/**
	 * 查询city_id表中的city_area，city_province，city_town三个列
	 * 并且用逗号连接字符串
	 * @return
	 */
	public ArrayList<String> getCityProvinceArea(){
		dbManager.openDatabase();
		db=dbManager.getDatabase();
		ArrayList<String> cityProvinceArea=new ArrayList<String>();
		Cursor cursor=db.query("city_id", new String[]{"city_area","city_province","city_town"}, null, null, null, null,null);
		while(cursor.moveToNext()){
			String provinceArea=cursor.getString(cursor.getColumnIndex("city_province"))+","+
					cursor.getString(cursor.getColumnIndex("city_town"))+","+
					cursor.getString(cursor.getColumnIndex("city_area"));
			cityProvinceArea.add(provinceArea);
		}
		cursor.close();
		dbManager.closeDatabase();
		db.close();
		return cityProvinceArea;
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
		cursor.close();
		dbManager.closeDatabase();
		db.close();
		return cityArea;
	}
	
}
