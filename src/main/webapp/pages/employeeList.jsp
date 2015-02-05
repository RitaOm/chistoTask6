<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>Employee List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css"></link>
</head>
<body>
<div class="content">
<h1>Employee List</h1>

			<table class="employee-list-table">
				<tr>
					<th>ID</th>
					<th>Employee first and last name </th>
					<th>Employee address</th>
					<th>Offices</th> 
				</tr>
				<c:forEach items="${employeeList}" var="employee">
					<tr>
						<td>${employee.id}</td>
						<td>${employee.firstName} ${employee.lastName}</td>
						<td>${employee.address.name} ${employee.address.city.name} ${employee.address.city.country.name}</td>
						<td>
						<c:forEach items="${employee.workplaces}" var="workplace">
						<strong>Position: </strong>
						${workplace.position.name} <strong>, Company: </strong>
						${workplace.office.company.name} <strong>, Address: </strong>
						${workplace.office.address.name}
						${workplace.office.address.city.name}
						${workplace.office.address.city.country.name}<strong>, Employees number: </strong>
						${workplace.office.employeesNumber}
						<br />
							</c:forEach></td> 
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>