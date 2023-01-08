<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Evergreen Bookstore - Online Books Store</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	
	<jsp:directive.include file="header.jsp" />

	<div class ="center">
		<br/>
		<h3>${message}</h3>
		<br/>
	</div>

	<jsp:directive.include file='footer.jsp' />
	</body>
</html>