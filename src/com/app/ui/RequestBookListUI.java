package com.app.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.app.base.BaseTextView;
import com.app.base.BaseUI;
import com.app.base.R;

public class RequestBookListUI extends BaseUI {
	
	private TitleLayout requestListTitleLayout;
	private BaseTextView IRequestBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestbooklist);
		initViews();
		initEvents();
	}

	private void initViews() {
		requestListTitleLayout = (TitleLayout)findViewById(R.id.requestListTitleLayout);
		IRequestBook = (BaseTextView)findViewById(R.id.IRequestBook);
	}
	
	private void initEvents() {
		requestListTitleLayout.setBackTitleVisibility(View.VISIBLE);
		requestListTitleLayout.setCenterTitle(R.string .requst_book_list);
		requestListTitleLayout.setRightTitleVisibility(View.GONE );
		
		IRequestBook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RequestBookListUI.this.forward(FillBookInfoUI.class);
			}
		});
	}

}
