<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="ISO-8859-1">
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
					crossorigin="anonymous">

				<title>Auction Houses</title>
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
								<li class="nav-item float-right"><a class="nav-link" href="#">Categories</a></li>
								<li class="nav-item float-right"><a class="nav-link" href="#">Auction Houses</a></li>
							</ul>
							<div class="float-left">
								<a class="btn btn-danger" href="/logout">Logout</a>
							</div>
						</div>
					</div>
				</nav>
				<div class="row">
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
				</div>

				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
					crossorigin="anonymous"></script>

			</body>

			</html>