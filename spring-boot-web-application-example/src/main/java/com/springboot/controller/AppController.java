package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.service.DashBoardService;
import com.springboot.service.Employee;
import com.springboot.service.UserLoginService;

@Controller
public class AppController {

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	DashBoardService dashBoardService;

	@RequestMapping("/")
	public String login() {
		return "index";
	}

	@PostMapping("/dashboardservice")
	public String validateUser(@RequestParam(name = "userName", required = false) String name,
			@RequestParam(name = "password", required = false) String password, Model model, HttpServletRequest request) {

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (name==null && password==null) {
			List<Employee> employees = dashBoardService.getAllEmployees();
			List<String> deptList = dashBoardService.getAllDept();
			model.addAttribute("employeeList", employees);
			model.addAttribute("deptList", deptList);
			return "dashboard";
		} else {
			boolean userValid = userLoginService.checkUserLoginDetails(name, password);
			if (userValid) {
				List<Employee> employees = dashBoardService.getAllEmployees();
				List<String> deptList = dashBoardService.getAllDept();
				model.addAttribute("employeeList", employees);
				model.addAttribute("deptList", deptList);
				return "dashboard";
			}

			return "unauthorized";
		}
	}

	@PostMapping("/addEmployee")
	public String addEmployee(@RequestParam("name") String name, @RequestParam("dept") String department,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("phone") String phone, Model model) {

		Employee employee = new Employee();
		employee.setAddress(address);
		employee.setDept(department);
		employee.setEmail(email);
		employee.setName(name);
		employee.setPhone(phone);

		dashBoardService.saveEmployee(employee);

		model.addAttribute("employeeList", dashBoardService.getAllEmployees());
		return "dashboard";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("phone") String phone, Model model) {

		Employee employee = new Employee();
		employee.setAddress(address);
		employee.setEmail(email);
		employee.setName(name);
		employee.setPhone(phone);

		boolean updatedSuccessfully = dashBoardService.updateEmployee(employee);
		model.addAttribute("updateRecord", updatedSuccessfully);
		return "updateEmpSuccess";
	}

	@PostMapping("redirectDashboard")
	public String redirectDashboard() {
		return "dashboard";
	}
}
