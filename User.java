package com.planonsoftware;

public class User {
	
	String role;
	String username;
	String password;
	private double balance;
	
	public User(String username,String password,String role)
	{
		this.username=username;
		this.password=password;
		this.role=role;
		this.balance=0.0;
	}
	
	void addBalance(double amount)
	{
		this.balance+=amount;
	}
	
	void deductBalance(double amount)
	{
		this.balance-=amount;
	}
	
	double getBalance() {
		return this.balance;
	}
}
