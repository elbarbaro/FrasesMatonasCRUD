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

@WebServlet("/personas/actualizar")
public class ActualizarPersonaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Integer idPersona = Integer.parseInt(req.getParameter("txtIdPersona"));
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		Persona persona = null;
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		persona = dbManager.obtenerPersona(idPersona);
		
		DatabaseUtil.closeConnection(conn);
		
		
		req.setAttribute("action", getServletContext().getContextPath()+"/personas/actualizar");
		req.setAttribute("actionMessage", "Actualizar");
		req.setAttribute("persona", persona);
		req.getRequestDispatcher("/formPersona.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Connection conn = null;
		DatabaseManager dbManager = null;
		Persona persona = null;
		
		//Obtener datos de la peticion POST
		Integer idPersona = Integer.parseInt(req.getParameter("txtIdPersona"));
		String txtNombre = req.getParameter("txtNombre");
		String txtEdad = req.getParameter("txtEdad");
		String txtCarrera = req.getParameter("txtCarrera");
		
		persona = new Persona();
		persona.setNombre(txtNombre);
		persona.setEdad(Integer.parseInt(txtEdad));
		persona.setCarrera(txtCarrera);
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		dbManager.actualizarPersona(idPersona, persona);
		
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("success", true);
		req.setAttribute("message", "Persona actualizada");
		doGet(req, resp);
	}
}
