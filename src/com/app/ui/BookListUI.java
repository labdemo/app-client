package com.app.ui;

import android.os.Bundle;

import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;

public class BookListUI extends BaseUI{
	
	private int getRequestCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booklist);
		
		getRequestCode = getIntent().getIntExtra("requestCode", C.ERROR);
		initViews();
		initEvents();
		
	}

	private void initViews() {
		
	}
	
	private void initEvents() {
		
		switch(getRequestCode){
		case C.START_BOOK_STORE:
			
			break;
		case C.START_TEACH_BOOK:
			
			break;
		case C.START_GOABROAD_BOOK:
			
			break;
		case C.START_EXAMFPG_BOOK:
			
			break;
		case C.ERROR:
			
			break;
		default:
			break;
		}
	}
}
