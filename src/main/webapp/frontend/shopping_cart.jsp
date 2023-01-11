<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Your Cart Details</h2>

		<c:if test="${message !=null }">

			<div align="center">
				<h4 class="message">${message }</h4>
			</div>

		</c:if>
		
		
		
		
		
		<c:set var="cart" value="${sessionScope['cart']}" />


	

		<c:if test="${cart.getTotalItems() == 0 }">
			<h2>There's no items in your cart</h2>
		</c:if>
		
		
		<c:if test="${cart.getTotalItems() > 0 }">
			<div>
				<form>
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>
								<a href=""><b> Clear Cart</b></a>
							</th>
						</tr>
						<c:forEach items="${cart.getItems()}" var="item" varStatus="status">
						<tr>
							<td>${status.index +1}</td>	
							<td valign="middle">
								<img class="book-small" src="data:image/jpg;base64,${item.key.base64Image}"/>
							</td>
							<td>
								
								<span id="book-title"> ${item.key.title}</span>
							</td>
							<td>${item.value }</td>
							<td><fmt:formatNumber value="${item.key.price }" type="currency"/></td>
							<td><fmt:formatNumber value="${item.value * item.key.price }" type="currency" /></td>
							<td><a href="">Remove</a></td>
						</tr>
						</c:forEach>
						
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><b>${cart.getTotalQuantity()} book(s)</b></td>
							<td>Total:</td>
							<td colspan="2"><fmt:formatNumber value="${cart.getTotalAmount() }" type="currency" /></td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>
		
	</div>

	<jsp:directive.include file='footer.jsp' />

	<script type="text/javascript">
		$(document).ready(function() {
			$('#loginForm').validate({
				rules : {
					email : {
						required : true,
						email : true
					},
					password : "required",
				},

				messages : {

					email : {
						required : "Please enter email",
						email : "Please enter an valid email address"
					},
					password : "Please enter password",
				}
			});

			$('#buttonCancel').click(function() {
				history.go(-1);
			});

		});
	</script>
</body>
</html>