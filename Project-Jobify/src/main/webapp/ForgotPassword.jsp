<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-secondary">
		<!-- Heading aligned to the left -->
		<div
			class="d-flex justify-content-start align-items-center flex-grow-1">
			<h3 class="text-white m-0">JOBIFY</h3>
		</div>

	</nav>
	<p></p>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">Reset Password</div>
					<div class="card-body">
						<form action="forgot" method="post">
							<div class="form-group">
								<label for="email">Email address</label> 
								<input type="email"
									class="form-control" id="email" 
									placeholder="Enter Email Id" value="${empty email ? '' : email}" name="email">
									 <p>${otp}</p>
                           			 <p>${noOtp}</p>
                           			<p>${otpSent}</p>
							</div>
                            <button type="submit" class="btn btn-primary">Submit</button>	
                         	</br>			
                            </br>					
                        
                    
							<div class="form-group">
								<label for="otp">OTP</label> 
								<input type="otp" class="form-control" id="otp" name="otp"
									placeholder="Enter otp">
									<p>${otpVerificationError}</p>
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
							<div class="mt-3">
							
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS, Popper.js, and jQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<p></p>
	</br>
	</br>


	<footer class="bg-body-light text-center text-lg-start">
		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.05);">© 2023 Copyright

		</div>

	</footer>

</body>
</html>

