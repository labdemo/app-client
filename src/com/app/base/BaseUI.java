package com.app.base;

import android.content.Intent;
import android.os.Bundle;

public class BaseUI extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	//启动下一个Aactivity
	public void forward(Class<?> classObj){
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void forward(Class<?> classObj, Bundle prama){
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtras(prama);
		startActivity(intent);
	}
	
	public void forward(Class<?> classObj, int prama){
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("requestCode", prama);
		startActivity(intent);
	}
}
