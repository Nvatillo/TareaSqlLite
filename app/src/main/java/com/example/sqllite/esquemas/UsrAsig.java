package com.example.sqllite.esquemas;

import android.provider.BaseColumns;

public class UsrAsig {

    public static abstract class Esquema implements BaseColumns {
        public static final String TABLE_NAME = "usuario_asignatura";

        public static final String ID="id_usr_asig";
        public static final String ID_USUARIO="id_usuario";
        public static final String ID_ASIGNATURA="id_asignatura";

        public static String[] ALLCOLUMNAS = {ID,ID_USUARIO,ID_ASIGNATURA};

        public static final String CREAR_TABLA_USUARIO_ASIGNATURA =
                "CREATE TABLE "+ TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        ID_USUARIO+" INTEGER," +
                        ID_ASIGNATURA+" INTEGER)";

        public static final String BORRAR_TABLA_USUARIO_ASIGNATURA =
                "DROP TABLE IF EXISTS "+ TABLE_NAME;
    }
}
