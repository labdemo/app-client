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
	private Button myOrder, myBookStore, myCollection, mySettings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mineView = inflater.inflate(R.layout.fragment_mine, null);
		initViews();
		initEvents();
		return mineView;
	}

	private void initViews() {
		myOrder = (Button) mineView.findViewById(R.id.myOrder);
		myBookStore = (Button) mineView.findViewById(R.id.myBookStore);
		myCollection = (Button) mineView .findViewById(R.id.myCollection);
		mySettings = (Button) mineView.findViewById(R.id.mySettings);
	}

	private void initEvents() {
		MineFragmentUIButtonListener mMineFragmentUIButtonListener = new MineFragmentUIButtonListener();
		myOrder.setOnClickListener(mMineFragmentUIButtonListener);
		myBookStore.setOnClickListener(mMineFragmentUIButtonListener);
		myCollection.setOnClickListener(mMineFragmentUIButtonListener);
		mySettings.setOnClickListener(mMineFragmentUIButtonListener);
	}

	private class MineFragmentUIButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.myOrder:
				((BaseUI) getActivity()).forward(MineDetialsUI.class, C.START_MY_ORDER);
				break;
			case R.id.myBookStore:
				((BaseUI) getActivity()).forward(MineDetialsUI.class, C.START_MY_BOOK_STORE);
				break;
			case R.id.myCollection:
				((BaseUI) getActivity()).forward(MineDetialsUI.class, C.START_MY_COLLECTION);
				break;
			case R.id.mySettings:
				((BaseUI) getActivity()).forward(MineDetialsUI.class, C.START_MY_SETTINGS);
				break;
			}
		}

	}
}
