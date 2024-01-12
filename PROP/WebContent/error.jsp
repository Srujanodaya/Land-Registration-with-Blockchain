<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Rating Prediction</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="Description" lang="en" content="ADD SITE DESCRIPTION">
<meta name="author" content="ADD AUTHOR INFORMATION">
<meta name="robots" content="index, follow">
<c:set var="context" value="${pageContext.request.contextPath }"></c:set>
<!-- icons -->
<link rel="apple-touch-icon" href="${context }/assets/img/apple-touch-icon.png">
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
				<img alt="" src="${context }/images/logo.pmg" height="50" width="150">
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${context}/user/signup"><span
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
				<div class="jumbotron text-center">
					<h2>Page Not Found</h2>
				</div>
						
			</div>
		</div>
	</div>
	
	<%-- <%@include file="footer.html"%> --%>
	<!-- /.container-fluid -->

	<!-- JQuery scripts -->
	<script src="${context }/assets/js/jquery-1.11.2.min.js"></script>

	<!-- Bootstrap Core scripts -->
	<script src="${context }/assets/js/bootstrap.min.js"></script>
</body>
</html>

