package com.app.ui;

import android.os.Bundle;
import android.view.View;

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
		bookListTitleLayout.setBackTitleVisibility(View.VISIBLE);
		
	}
	
	private void initEvents() {
		
		switch(getRequestCode){
		case C.START_REGISTER_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.register_book);
			break;
		case C.START_TEACH_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.teaching_book);
			break;
		case C.START_GOABROAD_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.goabroad_book);
			break;
		case C.START_EXAMFPG_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.examfpg_book);
			break;
		case C.ERROR:
			break;
		default:
			break;
		}
	}
}
