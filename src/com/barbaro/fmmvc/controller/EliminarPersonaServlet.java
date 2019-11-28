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

@WebServlet("/personas/eliminar")
public class EliminarPersonaServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer idPersona = Integer.parseInt(req.getParameter("txtIdPersona"));
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		
		dbManager.eliminarPersona(idPersona);
		
		DatabaseUtil.closeConnection(conn);
		
		resp.sendRedirect(getServletContext().getContextPath()+"/personas?s=true&m=e");
	}
}
