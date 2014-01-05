package com.licai.database;

import android.provider.BaseColumns;

public class LicaiDatabase {
	
	private LicaiDatabase() {}
	
	//�û��ƻ���֧�� ������ֶ���
	//����Ϊ��_ID��������
	public static final class Plan implements BaseColumns {

        private Plan() {}
        
        //����
        public static final String TABLE_NAME = "table_plan";
        
        public static final String USER_ID = "user_id";
        //�ƻ�����
        public static final String PLAN_INCOME = "plan_income";
        //�ƻ�֧��
        public static final String PLAN_EXPENSE = "plan_expense";
        //�ƻ���֧��������
        public static final String PLAN_DATE = "plan_date";
    }
    
    
    
    //�û�ʵ����֧�� ������ֶ���
	//����Ϊ��_ID��������
    public static final class Actual implements BaseColumns {

        private Actual() {}
        //����
        public static final String TABLE_NAME = "table_actual";
        
        public static final String USER_ID = "user_id";
        //ʵ������
        public static final String ACTUAL_INCOME = "actual_income";
        //ʵ��֧��
        public static final String ACTUAL_EXPENSE = "actual_expense";
        //ʵ����֧��������
        public static final String ACTUAL_DATE = "actual_date";
    }
}
