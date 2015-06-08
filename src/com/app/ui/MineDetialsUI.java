package com.app.ui;

import android.os.Bundle;
import android.view.View;

import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;

public class MineDetialsUI extends BaseUI {

private TitleLayout mineDetailsLayout;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine_detail);
		initViews();
		initEvents();
	}

	private void initViews() {
		mineDetailsLayout = (TitleLayout)findViewById(R.id.mineDetailsLayout);
	}

	private void initEvents() {
		mineDetailsLayout.setRightTitleVisibility(View.GONE);
		switch (getIntent().getIntExtra("requestCode", C.ERROR)) {
		case C.START_MY_ORDER:
			mineDetailsLayout.setCenterTitle(R.string.my_order);
			break;
		 case C.START_MY_BOOK_STORE:
			mineDetailsLayout.setCenterTitle(R.string.my_bookstore);
			break;
		case C.START_MY_COLLECTION:
			mineDetailsLayout.setCenterTitle(R.string.my_collection);
			break;
		case C.START_MY_SETTINGS:
			mineDetailsLayout.setCenterTitle(R.string.my_settings);
			break;
		}
	}
}
