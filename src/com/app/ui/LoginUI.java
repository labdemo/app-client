package com.app.ui;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.app.base.BaseActivity;
import com.app.base.BaseAuth;
import com.app.base.BaseMessage;
import com.app.base.C;
import com.app.base.R;
import com.app.model.Customer;

public class LoginUI extends BaseActivity{
	
	private EditText accountText, passwordText;
	private TextView registerText, forgetPasswordText;
	private Button login;
	private CheckBox isRememberedBox;
	private SharedPreferences settings;
	private mOnclickListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//如果已经登录，则直接跳转至main，否则显示登陆界面
		if(BaseAuth.isLogin()){
			LoginUI.this.forward(MainUI.class);
			LoginUI.this.finish();
		}
		
		setContentView(R.layout.activity_login);
		
		settings = getPreferences(Context.MODE_PRIVATE);
		init();
		
	}
	
	private void init(){
		accountText = (EditText)findViewById(R.id.accountText);
		passwordText = (EditText)findViewById(R.id.passwordText);
		isRememberedBox = (CheckBox)findViewById(R.id.isRemembered);
		login = (Button)findViewById(R.id.login);
		registerText = (TextView)findViewById(R.id.registerText);
		//判断是否已记住密码
		if(settings.getBoolean("isRemembered", false)){
			accountText.setText(settings.getString("username", ""));
			passwordText.setText(settings.getString("password", ""));
			isRememberedBox.setChecked(true);
		}
		
		listener = new mOnclickListener();
		login.setOnClickListener(listener);
		registerText.setOnClickListener(listener);
		
	}
	
	private void clear(){
		accountText.setText(null);
		passwordText.setText(null);
		isRememberedBox.setChecked(false);
	}
	
	private class mOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.login:
				//执行记住密码操作
				String account = accountText.getText().toString();
				String password = passwordText.getText().toString();
				if(account.length() != 0 && password.length() != 0){
					Editor editor = settings.edit();
					if(isRememberedBox.isChecked()){
						editor.putBoolean("isRemembered", true);
						editor.putString("username", account);
						editor.putString("password", password);
					} else {
						editor.putBoolean("isRemembered", false);
						editor.putString("username", "");
						editor.putString("password", "");
					}
					editor.commit();
					//执行异步登陆
					doLogin(account, password);
				} else {
					toast("用户名和密码不能为空！");
				}
				break;
			case R.id.registerText:
				LoginUI.this.forward(RegisterUI.class);
				break;
			default:
				break;
			}
		}
	}
	
	private void doLogin(String account, String password){
		HashMap<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("username", account);
		userInfo.put("password", password);
		try{
			this.doTaskAsync(C.task.login, C.api.login, userInfo);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTaskComplete(int taskID, BaseMessage message) {
		super.onTaskComplete(taskID, message);
		if(taskID == C.task.login){
			Customer customer = null;
			try{
				customer = (Customer) message.getResult("Customer");
				if(customer.getUser_name() != null){
					BaseAuth.setCustomer(customer);
					BaseAuth.setLogin(true);
					toast(getString(R.string.login_success));
				} else {
//					BaseAuth.setCustomer(customer);
					BaseAuth.setLogin(false);
					toast(getString(R.string.login_failed));
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			if(BaseAuth.isLogin()){
				//开启后台服务推送notification
				forward(MainUI.class);
			}
		}
	}
	
}
