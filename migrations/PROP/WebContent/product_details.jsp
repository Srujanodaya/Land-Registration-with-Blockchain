<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Properties</title>
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
					<a href="#" class="active list-group-item">User Options</a> <a
						href="${context }/user/view/products" class="list-group-item">View
						Properties</a> <a href="${context }/user/view/orders"
						class="list-group-item">View Order</a> <a
						href="${context }/user/view/cart" class="list-group-item">View
						Cart</a>
				</div>
				<div class="user-activity">
					<div class="panel panel-info">
						<div class="panel-heading">Filter All Products</div>
						<form action="${context }/user/view/products/filter" method="get">
							<div class="panel-body">

								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>Category : </label> <select name="category"
												class="form-control input-sm">
												<option value="">-- Select --</option>
												<option value="land">Land</option>
												<option value="site">Site</option>
												<option value="house">House</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>Property Name : </label> <input type="text"
												name="product" class="form-control input-sm"
												placeholder="Property Name">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>PIN Code : </label> <input type="text" name="pin_code"
												class="form-control input-sm" placeholder="PIN Code">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<!-- <label>Price : </label>  -->
											<input type="hidden" name="price"
												class="form-control input-sm" placeholder="Price">
											<!-- <select name="price"
												class="form-control input-sm">
												<option value="">-- Select --</option>
											</select> -->
										</div>
									</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-xs-12">
										<input type="submit" value="Filter Products"
											class="btn btn-primary btn-block btn-sm">
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- Container (Services Section) -->
			<div class="col-md-9">
				<div id="services">
					<c:if test="${not empty msgSuccess }">
						<div class="alert alert-info">${msgSuccess }</div>
					</c:if>
					<c:if test="${not empty msgError }">
						<c:if test="${msgError eq 'no_session' }">
							<div class="alert alert-warning text-center">
								<h5>
									Your session is Expired! <a href="${context }/login">Click
										Here</a> to Login Again!
								</h5>
							</div>
						</c:if>
						<c:if test="${msgError eq 'product_existing' }">
							<div class="alert alert-warning text-center">
								<h5>Product with ID : ${existing_product_id } already
									exists in your Cart!</h5>
								<br> <a href="${context }/user/view/cart"
									class="btn btn-primary btn-sm" style="text-shadow: none;">
									Go To Cart <span class="glyphicon glyphicon-shopping-cart"></span>
								</a>
							</div>
						</c:if>
					</c:if>
					<h2 class="text-info text-center">PROPERTIES</h2>
					<h4 class="text-info text-center">Properties available for
						Purchases</h4>
					<div class="row">
						<hr>
						<c:if test="${not empty filter_category }">
							<div class="col-md-3 filter-info">
								Filter Category : <strong> ${filter_category }</strong>
							</div>
						</c:if>
						<c:if test="${not empty filter_product }">
							<div class="col-md-3 filter-info">
								Filter Product Name : <strong> ${filter_product}</strong>
							</div>
						</c:if>
						<c:if test="${not empty filter_pin }">
							<div class="col-md-3 filter-info">
								Filter Product With Pin : <strong> ${filter_pin}</strong>
							</div>
						</c:if>
						<div class="clearfix"></div>
						<c:forEach items="${productDetails }" var="pd">
							<div class="col-md-4">
								<div class="cart-product-wrapper">
									<div class="cart-product">
										<label>Property ID : </label> <strong class="h5">${pd.product_id }</strong><br>
										<label>Name : </label> <strong class="h5">${pd.product_name }</strong><br>
										<label>Category : </label> <strong class="h5">${pd.product_category }</strong><br>
										<label>Price : Rs.</label> <strong class="h5">${pd.product_unit_price }</strong><br>
										<label>PIN Code : </label> <strong class="h5">${pd.pin_code }</strong><br>
										<%-- <label style="margin: 0;">Description : </label>
										<strong class="h5">${pd.product_description }</strong> --%>
									</div>
									<a
										href="${context }/user/view/product/details?product_id=${pd.product_id}&operation=pd_view_details"
										class="btn btn-warning btn-block btn-sm"> View Details </a>
								</div>

							</div>
						</c:forEach>
						<c:if test="${empty productDetails }">
							<h5 class="text-warning text-center">There are no Properties
								in the Catalog!</h5>
						</c:if>
					</div>
					<div class="row">
						<div class="col-md-12">
							<c:if test="${currentPage != 1 }">
								<a href="${context }/user/view/products?page=${currentPage - 1}"
									class="btn btn-sm btn-default">Previous</a>
							</c:if>
							<c:forEach begin="1" end="${noOfPages }" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<span class="text-primary">&nbsp;${i}&nbsp;</span>
									</c:when>
									<c:otherwise>
										<a href="${context }/user/view/products?page=${i}"
											class="btn btn-sm btn-default">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${currentPage lt noOfPages}">
								<a href="${context }/user/view/products?page=${currentPage + 1}"
									class="btn btn-sm btn-default">Next</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>
	<%@include file="footer.html"%>
</body>
</html>