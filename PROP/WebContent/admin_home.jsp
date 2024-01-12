<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="${context }/css/megastyles.css">
<!-- Bootstrap Core CSS file -->
<link rel="stylesheet" href="${context }/assets/css/bootstrap.min.css">

<!-- Override CSS file - add your own CSS rules -->
<link rel="stylesheet" href="${context }/css/style.css">

<!-- Conditional comment containing JS files for IE6 - 8 -->
<!--[if lt IE 9]>
			<script src="assets/js/html5.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.logo-small {
	color: #045FB4;
	font-size: 50px;
}

h4 {
	font-size: 19px;
	line-height: 1.375em;
	color: #303030;
	font-weight: 400;
}
</style>
</head>
<body>
	<!-- Navigation -->
	<%@include file="admin_header.jsp"%>

	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<!-- list group -->
				<div class="list-group margin-b-3">
					<a href="#" class="active list-group-item">Admin Options</a> 
					<a href="${context }/admin/product/registration" class="list-group-item">Add Property </a> 
					<a href="${context }/admin/view/products" class="list-group-item">View Property </a> 
					<!-- <a href="#" class="list-group-item">Modify Product</a> 
					<a href="#" class="list-group-item">Delete Product</a>  -->
<!-- 					<a href="#" class="list-group-item">View Order</a> 
					<a href="#" class="list-group-item">Analysis &amp; Graph</a> -->
				</div>
			</div>
			<!-- Container (Services Section) -->
			<div class="col-md-9">
				<div id="services" class="text-center">
					<h2 class="text-info">SERVICES</h2>
					<h4 class="text-info">Various Administrator Services</h4>
					<div class="row">
						<hr>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-plus logo-small"></span>
							<h4>
								<a href="${context }/admin/product/registration">ADD Property </a>
							</h4>
							<p>Admin can add the new Property ..</p>
						</div>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-search logo-small"></span>
							<h4>
								<a href="${context }/admin/view/products">VIEW Property </a>
							</h4>
							<p>Admin List all Property ..</p>
						</div>
						<!-- <div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-pencil logo-small"></span>
							<h4>
								<a href="">MODIFY PRODUCT</a>
							</h4>
							<p>Admin Edit products..</p>
						</div>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-remove-sign logo-small"></span>
							<h4>
								<a href="">DELETE PRODUCT</a>
							</h4>
							<p>Admin Remove products..</p>
						</div> -->
<!-- 						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-envelope logo-small"></span>
							<h4>
								<a href="">VIEW ORDERS</a>
							</h4>
							<p>Admin List Orders..</p>
						</div>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-tasks logo-small"></span>
							<h4>
								<a href="">ANALYSIS &amp; GRAPH</a>
							</h4>
							<p>Analysis and Prediction graph..</p>
						</div> -->
					</div>
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>