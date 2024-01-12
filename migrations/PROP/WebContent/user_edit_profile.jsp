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
						<div class="alert alert-info">
							${msgSuccess }
						</div>
					</c:if>
					<c:if test="${not empty msgError }">
						<div class="alert alert-warning">
							<h5>
								Your session is Expired! 
								<a href="${context }/login">Click Here</a> to Login Again!
							</h5>
						</div>
					</c:if>
					<h2 class="text-info text-center">EDIT PROFILE</h2>
					<h4 class="text-info text-center">Update the details of your profile</h4>
					<div class="row">
						<hr>
						<div class="col-md-8 col-md-offset-2">
							<form action="${context }/user/edit/profile.do" method="post" autocomplete="off">
								<div class="panel panel-info">
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12 text-center">
												<span class="glyphicon glyphicon-user text-primary logo-small"></span><br>
												<h4 class="text-info">Edit Profile</h4>
											</div>
										</div>
										<div class="row">
											<hr style="margin: 6px 0px;">
											<div class="col-md-4">
												<div class="form-group">
													<label>Username : </label>
													<input type="text" name="username" class="form-control" readonly="readonly" value="${userDetails.username }">
												</div>
											</div>
										</div>
										<div class="row">
											<hr style="margin: 6px 0px;">
											<div class="col-md-6">
												<div class="form-group">
													<label>Firstname : </label>
													<input type="text" required="required" name="firstname" class="form-control" value="${userDetails.firstname }">
												</div>
													
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Lastname : </label>
													<input type="text" required="required" name="lastname" class="form-control" value="${userDetails.lastname }">
												</div>
													
											</div>
										</div>		
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Gender : </label>
													<input type="text" name="gender" readonly="readonly" class="form-control" value="${userDetails.gender }">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Date of Birth : </label>
													<input type="date" name="dob" required="required" class="form-control" value="${userDetails.dob }">
												</div>
											</div>
										</div>		
										<div class="row">
											<hr style="margin: 6px 0px;">
											<div class="col-md-6">
												<div class="form-group">
													<label>Phone : </label>
													<input type="text" name="phone" required="required" class="form-control" value="${userDetails.phone }">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Email : </label>
													<input type="text" name="email" required="required" class="form-control" value="${userDetails.email }">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Address : </label>
													<input type="text" name="address" required="required" class="form-control" value="${userDetails.address }">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>City : </label>
													<input type="text" name="city" required="required" class="form-control" value="${userDetails.city }">
												</div>
											</div>
										</div>	
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>State : </label>
													<input type="text" name="state" required="required" class="form-control" value="${userDetails.state }">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Country : </label>
													<input type="text" name="country" required="required" class="form-control" value="${userDetails.country }">
												</div>
											</div>
										</div>	
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Zipcode : </label>
													<input type="text" name="zipcode" required="required" class="form-control" value="${userDetails.zipcode }">
												</div>
											</div>
										</div>	
										<div class="row">
											<hr style="margin: 6px 0px;"><br>
											<div class="col-md-12 text-center">
												<div class="form-group">
													<input type="hidden" name="operation" value="update_user_profile">
													<input type="submit" class="btn btn-primary" value="Update Profile">
												</div>
											</div>
										</div>				
									</div>
								</div>
							</form>	
						</div>
					</div>
				</div>
			</div>
				
		</div>
		
	</div>
	<%@include file="footer.html"%>
</body>
</html>