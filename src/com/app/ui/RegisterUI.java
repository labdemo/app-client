package com.app.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.app.base.BaseActivity;
import com.app.base.R;

public class RegisterUI extends BaseActivity {
	
	private EditText accountText, passwordText, passwordTextAgain;
	private Button registerSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initViews();
		initEvents();
	}

	private void initViews() {
		accountText = (EditText)findViewById(R.id.accountText);
		passwordText = (EditText)findViewById(R.id.passwordText);
		passwordTextAgain = (EditText)findViewById(R.id.passwordTextAgain);
		
		registerSubmit = (Button)findViewById(R.id.registerSubmit);
	}	

	private void initEvents() {
		registerSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RegisterUI.this.forward(FillPersonInfoUI.class);
			}
		});
	}
}
