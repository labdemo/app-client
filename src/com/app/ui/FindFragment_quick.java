package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.base.BaseFragment;
import com.app.base.R;

public class FindFragment_quick extends BaseFragment {
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_find_quick, null);
		return view;
	}
	public View getView(){
		return view;
	}
}
