package com.psm.SqLite;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.psm.model.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//generar updates
//db.update("NOTAS", values, "ID = ?", params );

//generar deletes
//db.delete("NOTAS", "ID = ?", params);
public class FarmacyDataBase {
	private SQLiteHelper dbHelper;
	private SQLiteDatabase database;	
	private ContentValues values;
	private String query;
	
	public FarmacyDataBase(Context context) throws SQLException{
		dbHelper= new SQLiteHelper(context);
		values= new ContentValues();
	}
	
	private void OpenToWrite()
	{
		this.database=this.dbHelper.getWritableDatabase();
	}
	
	private void OpenToRead()
	{
		this.database=this.dbHelper.getWritableDatabase();
	}
	
	private void Close()
	{		
		if(this.database.isOpen())
			this.database.close();
	}
	
	public List<Usuario> TraerUsuarios()
	{
		List<Usuario>Lista= new ArrayList<Usuario>();
		try
		{	
			query="Select * from tbl_usuario";
			OpenToRead();
			
			Cursor cursor= database.rawQuery(query, null);
			if(cursor.isBeforeFirst())
			{
				cursor.moveToNext();
				while(!cursor.isAfterLast())
				{
					Lista.add(
							new Usuario(
									cursor.getInt(cursor.getColumnIndex("UsuarioId")),
									cursor.getString(cursor.getColumnIndex("Usuario")),
									cursor.getInt(cursor.getColumnIndex("Edad")),
									cursor.getString(cursor.getColumnIndex("Correo")),
									cursor.getString(cursor.getColumnIndex("Sexo"))					
								)
							);
					cursor.moveToNext();
				}
			}
			
		Close();
		}catch(Exception ex)
		{
			String msj=ex.getMessage();
		}
		return Lista;
	}
	
	public Boolean AgregarUsuario(Usuario usr)
	{
		try
		{			
			OpenToWrite();
			values.clear();
			values.put("Usuario", usr.getUsuario());
			values.put("Edad", usr.getEdad());
			values.put("Correo", usr.getCorreo());
			values.put("Sexo", usr.getSexo());
			database.insert("tbl_usuario", null, values);
			Close();
			return true;
		}catch(Exception ex)
		{
			Close();
			return false;					
		}
				
	}
	
	public Boolean AgregarMedicina(Medicina med)
	{
		try
		{
		OpenToWrite();
		values.clear();
		values.put("MEDICINA", med.getMedicina());
		values.put("NOMBRECOMERCIAL", med.getNombreComercial());
		values.put("INDICACIONTERAPEUTICA", med.getIndicacionTerapeutica());
		values.put("TIPOEXCIPIENTEID", med.getTipoExcipienteId());
		values.put("TIPOEXCIPIENTE", med.getTipoExcipiente());
		database.insert("MEDICINA",null,values);
		Close();
		return true;
		}
		catch(Exception ex)
		{
			Close();
			return false;
		}
	}
	
	public Boolean AgregarTratamiento(Tratamiento tra)
	{
		try
		{
			OpenToWrite();			
			query="Insert into tbl_tratamiento(Tratamiento,Enfermedad,UsuarioId,FecAlta) " +
					"VALUES('"+ tra.getTratamiento()+"','"+tra.getEnfermedad()+"',"+tra.getUsuarioId()+",'"+tra.getFecha()+"')";
			database.execSQL(query);
			
			/*if(value==-1)
			{
				return true;
			}
			database.insert("TRATAMIENTO",null,values);	
			query="select * from tbl_tratamiento";
			Cursor cursor=database.rawQuery(query, null);
			cursor.moveToFirst();*/
					
			Close();
			return true;
		}
		catch(Exception ex)
		{
			Close();
			return false;
		}
	}
	
	public List<Medicina> TraerMedicinas(int tra)
	{
		OpenToRead();
		query="SELECT * FROM MEDICINA";
		Cursor cursor = database.rawQuery(query, null);
		List<Medicina> Lista= new ArrayList<Medicina>();
		while(!cursor.isAfterLast())
		{
			Lista.add(
					new Medicina(
							cursor.getInt(cursor.getColumnIndex("MEDICINAID")),
							cursor.getString(cursor.getColumnIndex("MEDICINA")),
							cursor.getString(cursor.getColumnIndex("NOMBRECOMERCIAL")),
							cursor.getString(cursor.getColumnIndex("INDICACIONTERAPEUTICA")),
							cursor.getInt(cursor.getColumnIndex("TIPOEXCIPIENTEID")),
							cursor.getString(cursor.getColumnIndex("TIPOEXCIPIENTE"))							
							)
					);
			cursor.moveToNext();
		}						
		Close();
		return Lista;
	}
	
	public List<Tratamiento> TraerTratamientos(int usr)
	{
		String[] params={Integer.toString(usr)};
		query="SELECT * FROM TRATAMIENTO WHERE USUARIOID=?";		
		OpenToRead();		
		Cursor cursor = database.rawQuery(query,params);
		Close();
		List<Tratamiento> Lista= new ArrayList<Tratamiento>();
		while(!cursor.isAfterLast())
		{
			Lista.add(
					new Tratamiento(
							cursor.getInt(cursor.getColumnIndex("TRATAMIENTOID")),
							cursor.getString(cursor.getColumnIndex("TRATAMIENTO")),
							cursor.getString(cursor.getColumnIndex("ENFERMEDAD")),
							cursor.getInt(cursor.getColumnIndex("USUARIOID"))				
							)
					);
			cursor.moveToNext();
		}			
		return Lista;
	}	
	
	public List<Toma> TraerTomasPorTratamiento()
	{
		List<Toma> Lista= new ArrayList<Toma>();
		OpenToRead();
		Close();
		return Lista;		
	}
	
