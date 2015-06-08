package com.app.base;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.app.util.AppClient;

import android.content.Context;
import android.support.v4.app.TaskStackBuilder;

public class BaseTaskPool {
	
	//创建了一个线程池接口对象
	private static ExecutorService taskPool;
	
	private Context context;
	
	public BaseTaskPool (BaseActivity baseActivity){
		context = baseActivity.getContext();
		//创建一个可缓存的线程池，若线程池中的线程多于处理任务的线程则自动关闭部分空闲线程，任务增多时又可以自动增加线程处理任务
		taskPool = Executors.newCachedThreadPool();
	}
	
	//创建含参数的异步远程任务方法
	public void addTask(int taskID, String taskUrl, HashMap<String, String> taskMap, BaseTask baseTask, int delayTime){
		taskPool.execute(new TaskThread(context, taskUrl, taskMap, baseTask, delayTime));
	}
	
	//创建不含参数的远程异步任务方法
	public void addTask(int taskID, String taskUrl, BaseTask baseTask, int delayTime){
		taskPool.execute(new TaskThread(context, taskUrl, null, baseTask, delayTime));
	}
	
	//创建自定义异步任务方法
	public void addTask(int taskID, BaseTask baseTask, int delayTime){
		baseTask.setId(taskID);
		taskPool.execute(new TaskThread(context, null, null, baseTask, delayTime));
	}
	
	//任务线程类
	private class TaskThread implements Runnable{
		private Context context;
		private String taskUrl;
		private HashMap<String, String> taskMap;
		private BaseTask baseTask;
		private int delayTime = 0;
		public TaskThread(Context context, String taskUrl, HashMap<String, String> taskMap, BaseTask baseTask, int delayTime) {
			this.context = context;
			this.taskUrl = taskUrl;
			this.taskMap = taskMap;
			this.baseTask = baseTask;
			this.delayTime = delayTime;
		}
		@Override
		public void run() {
			try{
				baseTask.onStart();
				String httpResult = null;
				if(this.delayTime > 0){
					try {
						Thread.sleep(delayTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try{
						if(this.taskUrl != null){
							AppClient client = new AppClient(this.taskUrl);
							//判断请求类型（post/get）
							if(taskMap == null){
								httpResult = client.get();
							} else {
								httpResult = client.post(this.taskMap);
							}
						}
						if(httpResult == null){
							//远程任务处理
							baseTask.onComplete(httpResult);
						} else {
							//本地任务处理
							baseTask.onComplete();
						}
					} catch (Exception e){
						baseTask.onError(e.getMessage());
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				try{
					baseTask.onStop();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
