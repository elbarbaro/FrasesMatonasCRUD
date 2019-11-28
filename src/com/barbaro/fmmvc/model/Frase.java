package com.barbaro.fmmvc.model;

import java.util.Date;

public class Frase {
	
	private int idFrase;
	private String contenido;
	private int cantidad;
	private java.sql.Date fechaDesde;
	private Date fecha;
	
	public int getIdFrase() {
		return idFrase;
	}
	public void setIdFrase(int idFrase) {
		this.idFrase = idFrase;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public java.sql.Date getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(java.sql.Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
