package com.psm.ui;

import android.graphics.drawable.Drawable;

public class ItemHomeList {
	private String tratamiento;
	private String medicina;
	private String fecha;
	private String hora;
	private Drawable icon;
	private int tipoExcipienteId;
	
	public ItemHomeList(){}
	public ItemHomeList(String tra,String med,String fec,String hor){
		setFecha(fec);
		setHora(hor);
		setMedicina(med);
		setTratamiento(tra);
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	public String getMedicina() {
		return medicina;
	}
	public void setMedicina(String medicina) {
		this.medicina = medicina;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public int getTipoExcipienteId() {
		return tipoExcipienteId;
	}
	public void setTipoExcipienteId(int tipoExcipienteId) {
		this.tipoExcipienteId = tipoExcipienteId;
	}

}
