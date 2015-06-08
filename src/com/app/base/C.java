package com.app.base;

public final class C {
	
	public static final class code {
        public static final int login               = 1000;
        
        
        
        public static final int device_list         = 1001;
        public static final int order_list          = 1002;
        public static final int report              = 1003;
        public static final int password            = 1004;
        public static final int order               = 1005;
        
        public static final int logout  = 1001;
        public static final int notice  = 1002;
        public static final int error   = 1003;
    }
	
	public static final class api {
        public static final String base             = "http://192.168.9.69:8080";

        public static final String login            = "/app-client/app/_login";
        
        
        
        
        public static final String device_list      = "/Lab-demo/pc_app/pc_app_device_list";
        public static final String order_list       = "/Lab-demo/pc_app/pc_app_order_list";
        public static final String report           = "/Lab-demo/pc_app/pc_app_report";
        public static final String password         = "/Lab-demo/pc_app/pc_app_password";
        public static final String order            = "/Lab-demo/pc_app/pc_app_order";
        
        
        public static final String logout           = "/Lab-demo/pc_app/pc_app_logout";
        public static final String notice           = "/Lab-demo/pc_app/pc_app_notice";
    }
	
	public static final class task {
        public static final int login               = 1000;
        
        
        
        
        
        
        
        public static final int device_list         = 1001;
        public static final int order_list          = 1002;
        public static final int report              = 1003;
        public static final int password            = 1004;
        public static final int order               = 1005;
        
        
        public static final int logout              = 1003;
        public static final int notice              = 1004;
    }
	

	// home activity jump code
	public static final int START_SELL_BOOK = 0;
	public static final int START_TEACH_BOOK = 1;
	public static final int START_GOABROAD_BOOK = 2;
	public static final int START_EXAMFPG_BOOK = 3;
	public static final int START_OTHER_BOOK = 4;

	// mine activity jump code
	public static final int START_MY_ORDER = 10;
	public static final int START_MY_BOOK_STORE = 11;
	public static final int START_MY_COLLECTION = 12;
	public static final int START_MY_SETTINGS = 15;

	// erro jump code
	public static final int ERROR = 444;
}
