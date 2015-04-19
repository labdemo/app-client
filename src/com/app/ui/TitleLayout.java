package com.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.base.BaseActivity;
import com.app.base.R;

public class TitleLayout extends RelativeLayout {
	
	private TextView titleCenterText,titleBackText, titleRightText;

	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.layout_top, this);
		titleCenterText = (TextView)findViewById(R.id.titleCenterText);
		titleBackText = (TextView)findViewById(R.id.titleBackText);
		titleRightText = (TextView)findViewById(R.id.titleRightText);
		initEvents();
	}
	
	private void initEvents() {
		if(titleBackText.getVisibility() != View.GONE){
			titleBackText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((BaseActivity) getContext()).finish();
			}
		});
		}
		if(titleRightText.getVisibility() != View.GONE){
			titleRightText.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					((BaseActivity) getContext()).forward(SearchUI.class);
				}
			});
		}
	}

	public void setBackTitleVisibility(int visibility){
		titleBackText.setVisibility(visibility);
	}
	
	public void setCenterTitle(int textId){
		titleCenterText.setText(getResources().getText(textId).toString());
	}
	
	public void setCenterTitle(String text){
		titleCenterText.setText(text);
	}
	
	public void setRightTitleVisibility(int visibility){
		titleRightText.setVisibility(visibility);
	}
	
}
