package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.base.BaseFragment;
import com.app.base.R;

public class MineFragmentUI extends BaseFragment{
	
	private View mineView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mineView = inflater.inflate(R.layout.fragment_mine, null);
		return mineView;
	}
}
