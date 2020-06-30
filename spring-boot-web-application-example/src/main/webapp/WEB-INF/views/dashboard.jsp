<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.springboot.service.Employee"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
		
		function deletefun(val){
			 //alert(document.forms[0].action);
			 document.forms[0].action="/deleteEmployee?empId="+val;
			 //alert(document.forms[0].action);
			document.getElementById("deleteId").click();
			}
		function editfun(empid,name,dept,email,address,phone){
			 document.getElementById("editEmpId").value=empid;
			 document.getElementById("editEmpId1").value=empid;
			 document.getElementById("editName").value=name;
			 document.getElementById("editDept").value=dept;
			 document.getElementById("editEmail").value=email;
			 document.getElementById("editAddress").value=address;
			 document.getElementById("editPhone").value=phone;
			document.getElementById("editId").click();
			}
		function clear(){
			alert("clear");
			 document.getElementById("name").value="";
			 document.getElementById("dept").value="";
			 document.getElementById("email").value="";
			 document.getElementById("address").value="";
			 document.getElementById("phone").value="";
			}
		
	</script>

</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management</title>
</head>

<body>

	<div class="col-sm-6">
		<h2>
			Manage <b>Employees</b>
		</h2>
	</div>
	<div align="right">
		<a href="#addEmployeeModal" class="btn btn-success"
			data-toggle="modal"><span>Add New Employee</span></a>
	</div>
	<br />
	<table id="example" class="table table-striped table-bordered"
		style="width: 100%">

		<thead align="center">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Dept</th>
				<th>Address</th>
				<th>Email</th>
				<th>Phone No.</th>
				<th>Update?</th>
				<th>Delete?</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="employee" items="${employeeList}">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.dept}</td>
					<td>${employee.address}</td>
					<td>${employee.email}</td>
					<td>${employee.phone}</td>
					<td>
					<button type="button" class="btn btn-primary" onclick="editfun('${employee.id}', '${employee.name}', '${employee.dept}', '${employee.email}', '${employee.address}', '${employee.phone}')">Edit</button>
						<a href="#editEmployeeModal" class="edit" data-toggle="modal" id="editId"></a>
					</td>
					<td>
						<button type="button" class="btn btn-primary"
							onclick="deletefun('${employee.id}')">Delete</button>
						<a href="#deleteEmployeeModal1" class="delete" data-toggle="modal" id="deleteId"></a>
					</td>
					<td>
					<input type="hidden" name="hidden" value="${employee.id}"/>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<%-- <html:link action="/addEmployee">Add Employee</html:link>
	<br></br>
	<html:link action="/updateEmployee">Update Employee</html:link> --%>
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="/addEmployee" method="post">
					<div class="modal-header">
						<h4 class="modal-title">Add Employee</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Name</label>
							<!--                         <input type="text" id="name"   class="employeeForm" required> -->

							<input type="text" class="form-control" id="name" name="name"
								required="required" value="">

						</div>
						<div class="form-group">
							<label>Department</label> <select id="dept" name="dept"
								Class="form-control" required="required">

								<option value="">-- Select --</option>
								<c:forEach var="dept" items="${deptList}">
									<option value="${dept}">${dept} </option>
								</c:forEach>

							</select>

						</div>

						<div class="form-group">
							<label>Email</label> <input type="email" value=""
								class="form-control" id="email" name="email" required="required"
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
						</div>
						<div class="form-group">
							<label>Address</label> <input type="text" value=""
								class="form-control" id="address" name="address"
								required="required">
						</div>
						<div class="form-group">
							<label>Phone</label> <input type="tel" value=""
								class="form-control" id="phone" name="phone" required="required"
								pattern="[6-9]{1}[0-9]{9}">
						</div>
					</div>

					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel" oncancel="clear();"> <input type="submit"
							class="btn btn-success" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="editEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="/updateEmployee" method="post">
					<div class="modal-header">
						<h4 class="modal-title">Edit Employee</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Employee Id</label> <input type="text"
								class="form-control" id="editEmpId" name="id1"
								disabled="disabled"> <input type="hidden"
								class="form-control" id="editEmpId1" name="id">

						</div>
						<div class="form-group">
							<label>Employee Name</label> <input type="text"
								class="form-control" id="editName" name="name"
								required="required">
						</div>
						<div class="form-group">
							<label>Dept Name</label> <input type="text" class="form-control"
								id="editDept" name="dept" disabled="disabled">
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" class="form-control"
								id="editEmail" name="email" required="required"
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
						</div>
						<div class="form-group">
							<label>Address</label>
							<textarea class="form-control" id="editAddress" name="address"
								required="required"></textarea>
						</div>
						<div class="form-group">
							<label>Phone</label> <input type="tel" class="form-control"
								id="editPhone" name="phone" required="required"
								pattern="[6-9]{1}[0-9]{9}">
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit" class="btn btn-info"
							value="Save">
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Delete Modal HTML -->
	 <div id="deleteEmployeeModal1" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="/deleteEmployee" method="GET">
					<div class="modal-header">
						<h4 class="modal-title">Delete Employee</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="clear();">&times;</button>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete these record?</p>
						<p class="text-warning">
							<small>This action cannot be undone.</small>
						</p>
						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-danger" value="Delete">
					</div>
				</form>
			</div>
		</div>
	</div> 

</body>
</html>