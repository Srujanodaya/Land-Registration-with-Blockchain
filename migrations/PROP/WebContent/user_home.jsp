<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
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
	<%@include file="user_header.jsp"%>

	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<!-- list group -->
				<div class="list-group margin-b-3">
					<a href="#" class="active list-group-item">User Options</a> 
					<a href="${context }/user/view/products" class="list-group-item">View Properties</a> 
					<a href="${context }/user/view/orders" class="list-group-item">View Order</a> 
					<a href="${context }/user/view/cart" class="list-group-item">View Cart</a> 
				</div>
				<div class="user-activity">
					<div class="panel panel-default">
						<div class="panel-heading">
							User Activities
						</div>
						<div class="panel-body">	
							<h5 class="text-info">Welcome, ${username }</h5>
							<hr style="margin: 10px 0px;">
							<h5 class="text-info">Last Login On : </h5>
							<h5 class="text-warning">${last_login }</h5>
						</div>
					</div>
				</div>
			</div>
			<!-- Container (Services Section) -->
			<div class="col-md-9">
				<div id="user-services" class="text-center">
					<c:if test="${not empty msgSuccess }">
						<div class="alert alert-info">
							${msgSuccess }
						</div>
					</c:if>
					<c:if test="${not empty msgError }">
						<c:if test="${msgError eq 'no_session' }">
							<div class="alert alert-warning">
								<h5>
									Your session is Expired! 
									<a href="${context }/login">Click Here</a> to Login Again!
								</h5>
							</div>
						</c:if>
						<c:if test="${msgError eq 'product_existing' }">
							<div class="alert alert-warning">
								<h5>
									Property with ID : ${existing_product_id } already exists in your Cart! 
								</h5>
							</div>
						</c:if>
					</c:if>
						
					<h2 class="text-info">SERVICES</h2>
					<h4 class="text-info">Various User Services</h4>
					<div class="row">
						<hr>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-search logo-small"></span>
							<h4>
								<a href="${context }/user/view/products">VIEW PROPERTIES</a>
							</h4>
							<p>View All Properties of the Catalog</p>
						</div>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-envelope logo-small"></span>
							<h4>
								<a href="${context }/user/view/orders">VIEW ORDERS</a>
							</h4>
							<p>View Orders Made by You</p>
						</div>
						<div class="col-sm-4 operation-thumbs">
							<span class="glyphicon glyphicon-shopping-cart logo-small"></span>
							<h4>
								<a href="${context }/user/view/cart">VIEW CART</a>
							</h4>
							<p>View Properties in the Cart</p>
						</div>
					</div>
					
				</div>
				<div class="row">
					<hr>
					<div class="col-md-12 text-center">
						<h2 class="text-info">PRODUCTS</h2>
						<h4 class="text-info">Recently Added Products</h4>
					</div>
					<div class="col-md-12">
						<div class="row">
							<hr>
							<c:forEach items="${productDetails }" var="pd">
								<div class="col-sm-4 recent-products-thumbs-wrapper">
									<div class="recent-products-thumbs">
										<label>Property ID : </label>
										<strong class="h5">${pd.product_id }</strong><br>
										<label>Name : </label>
										<strong class="h5">${pd.product_name }</strong><br>
										<label>Category : </label>
										<strong class="h5">${pd.product_category }</strong><br>
										<label>Price : Rs.</label>
										<strong class="h5">${pd.product_unit_price }</strong><br>
										<%-- <label>Description : </label>
										<strong class="h5">${pd.product_description }</strong> --%>
									</div>
									
									<%-- <a href="${context }/user/product/add/cart?product_id=${pd.product_id}&operation=add_to_cart" class="btn btn-warning btn-block btn-sm">
										Add to Cart 
										<span class="glyphicon glyphicon-shopping-cart"></span>
									</a> --%>
									<a href="${context }/user/view/product/details?product_id=${pd.product_id}&operation=home_view_details" 
										class="btn btn-warning btn-block btn-sm">View Details</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>