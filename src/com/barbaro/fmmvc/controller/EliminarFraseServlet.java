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


@WebServlet("/frases/eliminar")
public class EliminarFraseServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		String txtIdFrase = req.getParameter("txtIdFrase");
		
		Integer idFrase = Integer.parseInt(txtIdFrase);
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		dbManager.eliminarFrase(idFrase);
		
		DatabaseUtil.closeConnection(conn);
		
		resp.sendRedirect(getServletContext().getContextPath() + "/frases");
	}
}
