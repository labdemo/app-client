package com.app.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.app.adapter.BookListAdapter;
import com.app.base.BaseFragment;
import com.app.base.BaseViewPager;
import com.app.base.R;

public class FindFragmentUI extends BaseFragment {

	private View findView;

	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合

	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点

	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号

	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};
	// 快速抢单，猜你喜欢变量初始化
	private TextView textViewQuick;
	private TextView textViewGuess;
	private BaseViewPager mViewPager;
	private List<Fragment> fragmentList;
	private Fragment fragmentQuick;
	private Fragment fragmentGuess;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findView = inflater.inflate(R.layout.fragment_find, null);

		// 自动切换图片
		imageResId = new int[] { R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.pic_account, R.drawable.ic_launcher };
		titles = new String[imageResId.length];
		titles[0] = "1";
		titles[1] = "2";
		titles[2] = "3";
		titles[3] = "4";
		titles[4] = "5";

		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(findView.getContext());
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}

		dots = new ArrayList<View>();
		dots.add(findView.findViewById(R.id.v_dot0));
		dots.add(findView.findViewById(R.id.v_dot1));
		dots.add(findView.findViewById(R.id.v_dot2));
		dots.add(findView.findViewById(R.id.v_dot3));
		dots.add(findView.findViewById(R.id.v_dot4));

		tv_title = (TextView) findView.findViewById(R.id.tv_title);
		if (tv_title == null) {
			Log.e("why", "is null");
		}
		tv_title.setText(titles[0]);//

		viewPager = (ViewPager) findView.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		// 自动初始化图片结束

		return findView;

	}

	// 快速抢单，猜你喜欢界面
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fragmentList = new ArrayList<Fragment>();
		fragmentQuick = new FindFragment_quick();
		fragmentGuess = new FindFragment_guess();
		/*
		 * FragmentTransaction mFragmentTransation = getFragmentManager()
		 * .beginTransaction();
		 * mFragmentTransation.add(R.layout.fragment_find_quick, fragmentQuick);
		 * mFragmentTransation.commit();
		 * mFragmentTransation.add(R.layout.fragment_find_guess, fragmentGuess);
		 * mFragmentTransation.commit()
		 */
		fragmentList.add(fragmentQuick);
		fragmentList.add(fragmentGuess);
		textViewQuick = (TextView) findView.findViewById(R.id.find_quick_order);
		textViewGuess = (TextView) findView
				.findViewById(R.id.find_guess_you_like);
		mViewPager = (BaseViewPager) findView.findViewById(R.id.vp2);
		/*
		 * FragmentTransaction mFragmentTransation = fragmentManager
		 * .beginTransaction(); mFragmentTransation.add(fragmentQuick, null);
		 * mFragmentTransation.commit(); mFragmentTransation.add(fragmentGuess,
		 * null); mFragmentTransation.commit(); Log.e("number_of_feagment",
		 * Integer.toString(fragmentManager.getFragments().size()));
		 */
		mViewPager.setAdapter(new FragmentStatePagerAdapter(
				getChildFragmentManager()) {

			@Override
			public Fragment getItem(final int paramInt) {
				// TODO Auto-generated method stub
				// return
				// fragmentList.get(paramInt).instantiate(mViewPager.getContext(),fragmentList.get(paramInt).getClass().getName());
				return fragmentList.get(paramInt);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragmentList.size();
			}

		});
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				if (position == 0) {
					textViewQuick.setBackgroundResource(R.color.yellow_module);
					textViewGuess.setBackgroundResource(R.color.blue_module);
				} else {
					textViewQuick.setBackgroundResource(R.color.blue_module);
					textViewGuess.setBackgroundResource(R.color.yellow_module);
				}
			}

			@Override
			public void onPageScrolled(int paramInt1, float paramFloat,
					int paramInt2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int paramInt) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}
}
