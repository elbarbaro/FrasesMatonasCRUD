package com.barbaro.fmmvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbaro.fmmvc.DatabaseUtil;
import com.barbaro.fmmvc.db.DatabaseManager;
import com.barbaro.fmmvc.model.Frase;

@WebServlet({"/frases", "/personas/frases"})
public class FraseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String txtIdPersona = req.getParameter("txtIdPersona");
		Connection conn = null;
		DatabaseManager dbManager = null;
		List<Frase> listFrases = null;
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		if(txtIdPersona != null && !txtIdPersona.isEmpty()) {
			Integer idPersona = Integer.parseInt(txtIdPersona);
			listFrases = dbManager.consultarFrasesPersona(idPersona);
		}else {
			listFrases = dbManager.consultarFrases();
		}
		
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("listFrases", listFrases);
		req.setAttribute("idPersona", txtIdPersona);
		req.getRequestDispatcher("/frases.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
