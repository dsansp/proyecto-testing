<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Product List | Awesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
	
	<div class = "pt-5 container">
		
		<h1>Products Directory</h1>
		<hr/>
		
		<p>${NOTIFICATION}</p>
		
		<p>
			<a class = "btn btn-primary" href="${pageContext.request.contextPath}/products/new">AÑADIR PRODUCTO</a>
			<a class = "btn btn-danger" href="${pageContext.request.contextPath}/products/delete/all">BORRAR PRODUCTOS</a>
			
		</p>
	
		<table id ="products-list" class = "table table-striped table-bordered">
			
			<tr class = "thead-dark">
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Fabricante</th>
				<th>Categorías</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${products}" var="product">
			
				<tr>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.description}</td>
					<td>${product.quantity}</td>

					<td>
						<a href="${pageContext.request.contextPath}/manufacturers/${product.manufacturer.id}/view">${product.manufacturer.name}</a> 
					</td>
					<td>
						<c:forEach items="${product.categories}"  var="category">
						<span id="category-${category.id}" class="badge bg-primary">${category.name}</span>
						
						</c:forEach>
					</td>
					<td> 
						<a class="btn btn-info" href = "${pageContext.request.contextPath}/products/${product.id}/view">Ver</a> 
						<a class="btn btn-success" href = "${pageContext.request.contextPath}/products/${product.id}/edit">Editar</a> 
						<a class="btn btn-danger" href = "${pageContext.request.contextPath}/products/${product.id}/delete">Borrar</a> 
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
		
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>