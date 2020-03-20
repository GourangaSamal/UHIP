<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Account Creation</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript">

 $(document).ready(function() {
 	$("#emailId").blur(function() {
 		
 		$("#errMsg").html("");
 		$.ajax({
 			type:'GET',
 			url : window.location+'/checkEmail',
 			data : {
 				'email' : $("#emailId").val()
 				
 		
 			},
 			success : function(data) {
 				if(data=="duplicate"){
 					$("#errMsg").html("Email already available");
 					$("#createAccBtn").attr('disable',true);
 				}
 				else
 				{
 					$("#createAccBtn").attr('disable',false);
 				}
 				
 			}
 		});
 	});
 });
</script>
</head>
<body>
	<h2>User Account Creation</h2>
	<font color='green'>${succMsg}</font>
	<font color='red'>${failMsg}</font>
	<form:form action="createUserAccount" method="POST" modelAttribute="USER_ACC_MODEL">
		<table>
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="emailId" id="emailId"/> <font color="red"><span
						id="errMsg"></span></font></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><form:input path="dob" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><form:radiobuttons items="${genders}" path="gender" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:select items="${roles}" path="role" /></td>
			</tr>
			<tr>
				<td>Ssn</td>
				<td><form:input path="ssn" /></td>
			</tr>

			<tr>
				<td><input type="reset" value="reset"></td>
				<td><input type="submit" id="createAccBtn" value="Create Account">
			</tr>


		</table>
	</form:form>


</body>
</html>