<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page isErrorPage="true" />
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css"></link>
</head>
<body>
	<div class="error-content">
		<h1>Sorry you are not a winner..</h1>
		<p>Error page</p>
		<p>
			<a href="/hibernateTask/employeeList">Go to employee list</a>
		</p>
	</div>
</body>
</html>



