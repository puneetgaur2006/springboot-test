package com.springboot.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.service.DashBoardService;
import com.springboot.service.Employee;
import com.springboot.service.UserLoginForm;
import com.springboot.service.UserLoginService;

@Controller
public class AppController {

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	DashBoardService dashBoardService;

	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("userform", new UserLoginForm());
		return "index";
	}

	@PostMapping("/dashboardservice")
	public String validateUser(Model model, @Valid @ModelAttribute("userform") UserLoginForm user,
			BindingResult result) {

		if (result.hasErrors()) {
			return "index";
		}

		boolean userValid = userLoginService.checkUserLoginDetails(user.getUserName(), user.getPassword());
		if (userValid) {
			List<Employee> employees = dashBoardService.getAllEmployees();
			List<String> deptList = dashBoardService.getAllDept();
			model.addAttribute("employeeList", employees);
			model.addAttribute("deptList", deptList);
			return "dashboard";
		}

		return "index";
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
			@RequestParam("address") String address, @RequestParam("phone") String phone, Model model)
			throws SQLException {

		Employee employee = new Employee();
		employee.setAddress(address);
		employee.setEmail(email);
		employee.setName(name);
		employee.setPhone(phone);

		boolean updatedSuccessfully = dashBoardService.updateEmployee(employee);
		model.addAttribute("updateRecord", updatedSuccessfully);
		return "updateEmpSuccess";
	}

	@PostMapping("/redirectDashboard")
	public String redirectDashboard(Model model) {

		List<Employee> employees = dashBoardService.getAllEmployees();
		List<String> deptList = dashBoardService.getAllDept();
		model.addAttribute("employeeList", employees);
		model.addAttribute("deptList", deptList);
		return "dashboard";
	}

	@PostMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam(name = "empId", required = false) String empId, Model model,
			HttpServletRequest request) {
		boolean deletedSuccessfully = dashBoardService.deleteEmployee(empId);
		model.addAttribute("employeeDeleted", deletedSuccessfully);
		return "deleteSuccess";
	}
}
