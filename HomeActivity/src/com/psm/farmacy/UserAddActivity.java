package com.psm.farmacy;

import java.util.ArrayList;
import java.util.List;


import com.psm.SqLite.FarmacyDataBase;
import com.psm.model.Usuario;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserAddActivity extends Activity {

	Button btn_AddUser;
	EditText tv_Name;
	EditText tv_Correo;
	EditText tv_Age;
	Spinner sp_Sexo;
	private FarmacyDataBase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_addr);
		
		btn_AddUser = (Button)findViewById(R.id.btnAdd);
		tv_Name = (EditText)findViewById(R.id.etNombre);
		tv_Correo = (EditText)findViewById(R.id.etCorreo);
		tv_Age = (EditText)findViewById(R.id.etEdad);
		sp_Sexo = (Spinner)findViewById(R.id.spSexo);
		List<String>elements = new ArrayList<String>();
		Resources res = getResources();
		elements.add(res.getString(R.string.str_Masculino));
		elements.add(res.getString(R.string.str_Femenino));
		ArrayAdapter<String> strAdap= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,elements);
		//usrAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_Sexo.setAdapter(strAdap);
		btn_AddUser.setOnClickListener(btnAdd_onClick);
		
	}

	
	private OnClickListener btnAdd_onClick = new OnClickListener()
	{
		public void onClick(View v)
		{
			//if(tv_Name.getText().equals(""))
			//{
			//	Toast.makeText(UserAddActivity.this, "Cancelations", Toast.LENGTH_SHORT).show();
			//}else{
			//	Toast.makeText(UserAddActivity.this, tv_Name.getText(), Toast.LENGTH_SHORT).show();
			//}
			db.AgregarUsuario(new Usuario(0,tv_Name.getText().toString(),Integer.parseInt(tv_Age.getText().toString()),
					tv_Correo.getText().toString(),
					sp_Sexo.getSelectedItem().toString()));
			
		}
	};

}
