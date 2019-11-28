<%@ include file="header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12 p-2">
			<div class="btn-group">
				<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/personas/alta">Crear persona</a>
				<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}">Regresar</a>
			</div>
			<!-- Java en HTML -->
			<table class="table mt-2">
				<thead>
					<tr>
						<th>#</th>
						<th>Id</th>
						<th>Nombre</th>
						<th>Edad</th>
						<th>Carrera</th>
						<th>Fecha</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="persona" items="${listPersona}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${persona.idPersona}</td>
							<td>${persona.nombre}</td>
							<td>${persona.edad}</td>
							<td>${persona.carrera}</td>
							<td>${persona.fecha}</td>
							<td>
								<div class="btn-group">
									<form action="${pageContext.servletContext.contextPath}/personas/frases">
										<input type="hidden" name="txtIdPersona" value="${persona.idPersona}">
										<button type="submit" class="btn btn-primary"><i class="fas fa-quote-right"></i></button>
									</form>
									<form action="${pageContext.servletContext.contextPath}/personas/actualizar">
										<input type="hidden" name="txtIdPersona" value="${persona.idPersona}">
										<button type="submit" class="btn btn-primary"><i class="fas fa-pen"></i></button>
									</form>
									<form action="${pageContext.servletContext.contextPath}/personas/eliminar" method="post">
										<input type="hidden" name="txtIdPersona" value="${persona.idPersona}">
										<button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>