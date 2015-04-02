package com.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.app.base.BaseFragment;
import com.app.base.R;

public class HomeFragmentUI extends BaseFragment {
	
	private View homeView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeView = inflater.inflate(R.layout.fragment_home, null);
		return homeView;
	}
	
}
