package com.app.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class BaseTextView extends TextView {

	public BaseTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTextColor(getResources().getColor(R.color.whitecolor));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.setTextColor(getResources().getColor(R.color.white_blue));
			break;
		case MotionEvent.ACTION_UP:
			this.setTextColor(getResources().getColor(R.color.whitecolor));
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		default:
			break;
		}
		return super.onTouchEvent(event) ;
	}
	
}
