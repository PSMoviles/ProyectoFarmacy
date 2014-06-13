package com.psm.farmacy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.SharedPreferences;

import com.psm.SqLite.FarmacyDataBase;
import com.psm.SqLite.SQLiteHelper;
import com.psm.model.*;
import com.psm.ui.*;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class HomeActivity extends Activity {

	private DrawerLayout dlMenu;
	private ListView dwMenu;	
	private ActionBarDrawerToggle toggle;	
	private OnItemClickListener MenuClickListener;
	private FarmacyDataBase db;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);		
		InicializarControles();
		//Funcion para generar el menu lateral
		Cargar10Proximos();
		db.TraerExcipiente();
		//db.TomasRecientes();
		
		VerificarUsuario();
		GenerarMenu();
		Cargar10Proximos();
	
	}
	private void InicializarMenuListener()
	{
		MenuClickListener= new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int SelectedId, long arg3) {
				Intent i = null;
				switch(SelectedId)
				{
					case 0:
						i = new Intent(HomeActivity.this, ProfileActivity.class);	
						break;
					case 1 :							
						break;
					case 2 :
						i = new Intent(HomeActivity.this, MedicationActivity.class);	
						break;
					case 3 :
						i = new Intent(HomeActivity.this, MapActivity.class);	
						break;
					case 4:
						i = new Intent(HomeActivity.this, ConfigurationActivity.class);
						break;
					case 5:
						i = new Intent(HomeActivity.this, LogoActivity.class);
						break;
					default:
						i=null;
				}
				if (i!=null)
				{
					startActivity(i);
				}
				//Toast.makeText(HomeActivity.this, "Entro al menu", Toast.LENGTH_SHORT).show();
				dlMenu.closeDrawers();
			}
		};
	}

	private void InicializarToggle()
	{
		toggle = new ActionBarDrawerToggle(
				this, // Activity
				dlMenu, // Panel del Navigation Drawer
				R.drawable.ic_drawer, // Icono que va a utilizar
				R.string.app_name, // Descripcion al abrir el drawer
				R.string.app_name // Descripcion al cerrar el drawer
				){
			public void onDrawerClosed(View view) {
				// Drawer cerrado
				getActionBar().setTitle(getResources().getString(R.string.app_name));
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				// Drawer abierto
				getActionBar().setTitle("Farmacy");
				invalidateOptionsMenu(); 
			}
		};
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (toggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		toggle.syncState();
	}
	
	public void GenerarMenu()
	{
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		InicializarMenuListener();
		dwMenu.setOnItemClickListener(MenuClickListener);
		dlMenu.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);	
		InicializarToggle();
		dlMenu.setDrawerListener(toggle);
					
		ArrayList<ItemHomeMenu> OptionsMenu= new ArrayList<ItemHomeMenu>();
		OptionsMenu.add(new ItemHomeMenu(1,getResources().getString(R.string.home_menuItem1),getResources().getDrawable(R.drawable.ecg)));
		OptionsMenu.add(new ItemHomeMenu(2,getResources().getString(R.string.home_menuItem2),getResources().getDrawable(R.drawable.cross2)));
		OptionsMenu.add(new ItemHomeMenu(3,getResources().getString(R.string.home_menuItem3),getResources().getDrawable(R.drawable.prescription)));
		OptionsMenu.add(new ItemHomeMenu(4,getResources().getString(R.string.home_menuItem4),getResources().getDrawable(R.drawable.bottle)));
		OptionsMenu.add(new ItemHomeMenu(5,getResources().getString(R.string.home_menuItem5),getResources().getDrawable(R.drawable.onlinepharmacycheck)));		
		OptionsMenu.add(new ItemHomeMenu(1,"Farmacy",getResources().getDrawable(R.drawable.caduceus)));
		ItemHomeMenuAdapter MenuAdapter = new ItemHomeMenuAdapter(this,OptionsMenu);
		dwMenu.setAdapter(MenuAdapter);
	}
	
	public void InicializarControles()
	{try
		{
			dwMenu = (ListView) findViewById(R.id.dwMenu);
			dlMenu = (DrawerLayout) findViewById(R.id.dlMenuLateral);
	
			db= new FarmacyDataBase(this);
		}
		catch(Exception ex)
		{
			Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG).show();
		}
	}
	
	public void Cargar10Proximos()
	{
		List<Toma> lista= db.TomasRecientes();
		if(lista!=null)
		{
			ArrayList<ItemHomeList> items=new ArrayList<ItemHomeList>(); 	
			for(Toma t : lista)
			{
				ItemHomeList i= new ItemHomeList();
				switch(t.getTipoExcipiente())
				{
					case 1:
						i.setIcon(getResources().getDrawable(R.drawable.syringe));
						break;						
					case 2:
						i.setIcon(getResources().getDrawable(R.drawable.syrup));
						break;
					case 3:
						i.setIcon(getResources().getDrawable(R.drawable.pill));
						break;
					case 4:
						i.setIcon(getResources().getDrawable(R.drawable.paste));
						break;
				}				
				i.setMedicina(i.getMedicina());
				i.setTratamiento(i.getTratamiento());
			}
			ItemHomeListAdapter adapt=new ItemHomeListAdapter(this,items);
		}
		
	}
	
	public void VerificarUsuario()
	{
		
		SharedPreferences settings = getSharedPreferences("Profile", MODE_PRIVATE);	
		int userid= settings.getInt("UserId",1);
		String user=settings.getString("User","FarmacyDefaultUser");
		int actualizado=settings.getInt("Actualizado",0);
		if(userid==1 && actualizado==0)
		{
			SharedPreferences usersettings = getSharedPreferences("FarmacyDefaultUser", MODE_PRIVATE);	
			SharedPreferences.Editor editor = usersettings.edit();
			editor.putInt("User",1);
			editor.putString("User","FarmacyDefaultUser");
			
			editor.putString("Language","es");
			editor.putString("Theme","White");
			editor.putBoolean("Notifications", true);
			editor.putBoolean("GPS", true);
			editor.putBoolean("Internet", true);
			editor.commit();
			editor=settings.edit();
			editor.putInt("Actualizado", 1);
			editor.putString("User","FarmacyDefaultUser");
			editor.commit();			
		}		
				
		SharedPreferences usersettings = getSharedPreferences(user, MODE_PRIVATE);
		String Lang=usersettings.getString("Language","en");
		Configuration c = new Configuration(getResources().getConfiguration());
		if(Lang.equals("fr"))
		{
			c.locale = Locale.FRENCH;
		}else if(Lang.equals("es"))
		{
			c.locale = Locale.ENGLISH;	
		}
	}
	
	

}
