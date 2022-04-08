<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ include file="template.jsp"%>
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
				<th>Title</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Base Price</th>
				<th>Image</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody class="table-striped">
			<c:forEach items="${listOfLots}" var="lot">
				<tr>
					<td>${lot.lotId}</td>
					<td>${lot.title}</td>
					<td>${lot.description}</td>
					<td>${lot.quantity}</td>
					<td>${lot.basePrice}</td>
					<td> <img class="card-img-top"
						src="/lots/${lot.image}"
						alt="Card image cap"></td>
					<td>
<%-- 					<a class="btn btn-warning" href="http://localhost:8080/deleted?id=${Lot.lotId}">Delete</a> --%>
<%-- 					<a class="btn btn-success" href="http://localhost:8080/update?id=${Lot.lotId}">Edit</a> --%>
						<a class="btn btn-warning" href="#">Delete</a> <a
						class="btn btn-success" href="#">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>