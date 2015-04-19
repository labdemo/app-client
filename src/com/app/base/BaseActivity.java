package com.app.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.app.util.ActivityCollector;

public class BaseActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActivityCollector.addActivity(this);
	}

	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	//启动下一个Activity
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	
}
