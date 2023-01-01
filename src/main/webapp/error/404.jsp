<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Not Found Error</title>
</head>
<body>

	<div align="center">
		<div>
			<img src='${pageContext.request.contextPath}/images/BookstoreLogo.png' />
		</div>

		<div>
			<h2>Sorry, the requested page could not be found.</h2>
		</div>
		<div>
			<a href="javascript:history.go(-1)">Go Back</a>
		</div>
	</div>

</body>
</html>