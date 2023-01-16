<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders Details - Evergreen Bookstore Adminstration</title>
<link rel="stylesheet" href="../css/style.css">
		<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.orderId }</h2>
	</div>

	<c:if test="${message !=null }">
	
		<div align="center">
			<h4 class="message">${message }</h4>
		</div>
	
	</c:if>
	
	<div align="center">
		<h2>Order Overview:</h2>
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname }</td>
			</tr>
			<tr>
				<td><b>Book Copies: </b></td>
				<td>${order.bookCopies }</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency"/></td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td>${order.recipientName }</td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td>${order.recipientName }</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod }</td>
			</tr>
			<tr>
				<td><b>Shipping Address: </b></td>
				<td>${order.shippingAddress }</td>
			</tr>
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status }</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate }</td>
			</tr>
		</table>
	</div>
	
	<div align="center">
		<h2>Ordered Books</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td>
						<img style="vertical-align:middle;" src="data:image/jpg;base64,${orderDetail.book.base64Image }" width="48" height="64"/>
						${orderDetail.book.title }
					</td>
					
					<td>${orderDetail.book.author }</td>
					<td>${orderDetail.book.price }</td>
					<td>${orderDetail.quantity }</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /> </td>
				</tr>			
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b> 
				</td>
				<td>
					<b>${order.bookCopies}</b> 
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
			</tr>
			
		</table>
	</div>
	
	<div align="center">
		<br/>
		<a href="edit_order?id=${order.orderId }">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Delete this Order</a>
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