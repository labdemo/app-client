package com.app.model;

import com.app.base.BaseModel;

public class Customer extends BaseModel{
	
	
	private boolean isLogin = false;
	
	private String id = null;
	private String user_name = null;
	private String password = null;
	
	private String name = null;
	private String sex = null;
	private String grade_id = null;
	private String phone = null;
	private String QQ = null;
	private String major_id = null;
	
	
	public void setLogin(boolean isLogin){
		this.isLogin = isLogin;
	}
	public boolean getLogin(){
		return isLogin;
	}
	
	public void setUser_name(String user_name){
		this.user_name = user_name;
	}
	public String getUser_name(){
		return this.user_name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getSex(){
		return this.sex;
	}
	
	public void setGrade_id(String grade_id){
		this.grade_id = grade_id;
	}
	public String getGrade_id(){
		return this.grade_id;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setQQ(String QQ){
		this.QQ = QQ;
	}
	public String getQQ(){
		return this.QQ;
	}
	
	public void setMajor_id(String major_id){
		this.major_id = major_id;
	}
	public String getMajor_id(){
		return this.major_id;
	}
	
	
	//for login customer
	private static Customer customer = null;
	public static Customer getCustomer(){
		return customer;
	}
	
	public static Customer getInstance() {
		if (Customer.customer == null) {
			Customer.customer = new Customer();
        }
        return Customer.customer;
	}
	
	public static void setCustomer(Customer customer){
		Customer.customer = customer;
	}

}
