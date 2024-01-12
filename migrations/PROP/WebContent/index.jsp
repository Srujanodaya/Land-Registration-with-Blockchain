<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>PROP</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="Description" lang="en" content="ADD SITE DESCRIPTION">
<meta name="author" content="ADD AUTHOR INFORMATION">
<meta name="robots" content="index, follow">
<c:set value="${pageContext.request.contextPath }" var="context"/>
<!-- icons -->
<link rel="apple-touch-icon" href="${context }/assets/img/apple-touch-icon.png">
<link rel="shortcut icon" href="favicon.ico">

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
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="col-md-2">
				<img alt="" src="${context }/images/logo.png" height="50" width="150">
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${context }/home">Home</a></li>
				<li><a href="about_us.jsp">About Us</a></li>
				<li><a href="contact_us.jsp">Contact Us</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${context}/user/signup"><span
						class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="${context}/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<h2 class="text-info">
					Real Estate
				</h2>
				<hr>
			</div>
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">Abstract</div>
					<div class="panel-body">
						<div class="col-md-12">
							Real Estate is growing rapidly... <br> <br>
						</div>
						<div class="col-md-8">Secondly, we want to make online to reach people over..</div>
						<div class="col-md-4">
							<img src="images/home-page.jpg" class="img-responsive"
								alt="Cinque Terre">
						</div>
						<br><br>
						<div class="col-md-12" >
							Improve the security and privacy<br>
							<br>
						</div>
					</div>
					
						

					</div>
				</div>
			</div>
		</div>
	<hr>
	<div class="container-fluid">
		<footer>
			<div class="row">
				<div class="col-md-12 text-center">
					<p>Copyright &copy; Megainfoware 2017</p>
				</div>
			</div>
		</footer>
	</div>
		
</body>
</html>