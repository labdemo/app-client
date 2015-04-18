package com.app.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.app.base.BaseActivity;
import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;

public class MineUIDemo extends BaseUI {
	private TextView mineBack, mineCenter, mineRight;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_demo_layout);
		initViews();
		initEvents();
		mineBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initViews() {
		mineBack = (TextView) findViewById(R.id.titleBackText);
		mineCenter = (TextView) findViewById(R.id.titleCenterText);
		mineRight = (TextView) findViewById(R.id.titleRightText);
	}

	private void initEvents() {
		switch (getIntent().getIntExtra("requestCode", C.ERROR)) {
		case C.START_MY_ORDER:
			mineCenter.setText(R.string.my_order);
			break;
		case C.START_MY_BOOK_STORE:
			mineCenter.setText(R.string.my_bookstore);
			break;
		case C.START_MY_COLLECTION:
			mineCenter.setText(R.string.my_collection);
			break;
		case C.START_MY_BOOK_FRIENDS:
			mineCenter.setText(R.string.my_book_friends);
			break;
		case C.START_MY_MESSAGE:
			mineCenter.setText(R.string.my_message);
			break;
		case C.START_MY_SETTINGS:
			mineCenter.setText(R.string.my_settings);
			break;
		}
	}

}
