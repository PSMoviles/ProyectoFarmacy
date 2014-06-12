package com.psm.model;

public class Usuario {
	private int usuarioId;
	private String usuario;
	private String sexo;
	private int edad;
	private String correo;

	public Usuario() {
	}

	public Usuario(int usuarioId, String usuario, int edad, String correo,String sexo) {
		this.setUsuarioId(usuarioId);
		this.setUsuario(usuario);
		this.setEdad(edad);
		this.setCorreo(correo);
		this.setSexo(sexo);
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
