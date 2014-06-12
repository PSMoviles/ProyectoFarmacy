package com.psm.model;

public class Medicina {
	private int medicinaId;
	private String medicina;
	private String nombreComercial;
	private String indicacionTerapeutica;
	private int tipoExcipienteId;
	private String tipoExcipiente;
	
	public Medicina(){}
	
	public Medicina(int medicinaId,String medicina,String nombreComercial,String indicacion,int excipienteId,String excipiente)
	{
		this.setMedicinaId(medicinaId);
		this.setMedicina(medicina);
		this.setNombreComercial(nombreComercial);
		this.setIndicacionTerapeutica(indicacion);
		this.setTipoExcipienteId(excipienteId);
		this.setTipoExcipiente(excipiente);
	}

	public int getMedicinaId() {
		return medicinaId;
	}

	public void setMedicinaId(int medicinaId) {
		this.medicinaId = medicinaId;
	}

	public String getMedicina() {
		return medicina;
	}

	public void setMedicina(String medicina) {
		this.medicina = medicina;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getIndicacionTerapeutica() {
		return indicacionTerapeutica;
	}

	public void setIndicacionTerapeutica(String indicacionTerapeutica) {
		this.indicacionTerapeutica = indicacionTerapeutica;
	}

	public int getTipoExcipienteId() {
		return tipoExcipienteId;
	}

	public void setTipoExcipienteId(int tipoExcipienteId) {
		this.tipoExcipienteId = tipoExcipienteId;
	}

	public String getTipoExcipiente() {
		return tipoExcipiente;
	}

	public void setTipoExcipiente(String tipoExcipiente) {
		this.tipoExcipiente = tipoExcipiente;
	}
}
