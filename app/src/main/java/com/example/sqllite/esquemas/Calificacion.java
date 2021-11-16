package com.example.sqllite.esquemas;

import android.provider.BaseColumns;

public class Calificacion  {

    public static abstract class Esquema implements BaseColumns {

    public static final String TABLE_NAME = "calificacion";

    public static final String ID="id_calificacion";
    public static final String ID_USUARIO="id_usuario";
    public static final String ID_ASIGNATURA="id_asignatura";
    public static final String FECHA="fecha";
    public static final String CALIFICACION="calificacion";
    public static final String COMENTARIO="comentario";

    public static String[] ALLCOLUMNAS = {ID,ID_USUARIO,ID_ASIGNATURA,FECHA,CALIFICACION,COMENTARIO};

    public static final String CREAR_TABLA_CALIFICACION =
            "CREATE TABLE "+ TABLE_NAME + " ("+
                    ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ID_ASIGNATURA+" INTEGER,"+
                    ID_USUARIO + " INTEGER,"+
                    FECHA+ " TEXT,"+
                    CALIFICACION+" TEXT,"+
                    COMENTARIO + " TEXT)";

    public static final String BORRAR_TABLA_CLACIFICACION =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
}
