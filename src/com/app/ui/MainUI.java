package com.app.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.base.BaseUI;
import com.app.base.BaseViewPager;
import com.app.base.C;
import com.app.base.R;
import com.app.broadcast.NetStateBroadcastReceiver;
import com.app.util.ActivityCollector;

public class MainUI extends BaseUI {
	
	
	
	private List<Fragment> fragmentList;
	private LinearLayout netWarningLayout, homePageLayout, onGoingPageLayout, findPageLayout, minePageLayout;
	
	private ImageView homeImage, onGoingImage, findImage, mineImage;
	private List<ImageView> imageList;
	private TextView homeText, onGoingText, findText, mineText;
	private List<TextView> textList;
	
	private BaseViewPager viewPager;
	
	private TitleLayout mainTitleLayout;
	
	private long lastPressBackTime = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
		initReceivers();
	}

	private void initViews() {
		homeImage = (ImageView)findViewById(R.id.homeImage);
		onGoingImage = (ImageView)findViewById(R.id.onGoingImage);
		findImage = (ImageView)findViewById(R.id.findImage);
		mineImage = (ImageView)findViewById(R.id.mineImage);
		imageList = new ArrayList<ImageView>();
		imageList.add(homeImage);
		imageList.add(onGoingImage);
		imageList.add(findImage);
		imageList.add(mineImage);
		
		homeText = (TextView)findViewById(R.id.homeText);
		onGoingText = (TextView)findViewById(R.id.onGoingText);
		findText = (TextView)findViewById(R.id.findText);
		mineText = (TextView)findViewById(R.id.mineText);
		textList = new ArrayList<TextView>();
		textList.add(homeText);
		textList.add(onGoingText);
		textList.add(findText);
		textList.add(mineText);
		
		netWarningLayout = (LinearLayout)findViewById(R.id.netWarningLayout);
		homePageLayout = (LinearLayout)findViewById(R.id.homePageLayout);
		onGoingPageLayout = (LinearLayout)findViewById(R.id.onGoingPageLayout);
		findPageLayout = (LinearLayout)findViewById(R.id.findPageLayout);
		minePageLayout = (LinearLayout)findViewById(R.id.minePageLayout);
		netWarningLayout.setOnClickListener(new mOnClickListener(C.ERROR));
		homePageLayout.setOnClickListener(new mOnClickListener(0));
		onGoingPageLayout.setOnClickListener(new mOnClickListener(1));
		findPageLayout.setOnClickListener(new mOnClickListener(2));
		minePageLayout.setOnClickListener(new mOnClickListener(3));
		
		fragmentList = new ArrayList<Fragment>();
		Fragment homeFragment = new HomeFragmentUI();
		Fragment onGoingFragment = new OnGoingFragmentUI();
		Fragment findFragment = new FindFragmentUI();
		Fragment mineFragment = new MineFragmentUI();
		fragmentList.add(homeFragment);
		fragmentList.add(onGoingFragment);
		fragmentList.add(findFragment);
		fragmentList.add(mineFragment);
		
		mainTitleLayout = (TitleLayout)findViewById(R.id.mainTitleLayout);
		mainTitleLayout.setBackTitleVisibility(View.GONE);
		mainTitleLayout.setCenterTitle(R.string.home_page);
		
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
	
	private void initReceivers(){
		IntentFilter netFilter = new IntentFilter();
		netFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mNetStateReceiver, netFilter);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mNetStateReceiver);
	}
	
	class mOnClickListener implements OnClickListener{
		private int selection;
		public mOnClickListener(int arg0) {
			selection = arg0;
		}
		@Override
		public void onClick(View v) {
			if(selection != C.ERROR){
				viewPager.setCurrentItem(selection);
				mainTitleLayout.setCenterTitle(textList.get(selection).getText().toString()) ;
			} else {
				Intent intent =  new Intent(Settings.ACTION_SETTINGS);  
				startActivity(intent);
			}
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
	
	private BroadcastReceiver mNetStateReceiver = new NetStateBroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				ConnectivityManager netConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = netConnectivityManager.getActiveNetworkInfo();
				if (info != null) {
					netWarningLayout.setVisibility(View.GONE);
				} else {
					netWarningLayout.setVisibility(View.VISIBLE);
				}
			}
		};
	};
	
}
