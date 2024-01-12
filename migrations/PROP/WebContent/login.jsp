<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<!-- icons -->
<link rel="apple-touch-icon" href="${context }/assets/img/apple-touch-icon.png">
<link rel="shortcut icon" href="favicon.ico">

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
				<img alt="" src="${context }/images/logo.png" height="50" width="150">
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${context }/home">Home</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${context}/user/signup"><span
						class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li class="active"><a href="${context}/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<!-- /.navbar -->

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			
			<div class="col-md-8 col-md-offset-2 text-center">
				<h4 class="text-info">User Login</h4>
			</div>
			<br>
			<hr>
			<br>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<c:if test="${not empty message }">
					<div class="alert alert-warning text-center">
						${message }
					</div>
				</c:if>
				<div class="panel panel-primary">
					<div class="panel-heading">Enter Login Details</div>
					<div class="panel-body">
						<form action="${context }/login.do" method="post" autocomplete="off">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>
											Enter Username : *
										</label>
										<input type="text" name="username" class="form-control" placeholder="Username" required="required">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>
											Enter Password : *
										</label>
										<input type="password" name="password" class="form-control" placeholder="Password" required="required">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>
											Select User Type : *
										</label>
										<select name="user_type" required="required" class="form-control">
											<option value="">-- Select Type --</option>
											<option value="buyer">Buyer</option>
											<option value="seller">Seller</option>
										</select>
									</div>
								</div>
									
							</div>
							<div class="row">
								<hr>
								<div class="col-md-12 text-center">
									<input type="submit" value="Login" class="btn btn-primary btn-sm btn-block">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	<!-- /.row -->

	<%@include file="footer.html"%>
	<!-- /.container-fluid -->

	<!-- JQuery scripts -->
	<script src="${context }/assets/js/jquery-1.11.2.min.js"></script>

	<!-- Bootstrap Core scripts -->
	<script src="${context }/assets/js/bootstrap.min.js"></script>
</body>
</html>

