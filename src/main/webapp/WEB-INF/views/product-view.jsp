<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product View | Aswesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	
	<div class = "pt-5 container">
			<h2>Producto ${product.id}</h2>
			<hr>
		<div class = " row justify-content-center">

			<div class = "col-md-8 mb-5">
					<p><b>Nombre</b>: ${product.name}</p>
					<p><b>Descripción</b>: ${product.description}</p>
					<p><b>Precio</b>: ${product.price} (€)</p>
					<p><b>Cantidad</b>: ${product.quantity}</p>
					<p><b>Fabricante</b>: <a href="${pageContext.request.contextPath}/manufacturers/${product.manufacturer.id}/view">${product.manufacturer.name}</a></p>
					
					<h3 class="mt-3 mb-3">Categorías asociadas</h3>
					
					<ul>
					<c:forEach items="${product.categories}" var="category">
						<li><span class="badge bg-primary text-white">${category.name}</span></li>
					</c:forEach>
					</ul>
			<a class="btn btn-info" href = "${pageContext.request.contextPath}/products">Volver</a> 
			<a class="btn btn-success" href = "${pageContext.request.contextPath}/products/${product.id}/edit">Editar</a> 
			<a class="btn btn-danger" href = "${pageContext.request.contextPath}/products/${product.id}/delete">Borrar</a>
			
			</div>

		</div>
	</div>
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>