package com.app.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;
import com.app.list.PullToRefreshList;
import com.app.list.PullToRefreshList.IXListViewListener;

public class BookListUI extends BaseUI implements IXListViewListener{
	
	private int getRequestCode;
	private TitleLayout bookListTitleLayout;
	
	private Spinner classifyGroupOne, classifyGroupTwo, classifyGroupThree, classifyGroupFour;
	private ArrayAdapter<CharSequence> adapterOne, adapterTwo, adapterThree, adapterFour;
	
	private PullToRefreshList bookListView;
	private List<HashMap<String, Object>> bookList;
	private SimpleAdapter bookListAdapter;
	
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
		classifyGroupOne = (Spinner)findViewById(R.id.classifyGroupOne);
		classifyGroupTwo = (Spinner)findViewById(R.id.classifyGroupTwo);
		classifyGroupThree = (Spinner)findViewById(R.id.classifyGroupThree);
		classifyGroupFour = (Spinner)findViewById(R.id.classifyGroupFour);
		bookListView = (PullToRefreshList)findViewById(R.id.bookListView);
	}
	
	private void initEvents() {
		bookListTitleLayout.setBackTitleVisibility(View.VISIBLE);
		switch(getRequestCode){
		case C.START_TEACH_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.teaching_book);
			classifyGroupTwo.setVisibility(View.GONE);
			classifyGroupThree.setVisibility(View.GONE);
			classifyGroupFour.setVisibility(View.GONE);
			adapterOne = ArrayAdapter.createFromResource(this, R.array.teaching_book_classify, R.layout.spinner_text);
			classifyGroupOne.setAdapter(adapterOne);
			break;
		case C.START_GOABROAD_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.goabroad_book);
			classifyGroupThree.setVisibility(View.GONE);
			classifyGroupFour.setVisibility(View.GONE);
			adapterOne = ArrayAdapter.createFromResource(this, R.array.examfpg_book_base_classify, R.layout.spinner_text);
			classifyGroupOne.setAdapter(adapterOne);
			adapterTwo = ArrayAdapter.createFromResource(this, R.array.examfpg_book_major_classify, R.layout.spinner_text);
			classifyGroupTwo.setAdapter(adapterTwo);
			break;
		case C.START_EXAMFPG_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.examfpg_book);
			break;
		case C.START_OTHER_BOOK:
			bookListTitleLayout.setCenterTitle(R.string.others);
			break;
		case C.ERROR:
			break;
		default:
			break;
		}
		
		bookList = new ArrayList<HashMap<String, Object>>();
		bookListAdapter = new SimpleAdapter(this, bookList, R.layout.listview_booklist,
				new String[]{"bookName"}, new int[]{R.id.bookName});
		
		loadData();
	}
	
	private void loadData(){
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("bookName", "书名" + "123");
			bookList.add(map);
		}
		bookListView.setAdapter(bookListAdapter);
		bookListView.setXListViewListener(this);
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	public void onLoadMore() {
		
	}
}
