package com.springboot.service;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginForm {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
