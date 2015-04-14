package com.app.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.app.util.ActivityCollector;

public class BaseActivity extends FragmentActivity {
	
	public static boolean isNetAvailable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActivityCollector.addActivity(this);
		initReceivers();
	}
	
/*	public void initBaseEvents(){
		titleBackImage = (ImageView)findViewById(R.id.titleBackImage);
		titleText = (TextView)findViewById(R.id.titleText);
		titleBackImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityCollector.removeActivity((Activity)BaseApplication.getContext());
			}
		});
	}*/

	private void initReceivers() {
		IntentFilter netFilter = new IntentFilter();
		netFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(netStsteReceiver, netFilter);
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(netStsteReceiver);
		ActivityCollector.removeActivity(this);
	}
	
	
	private BroadcastReceiver netStsteReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				ConnectivityManager netConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = netConnectivityManager.getActiveNetworkInfo();
				if (info != null) {
					isNetAvailable = true;
//					toast("当前网络类型：" + info.getTypeName());
				} else {
					isNetAvailable = false;
					toast("网络连接不可用，请稍后重试");
				}
			}
		}
	};

}
