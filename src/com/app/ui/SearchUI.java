package com.app.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.app.base.BaseUI;
import com.app.base.R;

public class SearchUI extends BaseUI {
	
	private EditText searchEditText;
	private TextView searchText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		searchEditText = (EditText)findViewById(R.id.searchEditText);
		searchText = (TextView)findViewById(R.id.searchText);
//		InputMethodManager imm =  (InputMethodManager)SearchUI.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		initEvents();
	}
	

	private void initEvents() {
		searchEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(searchEditText.length() > 0){
					searchText.setText(getResources().getString(R.string.search));
				} else {
					searchText.setText(getResources().getString(R.string.cancel));
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		
		searchText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(searchText.getText().toString().equals(getResources().getString(R.string.search))){
					//Search
				} else if (searchText.getText().toString().equals(getResources().getString(R.string.cancel))){
					SearchUI.this.finish();
				}
			}
		});
	}

}
