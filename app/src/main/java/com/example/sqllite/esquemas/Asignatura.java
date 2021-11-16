package com.example.sqllite.esquemas;

import android.provider.BaseColumns;

public class Asignatura{


    public static abstract class Esquema implements BaseColumns {

        public static final String TABLE_NAME = "asignatura";

        public static final String ID = "id_asignatura";
        public static final String CODIGO = "codigo";
        public static final String DESCRIPCION = "descripcion";

        public static String[] ALLCOLUMNAS = {ID, CODIGO, DESCRIPCION};

        public static final String CREAR_TABLA_ASIGNATURA =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CODIGO + " TEXT," +
                        DESCRIPCION + " TEXT)";

        public static final String BORRA_TABLA_ASIGNATURA =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
