package com.app.base;

import com.app.util.AppUtil;

import android.os.Handler;
import android.os.Message;

public class BaseHandler extends Handler {
	
	private BaseActivity baseActivity;
	
	public BaseHandler(BaseActivity baseActivity){
		this.baseActivity = baseActivity;
	}
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		int taskID;
		String result;
		switch(msg.what){
		case BaseTask.TASK_COMPLETE:
			taskID = msg.getData().getInt("taskID");
			result = msg.getData().getString("data");
			if(result != null){
				baseActivity.onTaskComplete(taskID, AppUtil.getMessage(result));
			} else if (Integer.valueOf(taskID) == null) {
				baseActivity.onTaskComplete(taskID);
			} else {
				baseActivity.toast("net error");
			}
			break;
		case BaseTask.NETWORK_ERROR:
			
			break;
		case BaseTask.SHOW_LOADBAR:
			
            break;
        case BaseTask.HIDE_LOADBAR:
        	
            break;
        case BaseTask.SHOW_TOAST:
        	
            break;
		}
	}

}
