package com.springboot.service;

import com.springboot.utils.DbUtils;

public class UserLoginService {

	public boolean checkUserLoginDetails(String userName, String password) {
		return DbUtils.getuserDetails(userName, password);
	}
}
