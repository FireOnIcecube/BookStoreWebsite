<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Interanl Server Error</title>
</head>
<body>

	<div align="center">
		<div>
			<img src='${pageContext.request.contextPath}/images/BookstoreLogo.png' />
		</div>

		<div>
			<h2>Sorry, the server has encountered an error while fulfilling your request.</h2>
			<h2>Please check back later or contact our administrators.</h2>
		</div>
		<div>
			<a href="javascript:history.go(-1)">Go Back</a>
		</div>
	</div>

</body>
</html>