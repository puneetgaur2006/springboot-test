package com.springboot.service;

import com.springboot.utils.DbUtils;

public class UserLoginService {

	public boolean checkUserLoginDetails(UserLoginForm form) {
		return DbUtils.getuserDetails(form);
	}
}
