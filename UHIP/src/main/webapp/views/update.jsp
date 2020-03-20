<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center><h1 style="color:red">---Enter the form details To Update---</h1></center>
	<center>
	<h3 style="color:green">${success}</h3>
	<form:form action="updateuser" method="POST" id="form" modelAttribute="userAccModel">
		<table width="280px" border="1">
			<tr>
				<th>User Id</th>
				<td><form:input path="userId" disabled="true"/></td>
			</tr>
			<tr>
				<th>First Name</th>
				<td><form:input path="firstName" placeholder="Enter First Name"/></td>
			</tr>
			<tr>
				<th>Last Name</th>
				<td><form:input path="lastName" placeholder="Enter Last Name"/></td>
			</tr>
			<tr>
				<th>Date of Birth</th>
				<td><form:input path="userDob" id="datepicker" placeholder="Enter DOB"/></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><form:radiobuttons path="gender" items="${gender}"/></td>
			</tr>
			<tr>
				<th>SSN</th>
				<td><form:input path="ssn" placeholder="Enter SSN"/></td>
			</tr>
			<tr>
				<th>User Role</th>
				<td><form:select path="userRole" items="${role}"></form:select></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset"/></td>
				<td><input type="submit" id="update" value="Update"/></td>
			</tr>
		</table>
	</form:form>
	</center>
</body>
</html>