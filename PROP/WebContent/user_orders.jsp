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
						<c:if test="${msgSuccess eq 'order_cancelled' }">
							<div class="alert alert-info text-center">
								Product with ID : ${cancelled_product_id } is removed from the Cart.
							</div>
						</c:if>
						<c:if test="${msgSuccess eq 'order_success' }">
							<div class="alert alert-info text-center">
								Product with ID : ${ordered_product_id } is added for Payment Checkout.
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
					<h2 class="text-info text-center">ORDERS</h2>
					<h4 class="text-info text-center">Ordered Properties ready for Payment Checkout</h4>
					<div class="row">
						<hr>
						<c:forEach items="${orderDetails }" var="od">
							<div class="col-md-12">	
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="row">
											<div class="col-md-3">
												<label>Order ID : </label>
												${od.order_id }
											</div>
											<div class="col-md-3">
												<label>Property ID : </label>
												${od.ordered_product_id }
											</div>
											<div class="col-md-3">
												<label>Ordered Units : </label>
												${od.ordered_product_units }
											</div>
											<div class="col-md-3">
												<label>Total Amount (Rs.) : </label>
												${od.total_order_amount }
											</div>
											<div class="col-md-3">
												<label>Order Date : </label>
												${od.ordered_on }
											</div>
											<div class="col-md-3">
												<label>Order Status : </label>
												${od.order_status }
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<c:if test="${empty orderDetails }">
							<h5 class="text-warning text-center">You do not have any orders placed.</h5>
							<div class="col-md-12 text-center">
								<br>
								<a href="${context }/user/view/cart" class="btn btn-primary btn-sm" style="text-shadow: none;">
									Go To Cart
									<span class="glyphicon glyphicon-shopping-cart"></span>
								</a>
							</div>
						</c:if>
					</div>
					
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>