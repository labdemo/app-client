package com.app.base;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.app.util.ActivityCollector;

public class BaseActivity extends FragmentActivity {
	
	private BaseTaskPool taskPool;
	private BaseHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActivityCollector.addActivity(this);
		taskPool = new BaseTaskPool(this);
		handler = new BaseHandler(this);
	}

	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	// 启动下一个Activity
	public void forward(Class<?> classObj) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void forward(Class<?> classObj, Bundle prama) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtras(prama);
		startActivity(intent);
	}

	public void forward(Class<?> classObj, int prama) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("requestCode", prama);
		startActivity(intent);
	}
	
	public Context getContext(){
		return this;
	}
	
	public void sendMessage(int what, int taskID, String data){
		Bundle bundle = new Bundle();
		bundle.putInt("taskID", taskID);
		bundle.putString("data", data);
		Message msg = new Message();
		msg.what = what;
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
	
	public void doTaskAsync(int taskID, String taskUrl, HashMap<String, String> taskInfo){
		taskPool.addTask(taskID, taskUrl, new BaseTask(){
			@Override
			public void onComplete(String httpResult) {
				sendMessage(BaseTask.TASK_COMPLETE, this.getId(), httpResult);
			}
			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR, this.getId(),null);
			}
			
		}, 0);
	}
	
	public void onTaskComplete(int taskID){
		
	}
	public void onTaskComplete(int taskID, BaseMessage message){
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

}
