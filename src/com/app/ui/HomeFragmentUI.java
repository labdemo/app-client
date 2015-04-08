package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.base.BaseFragment;
import com.app.base.BaseHomeButton;
import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;

public class HomeFragmentUI extends BaseFragment {
	
	private View homeView;
	private TextView searchTipText;
	private BaseHomeButton requestBookButton, bookStoreButton, teachingBookButton, goabroadBookButton, examfpgBookButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeView = inflater.inflate(R.layout.fragment_home, null);
		initViews();
		initEvents();
		
		return homeView;
	}

	private void initViews() {
		searchTipText = (TextView) homeView.findViewById(R.id.searchTipText);
		requestBookButton = (BaseHomeButton)homeView.findViewById(R.id.requestBookButton);
		bookStoreButton = (BaseHomeButton)homeView.findViewById(R.id.bookStoreButton);
		teachingBookButton = (BaseHomeButton)homeView.findViewById(R.id.teachingBookButton);
		goabroadBookButton = (BaseHomeButton)homeView.findViewById(R.id.goabroadBookButton);
		examfpgBookButton = (BaseHomeButton)homeView.findViewById(R.id.examfpgBookButton);
	}
	
	class BaseHomeButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.searchTipText:
				((BaseUI) getActivity()).forward(SearchUI.class);
				break;
			case R.id.requestBookButton:
				((BaseUI) getActivity()).forward(RequestBookListUI.class);
				break;
			case R.id.bookStoreButton:
				((BaseUI) getActivity()).forward(BookListUI.class, C.START_BOOK_STORE);
				break;
			case R.id.teachingBookButton:
				((BaseUI) getActivity()).forward(BookListUI.class, C.START_TEACH_BOOK);
				break;
			case R.id.goabroadBookButton:
				((BaseUI) getActivity()).forward(BookListUI.class, C.START_GOABROAD_BOOK);
				break;
			case R.id.examfpgBookButton:
				((BaseUI) getActivity()).forward(BookListUI.class, C.START_EXAMFPG_BOOK);
				break;
			default:
				break;
			}
		}
		
	}
	
	private void initEvents(){
		BaseHomeButtonListener mListener = new BaseHomeButtonListener();
		searchTipText.setOnClickListener(mListener);
		requestBookButton.setOnClickListener(mListener);
		bookStoreButton.setOnClickListener(mListener);
		teachingBookButton.setOnClickListener(mListener);
		goabroadBookButton.setOnClickListener(mListener);
		examfpgBookButton.setOnClickListener(mListener);
		
	}
	
	
}
