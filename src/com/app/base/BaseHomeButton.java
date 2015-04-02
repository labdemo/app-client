package com.app.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

public class BaseHomeButton extends Button {
	
	private TextView textView;

	public BaseHomeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		textView = new TextView(context, attrs);
		textView.setGravity(android.view.Gravity.CENTER);
		textView.setPadding(0, 0, 0, 0);
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case KeyEvent.ACTION_DOWN:
			AnimationSet smallAnimationSet = new AnimationSet(true);
			ScaleAnimation smallScaleAnimation = new ScaleAnimation(1, 0.97f, 1, 0.97f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			smallAnimationSet.setDuration(350);
			smallAnimationSet.setFillAfter(true);
			smallAnimationSet.addAnimation(smallScaleAnimation);
			this.startAnimation(smallAnimationSet);
			break;
		case KeyEvent.ACTION_UP:
			AnimationSet recoverAnimationSet = new AnimationSet(true);
			ScaleAnimation recoverScaleAnimation = new ScaleAnimation(0.97f, 1, 0.97f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			recoverAnimationSet.setDuration(350);
//			recoverAnimationSet.setStartOffset(400);
			recoverAnimationSet.setFillAfter(true);
			recoverAnimationSet.addAnimation(recoverScaleAnimation);
			this.startAnimation(recoverAnimationSet);
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
