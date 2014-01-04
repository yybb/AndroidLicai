package com.licai.activity;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.licai.activity.LicaiActivity;
import com.licai.database.LicaiDatabase.Actual;
import com.licai.database.LicaiDatabase.Plan;

public class IndexActivity extends LicaiActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
/*		String userId = this.getIntent().getExtras().getString("userId");
		if (userId == null){
			userId = "1";
		}
		final String id = userId;
*/		

		//��ȡϵͳ��ǰ����
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sDateFormat.format(new java.util.Date());
		
		//��ѯ���ݿ��ȡ��ǰ���ڵļƻ���ʵ����֧���
		SQLiteDatabase db = myDB.getReadableDatabase();
		Cursor c1 = db.query(Plan.TABLE_NAME, null, Plan.PLAN_DATE+"=?", new String[]{currentDate}, null, null, null);
		Cursor c2 = db.query(Actual.TABLE_NAME, null, Actual.ACTUAL_DATE+"=?", new String[]{currentDate}, null, null, null);
		Double plan_expense = (double) 0;
		Double plan_income =(double) 0;
		Double actual_expense = (double)0;
		Double actual_income = (double)0;
		if (c1.getCount() != 0){
			c1.moveToFirst();
			plan_expense = c1.getDouble(c1.getColumnIndex(Plan.PLAN_EXPENSE));
			plan_income = c1.getDouble(c1.getColumnIndex(Plan.PLAN_INCOME));
		}
		if (c2.getCount() != 0){
			c2.moveToFirst();
			actual_expense = c2.getDouble(c2.getColumnIndex(Actual.ACTUAL_EXPENSE));
			actual_income = c2.getDouble(c2.getColumnIndex(Actual.ACTUAL_INCOME));
		}
		c1.close();
		c2.close();
		db.close();
		
		//�ƻ�֧���ı���
		TextView textIndexPlanExpense = (TextView) findViewById(R.id.text_index_plan_expense);
		textIndexPlanExpense.setText(String.valueOf(plan_expense));
		//�ƻ������ı���
		TextView textIndexPlanIncome = (TextView) findViewById(R.id.text_index_plan_income);
		textIndexPlanIncome.setText(String.valueOf(plan_income));
		//ʵ��֧���ı���
		TextView textIndexActualExpense = (TextView) findViewById(R.id.text_index_actual_expense);
		textIndexActualExpense.setText(String.valueOf(actual_expense));
		//ʵ�������ı���
		TextView textIndexActualIncome = (TextView) findViewById(R.id.text_index_actual_income);
		textIndexActualIncome.setText(String.valueOf(actual_income));
		
		//�ƻ���֧��ť
		Button buttonIndexPlan = (Button) findViewById(R.id.button_index_plan);
		buttonIndexPlan.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(IndexActivity.this, PlanActivity.class);
//				intent.putExtra("userId", id);
				startActivity(intent);
			}
		});
		
		//ʵ����֧��ť
		Button buttonIndexActual = (Button) findViewById(R.id.button_index_actual);
		buttonIndexActual.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(IndexActivity.this, ActualActivity.class);
//				intent.putExtra("userId", id);
				startActivity(intent);
			}
		});
		
		//��ʷ����
		Button buttonIndexHistory = (Button) findViewById(R.id.button_index_history);
		buttonIndexHistory.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(IndexActivity.this, HistoryActivity.class);
//				intent.putExtra("userId", id);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}
/*	
	//�����ã������ݿ����������
	//insert into table_plan (user_id, plan_income, plan_expense) values(1, 500, 100, "2014-01-03");
	public void insertDataIntoTablePlan(){
		SQLiteDatabase db = myDB.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put(Plan.USER_ID, 1);
		content.put(Plan.PLAN_INCOME, 500);
		content.put(Plan.PLAN_EXPENSE, 100);
		
		content.put(Plan.PLAN_DATE, "2014-01-03");
		
		db.insert(Plan.TABLE_NAME, null, content);
		db.close();
	}
	//insert into table_actual (user_id, actual_income, actual_expense) values(1, 800, 280, "2014-01-03");
	public void insertDataIntoTableActual(){
		SQLiteDatabase db = myDB.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put(Actual.USER_ID, 1);
		content.put(Actual.ACTUAL_INCOME, 800);
		content.put(Actual.ACTUAL_EXPENSE, 280);
		
		content.put(Actual.ACTUAL_DATE, "2014-01-03");
		
		db.insert(Actual.TABLE_NAME, null, content);
		db.close();
	}
	//�����ã�ɾ����������
	//delete from table_plan where user_id=2;
	public void deleteDateFromTablePlan(){
		SQLiteDatabase db = myDB.getWritableDatabase();
		db.delete(Plan.TABLE_NAME, Plan.USER_ID+"=?", new String[]{"2"});
		db.close();
	}
*/
}
