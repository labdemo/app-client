package com.app.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;

public class ActivityCollector {
	
	private static List<Activity> activityList = new ArrayList<Activity>();
	
	public static void addActivity(Activity activity){
		activityList.add(activity);
	}
	
	public static void removeActivity(Activity activity){
		activityList.remove(activity);
		activity.finish();
	}
	
	public static void finishAllActivity(){
		Iterator<Activity> it = activityList.iterator();
		while(it.hasNext()){
			it.next().finish();
		}
	}

}
