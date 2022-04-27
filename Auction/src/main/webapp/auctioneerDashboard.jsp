<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="bootstrapTemplate.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Auctioneer Dashboard</title>
</head>
<body>
	<nav
		class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3">
		<div class="container-fluid">
			<a class="navbar-brand" href="/auctioneer/dashboard">Manage
				Auctions</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

					<li class="nav-item float-right"><a class="nav-link" href="/auction/register">Create
							Auction</a></li>
                            <li class="nav-item float-right"><a class="nav-link" href="/auctioneer/update">Update Details</a></li>
				</ul>
				<form action="/logout" method="post">
					<button type="submit" class="btn btn-danger">Log Out</button>
				</form>
			</div>
		</div>
	</nav>
	<div class="row">
		<c:forEach items="${listOfAuctions}" var="auction">
			<div class="col-sm-4" style="width: 300px; height: 350px">
				<div class="card">
					<img class="card-img-top" src="/auctionImage/${auction.image}"
						alt="Card image cap" style="width: 275px; height: 200px">
					<div class="card-body">
						<h5 class="card-title">${auction.title}</h5>
						<p class="card-text">${auction.description}</p>
						<a href="/showCatalog/${auction.auctionId}"
							class="btn btn-primary">Catalog</a> <a
							href="/editCatalog/${auction.auctionId}" class="btn btn-primary">Edit</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>