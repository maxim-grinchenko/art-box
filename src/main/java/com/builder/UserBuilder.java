package com.builder;

import com.model.User;

public class UserBuilder {

	private User user;

	public UserBuilder() {
		this.user = new User();
	}
	
	public UserBuilder setUserName(String name){
		this.user.setName(name);
		return this;
	}

	public UserBuilder setUserEmail(String email) {
		this.user.setEmail(email);
		return this;
	}

	public UserBuilder setUserPassword(String password) {
		this.user.setPassword(password);
		return this;
	}

	public User getUserBuild() {
		return this.user;
	}

}
