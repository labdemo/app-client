package com.app.list;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.base.R;

public class HeaderLayout extends RelativeLayout {
	private LinearLayout headerContainer;
	private TextView pullRefreshText;
	
	private ImageView pullImage;
	private ProgressBar headerLoadingBar;
	private Animation headerUpAnimotion,headerDownAnimotion;
	private int currentState;
	
	
	public static final int STATE_NORMAL = 0;
	public static final int STATE_RELASE_TOREFRESH = 2;
	public static final int STATE_ISREFRESHING = 3;
	
	public TextView refreshTimeText;
	
	public HeaderLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	public HeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	public HeaderLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	private void initViews(Context context){
		@SuppressWarnings("deprecation")
		RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, 0);
		headerContainer = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.listview_header, null);
		addView(headerContainer,lp);
		
		pullRefreshText = (TextView)findViewById(R.id.pullRefreshText);
		refreshTimeText = (TextView)findViewById(R.id.refreshTimeText);
		pullImage = (ImageView)findViewById(R.id.pulljiantou);
		headerLoadingBar = (ProgressBar)findViewById(R.id.headerLoadingBar);
		
		headerUpAnimotion = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		headerUpAnimotion.setDuration(200);
		headerUpAnimotion.setFillAfter(true);
		headerDownAnimotion = new RotateAnimation(180.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		headerDownAnimotion.setDuration(200);
		headerDownAnimotion.setFillAfter(true);
		currentState = STATE_NORMAL;
	}
	
	public void setState(int state){
		if(currentState == state){
			return ;
		}
		if (state == STATE_ISREFRESHING) {
			pullImage.clearAnimation();
			pullImage.setVisibility(View.GONE);
			headerLoadingBar.setVisibility(View.VISIBLE);
		}
		else {
			pullImage.setVisibility(View.VISIBLE);
			headerLoadingBar.setVisibility(View.GONE);
		}
		Log.i("testout", "------------->setState");
		
		switch(state){
		case STATE_NORMAL:
			if(currentState == STATE_RELASE_TOREFRESH){
				Log.i("testout", "---------------startAnimation");
				pullImage.startAnimation(headerDownAnimotion);
			}
			if(currentState == STATE_ISREFRESHING){
				pullImage.clearAnimation();
			}
			pullRefreshText.setText(R.string.pull_to_refresh);
			break;
		case STATE_RELASE_TOREFRESH:
			if(currentState != STATE_RELASE_TOREFRESH){
				pullImage.clearAnimation();
				pullImage.startAnimation(headerUpAnimotion);
				pullRefreshText.setText(R.string.release_to_refresh);
			}
			break;
		case STATE_ISREFRESHING:
			pullRefreshText.setText(R.string.is_refreshing);
			break;
		}
		currentState = state;
	}
	
	
	public void setHeight(int height) {
		if (height < 0){
			height = 0;
		}
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) headerContainer.getLayoutParams();
		lp.height = height;
		headerContainer.setLayoutParams(lp);
	}
}
