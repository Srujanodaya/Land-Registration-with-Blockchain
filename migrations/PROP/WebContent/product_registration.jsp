<%@page import="com.megainfo.model.Product"%>
<%@page import="com.megainfo.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
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
					<a href="#" class="active list-group-item">Admin Options</a> <a
						href="${context }/admin/product/registration"
						class="list-group-item">Add Property</a> <a
						href="${context }/admin/view/products" class="list-group-item">View
						Property</a>
					<!-- 					<a href="#" class="list-group-item">Modify Product</a> 
					<a href="#" class="list-group-item">Delete Product</a> 
					<a href="#" class="list-group-item">View Order</a> 
					<a href="#" class="list-group-item">Analysis &amp; Graph</a> -->
				</div>
			</div>
			<div class="col-md-9">
				<c:choose>
					<c:when test="${not empty msgSuccess}">
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div class="alert text-center">
							<strong>${msgSuccess }</strong> <br> <br> <a
								href="${context }/admin/product/registration"
								style="text-shadow: none;" class="btn btn-success btn-sm">Click
								here to Add New Property</a>
						</div>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</c:when>
					<c:otherwise>
						<div class="row">
							<div class="col-md-12 text-center">
								<h2 class="text-info">PROPERTY REGISTRATION</h2>
								<h4 class="text-info">Add New Property into the Catalog</h4>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-offset-1 col-md-10">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Fill in the following details
										<%
									ProductDAO obj = new ProductDAO();
									Product product = new Product();
									product = obj.loadProductId();
									%>
									</div>
									<div class="panel-body">
										<form action="${context}/admin/product/registration.do"
											method="post" autocomplete="off">
											<input type="hidden" name="operation"
												value="product_registration">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label> Enter Property ID : * </label> <input type="text"
															name="product_id" class="form-control input-sm"
															placeholder="Property ID"
															value="<%=product.getProduct_id()%>" readonly="readonly">
														<span class="error-span"> ${errorExistingProductID }
														</span>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label> PIN Code : * </label> <input type="text"
															name="pin_code" class="form-control input-sm"
															placeholder="PIN Code"> <span class="error-span">
															${errorExistingProductID } </span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<div class="form-group">
														<label> Enter Product Name : * </label> <input type="text"
															name="product_name" class="form-control input-sm"
															onkeydown="return /[a-z]/i.test(event.key)"
															placeholder="Property Name">
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label> Select Product Category : * </label> <select
															name="product_category" class="form-control input-sm">
															<option value="">-- Select --</option>
															<option value="land">Land</option>
															<option value="site">Site</option>
															<option value="house">House</option>
														</select>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label> Enter Product Unit Price : * </label> <input
															type="text" name="product_unit_price"
															class="form-control input-sm"
															placeholder="Property Price">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label> Enter Property Description : * </label>
														<textarea name="product_description"
															class="form-control input-sm" rows="3"
															placeholder="Property Description [Maximum of 200 Characters]"></textarea>
													</div>
												</div>
											</div>
											<div class="row">
												<hr>
												<div class="col-md-12 text-center">
													<input type="submit" value="Register Property"
														class="btn btn-primary btn-sm"> <input
														type="reset" value="Reset Form Data"
														class="btn btn-default btn-sm">
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>
	<%@include file="footer.html"%>
	<!-- JQuery scripts -->
	<script src="${context }/assets/js/jquery-1.11.2.min.js"></script>

	<!-- Bootstrap Core scripts -->
	<script src="${context }/assets/js/bootstrap.min.js"></script>
</body>
</html>