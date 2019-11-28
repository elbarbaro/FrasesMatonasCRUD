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


@WebServlet("/frases/actualizar")
public class ActualizarFraseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String txtIdFrase = req.getParameter("txtIdFrase");
		Connection conn = null;
		DatabaseManager dbManager = null;
		Frase frase = null;
		
		Integer idFrase = Integer.parseInt(txtIdFrase);
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		frase = dbManager.obtenerFrase(idFrase);
		
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("action", getServletContext().getContextPath()+"/frases/actualizar");
		req.setAttribute("actionMessage", "Actualizar");
		req.setAttribute("frase", frase);
		req.getRequestDispatcher("/formFrase.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		Frase frase = null;
		
		String txtIdFrase = req.getParameter("txtIdFrase");
		String txtContenido = req.getParameter("txtContenido");
		String txtCantidad = req.getParameter("txtCantidad");
		String txtFechaDesde = req.getParameter("txtFechaDesde");
		
		Integer idFrase = Integer.parseInt(txtIdFrase);
		frase = new Frase();
		frase.setContenido(txtContenido);
		frase.setCantidad(Integer.parseInt(txtCantidad));
		frase.setFechaDesde(DateFormatUtil.parseSQLDate(txtFechaDesde));
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		dbManager.actualizarFrase(idFrase, frase);
		
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("success", true);
		req.setAttribute("message", "Frase actualizada");
		doGet(req, resp);
	}
}
