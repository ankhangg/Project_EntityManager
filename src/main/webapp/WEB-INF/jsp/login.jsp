<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/decorator_loginwebsite/css/styles.css" />
</head>
<body>
	<div class="sidenav">
		<div class="login-main-text">
			<h2>
				AN KHANG WEBISTE<br> Login Page
			</h2>
			<p>Login or register from here to access.</p>
		</div>
	</div>
	<div class="main">
		<div class="col-md-6 col-sm-12">
			<div class="login-form">
				<!--         /login?error=true 
        /login?error=true  -->
				<c:if test="${not empty message}">
					<div class="alert alert-${getAlert }">${message }</div>
				</c:if>
				<form method="POST"
					action="${pageContext.request.contextPath}/j_spring_security_check">
					<div class="form-group">
						<label>User Name</label> <input type="text" name="userName"
							class="form-control" placeholder="User Name">
					</div>
					<div class="form-group">
						<label>Password</label> <input name="passWord" type="password"
							class="form-control" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-black">Login</button>
				</form>
				<form action="regist">
					<button type="submit" class="btn btn-secondary">Regist</button>
				</form>
				<form action="home">
					<button type="submit" class="btn btn-secondary">Home</button>
				</form>
				<form action="forgetpassword">
					<button type="submit" class="btn btn-secondary">Forget Password</button>
				</form>
			</div>
		</div>
	</div>
</html>