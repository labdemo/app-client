package com.app.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.base.BaseActivity;
import com.app.base.BaseTitleLayout;
import com.app.base.R;

public class TitleLayout extends BaseTitleLayout {
	
	private TextView titleCenterText,titleBackText, titleRightText;

	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.layout_top, this);
		titleCenterText = (TextView)findViewById(R.id.titleCenterText);
		titleBackText = (TextView)findViewById(R.id.titleBackText);
		titleRightText = (TextView)findViewById(R.id.titleRightText);
		
		titleBackText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((BaseActivity) getContext()).finish();
			}
		});
	}
	
	
	public void setCenterTitle(String text, Drawable image, float textSize, final boolean canJump){
		titleCenterText.setText(text);
		titleCenterText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
		if(image!=null){
			image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
		}
		titleBackText.setCompoundDrawables(image, null, null, null);
	}
	
	public void setCenterTitleClick(boolean canClick){
		if(canClick){
			titleCenterText.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					((BaseActivity) getContext()).forward(SearchUI.class);
				}
			});
		}
	}
	
	public void setBackTitle(String text, Drawable image, boolean canCilck){
		titleBackText.setText(text);
		if(image!=null){
			image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
		}
		titleBackText.setCompoundDrawables(image, null, null, null);
		if(!canCilck){
			titleBackText.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return true;
				}
			});
		}
	}
	
	public void setRightTitle(String text){
		titleRightText.setText(text);
	}
	
}
