<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>


<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Cart</h1>

			<p class="lead">List of Items below</p>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>Image</th>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Edit Quantity</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${items}" var="items">
				<tr>
					<td><img
						src="<c:url value="/resource/images/${product.id}.png" /> "
						alt="image" style="width: 100%" /></td>
					<td>${items.name}</td>
					<td>${items.price}USD</td>
					<td>${items.quantity}</td>
					<td><input type="number" id="quantity_${item.id}"
						value="${items.quantity}" style="width: 50px" /></td>
					<a href="<spring:url value="/removeFromCart/${product.id}" />">X</a>
					</td>
					<td>
				</tr>
			</c:forEach>
			<label style="color: red";>${shoppingCart.total}</label>
			<a href="<spring:url value="shoppingCart/checkOut" />"></span>proceed To
				CheckOut</a>
		</table>

		<%@include file="/WEB-INF/jsp/template/footer.jsp"%>