package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

   @RequestMapping("/")
   public String login() {
      return "login";
   }

   @PostMapping("/userLogin")
   public String validateUser(@RequestParam("userName") String name, @RequestParam("password") String password
		   ,Model model) {

	   //TODO: Call user service..validate username/password and return manage-employee page
	   model.addAttribute("name", name);
      return "manage-employee";
   }
}
