<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="col-md-2"><img alt="" src="${context }/images/logo.png" height="50" width="150"> </div>
			<ul class="nav navbar-nav">
				<li><a href="${context }/admin/home">Home</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${context }/login"><span class="glyphicon glyphicon-user"></span>
						Sign Out</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>