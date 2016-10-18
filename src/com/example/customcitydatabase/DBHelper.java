package com.example.customcitydatabase;
/**
 * ������DBManager�������ݿ��ʵ����Ȼ�����˴����ݿ��л�ȡcity_spell,city_area,city_province�ķ���
 * û�м̳�SQLiteOpenHelper����Ϊ����Ҫ�����±�
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
		dbManager.openDatabase();//�ȵ������ݿ���ߴ����ݿ�
		db=dbManager.getDatabase();//��dbManager������ݿ�ʵ��
		ArrayList<String> cityProvince=new ArrayList<String>();
		//��ѯcity_id����city_province��
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
	 * ��ѯcity_id���е�city_area��city_province��city_town������
	 * �����ö��������ַ���
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
