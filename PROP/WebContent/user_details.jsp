<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>User Details</title>
<meta charset="utf-8">
<!-- icons -->
<link rel="apple-touch-icon" href="assets/img/apple-touch-icon.png">
<!-- Bootstrap Core CSS file -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<!-- Override CSS file - add your own CSS rules -->
<link rel="stylesheet" href="assets/css/styles.css">

<!-- Conditional comment containing JS files for IE6 - 8 -->
<!--[if lt IE 9]>
			<script src="assets/js/html5.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>
<body>

	<!-- Navigation -->
	<%@include file="admin_header.jsp"%>
	<!-- /.navbar -->

	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3">
				<!-- list group -->
				<div class="list-group margin-b-3">
					<a href="#" class="active list-group-item">Admin Options</a> <a
						href="#" class="list-group-item">Add Property</a> <a href="#"
						class="list-group-item">View Property</a> <a href="#"
						class="list-group-item">Modify Property</a> <a href="#"
						class="list-group-item">Delete Property</a> <a href="#"
						class="list-group-item">View Order</a>
				</div>
				<!-- Panel -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">User Details</h4>
					</div>
					<div class="panel-body">
						<p>User details will be displayed along with User ID, Email, Phone Number which help Admin detailed information about users registration</p>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="panel panel-primary">
					<div class="panel-heading">User Details</div>
					<div class="panel-body" style="min-height: 405px;">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>User ID</th>
									<th>Dr. Name</th>
									<th>Email</th>
									<th>Phone</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>U0020</td>
									<td>Rose</td>
									<td>rose@gmail.com</td>
									<td>9785474541</td>
								</tr>
								<tr>
									<td>U0021</td>
									<td>Vivek</td>
									<td>vivek@yahoo.com</td>
									<td>9885477854</td>
								</tr>
								<tr>
									<td>U0022</td>
									<td>Ullas</td>
									<td>ullas@yahoo.com</td>
									<td>8344474552</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

			</div>

		</div>
		<!-- /.row -->

		<hr>
		<%@include file="footer.html"%>
	</div>
	<!-- /.container-fluid -->

	<!-- JQuery scripts -->
	<script src="assets/js/jquery-1.11.2.min.js"></script>

	<!-- Bootstrap Core scripts -->
	<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>

