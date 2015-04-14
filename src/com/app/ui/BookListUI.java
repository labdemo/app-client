package com.app.ui;

import android.os.Bundle;

import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;

public class BookListUI extends BaseUI{
	
	private int getRequestCode;
	private TitleLayout bookListTitleLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booklist);
		
		getRequestCode = getIntent().getIntExtra("requestCode", C.ERROR);
		initViews();
		initEvents();
		
	}

	private void initViews() {
		bookListTitleLayout = (TitleLayout)findViewById(R.id.bookListTitleLayout);
	}
	
	private void initEvents() {
		
		switch(getRequestCode){
		case C.START_REGISTER_BOOK:
			bookListTitleLayout.setRightTitle(getResources().getString(R.string.register_book));
			bookListTitleLayout.setCenterTitleClick(true);
			break;
		case C.START_TEACH_BOOK:
			bookListTitleLayout.setRightTitle(getResources().getString(R.string.teaching_book));
			bookListTitleLayout.setCenterTitleClick(true);
			break;
		case C.START_GOABROAD_BOOK:
			bookListTitleLayout.setRightTitle(getResources().getString(R.string.goabroad_book));
			bookListTitleLayout.setCenterTitleClick(true);
			break;
		case C.START_EXAMFPG_BOOK:
			bookListTitleLayout.setRightTitle(getResources().getString(R.string.examfpg_book));
			bookListTitleLayout.setCenterTitleClick(true);
			break;
		case C.ERROR:
			break;
		default:
			break;
		}
		
	}
	
}
