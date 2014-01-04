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

		//获取系统当前日期
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sDateFormat.format(new java.util.Date());
		
		//查询数据库获取当前日期的计划和实际收支情况
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
		
		//计划支出文本框
		TextView textIndexPlanExpense = (TextView) findViewById(R.id.text_index_plan_expense);
		textIndexPlanExpense.setText(String.valueOf(plan_expense));
		//计划收入文本框
		TextView textIndexPlanIncome = (TextView) findViewById(R.id.text_index_plan_income);
		textIndexPlanIncome.setText(String.valueOf(plan_income));
		//实际支出文本框
		TextView textIndexActualExpense = (TextView) findViewById(R.id.text_index_actual_expense);
		textIndexActualExpense.setText(String.valueOf(actual_expense));
		//实际收入文本框
		TextView textIndexActualIncome = (TextView) findViewById(R.id.text_index_actual_income);
		textIndexActualIncome.setText(String.valueOf(actual_income));
		
		//计划收支按钮
		Button buttonIndexPlan = (Button) findViewById(R.id.button_index_plan);
		buttonIndexPlan.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(IndexActivity.this, PlanActivity.class);
//				intent.putExtra("userId", id);
				startActivity(intent);
			}
		});
		
		//实际收支按钮
		Button buttonIndexActual = (Button) findViewById(R.id.button_index_actual);
		buttonIndexActual.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(IndexActivity.this, ActualActivity.class);
//				intent.putExtra("userId", id);
				startActivity(intent);
			}
		});
		
		//历史开销
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
	//测试用，向数据库里添加数据
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
	//测试用，删除表中数据
	//delete from table_plan where user_id=2;
	public void deleteDateFromTablePlan(){
		SQLiteDatabase db = myDB.getWritableDatabase();
		db.delete(Plan.TABLE_NAME, Plan.USER_ID+"=?", new String[]{"2"});
		db.close();
	}
*/
}
