<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Auction Houses</title>
</head>
<body>
	<div class="row">
		<c:forEach items="${listOfLots}" var="lot">
			<div class="col-sm-4" style="width: 300px; height: 350px">
				<div class="card">
					<img class="card-img-top" src="/lots/${lot.image}"
						alt="Card image cap" style="width: 275px; height: 200px">
					<div class="card-body">
						<h5 class="card-title">${lot.title}</h5>
						<p class="card-text">${lot.description}</p>
						<p class="card-text">${lot.basePrice} $ for ${lot.quantity} items</p>
						<p class="card-text">Start: ${lot.startTime}  End: ${lot.endTime}</p>
<%-- 					<a class="btn btn-warning" href="http://localhost:8080/deleted?id=${Lot.lotId}">Delete</a> --%>
<%-- 					<a class="btn btn-success" href="http://localhost:8080/update?id=${Lot.lotId}">Edit</a> --%>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>