package com.app.list;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.base.R;

public class FooterLayout extends RelativeLayout {
	private TextView loadMoreText;
	private ProgressBar loadingMoreBar;
	
	public static final int STATE_NORMAL = 0;
	public static final int STATE_ISLOADING = 1;
	
	private int currentState;

	public FooterLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	public FooterLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	public FooterLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	private void initViews(Context context){
		RelativeLayout footerview = (RelativeLayout)LayoutInflater.from(context).inflate(R.layout.listview_footer, null);
		addView(footerview);
		footerview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		loadMoreText = (TextView)footerview.findViewById(R.id.loadMoreText);
		loadingMoreBar = (ProgressBar)footerview.findViewById(R.id.loadingMoreBar);
		
		currentState = STATE_NORMAL;
	}
	
	public void setStatus(int state){
		if(state == currentState){
			return ;
		}
		switch(state){
		case STATE_NORMAL:
			if(currentState == STATE_ISLOADING){
				loadingMoreBar.setVisibility(View.GONE);
				loadMoreText.setText(R.string.touch_to_load);
			}
			break;
		case STATE_ISLOADING:
			if(currentState == STATE_NORMAL){
				loadingMoreBar.setVisibility(View.VISIBLE);
				loadMoreText.setText(R.string.is_loading);
			}
			break;
		}
	}
	
}
