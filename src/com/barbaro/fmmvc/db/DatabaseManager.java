package com.barbaro.fmmvc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.barbaro.fmmvc.model.Frase;
import com.barbaro.fmmvc.model.Persona;

public class DatabaseManager {

	private Connection conn;
	
	public DatabaseManager(Connection conn) {
		this.conn = conn;
	}
	
	public void crearPersona(Persona persona) {
		String query = "insert into persona(nombre, edad, carrera) "
				+ "values(" + persona.toString() + ")";
		Statement stmnt = null;
		
		try {
			stmnt = conn.createStatement();
			stmnt.executeUpdate(query);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null) {
					stmnt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void crearFrase(Frase frase) {
		String query = "insert into frase(contenido, cantidad, fechaDesde) "
				+ "values(?, ?, ?)";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
			
			stmnt.setString(1, frase.getContenido());
			stmnt.setInt(2, frase.getCantidad());
			stmnt.setDate(3, frase.getFechaDesde());
			
			stmnt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null) {
					stmnt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int crearPersonaFrase(int idPersona, int idFrase) {
		String query = "insert into persona_frase (idPersona, idFrase) "
				+ "values(?, ?)";
		PreparedStatement stmnt = null;
		int result = -1;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idPersona);
			stmnt.setInt(2, idFrase);
			result = stmnt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null) {
					stmnt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
		
	public List<Persona> consultarPersonas(){
		String query = "select * from persona";
		Statement stmnt = null;
		ResultSet rs = null;
		List<Persona> listPersonas = null;
		
		try {
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(query);
			listPersonas = new ArrayList<>();
			while(rs.next()) {
				// Creando el modelo
				Persona persona = new Persona();
				
				// Agregando valores al modelo
				persona.setIdPersona(rs.getInt("idPersona"));
				persona.setNombre(rs.getString("nombre"));
				persona.setEdad(rs.getInt("edad"));
				persona.setCarrera(rs.getString("carrera"));
				persona.setFecha(rs.getDate("fecha"));
				
				//Agregando modelo a la lista
				listPersonas.add(persona);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmnt != null) {
					stmnt.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listPersonas;
	}
	
	public List<Frase> consultarFrases(){
		String query = "select * from frase";
		Statement stmnt = null;
		ResultSet rs = null;
		List<Frase> listFrases = null;
		
		try {
			
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(query);
			listFrases = new ArrayList<>();
			while(rs.next()) {
				Frase frase = new Frase();
				frase.setIdFrase(rs.getInt("idFrase"));
				frase.setContenido(rs.getString("contenido"));
				frase.setCantidad(rs.getInt("cantidad"));
				frase.setFechaDesde(rs.getDate("fechaDesde"));
				frase.setFecha(rs.getDate("fecha"));
				listFrases.add(frase);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null) {
					stmnt.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listFrases;
	}
	
	public List<Frase> consultarFrasesPersona(int idPersona){
		String query = "select * from persona_frase pf "
				+ "inner join frase f "
				+ "on pf.idFrase = f.idFrase "
				+ "where pf.idPersona = ?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		List<Frase> listFrases = null;
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idPersona);
			rs = stmnt.executeQuery();
			listFrases = new ArrayList<>();
			while(rs.next()) {
				Frase frase = new Frase();
				frase.setIdFrase(rs.getInt("f.idFrase"));
				frase.setContenido(rs.getString("f.contenido"));
				frase.setCantidad(rs.getInt("f.cantidad"));
				frase.setFechaDesde(rs.getDate("f.fechaDesde"));
				frase.setFecha(rs.getDate("f.fecha"));
				listFrases.add(frase);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmnt != null) {
					stmnt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listFrases;
	}
	
	public Persona obtenerPersona(Integer idPersona) {
		String query = "select * from persona where idPersona = ?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		Persona persona = null;
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idPersona);
			
			rs = stmnt.executeQuery();
			if(rs.first()) {
				persona = new Persona();
				persona.setIdPersona(idPersona);
				persona.setNombre(rs.getString("nombre"));
				persona.setEdad(rs.getInt("edad"));
				persona.setCarrera(rs.getString("carrera"));
				persona.setFecha(rs.getDate("fecha"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmnt != null) {
					stmnt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return persona;
	}
	
	public Frase obtenerFrase(Integer idFrase) {
		String query = "select * from frase where idFrase = ?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		Frase frase = null;
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idFrase);
			
			rs = stmnt.executeQuery();
			if(rs.first()) {
				frase = new Frase();
				frase.setIdFrase(idFrase);
				frase.setContenido(rs.getString("contenido"));
				frase.setCantidad(rs.getInt("cantidad"));
				frase.setFechaDesde(rs.getDate("fechaDesde"));
				frase.setFecha(rs.getDate("fecha"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmnt != null) {
					stmnt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return frase;
	}
	
	public void actualizarPersona(Integer idPersona, Persona persona) {
		String query = "update persona set nombre = ?, edad = ?, carrera = ? "
				+ "where idPersona = ?";
		PreparedStatement stmnt = null;
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, persona.getNombre());
			stmnt.setInt(2, persona.getEdad());
			stmnt.setString(3, persona.getCarrera());
			stmnt.setInt(4, idPersona);
			
			stmnt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmnt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarPersona(Integer idPersona) {
		String query = "delete from persona where idPersona = ?";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idPersona);
			stmnt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null)
					stmnt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actualizarFrase(Integer idFrase, Frase frase) {
		
		String query = "update frase set contenido = ?, cantidad = ?, fechaDesde = ? "
				+ "where idFrase = ?";
		PreparedStatement stmnt = null;
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, frase.getContenido());
			stmnt.setInt(2, frase.getCantidad());
			stmnt.setDate(3, frase.getFechaDesde());
			stmnt.setInt(4, idFrase);
			stmnt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null)
					stmnt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarFrase(Integer idFrase) {
			
		String query = "delete from frase where idFrase = ?";
		PreparedStatement stmnt = null;
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idFrase);
			stmnt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmnt != null)
					stmnt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
