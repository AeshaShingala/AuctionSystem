<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
    <title>Show Admins</title>
	<link rel="stylesheet" type="text/css" href="/style.css" />
  </head>
  <body>
  	<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Manage Admins</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="     fhhgffalse"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link "
						aria-current="page" href="/admin">Dashboard</a></li>
					<li class="nav-item float-right"><a class="nav-link" href="/admin/register">Add Admin</a></li>
					<li class="nav-item float-right"><a class="nav-link"
						href="#">Add Admins</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<table class="table table-dark table-striped" id="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Status</th>
				<th>adminName</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody class="table-striped">
			<c:forEach items="${listOfAdmins}" var="admin">
				<tr>
					<td>${admin.adminId}</td>
					<c:if test="${admin.active}">
						<td>Active</td>
					</c:if>
					<c:if test="${not admin.active}">
						<td>Inactive</td>
					</c:if>
					<%-- 					<td>${admin.active }</td> --%>
					<td>${admin.name}</td>
					<td>${admin.email}</td>
					<td><c:if test="${admin.active }">
							<a class="btn btn-warning"
								href="http://localhost:8080/admins/deactive/${admin.adminId}">Deactivate</a>
						</c:if> <c:if test="${not admin.active }">
							<a class="btn btn-warning"
								href="http://localhost:8080/admins/active/${admin.adminId}">Activate</a>
						</c:if> <a class="btn btn-success"
						href="http://localhost:8080/admins/delete/${admin.adminId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>