<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Property</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="Description" lang="en" content="ADD SITE DESCRIPTION">
<meta name="author" content="ADD AUTHOR INFORMATION">
<meta name="robots" content="index, follow">
<c:set var="context" value="${pageContext.request.contextPath }"></c:set>
<!-- icons -->
<link rel="apple-touch-icon"
	href="${context }/assets/img/apple-touch-icon.png">
<link rel="shortcut icon" href="${context }/favicon.ico">

<!-- Bootstrap Core CSS file -->
<link rel="stylesheet" href="${context }/assets/css/bootstrap.min.css">

<!-- Override CSS file - add your own CSS rules -->
<link rel="stylesheet" href="${context }/css/style.css">

<!-- Conditional comment containing JS files for IE6 - 8 -->
<!--[if lt IE 9]>
			<script src="assets/js/html5.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="col-md-2">
				<img alt="" src="${context }/images/logo.png" height="50"
					width="150">
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${context }/home">Home</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${context}/user/signup"><span
						class="glyphicon glyphicon-user active"></span> Sign Up</a></li>
				<li><a href="${context }/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<!-- /.navbar -->

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<c:choose>
					<c:when test="${not empty msgUserSignUp}">
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div class="alert text-center">
							<strong>${msgUserSignUp }</strong> <br> <br> <a
								href="${context }/login" class="btn btn-success btn-sm">Click
								here to Login</a>
						</div>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</c:when>
					<c:otherwise>
						<div class="row">
							<div class="col-md-8 col-md-offset-2 text-center">
								<h4 class="text-info">New User Sign Up</h4>
							</div>
							<br>
							<hr>
							<br>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">Fill in the following details</div>
							<div class="panel-body">
								<form action="${context}/user/signup.do" method="post"
									autocomplete="off">
									<input type="hidden" name="operation" value="user_signup">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Enter Username : * </label> <input type="text"
													name="username" class="form-control input-sm"
													placeholder="Username" required="required"> <span
													class="error-span"> ${errorExistingUsername } </span>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Password : * </label> <input type="password"
													name="password" class="form-control input-sm"
													placeholder="Password" required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> Confirm Password : * </label> <input type="password"
													name="confirm_password" class="form-control input-sm"
													placeholder="Confirm Password" required="required">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Firstname : * </label> <input type="text"
													name="firstname" class="form-control input-sm"
													placeholder="Firstname" required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> Lastname : * </label> <input type="text"
													name="lastname" class="form-control input-sm"
													placeholder="Lastname" required="required">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Date of Birth : * </label> <input type="date"
													name="dob" class="form-control input-sm"
													required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> Gender : * </label> <select name="gender"
													required="required" class="form-control input-sm">
													<option value="">-- Select--</option>
													<option value="Male">Male</option>
													<option value="Female">Female</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Phone Number : * </label> <input type="text"
													name="phone" class="form-control input-sm"
													placeholder="Phone" required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> Email ID : * </label> <input type="email"
													name="email" class="form-control input-sm"
													placeholder="Email" required="required">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Address : * </label> <input type="text"
													name="address" class="form-control input-sm"
													placeholder="Address" required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> City : * </label> <input type="text" name="city"
													class="form-control input-sm" placeholder="City"
													required="required">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> State : * </label> <input type="text" name="state"
													class="form-control input-sm" placeholder="State"
													required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> Country : * </label> <input type="text"
													name="country" class="form-control input-sm"
													placeholder="Country" required="required">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label> Zipcode : * </label> <input type="text"
													name="zipcode" class="form-control input-sm"
													placeholder="Zipcode" required="required">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> User Type : * </label> <select name="user_type"
													required="required" class="form-control input-sm">
													<option value="">-- Select--</option>
													<option value="buyer">Buyer</option>
													<option value="seller">Seller</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="text-center">
											<hr>
											<input type="submit" class="btn btn-primary btn-sm"
												value="Sign Up"> <input type="reset"
												class="btn btn-default btn-sm" value="Reset">
										</div>
									</div>
								</form>
							</div>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>

	<%@include file="footer.html"%>
	<!-- /.container-fluid -->

	<!-- JQuery scripts -->
	<script src="${context }/assets/js/jquery-1.11.2.min.js"></script>

	<!-- Bootstrap Core scripts -->
	<script src="${context }/assets/js/bootstrap.min.js"></script>
</body>
</html>

