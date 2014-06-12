package com.psm.ui;
import android.graphics.drawable.*;

public class ItemNexTake {
	private int id;
	private String medicine;
	private String medication;
	private Drawable icon;
	
	public ItemNexTake(int id,String medicine,String medication,Drawable icon)
	{
		this.icon=icon;
		this.id= id;
		this.medication=medication;
		this.medicine=medicine;		
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
	 
}
