package com.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.service.DashBoardService;
import com.springboot.service.UserLoginService;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public UserLoginService userLoginService() {
		UserLoginService userLoginService = new UserLoginService();
		return userLoginService;
	}
	
	@Bean
	public DashBoardService dashBoardService() {
		DashBoardService dashBoardService = new DashBoardService();
		return dashBoardService;
	}
		

}
