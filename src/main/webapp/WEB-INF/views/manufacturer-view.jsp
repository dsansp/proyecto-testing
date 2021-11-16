<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manufacturer View | Aswesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	
	<div class = "pt-5 container">
			<h2>Fabricante ${manufacturer.id}</h2>
			<hr>
		<div class = " row justify-content-center">

			<div class = "col-md-8 mb-5">
					<p><b>Nombre</b>: ${manufacturer.name}</p>
					<p><b>CIF</b>: ${manufacturer.cif}</p>
					<p><b>Nº Empleados</b>: ${manufacturer.numEmployees}</p>
					<p><b>Año</b>: ${manufacturer.year}</p>

					<h3>Dirección</h3>
					<p> <b>Calle</b>: ${manufacturer.direction.street}</p>
					<p><b>Código postal</b>: ${manufacturer.direction.postalCode}</p>
					<p><b>Ciudad</b>: ${manufacturer.direction.city}</p>
					<p><b>País</b>: ${manufacturer.direction.country}</p>
					
					<h3>Productos fabricados</h3>
					
					<ul>
						<c:forEach items="${manufacturer.products}" var="product">
							<li> <a href="${pageContext.request.contextPath}/products/${product.id}/view">${product.name} (${product.price} €)</a> </li>
						</c:forEach>
					</ul>
					
					<div class="mt-5">
			<a class="btn btn-info" href = "${pageContext.request.contextPath}/manufacturers">Volver</a> 
			<a class="btn btn-success" href = "${pageContext.request.contextPath}/manufacturers/${manufacturer.id}/edit">Editar</a> 
			<a class="btn btn-danger" href = "${pageContext.request.contextPath}/manufacturers/${manufacturer.id}/delete">Borrar</a>
			<a class="btn btn-info" href = "${pageContext.request.contextPath}/products/new/manufacturer/${manufacturer.id}">Crear producto</a>
			
			</div>
			</div>

		</div>
	</div>
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>