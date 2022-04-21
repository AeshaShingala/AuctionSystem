<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ include file="bootstrapTemplate.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="/css/login.css">
<title>Admin Login</title>
</head>
<body>

	<div class="wrapper fadeInDown">
		<div id="formContent">


			<!-- Login Form -->
			<form action="http://localhost:8080/admin/login/data" method="post">
				<c:if test="${invalid == true}">
					<div class="alert alert-warning" role="alert">Invalid
						Credentials</div>
				</c:if>
				<input type="text" id="login" class="fadeIn second" name="email"
					placeholder="Email"> <input type="password" id="password"
					class="fadeIn third" name="password" placeholder="Password">
				<input type="submit" class="fadeIn fourth" value="Log In">
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a class="underlineHover"
					href="http://localhost:8080/admin/register">New Here? Sign Up</a>
			</div>
		</div>
	</div>
</body>
</html>