package com.app.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BaseViewPager extends ViewPager {
	
	private boolean isCanScroll = true;

	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setScanScroll(boolean isCanScroll){  
		this.isCanScroll = isCanScroll;
    }  
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if(isCanScroll){
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if(isCanScroll){
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}
	}
}
