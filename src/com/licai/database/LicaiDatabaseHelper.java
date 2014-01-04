package com.licai.database;

import com.licai.database.LicaiDatabase.Plan;
import com.licai.database.LicaiDatabase.Actual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LicaiDatabaseHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "android_licai.db";
    private static final int DATABASE_VERSION = 1;
	
    public LicaiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//创建用户计划收支表
		/*
		create table table_plan (
			_ID INTEGER PRIMARY KEY AUTOINCREMENT,
			user_id INTEGER,
			plan_income double,
			plan_expense double,
			plan_date DATE); 
		*/
		db.execSQL("CREATE TABLE " + Plan.TABLE_NAME+ " ("
		          + Plan._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
		          + Plan.USER_ID + " INTEGER, "
		          + Plan.PLAN_INCOME + " DOUBLE, "
		          + Plan.PLAN_EXPENSE + " DOUBLE, "
		          + Plan.PLAN_DATE + " DATE"
		          + ");");
				
		//创建用户实际收支表
		/*
		create table actual_plan (
			_ID INTEGER PRIMARY KEY AUTOINCREMENT,
			user_id INTEGER,
			actual_income double,
			actual_expense double,
			actual_date DATE);
		*/
		db.execSQL("CREATE TABLE " + Actual.TABLE_NAME + " ("
				+ Actual._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
		        + Actual.USER_ID + " INTEGER, "
		        + Actual.ACTUAL_INCOME + " DOUBLE, "
		        + Actual.ACTUAL_EXPENSE + " DOUBLE, "
		        + Actual.ACTUAL_DATE + " Date"
		        + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}
