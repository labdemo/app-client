package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

import com.app.base.BaseFragment;
import com.app.base.BaseUI;
import com.app.base.C;
import com.app.base.R;

public class MineFragmentUI extends BaseFragment {

	private View mineView;
	private Button myOrder, myBookStore, myCollection, myBookFriends,
			myMessage, mySettings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mineView = inflater.inflate(R.layout.fragment_mine, null);
		initViews();
		initEvents();
		return mineView;
	}

	private void initViews() {
		myOrder = (Button) mineView.findViewById(R.id.my_order_button);
		myBookStore = (Button) mineView.findViewById(R.id.my_book_store_button);
		myCollection = (Button) mineView
				.findViewById(R.id.my_collection_button);
		myBookFriends = (Button) mineView
				.findViewById(R.id.my_book_friends_button);
		myMessage = (Button) mineView.findViewById(R.id.my_message_button);
		mySettings = (Button) mineView.findViewById(R.id.my_settings_button);
	}

	private void initEvents() {
		MineFragmentUIButtonListener mMineFragmentUIButtonListener = new MineFragmentUIButtonListener();
		myOrder.setOnClickListener(mMineFragmentUIButtonListener);
		myBookStore.setOnClickListener(mMineFragmentUIButtonListener);
		myCollection.setOnClickListener(mMineFragmentUIButtonListener);
		myBookFriends.setOnClickListener(mMineFragmentUIButtonListener);
		myMessage.setOnClickListener(mMineFragmentUIButtonListener);
		mySettings.setOnClickListener(mMineFragmentUIButtonListener);
	}

	private class MineFragmentUIButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.my_order_button:
				((BaseUI) getActivity()).forward(MineUIDemo.class,
						C.START_MY_ORDER);
				break;
			case R.id.my_book_store_button:
				((BaseUI) getActivity()).forward(MineUIDemo.class,
						C.START_MY_BOOK_STORE);
				break;
			case R.id.my_collection_button:
				((BaseUI) getActivity()).forward(MineUIDemo.class,
						C.START_MY_COLLECTION);
				break;
			case R.id.my_book_friends_button:
				((BaseUI) getActivity()).forward(MineUIDemo.class,
						C.START_MY_BOOK_FRIENDS);
				break;
			case R.id.my_message_button:
				((BaseUI) getActivity()).forward(MineUIDemo.class,
						C.START_MY_MESSAGE);
				break;
			case R.id.my_settings_button:
				((BaseUI) getActivity()).forward(MineUIDemo.class,
						C.START_MY_SETTINGS);
				break;
			}
		}

	}
}
