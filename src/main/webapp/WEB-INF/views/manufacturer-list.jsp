<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Manufacturer List | Awesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
	
	<div class = "pt-5 container">
		
		<h1>Listado de fabricantes</h1>
		<hr/>
		
		<c:if test="${NOTIFICATION.length() > 0}">
				<div class="alert alert-danger" role="alert">
		  			${NOTIFICATION}
				</div>
		</c:if>
		

		
		<p>
			<a class = "btn btn-primary" href="${pageContext.request.contextPath}/manufacturers/new">AÑADIR FABRICANTE</a>
			<a id="delete-manufacturers" class = "btn btn-danger" href="${pageContext.request.contextPath}/manufacturers/delete/all">BORRAR FABRICANTES</a>
			
		</p>
	
		<table class = "table table-striped table-bordered">
			
			<tr class = "thead-dark">
				<th>Name</th>
				<th>CIF</th>
				<th>Nº Empleados</th>
				<th>Año fundación</th>
				<th>Calle</th>
				<th>País</th>
				<th>Productos</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${manufacturers}" var="manufacturer">
			
				<tr>
					<td>${manufacturer.name}</td>
					<td>${manufacturer.cif}</td>
					<td>${manufacturer.numEmployees}</td>
					<td>${manufacturer.year}</td>
					<td>${manufacturer.direction.street}</td>
					<td>${manufacturer.direction.country}</td>
					<td>
						<c:forEach items="${manufacturer.products}" var="product">
						<span class="badge bg-light text-light"><a href="${pageContext.request.contextPath}/products/${product.id}/view"> ${product.name} </a></span>
						</c:forEach> 
					</td>
					<td> 
				
					<a class="btn btn-info" href = "${pageContext.request.contextPath}/manufacturers/${manufacturer.id}/view">Ver</a> 
						<a class="btn btn-success" href = "${pageContext.request.contextPath}/manufacturers/${manufacturer.id}/edit">Editar</a> 
						<a class="btn btn-danger" href = "${pageContext.request.contextPath}/manufacturers/${manufacturer.id}/delete">Borrar</a>
				
						 
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
		
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>