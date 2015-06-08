package com.app.ui;

import android.os.Bundle;
import android.view.View;

import com.app.base.BaseUI;
import com.app.base.R;

public class FillBookInfoUI extends BaseUI {
	
	private TitleLayout requestBookInfoTitleLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_book_info);
		initViews();
		initEvents();
	}

	private void initViews() {
		requestBookInfoTitleLayout = (TitleLayout)findViewById(R.id.requestBookInfoTitleLayout);
	}

	private void initEvents() {
		requestBookInfoTitleLayout.setBackTitleVisibility(View.VISIBLE);
		requestBookInfoTitleLayout.setCenterTitle(R.string .requst_book_list);
		requestBookInfoTitleLayout.setRightTitleVisibility(View.GONE);
	}

}
