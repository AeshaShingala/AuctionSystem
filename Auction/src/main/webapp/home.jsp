<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ include file="bootstrapTemplate.jsp" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<title>Home</title>
		</head>

		<body>

			<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Welcome</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
						data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
						aria-expanded="ffalse" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item float-right"><a class="nav-link" href="/bidder/register">Create
									Bidder Account</a></li>
							<li class="nav-item float-right"><a class="nav-link"
									href="/auctionhouse/register">Categories</a></li>
						</ul>
						<div class="dropdown floatleft">
							<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
								data-bs-toggle="dropdown" aria-expanded="false">
								Login As
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li>
									<a class="dropdown-item" href="auctioneer/login">Auctioneer</a>
								</li>
								<li>
									<a class="dropdown-item" href="bidder/login">Bidder</a>
								</li>
								<li>
									<a class="dropdown-item" href="admin/login">Admin</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</nav>

			<!-- <div class="row">
				<c:forEach items="${listOfAuctions}" var="auction">
					<div class="col-sm-4" style="width: 300px; height: 350px">
						<div class="card">
							<img class="card-img-top" src="/auctionImage/${auction.image}" alt="Card image cap"
								style="width: 275px; height: 200px">
							<div class="card-body">
								<h5 class="card-title">${auction.title}</h5>
								<p class="card-text">${auction.description}</p>
								<a href="/showCatalog/${auction.auctionId}" class="btn btn-primary">Catalog</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div> -->
			
			<div class="container mt-2">
				<div class="center">

					<c:forEach items="${listOfAuctions}" var="auction">
						<div class="card mb-3">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img src="/auctionImage/${auction.image}" class="card-img" alt="..." width="250px" height="250px">
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h5 class="card-title">${auction.title}</h5>
										<p class="card-text">${auction.description}</p>
										<p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
										<a href="/showCatalog/${auction.auctionId}" class="btn btn-primary">Catalog</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</body>

		</html>