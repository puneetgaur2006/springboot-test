package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.service.UserLoginService;

@Controller
public class LoginController {
	
	@Autowired
	UserLoginService userLoginService;

   @RequestMapping("/")
   public String login() {
      return "login";
   }

   @PostMapping("/userLogin")
   public String validateUser(@RequestParam("userName") String name, @RequestParam("password") String password
		   ,Model model) {

	   boolean userValid = userLoginService.checkUserLoginDetails(name, password);
	   if(userValid) {
		   return "dashboard";
	   }
	   //model.addAttribute("name", name);
      return "unauthorized";
   }
}
