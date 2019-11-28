package com.barbaro.fmmvc.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbaro.fmmvc.DatabaseUtil;
import com.barbaro.fmmvc.db.DatabaseManager;
import com.barbaro.fmmvc.model.Persona;

@WebServlet("/personas/alta")
public class AltaPersonaServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("action", getServletContext().getContextPath()+"/personas/alta");
		req.setAttribute("actionMessage", "Agregar"); 
		req.getRequestDispatcher("/formPersona.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		DatabaseManager dbManager = null;
		Persona persona = null;
		
		
		/**Obtener datos**/
		// Identificar de donde vas recuperar los datos
		// Nombre de los datos
		String txtNombre = req.getParameter("txtNombre");
		String txtEdad = req.getParameter("txtEdad");
		String txtCarrera = req.getParameter("txtCarrera");
		
		//Validar datos (null, empty, range, date)
		
		/**Modelar datos**/
		persona = new Persona();
		persona.setNombre(txtNombre);
		persona.setEdad(Integer.parseInt(txtEdad));
		persona.setCarrera(txtCarrera);
		
		/**Guardar datos**/
		// Obtener conexion
		conn = DatabaseUtil.getConnection();
		// Encargado de manipular los datos
		dbManager = new DatabaseManager(conn);
		//Procesar datos, ejecutar la accion crear
		dbManager.crearPersona(persona);
		
		//Cerrar la conexión
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("success", true);
		req.setAttribute("message", "Se agrego la persona");
		doGet(req, resp);
		//resp.sendRedirect(getServletContext().getContextPath()+ "/personas");
	}
}
