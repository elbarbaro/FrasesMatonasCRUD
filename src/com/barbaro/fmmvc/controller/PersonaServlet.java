package com.barbaro.fmmvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbaro.fmmvc.DatabaseUtil;
import com.barbaro.fmmvc.db.DatabaseManager;
import com.barbaro.fmmvc.model.Persona;


@WebServlet("/personas")
public class PersonaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		DatabaseManager dbManager = null;
		List<Persona> personas = null;
		RequestDispatcher dispatcher = null;
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		
		//Recuperar modelos en una lista
		personas = dbManager.consultarPersonas();
		
		//Preparar datos para mandarlo a la vista (MVC)
		req.setAttribute("listPersona", personas);
		
		// Indicar la vista a utilizar
		dispatcher = req.getRequestDispatcher("personas.jsp");
		// Cargar la vista
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
