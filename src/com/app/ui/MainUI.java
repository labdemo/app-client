package com.app.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.base.BaseUI;
import com.app.base.BaseViewPager;
import com.app.base.R;
import com.app.util.ActivityCollector;

public class MainUI extends BaseUI {
	
	private List<Fragment> fragmentList;
	private LinearLayout homePageLayout, findPageLayout, minePageLayout;
	private ImageView homeImage, findImage, mineImage;
	private List<ImageView> imageList;
	private TextView homeText, findText, mineText;
	private List<TextView> textList;
	private BaseViewPager viewPager;
	
	private long lastPressBackTime = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
	}

	private void initViews() {
		homeImage = (ImageView)findViewById(R.id.homeImage);
		findImage = (ImageView)findViewById(R.id.findImage);
		mineImage = (ImageView)findViewById(R.id.mineImage);
		imageList = new ArrayList<ImageView>();
		imageList.add(homeImage);
		imageList.add(findImage);
		imageList.add(mineImage);
		
		homeText = (TextView)findViewById(R.id.homeText);
		findText = (TextView)findViewById(R.id.findText);
		mineText = (TextView)findViewById(R.id.mineText);
		textList = new ArrayList<TextView>();
		textList.add(homeText);
		textList.add(findText);
		textList.add(mineText);
		
		homePageLayout = (LinearLayout)findViewById(R.id.homePageLayout);
		findPageLayout = (LinearLayout)findViewById(R.id.findPageLayout);
		minePageLayout = (LinearLayout)findViewById(R.id.minePageLayout);
		homePageLayout.setOnClickListener(new mOnClickListener(0));
		findPageLayout.setOnClickListener(new mOnClickListener(1));
		minePageLayout.setOnClickListener(new mOnClickListener(2));
		
		fragmentList = new ArrayList<Fragment>();
		Fragment homeFragment = new HomeFragmentUI();
		Fragment findFragment = new FindFragmentUI();
		Fragment mineFragment = new MineFragmentUI();
		fragmentList.add(homeFragment);
		fragmentList.add(findFragment);
		fragmentList.add(mineFragment);
		
		viewPager = (BaseViewPager)findViewById(R.id.viewPager);
		//禁止滑动
		viewPager.setScanScroll(false);
		
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
			
			@Override
			public int getCount() {
				return fragmentList.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				return fragmentList.get(arg0);
			}
		};
		viewPager.setAdapter(fragmentPagerAdapter);
	}
	
	class mOnClickListener implements OnClickListener{
		private int selection;
		public mOnClickListener(int arg0) {
			selection = arg0;
		}
		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(selection);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
			if(System.currentTimeMillis() - lastPressBackTime < 2000){
				ActivityCollector.finishAllActivity();
			} else {
				lastPressBackTime = System.currentTimeMillis();
				toast(getResources().getString(R.string.press_twice_exit));
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
