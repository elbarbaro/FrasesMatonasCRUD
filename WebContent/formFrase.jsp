<%@ include file="header.jsp" %>
<div class="container">
	<div class="row">
		<c:if test="${success}">
			<div class="alert alert-success" role="alert">
				${message}
			</div>
		</c:if>
		<div class="col-4 p-4">
			<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/personas/frases">Regresar</a>
			<form class="mt-2" action="${action}" method="post">
				<c:if test="${frase != null}">
					<input type="hidden" name="id" value="${frase.idFrase}">
				</c:if>
				<input type="hidden" name="txtIdPersona" value="${idPersona}">
				<div class="form-group">
					<label for="txtContenido">Contenido</label>
					<input type="text" class="form-control" name="txtContenido" id="txtContenido" value="${frase.contenido}">
				</div>
				<div class="form-group">
					<label for="txtCantidad">Cantidad</label>
					<input type="text" class="form-control" name="txtCantidad" id="txtCantidad" value="${frase.cantidad}">
				</div>
				<div class="form-group">
					<label for="txtFechaDesde">Desde</label>
					<input type="text" class="form-control" name="txtFechaDesde" id="txtFechaDesde" value="${frase.fechaDesde}">
				</div>
				<button type="submit" class="btn btn-primary">${actionMessage}</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>