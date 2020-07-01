<%-- <%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> --%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!--===============================================================================================-->
		<style type="text/css">
	        .errormsg {
	            color: red;
	        }
	    </style>
</head>
<body>
	
	<html:javascript/>
	<div class="limiter">
		<div class="container-login100" style="background-image: url('resources/images/bg-01.jpg');">
			<div class="wrap-login100 logindiv">
				<form:form action="dashboardservice" method="post" modelAttribute="userform">
					<span class="login100-form-title p-b-49">
						Login
					</span>

					<div class="wrap-input100 validate-input m-b-23" data-validate = "Username is reauired">
						<span class="label-input100">User Name  </span>
						<div><small><form:errors path="userName" cssClass="errormsg" /></small></div>
						<input class="userLoginForm" id="userName" type="text" name="userName" property="userName" placeholder="Type your username">
						<span class="focus-input100" data-symbol="&#xf206;"></span>
					</div>
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<span class="label-input100">User Password</span>
						<div><small><form:errors path="password" cssClass="errormsg"/></small></div>
						<input class="userLoginForm" type="password" name="password" placeholder="Type your password">
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>
					
					<div class="text-right p-t-8 p-b-31">
						<a href="#">
							Forgot password?
						</a>
					</div>
					
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Login
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	

	


</body>
</html>