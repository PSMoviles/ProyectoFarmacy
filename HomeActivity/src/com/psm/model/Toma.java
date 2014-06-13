package com.psm.model;

import java.util.Date;

public class Toma {
	private int relacionId;
	private int medicinaid;
	private String medicina;	
	private int tratamientoId;
	private String tratamiento;
	private Date fecha;
	private Date hora;
	private int tomNo;
	private boolean tomada;
	private boolean reprogramada;
	private int tipoExcipiente;
	
	public Toma(){}
	public Toma(int relacionid,
			int medicinaId,
			String medicina,
			int tratamientoid,
			String tratamiento,
			Date fecha,
			Date hora,
			int tomano,
			boolean tomada,
			boolean reprogramada)
	{
		setMedicinaid(medicinaId);
		setFecha(fecha);
		setHora(hora);
		setMedicina(medicina);
		setRelacionId(relacionid);
		setReprogramada(reprogramada);
		setTomada(tomada);
		setTomNo(tomano);
		setTratamiento(tratamiento);
		setTratamientoId(tratamientoid);
		
	}
	
	public String getMedicina() {
		return medicina;
	}
	public void setMedicina(String medicina) {
		this.medicina = medicina;
	}	

	
	public boolean isTomada() {
		return tomada;
	}
	public void setTomada(boolean tomada) {
		this.tomada = tomada;
	}
	public boolean isReprogramada() {
		return reprogramada;
	}
	public void setReprogramada(boolean reprogramada) {
		this.reprogramada = reprogramada;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public int getRelacionId() {
		return relacionId;
	}
	public void setRelacionId(int relacionId) {
		this.relacionId = relacionId;
	}
	public int getTomNo() {
		return tomNo;
	}
	public void setTomNo(int tomNo) {
		this.tomNo = tomNo;
	}
	public int getMedicinaid() {
		return medicinaid;
	}
	public void setMedicinaid(int medicinaid) {
		this.medicinaid = medicinaid;
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
	public int getTipoExcipiente() {
		return tipoExcipiente;
	}
	public void setTipoExcipiente(int tipoExcipiente) {
		this.tipoExcipiente = tipoExcipiente;
	}

}
