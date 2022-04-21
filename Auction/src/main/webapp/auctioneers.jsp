<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ include file="bootstrapTemplate.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
<style type="text/css">
</style>
</head>
<body>
	<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Manage Auctioneers</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link "
						aria-current="page" href="/admins">Manage Admins</a></li>
					<li class="nav-item float-right"><a class="nav-link" href="/auctioneer/register">Create Auctioneer</a></li>
					<li class="nav-item float-right"><a class="nav-link"
						href="#">Create auctioneers</a></li>
				</ul>
			</div>
			<form action="/logout" method="post">
				<button type="submit" class="btn btn-danger">Log Out</button>
			</form>
		</div>
	</nav>
	<table class="table table-dark table-striped" id="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Contact</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody class="table-striped">
			<c:forEach items="${listOfAuctioneers}" var="auctioneer">
				<tr>
					<td>${auctioneer.auctioneerId}</td>
					<td>${auctioneer.name}</td>
					<td>${auctioneer.email}</td>
					<td>${auctioneer.contact}</td>
					<td>
<%-- 					<a class="btn btn-warning" href="http://localhost:8080/deleted?id=${auctioneer.auctioneerId}">Delete</a> --%>
<%-- 						<a class="btn btn-success" href="http://localhost:8080/update?id=${auctioneer.auctioneerId}">Edit</a> --%>
											<a class="btn btn-warning" href="#">Delete</a>
						<a class="btn btn-success" href="#">Edit</a>
						</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	
	
</body>

</html>