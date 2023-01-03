<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Books - Evergreen Bookstore Adminstration</title>
<link rel="stylesheet" href="../css/style.css">
		<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
		<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1 class="pageheading">Books Management</h1>
		<h3><a href="new_book">Create new Book</a></h3>
	</div>

	<c:if test="${message !=null }">
	
		<div align="center">
			<h4 class="message">${message }</h4>
		</div>
	
	</c:if>

	


	
	<div align='center' >
		<table border='1' cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			

			<c:forEach var="book" items="${listBooks}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${book.bookId}</td>
				
				<td>
					<img src="data:image/jpg;base64,${book.base64Image}" width="84" height="110"/>
				</td>
				
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.category.name}</td>
				<td>$ ${book.price}</td>
				<td> <fmt:formatDate pattern='MM/dd/yyyy' value="${book.lastUpdateTime}"/> </td>
				

				<td>
					<a href="edit_book?id=${book.bookId}">Edit</a> &nbsp;
					<a href="javascript:void(0)" class="deleteLink" id="${book.bookId }">Delete</a>
				</td>
			</tr>
			
			</c:forEach>
		</table>
	</div>



	<jsp:directive.include file='footer.jsp' />
	
	
	<script>
	
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click",function(){
					
					userId =$(this).attr("id");
					
					if(confirm('Are you sure you want to delete the user with ID ' +userId+'?')){
						window.location.replace('delete_user?id='+ userId);
						//window.location = 'delete_user';
					}
					
				});
			});
		});

	</script>
	</body>
</html>