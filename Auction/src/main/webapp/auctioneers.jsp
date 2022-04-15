<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ include file="template.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Record</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
<style type="text/css">
</style>
</head>
<body>
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