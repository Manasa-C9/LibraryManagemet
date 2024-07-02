package com.planonsoftware;

public class User {
	
	String role;
	String username;
	String password;
	
	public User(String username,String password,String role)
	{
		this.username=username;
		this.password=password;
		this.role=role;
	}
}
