package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapter.AdapterAsignaturas;
import com.example.objeto.Asignature;
import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Asignatura;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarioAsignatura extends AppCompatActivity {

    private int id_usuario;
    private Spinner asignaturaAgregar;
    private RecyclerView listaUserAsignatura;
    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;
    ArrayList<Asignature> listaAsignatura = new ArrayList<>();
    OperacionesCRUD instancia;
    List<String> dataSpinner = new ArrayList<>();
    ArrayAdapter adaptadorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario_asignatura);

        if (null != this.getIntent()){
            if (null != this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_usuario = parametrosEntrada.getInt("id");
            }
        }
        asignaturaAgregar = findViewById(R.id.userAsignaturas);
        listaUserAsignatura = findViewById(R.id.listaUserAsignaturas);
        instancia = new OperacionesCRUD(this,"BDPROGRAMA",null,1);

         llenarSpinner();
         llenarReciclerView();
    }


    public void llenarSpinner(){
        String columnaObtenerSpinner[] = {Asignatura.Esquema.ID,Asignatura.Esquema.CODIGO,Asignatura.Esquema.DESCRIPCION};
        String condicionSpinner = Asignatura.Esquema.ID + " not in (select id_asignatura from usuario_asignatura where id_usuario = ?)";
        String valoresCondicionesSpinner[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturasNoAsociadas = instancia.obtenerDatos(columnaObtenerSpinner,condicionSpinner,valoresCondicionesSpinner,Asignatura.Esquema.TABLE_NAME);

        if (asignaturasNoAsociadas==null){
            Toast.makeText(this, "no se obtuvieron asignaturas para agregar desde BD", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0 ; i < asignaturasNoAsociadas.size() ; i++){
                ContentValues auxiliar = asignaturasNoAsociadas.get(i);
                String opcionSpiner = "";

                for (String key : auxiliar.keySet()){
                    opcionSpiner+= auxiliar.get(key).toString()+":";

                }
                dataSpinner.add(opcionSpiner);
            }
        }

        adaptadorSpinner = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dataSpinner);
        asignaturaAgregar.setAdapter(adaptadorSpinner);
    }

    public void llenarReciclerView(){

        String columnaObtener[]  = {Asignatura.Esquema.ID,Asignatura.Esquema.CODIGO,Asignatura.Esquema.DESCRIPCION};

        String condicion = Asignatura.Esquema.ID + " in (select id_asignatura from usuario_asignatura where id_usuario = ?)";

        String valoresCondicion[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturaUsuario = instancia.obtenerDatos(columnaObtener,condicion,valoresCondicion,Asignatura.Esquema.TABLE_NAME);

        if (asignaturaUsuario == null){
            Toast.makeText(this, "No se obtuvioeron asignaturas asociadas al usuario", Toast.LENGTH_SHORT).show();
        }else{
            for (int i =  0 ; i< asignaturaUsuario.size() ; i++){
                ContentValues auxiliar =  asignaturaUsuario.get(i);
                Asignature nuevaAsignatura = new Asignature();
                for (String key : auxiliar.keySet()){
                    switch (key.toString()){
                        case Asignatura.Esquema.ID:
                            nuevaAsignatura.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            nuevaAsignatura.setCodig(auxiliar.get(key).toString());
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            nuevaAsignatura.setDescripcion(auxiliar.get(key).toString());
                            break;
                    }
                }
                listaAsignatura.add(nuevaAsignatura);
            }
        }
        listaUserAsignatura.setHasFixedSize(true);
        manejador = new LinearLayoutManager(this);
        adaptador = new AdapterAsignaturas(listaAsignatura);

        listaUserAsignatura.setLayoutManager(manejador);
        listaUserAsignatura.setAdapter(adaptador);
    }

    public void addAsignaturaUser(View v){

        ContentValues nuevo_userAsignatura = new ContentValues();

        nuevo_userAsignatura.put("id_usuario",id_usuario);

        String itemseleccionadoSpinner = asignaturaAgregar.getSelectedItem().toString();

        int posicionItemSeleccionado = asignaturaAgregar.getSelectedItemPosition();

        String dataItem[] = itemseleccionadoSpinner.split(":");
        nuevo_userAsignatura.put("id_asignatura",dataItem[2]);

        long ret = instancia.insertTabla(nuevo_userAsignatura,Asignatura.Esquema.TABLE_NAME);

        if (ret == 0){
            Toast.makeText(this, "no logro insertar usuario asignatura", Toast.LENGTH_SHORT).show();
        }else{
            Asignature nueva = new Asignature(dataItem[0],dataItem[1],Integer.parseInt(dataItem[2]));
            listaAsignatura.add(nueva);
            adaptador = new AdapterAsignaturas(listaAsignatura);

            listaUserAsignatura.setAdapter(adaptador);

            dataSpinner.remove(posicionItemSeleccionado);
            adaptadorSpinner = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dataSpinner);
            asignaturaAgregar.setAdapter(adaptadorSpinner);
        }
    }
}