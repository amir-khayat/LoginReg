<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Login And Registration</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-6 mb-3">
				<h2>Login</h2>
				<form:form action="/login" method="POST" modelAttribute="newLogin">
					<div class="form-group mb-3">
						<form:label path="email">Email:</form:label>
						<form:input path="email" class="form-control" 
							type="email" />
						<form:errors path="email" cssClass="text-danger" />
					</div>
					<div class="form-group mb-3">
						<form:label path="password">Password:</form:label>
						<form:input path="password" class="form-control" 
							type="password" />
						<form:errors path="password" cssClass="text-danger" />
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form:form>
			</div>
			<div class="col-md-6 mb-3">
				<h2>Registration</h2>
				<form:form action="/register" method="POST" modelAttribute="newUser">
					<div class="form-group mb-3">
						<form:label path="email">Email:</form:label>
						<form:input path="email" class="form-control" 
							type="email" />
						<form:errors path="email" cssClass="text-danger" />
					</div>
					<div class="form-group mb-3">
						<form:label path="userName">Username:</form:label>
						<form:input path="userName" class="form-control" 
							type="text" />
						<form:errors path="userName" cssClass="text-danger" />
					</div>
					<div class="form-group mb-3">
						<form:label path="password">Password:</form:label>
						<form:input path="password" class="form-control" 
							type="password" />
						<form:errors path="password" cssClass="text-danger" />
					</div>
					<div class="form-group mb-3">
						<form:label path="confirm">Confirm Password:</form:label>
						<form:input path="confirm" class="form-control" 
							type="password" />
						<form:errors path="confirm" cssClass="text-danger" />
					</div>
					<button type="submit" class="btn btn-primary">Register</button>
				</form:form>
			</div>
		</div>
	</div>




</body>
</html>