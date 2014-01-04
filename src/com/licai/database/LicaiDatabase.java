package com.licai.database;

import android.provider.BaseColumns;

public class LicaiDatabase {
	
	private LicaiDatabase() {}
	
	//用户计划收支表 表名及各字段名
	//主键为“_ID”自增长
	public static final class Plan implements BaseColumns {

        private Plan() {}
        
        //表名
        public static final String TABLE_NAME = "table_plan";
        
        public static final String USER_ID = "user_id";
        //计划收入
        public static final String PLAN_INCOME = "plan_income";
        //计划支出
        public static final String PLAN_EXPENSE = "plan_expense";
        //计划收支所属日期
        public static final String PLAN_DATE = "plan_date";
    }
    
    
    
    //用户实际收支表 表名及各字段名
	//主键为“_ID”自增长
    public static final class Actual implements BaseColumns {

        private Actual() {}
        //表名
        public static final String TABLE_NAME = "table_actual";
        
        public static final String USER_ID = "user_id";
        //实际收入
        public static final String ACTUAL_INCOME = "actual_income";
        //实际支出
        public static final String ACTUAL_EXPENSE = "actual_expense";
        //实际收支所属日期
        public static final String ACTUAL_DATE = "actual_date";
    }
}
