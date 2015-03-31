package com.app.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class BaseDialog {
	
	private Dialog mDialog;
	
	public BaseDialog(Context context, Bundle params){
		
	}

	public void show(){
		mDialog.show();
	}
}
