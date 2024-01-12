<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }"/>

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
				<%-- <div class="user-activity">
					<div class="panel panel-info">
						<div class="panel-heading">
							Filter Property
						</div>
						<form action="${context }/admin/view/products/filter" method="get">
							<div class="panel-body">	
							
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>Category : </label>
											<select name="category" class="form-control input-sm">
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
											<label>Price : </label>
											<select name="price" class="form-control input-sm">
												<option value="">-- Select --</option>
											</select>
										</div>
									</div>
								</div>	
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label>Property Name : </label>
											<input type="text" name="product" class="form-control input-sm" placeholder="Property Name">
										</div>
									</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-xs-12">
										<input type="submit" value="Filter Properties" class="btn btn-primary btn-block btn-sm">
									</div>
								</div>	
							</div>
						</form>
					</div>
				</div> --%>
			</div>
			<!-- Container (Services Section) -->
			<div class="col-md-9">
				<div id="services">
					<c:if test="${not empty msgSuccess }">
						<div class="alert alert-info">
							${msgSuccess }
						</div>
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
					<h2 class="text-info text-center">Properties</h2>
					<h4 class="text-info text-center">Property available in the Catalog</h4>
					<div class="row">
						<hr>
						<c:if test="${not empty filter_category }">
							<div class="col-md-3 filter-info">
								Filter Category : <strong> ${filter_category }</strong>
							</div>
						</c:if>
						<c:if test="${not empty filter_product }">
							<div class="col-md-3 filter-info">
								Filter Property Name : <strong> ${filter_product }</strong>
							</div>
						</c:if>
						<div class="clearfix">
						</div>
						<c:forEach items="${productDetails }" var="pd">
							<div class="col-md-4">
								<div class="cart-product-wrapper">
									<div class="cart-product">
										<label>Property ID : </label>
										<strong class="h5">${pd.product_id }</strong><br>
										<label>Name : </label>
										<strong class="h5">${pd.product_name }</strong><br>
										<label>Category : </label>
										<strong class="h5">${pd.product_category }</strong><br>
										<label>Unit Price : Rs.</label>
										<strong class="h5">${pd.product_unit_price }</strong><br>
										<%-- <label style="margin: 0;">Description : </label>
										<strong class="h5">${pd.product_description }</strong> --%>
									</div>	
									<div class="row">
										<hr style="margin: 7px 4px;">
										<div class="col-xs-12 text-center">
											<a href="${context }/admin/modify/product?product_id=${pd.product_id}&operation=view_details" 
												class="btn btn-primary btn-sm" title="Modify Product Details">
												<span class="glyphicon glyphicon-edit"></span>
											</a>
											<a href="${context }/admin/delete/product?product_id=${pd.product_id}" 
												class="btn btn-danger btn-sm" title="Remove Product">
												<span class="glyphicon glyphicon-trash"></span>
											</a>
										</div>
									</div>
											
								</div>
								
							</div>
						</c:forEach>
						<c:if test="${empty productDetails }">
							<h5 class="text-warning text-center">There are no property in the Catalog!</h5>
						</c:if>
					</div>
					<div class="row">
						<div class="col-md-12">
							<c:if test="${currentPage != 1 }">
								<a href="${context }/admin/view/products?page=${currentPage - 1}" class="btn btn-sm btn-default">Previous</a>
							</c:if>
							<c:forEach begin="1" end="${noOfPages }" var="i">
								<c:choose>
				                    <c:when test="${currentPage eq i}">
				                        <span class="text-primary">&nbsp;${i}&nbsp;</span>
				                    </c:when>
				                    <c:otherwise>
				                        <a href="${context }/admin/view/products?page=${i}" class="btn btn-sm btn-default">${i}</a>
				                    </c:otherwise>
				                </c:choose>
							</c:forEach>
							<c:if test="${currentPage lt noOfPages}">
						        <a href="${context }/admin/view/products?page=${currentPage + 1}" class="btn btn-sm btn-default">Next</a>
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