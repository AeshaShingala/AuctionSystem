<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<!doctype html>
	<html lang="en">

	<head>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"
			id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Fonts -->
		<link rel="dns-prefetch" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">



		<link rel="icon" href="Favicon.png">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<!-- <link rel="stylesheet" href="/static/css/style.css"> -->


		<title>Auction House Registration</title>
	</head>

	<body>

		<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
			<div class="container">
				<a class="navbar-brand">Create Catalog</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</nav>

		<main class="my-form">
			<div class="cotainer">
				<div class="row justify-content-center">
					<div class="col-md-8">
						<div class="card">
							<div class="card-header">${auction.title} Hello World </div>
							<div class="card-body">
								<form name="my-form" enctype="multipart/form-data"
									action="http://localhost:8080/lots/data" method="post">
									<div class="form-group row">
										<div class="col-md-1 text-center">
											<label for="auctionId">Catalog</label>
										</div>
										<div class="col-md-11">

											<input required="required" type="text" id="auctionId" class="form-control"
												name="auctionId" value=${auction.auctionId } readonly="readonly">

											<label class="col-md-4 col-form-label text-md-right">Category</label>
											<form:select required="required" path="listCategories" id="selectedCategory"
												name="selectedCategory">
												<div class="col-md-6 center">
													<form:option value="-" label="--Please Select Category--" />
													<form:options items="${listCategories}" itemValue="categoryId"
														itemLabel="category" />
												</div>
											</form:select>
										</div>

									</div>

									<div id="repeat" class="card">
										<div class="card-header text-center ">
											Lot 1
											<!-- <button class="btn btn-danger float-right">Delete Lot</button> -->
										</div>
										<div class="card-body">
											<div class="form-group row">
												<div class="col-sm-4 mb-3">
													<label for="title">Title
													</label>
													<input required="required" type="text" id="title"
														class="form-control" name="title">
												</div>

												<div class="col-sm-8 mb-3">
													<label for="description">Description
													</label>
													<input required="required" type="text" id="description"
														class="form-control" name="description">
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-4 mb-3">
													<label for="quantity">Quantity</label>

													<input required="required" type="number" id="quantity"
														name="quantity" class="form-control">

												</div>
												<div class="col-sm-4 mb-3">
													<label for="basePrice">BasePrice</label>
													<input required="required" type="number" id="basePrice"
														name="basePrice" class="form-control">
												</div>
												<div class="col-sm-4 mb-3">
													<label>Photo:
													</label> <input required="required" type="file" name="imagee" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-6 p-auto">
											<button type="submit" class="btn btn-primary">Register</button>
										</div>
										<div class="col-sm-6 p-auto">
											<button type="button" id="add" class="btn btn-primary">Add Another
												Lot</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<script src="/js/jquery-3.3.1.min.js"></script>
		<script src="/js/custom.js"></script>

		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous"></script>

	</body>

	</html>