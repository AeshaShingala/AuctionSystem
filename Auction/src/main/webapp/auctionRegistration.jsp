<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ include file="bootstrapTemplate.jsp"%> --%>

<!doctype html>
<html lang="en">

<head>
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

<title>Auction Registration</title>
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
					<li class="nav-item float-right"><a class="nav-link"
						href="/auction/register">Create Auction</a></li>
					<li class="nav-item float-right"><a class="nav-link" href="#">Update
							Details</a></li>
				</ul>
				<form action="/logout" method="post">
					<button type="submit" class="btn btn-danger">Log Out</button>
				</form>
			</div>
		</div>
	</nav>



	<!-- 	<nav -->
	<!-- 		class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark py-3"> -->
	<!-- 		<div class="container-fluid"> -->
	<!-- 			<a class="navbar-brand" href="/auctioneer/dashboard">Manage -->
	<!-- 				Auctions</a> -->
	<!-- 			<button class="navbar-toggler" type="button" -->
	<!-- 				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" -->
	<!-- 				aria-controls="navbarSupportedContent" aria-expanded="false" -->
	<!-- 				aria-label="Toggle navigation"> -->
	<!-- 				<span class="navbar-toggler-icon"></span> -->
	<!-- 			</button> -->
	<!-- 			<div class="collapse navbar-collapse" id="navbarSupportedContent"> -->
	<!-- 				<ul class="navbar-nav me-auto mb-2 mb-lg-0"> -->
	<!-- 					<li class="nav-item float-right"><a class="nav-link" -->
	<!-- 						href="/auction/register">Create Auction</a></li> -->
	<!-- 					<li class="nav-item float-right"><a class="nav-link" href="#">Update -->
	<!-- 							Details</a></li> -->
	<!-- 				</ul> -->
	<%-- 				<form action="/logout" method="post"> --%>
	<!-- 					<button type="submit" class="btn btn-primary">Log Out</button> -->
	<%-- 				</form> --%>
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</nav> -->

	<main class="my-form">
		<div class="cotainer">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header">Enter Auction Details</div>
						<div class="card-body">
							<form name="my-form" action="http://localhost:8080/auction/data"
								method="post" enctype="multipart/form-data">
								<div class="form-group row">
									<label for="title"
										class="col-md-4 col-form-label text-md-right">Enter
										Name:</label>
									<div class="col-md-6">
										<input required="required" type="text" id="title"
											class="form-control" name="title">
									</div>
								</div>
								<div class="form-group row">
									<label for="description"
										class="col-md-4 col-form-label text-md-right">Description:</label>
									<div class="col-md-6">
										<input required="required" type="text" id="description"
											name="description" class="form-control">
									</div>
								</div>
								<div class="form-group row">
									<label for="startDate"
										class="col-md-4 col-form-label text-md-right"> Start
										Date:</label>
									<div class="col-md-6">
										<input required="required" type="date" id="startDate"
											class="form-control" name="startDate">
									</div>
								</div>
								<div class="form-group row">
									<label for="startTime"
										class="col-md-4 col-form-label text-md-right"> Start
										Time:</label>
									<div class="col-md-6 mt-2">
										<input required="required" type="time" id="startTime"
											name="startTime">
									</div>
								</div>
								<div class="form-group row">
									<label for="endDate"
										class="col-md-4 col-form-label text-md-right"> End
										Date:</label>
									<div class="col-md-6">
										<input required="required" type="date" id="endDate"
											class="form-control" name="endDate">
									</div>
								</div>
								<div class="form-group row">
									<label for="endTime"
										class="col-md-4 col-form-label text-md-right"> End
										Time:</label>
									<div class="col-md-6 mt-2">
										<input required="required" type="time" id="endime"
											name="endTime">
									</div>
								</div>

								<div class="form-group row">
									<label for="imagee"
										class="col-md-4 col-form-label text-md-right">Photo: </label>
									<div class="col-md-6 mt-2">
										<input required="required" id="imagee" type="file"
											name="imagee" />
									</div>
								</div>
								<div class="form-group row">
									<label for="selectedAuctioneer"
										class="col-md-4 col-form-label text-md-right">Auctioneer:
									</label>
									<div class="col-md-6 mt-2">
										<form:select required="required" path="listAuctioneers"
											id="selectedAuctioneer" name="selectedAuctioneer">
											<form:option value="${auctioneer.auctioneerId}"
												label="${auctioneer.name}" />
											<form:options items="${listAuctioneers}"
												itemValue="auctioneerId" itemLabel="name" />
										</form:select>
									</div>
								</div>
								<div class="col-md-6 offset-md-4">
									<button type="submit" class="btn btn-primary">Create</button>
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
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		function successAlert() {
			Swal.fire('Auction Created Successfully',
					'Now Add Lots Information', 'success')

		}
	</script>
</body>

</html>