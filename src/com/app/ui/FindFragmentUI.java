package com.app.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.app.base.BaseFragment;
import com.app.base.BaseViewPager;
import com.app.base.R;

public class FindFragmentUI extends BaseFragment {

	private View findView;

	private BaseViewPager viewPager; // android-support-v4�еĻ������
	private List<ImageView> imageViews; // ������ͼƬ����

	private String[] titles; // ͼƬ����
	private int[] imageResId; // ͼƬID
	private List<View> dots; // ͼƬ�������ĵ���Щ��

	private TextView tvTitle;
	private int currentItem = 0; // ��ǰͼƬ��������

	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;

	// �л���ǰ��ʾ��ͼƬ
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// �л���ǰ��ʾ��ͼƬ
		};
	};
	// ��������������ϲ��������ʼ��
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

		// �Զ��л�ͼƬ
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

		// ��ʼ��ͼƬ��Դ
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

		tvTitle = (TextView) findView.findViewById(R.id.tv_title);
		if (tvTitle == null) {
			Log.e("why", "is null");
		}
		tvTitle.setText(titles[0]);//

		viewPager = (BaseViewPager) findView.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// �������ViewPagerҳ���������
		// ����һ������������ViewPager�е�ҳ��ı�ʱ����
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		// �Զ���ʼ��ͼƬ����

		return findView;

	}

	// ��������������ϲ������
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fragmentList = new ArrayList<Fragment>();
		fragmentQuick = new FindFragmentQuick();
		fragmentGuess = new FindFragmentGuess();
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
		textViewQuick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(0);
			}

		});
		textViewGuess = (TextView) findView
				.findViewById(R.id.find_guess_you_like);
		textViewGuess.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(1);
			}

		});
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
		// ��Activity��ʾ������ÿ�������л�һ��ͼƬ��ʾ
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		// ��Activity���ɼ���ʱ��ֹͣ�л�
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * �����л�����
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // ͨ��Handler�л�ͼƬ
			}
		}

	}

	/**
	 * ��ViewPager��ҳ���״̬�����ı�ʱ����
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
			tvTitle.setText(titles[position]);
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
	 * ���ViewPagerҳ���������
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
