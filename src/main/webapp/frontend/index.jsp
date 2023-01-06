<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	
	<jsp:directive.include file="header.jsp" />
	
	<div class ="center"> 
		<div >
			<h2>New Books:</h2>
			<c:forEach items="${listNewBooks }" var="book">
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId}">
							<img class="book-small" 
								src="data:image/jpg;base64,${book.base64Image}"/>
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId}">
							<b>${book.title}</b>
						</a>
					</div>
					<div>Rating *****</div>
					<div>by ${book.author }</div>
					<div><b>$ ${book.price }</b></div>
					
				</div>
				
			</c:forEach>
		</div>
		<div class="next-row">
			<h2>Best-selling Book:</h2>
		</div>
		
		<div class="next-row">
			<h2>Most-favored Book:</h2>
		</div>
		
		<br/><br/>
	</div>
	
	<jsp:directive.include file='footer.jsp' />

</body>
</html>