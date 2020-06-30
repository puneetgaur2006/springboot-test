package com.springboot.service;

import java.sql.SQLException;
import java.util.List;

import com.springboot.utils.DbUtils;

public class DashBoardService {

	public boolean deleteEmployee(String empId) {
		return DbUtils.deleteEmployeeDetails(empId);
	}
	public boolean updateEmployee(Employee updatedEmpForm) throws SQLException {
		return DbUtils.updateEmployee(updatedEmpForm);
	}
	
	public List<Employee> getAllEmployees() {
		return DbUtils.getALLEmployee();
	}
	
	public List<String> getAllDept() {
		return DbUtils.getAllDept();
	}
	
	
	public int saveEmployee(Employee addEmpForm) {
		return DbUtils.saveEmployee(addEmpForm);
	}
}
