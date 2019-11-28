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
import com.barbaro.fmmvc.model.Frase;
import com.barbaro.fmmvc.util.DateFormatUtil;


@WebServlet("/frases/alta")
public class AltaFraseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("actionMessage", "Agregar");
		req.setAttribute("action", getServletContext().getContextPath()+"/frases/alta");
		req.setAttribute("idPersona", req.getParameter("idPersona"));
		req.getRequestDispatcher("/formFrase.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String txtContenido = req.getParameter("txtContenido");
		String txtCantidad = req.getParameter("txtCantidad");
		String txtFechaDesde = req.getParameter("txtFechaDesde");
		// TODO Integer idPersona = Integer.parseInt(req.getParameter("txtIdPersona"));
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		Frase frase = null;
		
		frase = new Frase();
		frase.setContenido(txtContenido);
		frase.setCantidad(Integer.parseInt(txtCantidad));
		frase.setFechaDesde(DateFormatUtil.parseSQLDate(txtFechaDesde));
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		
		dbManager.crearFrase(frase);
		// TODO dbManager.crearPersonaFrase(idPersona, idFrase)
		
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("success", true);
		req.setAttribute("message", "Frase agregada");
		doGet(req, resp);
	}
}
