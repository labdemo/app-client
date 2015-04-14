package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.base.BaseFragment;
import com.app.base.R;

public class OnGoingFragmentUI extends BaseFragment {
	
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_ongoing, null);
		return view;
	}

}
