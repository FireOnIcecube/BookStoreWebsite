<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result for ${keyword } - Evergreen Books - Online Books
	Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class ="center">
		<c:if test="${fn:length(result) == 0}">
			<h2>No Results for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">

			<div class="book_group">
				<center><h2>Result for "${keyword }":</h2></center>
				<c:forEach items="${result }" var="book">
					<div>
						<div id="search-image">
							<div align="left">
								<a href="view_book?id=${book.bookId}"> 
								<img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
								</a>
							</div>
						</div>

						<div id="search-description">
							<div>
								<h2><a href="view_book?id=${book.bookId}"> <b>${book.title}</b></a></h2>
							</div>
							<div>Rating *****</div>
							
							<div>
								<i>by ${book.author }</i>
							</div>
							
							<div>
								<p> ${fn:substring(book.description,0,80) }.... </p>
							</div>
						</div>

						<div id="search-price">
							<h3>$${book.price }</h3>
							<h3><a href="add_to_cart?book_id=${book.bookId}">Add To Cart</a></h3>
						</div>

					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>


	<jsp:directive.include file='footer.jsp' />

</body>
</html>