	public List<Toma> TraerTomasProximas(int top)
	{
		List<Toma> Lista= new ArrayList<Toma>();
		OpenToRead();
		String sql="Select RelacionId,TratamientoId,Tratamiento,MedicinaId,Medicina,TomaNo,Fecha,Hora,Tomada,Reprogramada from tbl_horario " +
				"inner join tbl_medicina on tbl_horario.MedicinaId=tbl_medicina.MedicinaId" +
				"inner join tbl_tratamiento on tbl_horario.TratamientoId=tbl_tratamiento.TratamientoId LIMIT 10 order by Fecha desc";
		Cursor cursor =database.rawQuery(sql, null);
		if(cursor.isBeforeFirst())
		{
			cursor.moveToFirst();
			while(!cursor.isAfterLast())
			{
				try{
					Lista.add(new Toma(cursor.getInt(cursor.getColumnIndex("RelacionId")),
							cursor.getInt(cursor.getColumnIndex("MedicinaId")), 
							cursor.getString(cursor.getColumnIndex("Medicina")), 
							cursor.getInt(cursor.getColumnIndex("TratamientoId")),
							cursor.getString(cursor.getColumnIndex("Tratamiento")),
							new SimpleDateFormat("dd/MMM/yyyy").parse((cursor.getString(cursor.getColumnIndex("Fecha")))),
							new SimpleDateFormat("hh:mm").parse((cursor.getString(cursor.getColumnIndex("Fecha")))),
							cursor.getInt(cursor.getColumnIndex("tomano")),
							Boolean.parseBoolean(Integer.toString((cursor.getInt(cursor.getColumnIndex("Tomada"))))),
							Boolean.parseBoolean(Integer.toString((cursor.getInt(cursor.getColumnIndex("Reprogramada")))))
							));
				}catch(Exception ex)
				{
					
				}
				}
		}		
		Close();
		return Lista;	
	}
	
	public int MaxTratamientoId()
	{
		OpenToRead();
		int TratamientoId=0;
		query="Select MAX(TratamientoId) from tbl_tratamiento";	
		Cursor cursor= database.rawQuery(query, null);
		if(cursor.isBeforeFirst())
		{
			cursor.moveToNext();
			TratamientoId=cursor.getInt(cursor.getColumnIndex("TratamientoId"));
		}
		Close();
		return TratamientoId;
	}
	
	//Funcionando
	public List<String> TraerExcipiente()
	{
		OpenToRead();
		query="SELECT TipoExcipienteId, TipoExcipiente FROM tbl_tipoExcipiente";
		Cursor cursor = database.rawQuery(query, null);
		List<String> Lista= new ArrayList<String>();
		if(cursor.moveToFirst())
		{
			while(!cursor.isAfterLast())
			{
				int i=cursor.getInt(cursor.getColumnIndex("TipoExcipienteId"));
				Lista.add(cursor.getString(cursor.getColumnIndex("TipoExcipiente")));
				cursor.moveToNext();
			}	
		}
		Close();
		return Lista;
	}
	public List<String> TraerPeriodicidad()
	{
		OpenToRead();
		query="SELECT tipoPeriodicidad FROM tbl_tipoPeriodicidad";
		Cursor cursor = database.rawQuery(query, null);
		List<String> Lista= new ArrayList<String>();
		while(!cursor.isAfterLast())
		{
			Lista.add(cursor.getString(cursor.getColumnIndex("TipoExcipiente")));
			cursor.moveToNext();
		}						
		Close();
		return Lista;
	}
	
	//Funcionando
	public List<Toma> TomasRecientes()
	{
		try
		{
			OpenToRead();			
			query="Select a.RelacionId," +
					"a.TomaNo," +
					"a.Fecha," +
					"a.Hora," +
					"a.Tomada," +
					"a.Reprogramada," +
					"c.TratamientoId," +
					"c.Tratamiento," +
					"d.MedicinaId," +
					"d.Medicina," +
					"d.TipoExcipienteId " +
					"from tbl_horario a " +
					"inner join tbl_MedicinaTratamiento b on a.RelacionId=b.RelacionId " +
					"inner join tbl_tratamiento c on b.TratamientoId=c.TratamientoId " +
					"inner join tbl_medicina d on b.MedicinaId=d.MedicinaId " +
					"limit 3";
			Cursor cursor = database.rawQuery(query, null);
			List<Toma> lista= new ArrayList<Toma>();
			if(cursor.moveToFirst())
			{
				while(!cursor.isAfterLast())
				{
					Toma take= new Toma();
					take.setRelacionId(cursor.getInt(cursor.getColumnIndex("RelacionId")));
					take.setMedicina(cursor.getString(cursor.getColumnIndex("Medicina")));
					take.setMedicinaid(cursor.getInt(cursor.getColumnIndex("MedicinaId")));
					take.setTratamientoId(cursor.getInt(cursor.getColumnIndex("TratamientoId")));
					take.setTratamiento(cursor.getString(cursor.getColumnIndex("Tratamiento")));
					//take.setFecha(cursor.getString(cursor.getColumnIndex("Fecha")));
					//take.setHora(cursor.getString(cursor.getColumnIndex("Hora")));
					take.setTomNo(cursor.getInt(cursor.getColumnIndex("TomaNo")));
					take.setTipoExcipiente(cursor.getInt(cursor.getColumnIndex("TipoExcipienteId")));
					int tomada= cursor.getInt(cursor.getColumnIndex("Tomada"));
					int reprogramada=cursor.getInt(cursor.getColumnIndex("Reprogramada"));
					if(tomada==1)					
					take.setTomada(true);
					if(reprogramada==1)
					take.setReprogramada(true);
					cursor.moveToNext();
					lista.add(take);
				}		
			}
			Close();
			return lista;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
