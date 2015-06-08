package com.app.base;

import com.app.model.Customer;

public class BaseAuth {
	
	private static Customer customer = Customer.getInstance();
	
	public static boolean isLogin(){
		return customer.getLogin();
	}
	
	public static void setLogin(Boolean status){
		customer.setLogin(status);
	}
	
	public static void setCustomer(Customer cus){
		customer.setUser_name(cus.getUser_name());
		customer.setPassword(cus.getPassword());
	}
	
	public static Customer getCustomer(){
		return customer;
	}
}