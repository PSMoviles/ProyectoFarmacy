package com.psm.farmacy;

import java.util.ArrayList;
import java.util.List;

import com.psm.SqLite.FarmacyDataBase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.psm.model.Usuario;;

public class ConfigurationActivity extends Activity {
	private FarmacyDataBase db;
	private Spinner ddlUsers;
	private Button btnAddUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuration);
		InizalizarComponentes();
		InicializarDDL();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.configuration, menu);
		return true;
	}
	
	private void InizalizarComponentes()
	{
		try {
			db = new FarmacyDataBase(this);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			Toast.makeText(this,ex.getMessage() ,Toast.LENGTH_LONG).show();
		}
		ddlUsers=(Spinner)findViewById(R.id.ddlUser);
		btnAddUser=(Button)findViewById(R.id.btnAdd);
		btnAddUser.setOnClickListener(btnAddUser_onClick);
	}
	
	private OnClickListener btnAddUser_onClick = new OnClickListener()
	{
		public void onClick(View v)
		{
			Intent i = new Intent(ConfigurationActivity.this, UserAddActivity.class);
			startActivity(i);			
		}
	};
	
	private void InicializarDDL()
	{		
		try
		{			
			List<String>elementos = new ArrayList<String>();			
			List<Usuario> lst= db.TraerUsuarios();
			for(Usuario usr : lst)
			{
				elementos.add(usr.getUsuarioId()+" "+usr.getUsuario());
			}			
			ArrayAdapter<Usuario> usrAdap= new ArrayAdapter<Usuario>(
					this,android.R.layout.simple_dropdown_item_1line,
					lst
					);
			ArrayAdapter<String> strAdap= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,elementos);			
		}
		catch(Exception ex)
		{
			Toast.makeText(this,ex.getMessage() ,Toast.LENGTH_LONG).show();			
		}
	}
}
