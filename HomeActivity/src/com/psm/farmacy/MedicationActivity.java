package com.psm.farmacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.psm.ui.MedicationPageAdapter;
import com.psm.ui.TabMedicationAdd;
import com.psm.ui.TabMedicationCurrent;
import com.psm.ui.TabMedicationHistory;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabContentFactory;
import android.widget.Button;
import android.widget.TextView;
import com.psm.model.*;
import com.psm.SqLite.*;

public class MedicationActivity extends FragmentActivity

implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener
{	
	private MedicationPageAdapter pageAdapter;
	private TabHost mTabHost;
	private ViewPager pager;
	private Button btnAdd;
	private Button btnRegTratamiento;
	private EditText txtTratamiento;
	private EditText txtCausa;
	private TextView lblLegend;
	private ListView lstMedicamentos;
	private Button btnGuardar;
	private FarmacyDataBase db;
	private Boolean AgregandoTratamiento;
	
	private OnClickListener btnAddListener;
	private OnClickListener btnRegTratListener;
	private int TratamientoId;
	
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, MedicationActivity.TabInfo>();
	
	class TabInfo {
        private String tag;
        private Class<?> clss;
        private Bundle args;
        private Fragment fragment;
        TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }
	}
	
	class TabFactory implements TabContentFactory {		 
	        private final Context mContext;	 
	        public TabFactory(Context context) {
	            mContext = context;
	        }
	 
	        public View createTabContent(String tag) {
	            View v = new View(mContext);
	            v.setMinimumWidth(0);
	            v.setMinimumHeight(0);
	            return v;
	        }	 
	    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication);
		List<Fragment> fragments = getFragments();
		pageAdapter = new MedicationPageAdapter(getSupportFragmentManager(), fragments);
		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);	
		pager.setOnPageChangeListener(this);
		
		this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
        AgregandoTratamiento=false;        		
        btnAddOnclickListener();
        btnRegtratamiento();   
        //Instancia de la base de datos
        try {
			db = new FarmacyDataBase(this);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			Toast.makeText(this,ex.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}

	private void btnAddOnclickListener()
	{
		this.btnAddListener= new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i;
				i = new Intent(MedicationActivity.this, MedicineActivity.class);	
				if (i!=null)
				{
					i.putExtra("TratamientoId", TratamientoId);
					startActivity(i);
				}				
			}
		};		
	}
	
	private void btnRegtratamiento()
	{
		this.btnRegTratListener= new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//Proceso de la base de datos
				SharedPreferences settings = getSharedPreferences("Profile", MODE_PRIVATE);	
				Tratamiento t= new Tratamiento();
				t.setEnfermedad(txtCausa.getText().toString());
				t.setTratamiento(txtTratamiento.getText().toString());
				t.setUsuarioId(settings.getInt("UserId", 1));
				
				if(txtCausa.getText().toString().equals("")|| txtTratamiento.getText().toString().equals(""))
				{
					Toast.makeText(MedicationActivity.this, R.string.str_TratatmientoCampos, Toast.LENGTH_LONG).show();
				}
				else
				{
					if(db.AgregarTratamiento(t))
					{
						AgregandoTratamiento=true;
						Toast.makeText(MedicationActivity.this, R.string.str_TratatmientoExitoso, Toast.LENGTH_LONG).show();
						//---------
						txtTratamiento.setEnabled(false);
						txtCausa.setEnabled(false);
						btnRegTratamiento.setEnabled(false);
						btnAdd.setVisibility(View.VISIBLE);
						lblLegend.setVisibility(View.VISIBLE);
						lstMedicamentos.setVisibility(View.VISIBLE);		
						btnGuardar.setVisibility(View.VISIBLE);	
						TratamientoId= db.MaxTratamientoId();	
						ActualizarListaMedicinas();
					}else
					{
						Toast.makeText(MedicationActivity.this, R.string.str_TratatmientoError, Toast.LENGTH_LONG).show();					
					}
				}						
			}
		};
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.medication, menu);
		return true;
	}
	
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();
		fList.add(TabMedicationHistory.newInstance("Fragment 1"));
		fList.add(TabMedicationCurrent.newInstance("Fragment 2"));
		fList.add(TabMedicationAdd.newInstance("Fragment 3"));
		return fList;
	}
	
	protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }
	
	private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;
        MedicationActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Historial").setIndicator("Historial"), ( tabInfo = new TabInfo("Historial", TabMedicationHistory.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        MedicationActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("EnCurso").setIndicator("En curso"), ( tabInfo = new TabInfo("EnCurso", TabMedicationCurrent.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        MedicationActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Añadir").setIndicator("Añadir"), ( tabInfo = new TabInfo("Añadir", TabMedicationAdd.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);        
        mTabHost.setOnTabChangedListener(this);
    }
	
	private static void AddTab(MedicationActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int index) {
		// TODO Auto-generated method stub
		this.mTabHost.setCurrentTab(index);
		
	}

	@Override
	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		int pos = this.mTabHost.getCurrentTab();
        this.pager.setCurrentItem(pos);
        
        switch(pos)
        {
        	case 2:
        		ProcesoTabAdd();
        		break;       
        }       
	}
	
	private void ProcesoTabAdd()
	{
		btnRegTratamiento= (Button) findViewById(R.id.btnRegTratamiento);		
		txtTratamiento=(EditText) findViewById(R.id.txtMediicina);
		txtCausa=(EditText) findViewById(R.id.txtIndicacion);
		lblLegend=(TextView) findViewById(R.id.lblMedicinax);
		lstMedicamentos=(ListView) findViewById(R.id.lstMedicamentos);
		btnGuardar=(Button)findViewById(R.id.btnGuardar);
		btnAdd= (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(btnAddListener);
		btnRegTratamiento.setOnClickListener(btnRegTratListener);
		//Ocultar los demas controles
		if(AgregandoTratamiento)
		{
			
		}
		else
		{
			txtTratamiento.setVisibility(View.VISIBLE);
			txtTratamiento.setEnabled(true);
			txtCausa.setEnabled(true);
			btnAdd.setVisibility(View.INVISIBLE);
			lblLegend.setVisibility(View.INVISIBLE);
			lstMedicamentos.setVisibility(View.INVISIBLE);		
			btnGuardar.setVisibility(View.INVISIBLE);
		}
				
	}
	
	private void ActualizarListaMedicinas()
	{
		List<String>elementos = new ArrayList<String>();
		db.TraerMedicinas(TratamientoId);
		for(Medicina med : db.TraerMedicinas(TratamientoId))
		{
			elementos.add(med.getMedicina());
		}	
		ArrayAdapter<String> strAdap= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,elementos);
		lstMedicamentos.setAdapter(strAdap);
	}
}
