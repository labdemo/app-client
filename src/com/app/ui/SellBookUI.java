package com.app.ui;

import android.os.Bundle;
import android.view.View;

import com.app.base.BaseUI;
import com.app.base.R;

public class SellBookUI extends BaseUI{

	private TitleLayout sellBookInfoTitleLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_book_info);
		initViews();
		initEvents();
	}

	private void initViews() {
		sellBookInfoTitleLayout = (TitleLayout)findViewById(R.id.sellBookInfoTitleLayout);
	}

	private void initEvents() {
		sellBookInfoTitleLayout.setBackTitleVisibility(View.VISIBLE);
		sellBookInfoTitleLayout.setCenterTitle(R.string.sell_book);
		sellBookInfoTitleLayout.setRightTitleVisibility(View.GONE);
	}
}
