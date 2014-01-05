package com.licai.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;

public class ActualActivity extends LicaiActivity {
	
	//@author FJQ
	
	private EditText ed1;
	private EditText ed2;
	private List<TextView> tx=new ArrayList<TextView>();
	private DatePicker dt;
	private Button b;
	public static SQLiteDatabase db;
	List<tmpdata> getret;
	private String tmpdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actual);
		
		ed1=(EditText) findViewById(R.id.fjeditText1);
		ed2=(EditText) findViewById(R.id.fjeditText2);
		dt=(DatePicker)findViewById(R.id.fjdatePicker1);
	    tx.add((TextView)findViewById(R.id.fjtextView8));
	    tx.add((TextView)findViewById(R.id.fjtextView9));
	    tx.add((TextView)findViewById(R.id.fjtextView10));
	    tx.add((TextView)findViewById(R.id.fjtextView11));
	    tx.add((TextView)findViewById(R.id.fjtextView12));
	    tx.add((TextView)findViewById(R.id.fjtextView13));
	    tx.add((TextView)findViewById(R.id.fjtextView14));
	    tx.add((TextView)findViewById(R.id.fjtextView15));
	    tx.add((TextView)findViewById(R.id.fjtextView16));
		b=(Button)findViewById(R.id.fjbutton1);
		db = openOrCreateDatabase("android_licai.db", Context.MODE_PRIVATE, null);
		
		Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int monthOfYear=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        tmpdata=year+"-"+monthOfYear+"-"+dayOfMonth;
        dt.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener(){

            public void onDateChanged(DatePicker view, int year,
                    int monthOfYear, int dayOfMonth) {
            	tmpdata=year+"-"+monthOfYear+"-"+dayOfMonth;
            }
            
        });
        getret=selectAll();
        settext();
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String tmpdata1 = ed1.getText().toString();
				String tmpdata2 = ed2.getText().toString();

				insert(tmpdata1,tmpdata2,tmpdata);
				
			//	Log.v("sb","完成查询2");
			//	tx.get(0).setText(getret.get(0).ACTUAL_DATE.toString());
			//	Log.v("sb",tx.get(0).getText().toString());
			//	Log.v("size",getret.size()+"");
			//	Log.v("size",tx.size()+"");
				getret=selectAll();
				settext();
			//	Log.v("sb","完成查询4");
			}
		});
		
		
	}
	
	protected void insert(String tmp1,String tmp2,String tmp3){
		try {
			db.execSQL("insert into table_actual values(NULL,NULL,?,?,?)",new Object[]{tmp1,tmp2,tmp3});
		} catch (Exception e){}
	}
	
	protected List<tmpdata> selectAll(){
		List<tmpdata> results=new ArrayList<tmpdata>();
		
		String tmp="select * from table_actual order by _ID desc limit 3";
		Log.v("sb", tmp);
		Cursor c = db.rawQuery(tmp, new String[] {});
		while (c.moveToNext()) {
			tmpdata ac=new tmpdata();
			ac.ACTUAL_INCOME=c.getString(c.getColumnIndex("actual_income"));
			ac.ACTUAL_EXPENSE=c.getString(c.getColumnIndex("actual_expense"));
			ac.ACTUAL_DATE=c.getString(c.getColumnIndex("actual_date"));
	//		Log.v("out", c.getString(c.getColumnIndex("actual_date")));
	//		Log.v("out", ac.ACTUAL_INCOME);
	//		Log.v("out", ac.ACTUAL_EXPENSE);
	//		Log.v("out", ac.ACTUAL_DATE);
			results.add(ac);
		}
	//	Log.v("sb","完成查询1");
		c.close();
		return results;
	}
	
	public class tmpdata{
        
        public   String USER_ID ;
        //ʵ������
        public   String ACTUAL_INCOME  ;
        //ʵ��֧��
        public   String ACTUAL_EXPENSE ;
        //ʵ����֧��������
        public   String ACTUAL_DATE  ;
	}
	
	protected void settext()
	{
		for (int i=0;i<getret.size();i++){
			int flag=3*i;
			tx.get(flag).setText(getret.get(i).ACTUAL_INCOME.toString());
	//		Log.v("site","运行到"+flag);
			
			flag++;
			tx.get(flag).setText(getret.get(i).ACTUAL_EXPENSE.toString());
	//		Log.v("site","运行到"+flag);
			
			flag++;
			tx.get(flag).setText(getret.get(i).ACTUAL_DATE.toString());
	//		Log.v("site","运行到"+flag);
			
		}
	}
	
}
