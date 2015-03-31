package com.app.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BookListAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
	
	//保存加载的布局防止再次加载从而提升ListView的运行效率
	class ViewHolder{
		
	}
}
