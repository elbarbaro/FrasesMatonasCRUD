<%@ include file="header.jsp"%>
<div class="container">
	<div class="row">
		<c:if test="${success}">
			<div class="alert alert-success" role="alert">
				${message}
			</div>
		</c:if>
		<div class="col-4 mt-4">
			<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/personas">Regresar</a>
			<form class="mt-2" action="${action}" method="post">
				<c:if test="${persona != null}">
					<input type="hidden" name="txtIdPersona" value="${persona.idPersona}">
				</c:if>
				<div class="form-group">
					<label for="txtNombre">Nombre</label>
					<input type="text" class="form-control form-control-lg" name="txtNombre" id="txtNombre" value="${persona.nombre}">
				</div>
				<div class="form-group">
					<label for="txtEdad">Edad</label>
					<input type="text" class="form-control form-control-lg" name="txtEdad" id="txtEdad" value="${persona.edad}">
				</div>
				<div class="form-group">
					<label for="txtCarrera">Carrera</label>
					<input type="text" class="form-control form-control-lg" name="txtCarrera" id="txtCarrera" value="${persona.carrera}">
				</div>
				<button type="submit" class="btn btn-primary" value="Agregar">${actionMessage}</button>
			</form>
		</div>
		<div class="col-8">
			
		</div>
	</div>
</div>
</body>
</html>