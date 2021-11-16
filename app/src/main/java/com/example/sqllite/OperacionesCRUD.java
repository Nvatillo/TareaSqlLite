package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.sqllite.esquemas.Asignatura;
import com.example.sqllite.esquemas.Calificacion;
import com.example.sqllite.esquemas.UsrAsig;
import com.example.sqllite.esquemas.Usuario;

import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUD extends SQLiteOpenHelper {
    public OperacionesCRUD(@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Usuario.Esquema.CREAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Asignatura.Esquema.CREAR_TABLA_ASIGNATURA);
        sqLiteDatabase.execSQL(Calificacion.Esquema.CREAR_TABLA_CALIFICACION);
        sqLiteDatabase.execSQL(UsrAsig.Esquema.CREAR_TABLA_USUARIO_ASIGNATURA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(UsrAsig.Esquema.BORRAR_TABLA_USUARIO_ASIGNATURA);
        sqLiteDatabase.execSQL(Calificacion.Esquema.BORRAR_TABLA_CLACIFICACION);
        sqLiteDatabase.execSQL(Usuario.Esquema.BORRAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Asignatura.Esquema.BORRA_TABLA_ASIGNATURA);

    }

    public long insertTabla(ContentValues colunmas_valores_insertar,String nombre_tabla){

        long id_reg_insertado = 0;
        try {
           SQLiteDatabase baseDatos = this.getWritableDatabase();
           id_reg_insertado = baseDatos.insert(nombre_tabla,null,colunmas_valores_insertar);
        }catch (Exception e){
            System.out.println("ERROR EN METODO INSERTAR :" + e.getMessage());
        }

        return id_reg_insertado;
    }


    public List<ContentValues> obtenerDatos(String columnasObtener[] , String columnasFiltro, String valoresFiltros[], String nombreTabla ){
        Cursor resultado = null;

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            List<ContentValues> listaDeRegistro = new ArrayList<ContentValues>();

            resultado = db.query(
                    nombreTabla,
                    columnasObtener,
                    columnasFiltro,
                    valoresFiltros,
                    null,
                    null,
                    null
            );

            if (null != resultado){

                resultado.moveToFirst();

                while (resultado.isAfterLast() == false){
                    ContentValues auxiliar = new ContentValues();
                    for (int i = 0 ; i< resultado.getColumnCount();i++){
                        auxiliar.put(resultado.getColumnName(i),resultado.getString(i));
                    }
                    listaDeRegistro.add(auxiliar);
                    resultado.moveToNext();
                }
            }
            resultado.close();
            return listaDeRegistro;
        }catch (Exception e){
            System.out.println("ERROR motodo obtener registro : " +e.getMessage() );
        }
        return null;
    }

    public List<ContentValues> obtenerDatosQuery(String query){
        Cursor resultado = null;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            List<ContentValues> listaDeRegistro = new ArrayList<ContentValues>();

            System.out.println("query: "+query );
            resultado=db.rawQuery(query,null);
            if (null!= resultado){
                System.out.println("Cantidad de registros : "+resultado.getColumnCount());
                resultado.moveToFirst();
                while (resultado.isAfterLast() == false){
                    ContentValues auxiliar = new ContentValues();
                    for (int i = 0; i<resultado.getColumnCount();i++){
                        auxiliar.put(resultado.getColumnName(i),resultado.getString(i));
                    }
                    listaDeRegistro.add(auxiliar);
                    resultado.moveToNext();
                }
            }
            resultado.close();
            return listaDeRegistro;
        }catch (Exception e){
            System.out.println("ERROR motodo obtener datos por query : " +e.getMessage() );
        }
        return null;
    }

    public int borrarRegistro(String nombre_trabla , String condicion, String[] val_condicion){
        int registros_eliminados = 0;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            registros_eliminados = db.delete(nombre_trabla,condicion,val_condicion);
        }catch (Exception e){
            System.out.println("Error metodo borrar" + e.getMessage());

        }
        return registros_eliminados;
    }


    public int actualizarRegistro(ContentValues columnas,String condicion, String[] condicion_valores,String nombre_tabla){
        int cantidad_actualizados = 0;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cantidad_actualizados = db.update(
                    nombre_tabla,
                    columnas,
                    condicion,
                    condicion_valores
            );
        }catch (Exception e){
            System.out.println("Error metodo actualizar registros:" +e.getMessage());
        }
        return cantidad_actualizados;
    }

}
