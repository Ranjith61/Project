<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<title>Jobify</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/style.css">

</head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- <navigation bar ->header> -->
</head>

<!-- <navigation bar ->header> -->
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-secondary">

		<!-- Heading aligned to the left -->
		<div
			class="d-flex justify-content-start align-items-center flex-grow-1">
			<h3 class="text-white m-0">JOBIFY</h3>
		</div>

	</nav>

	<!-- <section class="ftco-section"> -->
	<div style="margin-top: 40px;">

		<div class="row justify-content-center">
			<div class="col-md-12 col-lg-10">
				<div class="wrap d-md-flex">
					<div class="img" style="background-image: url(images/job.jpg);">
					</div>
					<div class="login-wrap p-4 p-md-5">
						<div class="d-flex">
							<div class="w-100">
								<h3 class="mb-4">Sign Up</h3>
								<h2>${saved}</h2>
							</div>
						</div>
						<form action="save" method="post" class="signin-form">
							<div class="form-group mb-3">
								<input type="text" name="name" class="form-control"
									placeholder="Name">
							</div>
							<p style="color: red">${nameInvalid}</p>
							<div class="form-group mb-3">
								<input type="email" name="email" class="form-control"
									placeholder="Email">
							</div>
							<p style="color: red">${emailInvalid}</p>
							<p style="color: red">${emailExists}</p>
							<div class="form-group mb-3">
								<input type="text" name="mobile" class="form-control"
									placeholder="MobileNo">
							</div>
							<p style="color: red">${mobileInvalid}</p>
							<div class="form-group mb-3">
								<input type="password" name="password" class="form-control"
									placeholder="Password">
							</div>
							<p style="color: red">${passwordInvalid}</p>
							<div class="form-group mb-3">
								<input type="password" name="confirmPass" class="form-control"
									placeholder="Confirm Password">
							</div>
							<p style="color: red">${conPasswordInvalid}</p>
							<!-- Default inline 1-->
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input"
									value="Job Seeker" id="defaultInline1" name="account"
									checked="checked"> <label class="custom-control-label"
									for="defaultInline1">Job Seeker</label>
							</div>

							<!-- Default inline 2-->
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input"
									value="Job Provider" id="defaultInline2" name="account">
								<label class="custom-control-label" for="defaultInline2">Job
									Provider</label>
							</div>
							<p>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all terms and conditions</label>
							</div>

							<div class="form-group form-button">
								<button type="Submit" class="form-submit btn btn-primary">SignUp</button>
								<a href="Login.jsp" class="form-submit btn btn-primary">Login</a>
							</div>

						</form>
						<p></p>


						</br>
					</div>

				</div>
			</div>
		</div>
	</div>

	</br>
	<p></p>

	<footer class="bg-body-light text-center text-lg-start">
		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.05);">© 2023 Copyright

		</div>

	</footer>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
</html>

