package com.app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.app.base.BaseActivity;
import com.app.base.R;

public class LoginUI extends BaseActivity{
	
	private EditText accountText, passwordText;
	private Button login;
	private CheckBox isRememberedBox;
	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//如果已经登录，则直接跳转至main，否则显示登陆界面
//		if(BaseAuth.isLogin()){
//			this.forward(MainUI.class);
//			this.finish();
//		}
		setContentView(R.layout.activity_login);
		
		settings = getPreferences(Context.MODE_PRIVATE);
		init();
		
	}
	
	private void init(){
		accountText = (EditText)findViewById(R.id.accountText);
		passwordText = (EditText)findViewById(R.id.passwordText);
		isRememberedBox = (CheckBox)findViewById(R.id.isRemembered);
		login = (Button)findViewById(R.id.login);
		//判断是否已记住密码
		if(settings.getBoolean("isRemembered", false)){
			accountText.setText(settings.getString("username", ""));
			passwordText.setText(settings.getString("password", ""));
			isRememberedBox.setChecked(true);
		}
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
						doLogin();
					} else {
						toast("用户名和密码不能为空！");
					}
					break;
				default:
					break;
				}
			}
		});
	}
	
	private void doLogin(){
		
		
		startActivity(new Intent(LoginUI.this, MainUI.class));
		this.finish();
	}
	
	
	
}
