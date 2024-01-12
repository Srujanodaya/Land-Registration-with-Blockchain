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
				<%-- <div class="user-activity">
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
				</div> --%>
			</div>
			<!-- Container (Services Section) -->
			<div class="col-md-9">
				<div id="services">
					<c:if test="${not empty msgSuccess }">
						<c:if test="${msgSuccess eq 'product_removed' }">
							<div class="alert alert-info text-center">
								<h5>
									Property with ID : ${removed_product_id } is removed from the Cart.
								</h5><br>
								<a href="${context }/user/view/products" class="btn btn-primary btn-sm" style="text-shadow: none;">
									Go to Products
								</a>
							</div>
						</c:if>
						<c:if test="${msgSuccess eq 'order_success' }">
							<div class="alert alert-info text-center">
								Property with ID : ${ordered_product_id } is ordered successfully.
							</div>
						</c:if>	
					</c:if>
					<c:if test="${not empty msgError }">
						<c:if test="${msgError eq 'no_session' }">
							<div class="alert alert-warning text-center">
								<h5>
									Your session is Expired! 
									<a href="${context }/login">Click Here</a> to Login Again!
								</h5>
							</div>
						</c:if>
					</c:if>
					<h2 class="text-info text-center">CART</h2>
					<h4 class="text-info text-center">Properties in the User Cart</h4>
					<div class="row">
						<hr>
						<c:forEach items="${productDetails }" var="pd">
							<div class="col-md-6">
								<div class="cart-product-wrapper">
									<div class="cart-product">
										<div class="row">
											<div class="col-xs-10">
												<label>
													Property ID : 
												</label>
												<strong class="h4">${pd.product_id }</strong>
											</div>
											<div class="col-xs-2">
												<a href="${context }/user/cart/remove/product?product_id=${pd.product_id}&operation=remove_from_cart" title="Remove from Cart">
													<span class="glyphicon glyphicon-trash text-warning text-right"></span>
												</a>
											</div>
										</div>
												
										<label>Name : </label>
										<strong class="h4">${pd.product_name }</strong><br>
										<label>Category : </label>
										<strong class="h4">${pd.product_category }</strong><br>
										<label>Price : Rs.</label>
										<strong class="h4">${pd.product_unit_price }</strong><br>
										<label>Product Description : </label>
										<strong>${pd.product_description }</strong>
									</div>	
									<form action="${context }/user/cart/product/order-now" class="form-inline" method="get">
										<div class="row">
											<div class="col-xs-12 text-center">
												<hr style="margin: 8px 0px;">
												<div class="form-group">
													<label>
														Quantity :
													</label>
													<input type="number" name="ordered_product_units" class="form-control" min="1" max="1" required="required">
													<input type="hidden" name="ordered_product_id" value="${pd.product_id }">
													<input type="hidden" name="unit_price" value="${pd.product_unit_price }">
													<input type="hidden" name="operation" value="order_product">
													<input type="submit" value="Order Now" class="btn btn-warning">
												</div>
											</div>
										</div>
									</form>
									
								</div>
								
							</div>
						</c:forEach>
						<c:if test="${empty productDetails }">
							<h5 class="text-warning text-center">You do not have any items in the Cart.</h5>
						</c:if>
					</div>
					
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>