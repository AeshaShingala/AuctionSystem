<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">

<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Fonts -->
<link rel="dns-prefetch" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600"
	rel="stylesheet" type="text/css">



<link rel="icon" href="Favicon.png">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../resources/static/css/register.css">
<link rel="stylesheet" href="/css/register.css">

<title>Admin Registration</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
		<div class="container">
			<a class="navbar-brand" href="#">Registration</a>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="localhost:8080/admin/login">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="localhost:8080/admin/register">Sign Up</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<main class="my-form">
		<div class="cotainer">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">Admin</div>
						<div class="card-body">
							<form name="my-form" action="http://localhost:8080/admin/data"
								method="post">
								<c:if test="${exists == true}">
									<div class="alert alert-warning" role="alert">User
										Already Exists</div>
								</c:if>
								<div class="form-group row">
									<label for="name" class="col-md-4 col-form-label text-md-right">Name</label>
									<div class="col-md-6">
										<input required="required" type="text" id="name"
											class="form-control" name="name">
									</div>
								</div>
								<div class="form-group row">
									<label for="password"
										class="col-md-4 col-form-label text-md-right">Password</label>
									<div class="col-md-6">
										<input required="required" type="password" id="password"
											class="form-control" name="password">
									</div>
								</div>

								<div class="form-group row">
									<label for="email"
										class="col-md-4 col-form-label text-md-right">E-Mail
										Address</label>
									<div class="col-md-6">
										<input required="required" type="email" id="email"
											class="form-control" name="email">
									</div>
								</div>

								<div class="form-group row">
									<label for="contact"
										class="col-md-4 col-form-label text-md-right">Phone
										Number</label>
									<div class="col-md-6">
										<input required="required" maxlength="10" type="number"
											id="contact" class="form-control" name="contact">
									</div>
								</div>
								<div class="col-md-6 offset-md-4">
									<button type="submit" class="btn btn-primary">Register</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</main>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>

</html>