package com.example.retrofitexample_mvc.models;

import com.google.gson.annotations.SerializedName;

public class Todo{

	@SerializedName("id")
	private Integer id;

	@SerializedName("completed")
	private Boolean completed;

	@SerializedName("title")
	private String title;

	@SerializedName("userId")
	private Integer userId;

	public Integer getId(){
		return id;
	}

	public boolean isCompleted(){
		return completed;
	}

	public String getTitle(){
		return title;
	}

	public Integer getUserId(){
		return userId;
	}
}