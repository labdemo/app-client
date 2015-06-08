package com.app.model;

import android.widget.ImageView;

import com.app.base.BaseModel;

public class Book extends BaseModel {
	
	private String bookName;
	private String author;
	private String major_id;
	private ImageView bookImage;
	
	
	public void setBookName(String name){
		this.bookName = name;
	}
	public String getBookName(){
		return this.bookName;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	public String getAuthor(){
		return this.author;
	}
	
	public void setMajor_id(String major_id){
		this.major_id = major_id;
	}
	public String getMajor_id(){
		return this.major_id;
	}
	
	public void setBookImage(ImageView image){
		this.bookImage = image;
	}
	public ImageView getBookImage(){
		return this.bookImage;
	}
}
