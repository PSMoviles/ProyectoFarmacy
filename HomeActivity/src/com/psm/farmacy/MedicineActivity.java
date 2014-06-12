package com.psm.farmacy;

import java.util.ArrayList;
import java.util.List;

import com.psm.SqLite.FarmacyDataBase;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MedicineActivity extends Activity {
	private int TratamientoId;
	private EditText txtMedicina;
	private EditText txtIndicacion;
	private Spinner ddlExcipiente;
	private Spinner ddlPeriodicidad1;
	private Spinner ddlPeriodicidad2;
	private FarmacyDataBase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicine);
		Bundle bundle = getIntent().getExtras();
		TratamientoId=bundle.getInt("TratamientoId");
		InicializarViews();
		InicializarDDL();
	}

	public void InicializarViews()
	{
		txtMedicina=(EditText) findViewById(R.id.txtMediicina);
		txtIndicacion=(EditText) findViewById(R.id.txtIndicacion);
		ddlExcipiente=(Spinner) findViewById(R.id.ddlExcipiente);
		ddlPeriodicidad1=(Spinner) findViewById(R.id.ddlPeriodicidad1);
		ddlPeriodicidad2=(Spinner) findViewById(R.id.ddlPeriodicidad2);
	}
	public void InicializarDDL()
	{
		List<String>elementos = new ArrayList<String>();
		elementos=db.TraerExcipiente();
		ArrayAdapter<String> strAdap= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,elementos);
		ddlExcipiente.setAdapter(strAdap);		
		elementos.clear();
		elementos=db.TraerPeriodicidad();
		ArrayAdapter<String> strAdap2= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,elementos);
		ddlPeriodicidad1.setAdapter(strAdap2);
		ddlPeriodicidad2.setAdapter(strAdap2);
	}

}
