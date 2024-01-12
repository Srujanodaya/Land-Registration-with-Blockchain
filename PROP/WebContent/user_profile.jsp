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
					<a href="${context }/user/view/products" class="list-group-item">View Products</a> 
					<a href="#" class="list-group-item">View Order</a> 
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
						<div class="alert alert-info text-center">
							${msgSuccess }
						</div>
					</c:if>
					<c:choose>
						<c:when test="${not empty msgError }">
							<div class="alert alert-warning text-center">
								<h5>
									Your session is Expired! 
									<a href="${context }/login">Click Here</a> to Login Again!
								</h5>
							</div>
						</c:when>
						<c:otherwise>
							<h2 class="text-info text-center">USER PROFILE</h2>
							<h4 class="text-info text-center">Details of your profile</h4>
							<div class="row">
								<hr>
								<div class="col-md-8 col-md-offset-2">
									<div class="panel panel-info">
										<div class="panel-body">
											<div class="row">
												<div class="col-md-12 text-center">
													<span class="glyphicon glyphicon-user text-primary logo-small"></span><br>
													<label>Username : </label>
													<strong class="h5">
														${userDetails.username }
													</strong><br>
													<a href="${context }/user/edit/profile?username=${userDetails.username}" class="text-danger h6">Edit Profile</a>
												</div>
											</div>
											<div class="row">
												<hr style="margin: 6px 0px;">
												<div class="col-md-4">
													<label>Name : </label>
													<strong class="h5">
														${userDetails.firstname } ${userDetails.lastname }
													</strong>
												</div>
												<div class="col-md-4">
													<label>Date of Birth : </label>
													<strong class="h5">
														${userDetails.dob }
													</strong>
												</div>
												<div class="col-md-4">
													<label>Gender : </label>
													<strong class="h5">
														${userDetails.gender }
													</strong>
												</div>
											</div>		
											<div class="row">
												<hr style="margin: 6px 0px;">
												<div class="col-md-4">
													<label>Phone : </label>
													<strong class="h5">
														${userDetails.phone }
													</strong>
												</div>
												<div class="col-md-4">
													<label>Email : </label>
													<strong class="h5">
														${userDetails.email }
													</strong>
												</div>
											</div>		
											<div class="row">
												<hr style="margin: 6px 0px;">
												<div class="col-md-4">
													<label>Address : </label>
													<strong class="h5">
														${userDetails.address }
													</strong>
												</div>
												<div class="col-md-4">
													<label>City : </label>
													<strong class="h5">
														${userDetails.city }
													</strong>
												</div>
												<div class="col-md-4">
													<label>State : </label>
													<strong class="h5">
														${userDetails.state }
													</strong>
												</div>
											</div>	
											<div class="row">
												<div class="col-md-4">
													<label>Country : </label>
													<strong class="h5">
														${userDetails.country }
													</strong>
												</div>
												<div class="col-md-4">
													<label>Zipcode : </label>
													<strong class="h5">
														${userDetails.zipcode }
													</strong>
												</div>
											</div>				
										</div>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
							
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>