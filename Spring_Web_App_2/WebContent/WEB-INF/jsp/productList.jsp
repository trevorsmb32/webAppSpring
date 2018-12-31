<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>


<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>All Products</h1>

			<p class="lead">Checkout all the awesome products available now!</p>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>Product Name</th>
					<th>Category</th>
					<th>Condition</th>
					<th>Price</th>
					<th>Quantity</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.productCode}</td>
					<td>${product.description}</td>
					<td>${product.price}USD</td>
					<td>${product.quantity}</td>
					<td><a
						href="<spring:url value="/productList/viewProduct/${product.id}" />"><span
							class="glyphicon glyphicon-info-sign"></span></a> <a
						href="<spring:url value="/addToShoppingCart/${product.id}" />"><span
							class="glyphicon glyphicon-info-sign"></span></a></td>
					<a href="<spring:url value="/removeFromCart/${product.id}" />"><span
						class="glyphicon glyphicon-info-sign"></span></a>
					</td>

				</tr>

			</c:forEach>
		</table>

		<%@include file="/WEB-INF/jsp/template/footer.jsp"%>