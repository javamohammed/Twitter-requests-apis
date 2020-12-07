package com.twitter.clone.model;

import java.io.Serializable;

import com.twitter.clone.entity.User;

public class JwtResponse implements Serializable
{
	
	private final String jwttoken;
	private User user;

	public JwtResponse(String jwttoken, User user) {
		this.user = user;
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}