<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script type="text/javascript">
	$(function() {
		$("#result").dataTable();
	})
</script>
</head>
<body bgcolor="Aquamarine" >
	<table class="display" id="result" style="width: 100%">

		<thead>
			<tr>
				<th>FIRST NAME</th>
				<th>LAST NAME</th>
				<th>SSN NUMBER</th>
				<th>STATE</th>
				<th>MOBILE NUMBER</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allssn}"  var="b">
				<tr>
					<td><c:out value="${b.firstName}" /></td>
					<td><c:out value="${b.lastName }"></c:out></td>
					<td><c:out value="${b.ssn}" /></td>
					<td><c:out value="${b.state}" /></td>
					<td><c:out value="${b.phoneNo}"></c:out>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/form">Form</a>
</body>
</html>