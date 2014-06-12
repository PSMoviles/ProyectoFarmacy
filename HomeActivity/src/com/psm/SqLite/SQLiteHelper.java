package com.psm.SqLite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Farmacy.db";
	private static final String CREATE_USUARIO=
			"CREATE TABLE tbl_usuario(" +
			"UsuarioId INTEGER PRIMARY KEY AUTOINCREMENT," +
			"Usuario TEXT NULL," +
			"Edad  INTEGER NULL," +
			"Sexo TEXT NULL," +
			"Correo TEXT NULL" +
			")";	
	private static final String CREATE_TIPOEXCIPIENTE=
			"CREATE TABLE tbl_tipoExcipiente ( " +
			"TipoExcipienteId INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"TipoExcipiente TEXT " +								
			");";	
	private static final String CREATE_PERIODICIDAD=
			"CREATE TABLE tbl_tipoPeriodicidad (" +
			"TipoPeriodicidadId INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"TipoPeriodicidad TEXT " +
			");" +
			"";	
	private static final String CREATE_MEDICINA=
			"CREATE TABLE tbl_medicina (" +
			"MedicinaId INTEGER PRIMARY KEY," +
			"Medicina TEXT ," +
			"NombreComercial TEXT ," +
			"IndicacionTerapeutica TEXT ," +
			"TipoExcipienteId INT" +
			");";
	private static final String CREATE_TRATAMIENTO=
			"CREATE TABLE tbl_tratamiento ( " +
			"TratamientoId INTEGER PRIMARY KEY, " +
			"Tratamiento TEXT NOT NULL, " +
			"Enfermedad TEXT NOT NULL, " +
			"UsuarioId INTEGER ," +
			"FecAlta TEXT" +
			")"
			;
	private static final String CREATE_MEDICINATRATAMIENTO=
			"CREATE TABLE tbl_MedicinaTratamiento ( " +
			"RelacionId INTEGER PRIMARY KEY AUTOINCREMENT," +
			"TratamientoId INTEGER NOT NULL ," +
			"MedicinaId INTEGER NOT NULL, " +
			"DosisExcipiente INTGER NOT NULL, " +
			"FecIni TEXT NOT NULL, " +
			"TipoPeriodicidadDuracion INTEGER, " +
			"PeriodosDuracion INTEGER, " +
			"TipoPeriodicidadIntervalo INTEGER, " +
			"PeriodosIntervalo INTEGER," +
			"TomasTotales INTEGER);";

	private static final String CREATE_HORARIO=
			"CREATE TABLE tbl_horario(" +
			"RelacionId INTEGER," +
			"TomaNo INTEGER," +
			"Fecha TEXT," +
			"Hora TEXT," +
			"Tomada INTEGER," +
			"Reprogramada INTEGER" +
			")" +
			"";
	
//	private static final String
			
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		try
		{	//Se agrega default para probar un ususario default con un tratamiento de 2 medicinas 
			//para muestra y prueba de datos
			//Crea la tabla usario y carga el usuario default
			database.execSQL(CREATE_USUARIO);
			database.execSQL("Insert into tbl_usuario(Usuario,Edad,Sexo,Correo)" +"VALUES('FarmacyDefaultUser',12,'Undefined','farmacy@getbetter.com')");
			//Crea la tabla excipiente y carga los valores predeterminados
			database.execSQL(CREATE_TIPOEXCIPIENTE);
			database.execSQL("Insert into tbl_tipoExcipiente(TipoExcipiente)VALUES('Inyección')");
			database.execSQL("Insert into tbl_tipoExcipiente(TipoExcipiente)VALUES('Jarabe')");
			database.execSQL("Insert into tbl_tipoExcipiente(TipoExcipiente)VALUES('Pastilla')");
			database.execSQL("Insert into tbl_tipoExcipiente(TipoExcipiente)VALUES('Pomada')");
			//Se crea la tabla tipoPeriodicidad y sus valores
			database.execSQL(CREATE_PERIODICIDAD);
			database.execSQL("Insert into tbl_tipoPeriodicidad(tipoPeriodicidad) VALUES('Horas')");
			database.execSQL("Insert into tbl_tipoPeriodicidad(tipoPeriodicidad) VALUES('Días')");
			database.execSQL("Insert into tbl_tipoPeriodicidad(tipoPeriodicidad) VALUES('Semanas')");
			database.execSQL("Insert into tbl_tipoPeriodicidad(tipoPeriodicidad) VALUES('Quincenas')");
			database.execSQL("Insert into tbl_tipoPeriodicidad(tipoPeriodicidad) VALUES('Meses')");
			database.execSQL("Insert into tbl_tipoPeriodicidad(tipoPeriodicidad) VALUES('Años')");
			//Se crea la tabla medicina y se agregan 2 por default para el ejemplo
			database.execSQL(CREATE_MEDICINA);
			database.execSQL("Insert into tbl_medicina(Medicina,NombreComercial,IndicacionTerapeutica,TipoExcipienteId) VALUES('Paracetamol','Tempra','Medicina general para dolores',2)");
			database.execSQL("Insert into tbl_medicina(Medicina,NombreComercial,IndicacionTerapeutica,TipoExcipienteId) VALUES('Diclofenaco','Diclofenaco','Medicina general para dolores',3)");
			//crea la tabla tratamiento y agrega ejemplos
			database.execSQL(CREATE_TRATAMIENTO);
			database.execSQL("Insert into tbl_tratamiento(Tratamiento,Enfermedad,UsuarioId,FecAlta) VALUES('Tratamiento para gripa','Gripa',1,'01/01/2014')");
			//Creacion de la tabla relacion tratamiento medicina
			database.execSQL(CREATE_MEDICINATRATAMIENTO);
			database.execSQL("Insert into tbl_MedicinaTratamiento(TratamientoId,MedicinaId,DosisExcipiente,FecIni," +
					"TipoPeriodicidadDuracion,PeriodosDuracion,TipoPeriodicidadIntervalo,PeriodosIntervalo,TomasTotales) " +
					"VALUES(1,2,1,'01/20/2014',3,1,1,8,25)");
			database.execSQL("Insert into tbl_MedicinaTratamiento(TratamientoId,MedicinaId,DosisExcipiente,FecIni," +
					"TipoPeriodicidadDuracion,PeriodosDuracion,TipoPeriodicidadIntervalo,PeriodosIntervalo,TomasTotales) " +
					"VALUES(1,2,1,'01/20/2014',3,1,1,8,25)");
			//crea tabla horario y trae las primeras 2 tomas de cada una
			database.execSQL(CREATE_HORARIO);
			database.execSQL("Insert into tbl_horario(RelacionId,TomaNo,Fecha,Hora,Tomada,Reprogramada) VALUES(1,1,'01/02/2014','08:01:00',0,0)");
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS tbl_usuario");
		onCreate(db);
	}
}
