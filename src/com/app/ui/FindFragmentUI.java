package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.base.BaseFragment;
import com.app.base.R;

public class FindFragmentUI extends BaseFragment {
	
	private View findView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		findView = inflater.inflate(R.layout.fragment_find, null);
		return findView;
	}
}
