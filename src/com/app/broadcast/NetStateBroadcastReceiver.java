package com.app.broadcast;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.app.base.BaseBroadcastReceiver;

public class NetStateBroadcastReceiver extends BaseBroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		String action = intent.getAction();
		if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			ConnectivityManager netConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = netConnectivityManager.getActiveNetworkInfo();
			if (info != null) {
//				toast("当前网络类型：" + info.getTypeName());
			} else {
				Toast.makeText(context, "网络连接不可用，请稍后重试", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
