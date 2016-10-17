package com.example.customcitydatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btnTable;
    private Button btnCastedTable;
    private TextView tvText;
    private ListView listContent;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> areaData;
    private DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnTable=(Button)findViewById(R.id.btn_table);
		btnCastedTable=(Button)findViewById(R.id.btn_castedtable);
		tvText=(TextView)findViewById(R.id.tv_text);
		listContent=(ListView)findViewById(R.id.list_content);
		dbHelper=new DBHelper(this);
		areaData=dbHelper.getCityArea();
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,areaData);
		listContent.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
