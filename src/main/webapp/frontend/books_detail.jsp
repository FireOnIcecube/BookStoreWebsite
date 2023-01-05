<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}-Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class ="center">
		<table width="80%"style="border:0px">
			<tr >
				<td colspan="3" align="left">
					<h2>${book.title }</h2> by ${book.author }
				</td>
			</tr>
			<tr>
				<td rowspan ="2">
					<img src="data:image/jpg;base64,${book.base64Image}" width="240" height="300" />
				</td>
				<td valign="top" align="left">
					Rating *****
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>$${book.price}</h2>
					<br/>
					<button type="submit">Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align:justify">
					${book.description }
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td><h2>Customer Reviews</h2></td>
				<td colspan="2" align="center">
					<button>Write a Customer Review</button>
				</td>
			</tr>
		</table>

	</div>

	<jsp:directive.include file='footer.jsp' />

</body>
</html>