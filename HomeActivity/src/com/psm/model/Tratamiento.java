package com.psm.model;

public class Tratamiento {
	private int tratamientoId;
	private String tratamiento;
	private int usuarioId;
	private String enfermedad;
	
	public Tratamiento(){}
	
	public Tratamiento(int tratamientoId,String tratamiento,String enfermedad,	int usuarioId)
	{
		this.setTratamiento(tratamiento);
		this.setTratamientoId(tratamientoId);
		this.setUsuarioId(usuarioId);
		this.setEnfermedad(enfermedad);
	}

	public int getTratamientoId() {
		return tratamientoId;
	}

	public void setTratamientoId(int tratamientoId) {
		this.tratamientoId = tratamientoId;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}
	
}
