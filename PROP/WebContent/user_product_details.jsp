<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
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
				
			</div>
			<!-- Container (Services Section) -->
			<div class="col-md-9">
				<div id="services">
					<c:if test="${not empty msgSuccess }">
						<c:if test="${msgSuccess eq 'add_to_cart_success' }">
							<div class="alert alert-info text-center">
								Property with ID : ${added_product_id } is added to the Cart Successfully.
							</div>
						</c:if>
						<c:if test="${msgSuccess eq 'order_success' }">
							<div class="alert alert-info text-center">
								Product with ID : ${ordered_product_id } is ordered successfully.
							</div>
						</c:if>	
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
							<div class="alert alert-warning text-center">
								<h5>
									Property with ID : ${existing_product_id } already exists in your Cart! 
								</h5><br>
								<a href="${context }/user/view/cart" class="btn btn-primary btn-sm" style="text-shadow: none;">
									Go To Cart
									<span class="glyphicon glyphicon-shopping-cart"></span>
								</a>
							</div>
						</c:if>
					</c:if>
					<h2 class="text-info text-center">PROPERTY DETAILS</h2>
					<h4 class="text-info text-center">Details of the Property Viewed</h4>
					<div class="row">
						<hr>
						<c:set var="pd" value="${productDetails }"></c:set>
						<div class="col-md-12">
							<div class="cart-product-wrapper">
								<div class="cart-product">
									<div class="row">
										<div class="col-md-4">
											<label>
												Property ID : 
											</label>
										</div>
										<div class="col-md-8">
											<strong class="h4">${pd.product_id }</strong>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<label>Name : </label>
										</div>
										<div class="col-md-8">
											<strong class="h4">${pd.product_name }</strong>
										</div>
									</div>	
									<div class="row">
										<div class="col-md-4">
											<label>Category : </label>
										</div>
										<div class="col-md-8">
											<strong class="h4">${pd.product_category }</strong>
										</div>
									</div>		
									<div class="row">
										<div class="col-md-4">
											<label>Price : Rs. </label>
										</div>
										<div class="col-md-8">
											<strong class="h4">${pd.product_unit_price }</strong>
										</div>
									</div>	
									<div class="row">
										<div class="col-md-4">
											<label>Property Description : </label>
										</div>
										<div class="col-md-8">
											<strong class="h4">${pd.product_description }</strong>
										</div>
									</div>			
								</div>	
								<form action="${context }/user/cart/product/order-now" class="form-inline" method="get">
									<div class="row">
										<div class="col-xs-12 text-center">
											<hr style="margin: 8px 0px;">
											<div class="form-group">
												<%-- <label>
													Quantity :
												</label>
												<input type="number" name="ordered_product_units" class="form-control" min="1" required="required">
												<input type="hidden" name="ordered_product_id" value="${pd.product_id }">
												<input type="hidden" name="unit_price" value="${pd.product_unit_price }">
												<input type="hidden" name="operation" value="pd_order_product">
												<input type="submit" value="Order Now" class="btn btn-warning"> --%>
												<a href="${context }/user/product/add/cart?product_id=${pd.product_id}&operation=pd_add_to_cart" class="btn btn-warning">
													Add to Cart 
													<span class="glyphicon glyphicon-shopping-cart"></span>
												</a>
											</div>
										</div>
									</div>
								</form>
								
							</div>
							
						</div>
						<c:if test="${empty productDetails }">
							<h5 class="text-warning text-center">Error Occurred. Failed to load product details.</h5>
						</c:if>
					</div>
					
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>