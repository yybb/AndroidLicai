package com.licai.activity;

import android.app.Activity;

import android.os.Bundle;
import com.licai.database.LicaiDatabaseHelper;

public class LicaiActivity extends Activity {
	
	protected LicaiDatabaseHelper myDB = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		myDB = new LicaiDatabaseHelper(this.getApplicationContext());
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(myDB != null)
		{
			myDB.close();
		}
	}
}
