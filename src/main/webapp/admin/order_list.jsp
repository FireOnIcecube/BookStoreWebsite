<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Orders - Evergreen Bookstore Adminstration</title>
<link rel="stylesheet" href="../css/style.css">
		<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Book Orders Management</h2>
	</div>

	<c:if test="${message !=null }">
	
		<div align="center">
			<h4 class="message">${message }</h4>
		</div>
	
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered by</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrder }" varStatus="status">
			<tr>
				<td>${status.index +1 }</td>
				<td>${order.orderId }</td>
				<td>${order.customer.fullname }</td>
				<td>${order.getBookCopies() }</td>
				<td><fmt:formatNumber value ="${order.total }" type="currency" /></td>
				<td>${order.paymentMethod }</td>
				<td>${order.status }</td>
				<td>${order.orderDate }</td>
				<td>
					<a href="view_order?id=${order.orderId}">Details</a> &nbsp;
					<a href="edit_category?id=${cat.categoryId}">Edit</a> &nbsp;
					<a href="javascript:void(0)" class="deleteLink" id="${cat.categoryId }">Delete</a>
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
					
					customerId =$(this).attr("id");
					
					if(confirm('Are you sure you want to delete the customer with ID ' +customerId+'?')){
						window.location.replace('delete_customer?id='+ customerId);
						//window.location = 'delete_user';
					}
					
				});
			});
		});

	</script>
	</body>
</html>