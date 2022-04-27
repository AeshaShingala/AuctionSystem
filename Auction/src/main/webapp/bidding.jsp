<%@ page language="java" contentType="text/html" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@include file="/bootstrapTemplate.jsp" %>

			<!DOCTYPE html>
			<html>

			<head>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
				<title>Form Demo</title>
			</head>

			<body>
				<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3">
					<div class="container-fluid">
						<a class="navbar-brand" href="#">Welcome ${user}</a>
						<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
							aria-expanded="ffalse" aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item float-right"><a class="nav-link" href="/">Home</a></li>
								<li class="nav-item float-right"><a class="nav-link" href="#">Categories</a></li>
							</ul>
							<a class="btn btn-danger" href="/logout">Log Out</a>
						</div>
					</div>
				</nav>
				<div class="alert alert-success hidden" id="highBidAlert" style="display: none;" role="alert">
					You Are Highest Bidder
				</div>
				<div class="alert alert-warning" id="outBidAlert" style="display: none;">
					You Have Been Outbid! Bid Again
				</div>
				<span>Bid By: <span id="userName"></span></span>
				<span>Current Bid: <span align="center" id="currentBid"></span></span>
					<br>


					<form method="post" id="team">
						<input type="number" id="newBid" readonly name="bid"> <button type="button" id="submitBid"
							onclick="sendBid()">Submit Bid</button>
					</form>
			</body>
			<script src="/js/bidding.js"></script>

			</html>