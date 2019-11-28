<%@ include file="header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-12 p-2">
			<div class="btn-group">
				<form action="${pageContext.servletContext.contextPath}/frases/alta">
					<input type="hidden" name="txtIdPersona" value="${idPersona}">
					<button type="submit" class="btn btn-primary">Agregar</button>
				</form>
				<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/personas">Regresar</a>
			</div>
			<table class="table mt-2">
				<thead>
					<tr>
						<th>#</th>
						<th>Id</th>
						<th>Contenido</th>
						<th>Cantidad</th>
						<th>Desde</th>
						<th>Fecha</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="frase" items="${listFrases}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${frase.idFrase}</td>
							<td>${frase.contenido}</td>
							<td>${frase.cantidad}</td>
							<td>${frase.fechaDesde}</td>
							<td>${frase.fecha}</td>
							<td>
								<div class="btn-group">
									<form action="${pageContext.servletContext.contextPath}/frases/actualizar">
										<input type="hidden" name="txtIdFrase" value="${frase.idFrase}">
										<button type="submit" class="btn btn-primary"><i class="fas fa-pen"></i></button>
									</form>
									<form action="${pageContext.servletContext.contextPath}/frases/eliminar" method="post">
										<input type="hidden" name="txtIdFrase" value="${frase.idFrase}">
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