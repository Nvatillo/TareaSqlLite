package com.example.sqllite.esquemas;

import android.provider.BaseColumns;

public class Usuario {

    public static abstract class Esquema implements BaseColumns{
        public static final String TABLE_NAME = "usuario";

        public static final String ID = "id_usuario";
        public static final String NOMBRE = "nombre";
        public static final String APEPATERNO = "apePaterno";
        public static final String APEMATERNO = "apeMaterno";
        public static final String EMAIL = "email";
        public static final String EDAD = "edad";
        public static final String DIRECCION = "direccion";
        public static final String GENERO = "genero";
        public static final String TELEFONO = "telefono";

        public static final String[] ALLCOLUMNAS = {ID,NOMBRE,APEPATERNO,APEMATERNO,EMAIL,EDAD,DIRECCION,GENERO,TELEFONO};


        public static final String CREAR_TABLA_USUARIO =
                "CREATE TABLE "+TABLE_NAME+"("+
                        ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        NOMBRE+" TEXT,"+
                        APEPATERNO+" TEXT,"+
                        APEMATERNO+" TEXT,"+
                        EMAIL+" TEXT,"+
                        EDAD+" INTEGER,"+
                        DIRECCION+" TEXT,"+
                        GENERO+" TEXT,"+
                        TELEFONO+" INTEGER)";

        public static final String BORRAR_TABLA_USUARIO =
                "DROP TABLE IF EXISTS"+TABLE_NAME;

    }
}
