package com.example.customcitydatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btnTable;
    private Button btnCastedTable;
    private EditText etText;
    private ListView listContent;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> spellData=new ArrayList<String>();
    private DBHelper dbHelper;
    private ImageView imgDelete;
    private static final int TEXT_CHANGED=1;
    private ArrayList<String> provinceAreaData=new ArrayList<String>();
    ArrayList<String> subProvinceAreaData=new ArrayList<String>();
   
    private Handler myHandler=new Handler(){
    	@Override
    	public void handleMessage(Message msg){
    		switch(msg.what){
    		case TEXT_CHANGED:
    		//	Log.v("customdatabase", "msg.obj="+msg.obj);    			
    			String str=msg.obj.toString();
    			subProvinceAreaData.clear();
    			for(int i=0;i<spellData.size();i++){    				
    				if((provinceAreaData.get(i).contains(str))||(spellData.get(i).contains(str))){
        				subProvinceAreaData.add(provinceAreaData.get(i));
        			}
    			}
    			adapter=new ArrayAdapter<String>(MainActivity.this,
    					android.R.layout.simple_list_item_1,subProvinceAreaData);
    			listContent.setAdapter(adapter);
    		}
    	}
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnTable=(Button)findViewById(R.id.btn_table);
		btnCastedTable=(Button)findViewById(R.id.btn_castedtable);
		etText=(EditText)findViewById(R.id.et_text);
		listContent=(ListView)findViewById(R.id.list_content);		
		imgDelete=(ImageView)findViewById(R.id.img_delete);
		initialData();
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData());
		listContent.setAdapter(adapter);
		listContent.setOnItemClickListener(new OnItemClickListener(){//listview�ļ�������
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ʾѡ�еĳ��е���
				Toast.makeText(MainActivity.this, subProvinceAreaData.get(position), Toast.LENGTH_SHORT).show();
			}
			
		});
		imgDelete.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {				
				etText.setText("");//���EditText
			}
			
		});		
		etText.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO �Զ����ɵķ������				
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO �Զ����ɵķ������				
			}
			@Override
			public void afterTextChanged(Editable s) {
				// EditText���ʱ����xͼ�꣬EditText������ʱ��ʾxͼ��
				if(s.length()==0){
					imgDelete.setVisibility(View.GONE);
				}else{
					imgDelete.setVisibility(View.VISIBLE);
				}
				//����Message��myHandler����
				myHandler.obtainMessage(TEXT_CHANGED,etText.getText().toString()).sendToTarget();
				/*myHandler.post(new Runnable(){
					//Handler��post��sendMessage�÷�ʵ�ֵĹ�����һ����
					@Override
					public void run() {
						// TODO �Զ����ɵķ������
						String str=etText.getText().toString();
		    			subProvinceAreaData.clear();
		    			for(int i=0;i<spellData.size();i++){    				
		    				if((provinceAreaData.get(i).contains(str))||(spellData.get(i).contains(str))){
		        				subProvinceAreaData.add(provinceAreaData.get(i));
		        			}
		    			}
		    			adapter=new ArrayAdapter<String>(MainActivity.this,
		    					android.R.layout.simple_list_item_1,subProvinceAreaData);
		    			listContent.setAdapter(adapter);
					}});*/
			}});
	}
	private void initialData(){
		dbHelper=new DBHelper(this);
		subProvinceAreaData=dbHelper.getCityProvinceArea();
		spellData=dbHelper.getCitySpellZH();
		
	}
	private ArrayList<String> getData(){		
		provinceAreaData=dbHelper.getCityProvinceArea();
		return provinceAreaData;
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
