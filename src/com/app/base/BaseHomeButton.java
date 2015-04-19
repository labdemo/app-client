package com.app.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class BaseHomeButton extends Button {
	
	
	public BaseHomeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setTextColor(getResources().getColor(R.color.whitecolor));
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downAnimation();
			break;
		case MotionEvent.ACTION_UP:
			upAnimation();
			break;
		case MotionEvent.ACTION_MOVE:
			upAnimation();
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	private void downAnimation(){
		AnimationSet smallAnimationSet = new AnimationSet(true);
		ScaleAnimation smallScaleAnimation = new ScaleAnimation(1, 0.97f, 1, 0.97f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		smallAnimationSet.setDuration(350);
		smallAnimationSet.setFillAfter(true);
		smallAnimationSet.addAnimation(smallScaleAnimation);
		this.startAnimation(smallAnimationSet);
	}
	private void upAnimation(){
		AnimationSet recoverAnimationSet = new AnimationSet(true);
		ScaleAnimation recoverScaleAnimation = new ScaleAnimation(0.97f, 1, 0.97f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		recoverAnimationSet.setDuration(350);
		recoverAnimationSet.setFillAfter(true);
		recoverAnimationSet.addAnimation(recoverScaleAnimation);
		this.startAnimation(recoverAnimationSet);
	}
	

}
