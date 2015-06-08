package com.app.base;

public class BaseTask {
	
	public static final int TASK_COMPLETE = 0;
    public static final int NETWORK_ERROR = 1;
    public static final int SHOW_LOADBAR = 2;
    public static final int HIDE_LOADBAR = 3;
    public static final int SHOW_TOAST = 4;
    public static final int LOAD_IMAGE = 5;
    
    private int id = 0;
    private String name = "";

    public BaseTask() {}

    public int getId () {
        return this.id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void onStart () {
    	
    }

    public void onComplete () {
    	
    }

    public void onComplete (String httpResult) {
    }

    public void onError (String error) {
    	
    }

    public void onStop () throws Exception {
    	
    }
    
}
