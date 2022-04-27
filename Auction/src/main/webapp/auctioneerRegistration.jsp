<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">

<head>
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
<link rel="stylesheet" href="/css/register.css">

<title>User Registration</title>
</head>
<body>

	<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3">
		<div class="container-fluid">
			<a class="navbar-brand" href="/auctioneer/register">Create Auctioneer</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item float-right"><a class="nav-link"
						href="/admins">Manage Admin</a></li>
					<li class="nav-item"><a class="nav-link "
						aria-current="page" href="/auctioneers">Manage Auctioneers</a></li>
					<li class="nav-item float-right"><a class="nav-link" href="/admin/register">Create Admin</a></li>
					<!-- <li class="nav-item float-right"><a class="nav-link"
						href="#">Add Admins</a></li> -->
					
						<li class="nav-item float-right"><a class="nav-link"
							href="/auctioneers">Auctioneer Dashboard</a></li>
					<li class="nav-item float-right"><a class="nav-link" href="/auctionhouse/register">Create
							Auction House</a></li>
				</ul>
			</div>
			<form action="/logout" method="post">
				<button type="submit" class="btn btn-danger">Log Out</button>
			</form>
		</div>
	</nav>

	<main class="my-form">
		<div class="cotainer">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">Enter Auctioneer Details</div>
						<div class="card-body">
							<form name="my-form"
								action="http://localhost:8080/auctioneer/data" method="post">
								<c:if test="${exists == true}">
									<div class="alert alert-warning" role="alert">User
										Already Exists</div>
								</c:if>
								<div class="form-group row">
									<label for="name" class="col-md-4 col-form-label text-md-right">Enter
										Name</label>
									<div class="col-md-6">
										<input required="required" type="text" id="name"
											class="form-control" name="name">
									</div>
								</div>
								<div class="form-group row">
									<label for="password"
										class="col-md-4 col-form-label text-md-right">Enter
										Password</label>
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
								<div class="form-group row">
									<label class="col-md-4 col-form-label text-md-right">Auction
										House</label>
									<form:select required="required" path="listAuctionHouses"
										id="selectedAuctionHouse" name="selectedAuctionHouse">
										<div class="col-md-6 center">
											<form:option value="-"
												label="--Please Select Auction House--" />
											<form:options items="${listAuctionHouses}"
												itemValue="auctionHouseId" itemLabel="name" />
										</div>
									</form:select>
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