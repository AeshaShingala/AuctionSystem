    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="utf-8">
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
<link rel="stylesheet" href="static/css/style.css">

<title>Lot Registration</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
		<div class="container">
			<a class="navbar-brand">Create Catalog</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
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
						<div class="card-header">Add lots to catalog</div>
						<div class="card-body">
							<form name="my-form" enctype="multipart/form-data"
								action="http://localhost:8080/lot/data" method="post">
								<div class="form-group row">
									<h5>${auction.description}</h5>
									<br>
									<div class="col-md-8">
										<label for="auctionId"
											class="col-md-4 col-form-label text-md-right"></label> <input
											required="required" readonly="readonly" type="text"
											id="auctionId" class="form-control" name="auctionId"
											value=${auction.auctionId }>
									</div>
								</div>
								<div>
									<label class="col-md-4 col-form-label text-md-right">Category</label>
									<form:select required="required" path="listCategories"
										id="selectedCategory" name="selectedCategory">
										<div class="col-md-6 center">
											<form:option value="-" label="--Please Select Category--" />
											<form:options items="${listCategories}"
												itemValue="categoryId" itemLabel="category" />
										</div>
									</form:select>
								</div>

								<div id="repeat">
									<div class="form-group row">
										<label for="name"
				    							class="col-md-4 col-form-label text-md-right">Title </label>
										<div class="col-md-6">
											<input required="required" type="text" id="title"
												class="form-control" name="title">
										</div>
									</div>
									<div class="form-group row">
										<label for="name"
											class="col-md-4 col-form-label text-md-right">Description
										</label>
										<div class="col-md-6">
											<input required="required" type="text" id="description"
												class="form-control" name="description">
										</div>
									</div>
									<div class="form-group row">
										<label for="contact"
											class="col-md-4 col-form-label text-md-right">Quantity</label>
										<div class="col-md-6">
											<input required="required" type="number" id="quantity"
												name="quantity" class="form-control">
										</div>
									</div>
									<div class="form-group row">
										<label for="contact"
											class="col-md-4 col-form-label text-md-right">BasePrice</label>
										<div class="col-md-6">
											<input required="required" type="number" id="basePrice"
												name="basePrice" class="form-control">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-md-4 col-form-label text-md-right">Photo:
										</label> <input required="required" type="file" name="imagee" />
									</div>

									<div class="col-md-6 offset-md-4">
										<button type="submit" class="btn btn-primary">Register</button>
									</div>
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
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>