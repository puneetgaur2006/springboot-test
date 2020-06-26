package com.springboot.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.springboot.service.EmployeeForm;
import com.springboot.service.UserLoginForm;

public class DbUtils {

	public static int saveEmployee(EmployeeForm addEmpForm) {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
		int generatedEmpId=-1;
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String insertSql="insert into employee(EMP_NAME,Dept_Name,Email,Address,PhoneNo) "
						+" values('" + addEmpForm.getName() + "','" + addEmpForm.getDept()+ "','" + addEmpForm.getEmail()+ "','" + addEmpForm.getAddress() + "','" + addEmpForm.getPhone() + "')";
				System.out.println("query insert" +insertSql);
				int m=st.executeUpdate(insertSql);
				System.out.println("Ok its working");
				
				if (m> 0) {
					rs=st.getGeneratedKeys();
					
					if (rs.next()) {
						generatedEmpId=rs.getInt(1);
					}
				}
				
			}
			catch(SQLException se) {
	
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){

			}
		}
		
	}
		return generatedEmpId;

}



	public static List<String> getAllDept() {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
	
		List<String> listofdept=new ArrayList<String>();
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String Sql="Select DEPT_NAME from department";
				rs = st.executeQuery(Sql);
				while (rs.next()) {
				
					String dept = rs.getString("DEPT_NAME");
		
					
					
					listofdept.add(dept);
					
				}
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return listofdept;
}
	 

	
	
	public static EmployeeForm getEmployee(int employeeId) {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
		EmployeeForm ef=new EmployeeForm();
		int generatedEmpId=-1;
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String Sql="Select EMP_NAME,DEPT_NAME from employee where EMP_ID='"+employeeId+"'";
				rs = st.executeQuery(Sql);
				while (rs.next()) {
					String name = rs.getString("EMP_NAME");
					String dept = rs.getString("DEPT_NAME");
					ef.setEmployeeId(employeeId);
					ef.setName(name);
					ef.setDept(dept);
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return ef;

}

	public static boolean updateEmployee(EmployeeForm updatedEmpForm) {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
		//int generatedEmpId=-1;
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String updateSql="update employee"
						+" set EMP_NAME='" + updatedEmpForm.getName() + "',"+"EMAIL='"+updatedEmpForm.getEmail()+ "',"+"ADDRESS='"+updatedEmpForm.getAddress()+ "',"+"PHONENO="+updatedEmpForm.getPhone()+" where emp_id="+updatedEmpForm.getId();
				
				System.out.println(updateSql);
				
				if (st.executeUpdate(updateSql) > 0) {
					return true;
				}
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return false;

}
	public static List<EmployeeForm> getALLEmployee() {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
	
		List<EmployeeForm> listOfemp=new ArrayList<EmployeeForm>();
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String Sql="Select EMP_ID,EMP_NAME,DEPT_NAME,Address,Email,PhoneNo from employee";
				rs = st.executeQuery(Sql);
				while (rs.next()) {
					EmployeeForm ef=new EmployeeForm();
					int employeeId=Integer.parseInt(rs.getString("EMP_ID"));
					String id=String.valueOf(employeeId);
					String name = rs.getString("EMP_NAME");
					String dept = rs.getString("DEPT_NAME");
					String address = rs.getString("Address");
					String email = rs.getString("Email");
					String phoneNo = rs.getString("PhoneNo");
					
					ef.setId(id);
					ef.setName(name);
					ef.setDept(dept);
					ef.setAddress(address);
					ef.setEmail(email);
					ef.setPhone(phoneNo);
					listOfemp.add(ef);
				}
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return listOfemp;
}
	public static boolean getuserDetails(String userName, String password) {
		Connection conn=DbConnection.getConnection();
		ResultSet rs=null;
		boolean found=false;
		if(conn!=null) {
			try {
		        String sql = "SELECT * FROM userdetails WHERE userName = ? and password = ?";
		        PreparedStatement statement = conn.prepareStatement(sql);
		        statement.setString(1, userName);
		        statement.setString(2, password);
		 
		         rs = statement.executeQuery();
		 
		 
		        if (rs.next()) {
		        	found=true;
		        }
		 return found;
				
			}
			catch(SQLException se) {
				found=false;
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return found;

}
	public static boolean deleteEmployeeDetails(String empId) {
		Connection conn=DbConnection.getConnection();
		ResultSet rs=null;
		boolean delete=false;
		if(conn!=null) {
			try {
		        String sql = "DELETE FROM employee WHERE EMP_ID = ?";
		        PreparedStatement statement = conn.prepareStatement(sql);
		        statement.setString(1, empId);
		         int result = statement.executeUpdate();
		        if (result==1) {
		        	delete=true;
		        }
				
			}
			catch(SQLException se) {
				delete=false;
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return delete;

}

}

