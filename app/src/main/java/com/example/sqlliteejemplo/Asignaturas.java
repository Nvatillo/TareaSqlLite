package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.AdapterAsignaturas;
import com.example.adapter.AdapterUser;
import com.example.objeto.Asignature;
import com.example.objeto.User;
import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Asignatura;
import com.example.sqllite.esquemas.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Asignaturas extends AppCompatActivity {

    private RecyclerView milista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDPROGRAMA",null,1);

        String[] columnasUsuario = Asignatura.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String[] valoresCondicion = {};

        List<ContentValues> usuariosObtenidos = instancia.obtenerDatos(columnasUsuario,condicion,valoresCondicion,"asignatura");

        ArrayList<Asignature> listaAsignatura = new ArrayList<>();

        if (usuariosObtenidos==null){
            Toast.makeText(this, "no se obtuvieron asignaturas", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0 ; i < usuariosObtenidos.size();i++){
                ContentValues auxiliar = usuariosObtenidos.get(i);
                Asignature nuevoAsignatura = new Asignature();
                for (String key : auxiliar.keySet()){
                    switch (key.toString()){
                        case Asignatura.Esquema.ID:
                            nuevoAsignatura.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            nuevoAsignatura.setCodig(auxiliar.get(key).toString());
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            nuevoAsignatura.setDescripcion(auxiliar.get(key).toString());
                            break;

                    }
                }
                listaAsignatura.add(nuevoAsignatura);
            }
        }
        milista = findViewById(R.id.miListaAsignaturas);
        milista.setHasFixedSize(true);
        LinearLayoutManager manejador = new LinearLayoutManager(this);
        AdapterAsignaturas adaptador = new AdapterAsignaturas(listaAsignatura);

        milista.setLayoutManager(manejador);
        milista.setAdapter(adaptador);
    }


    public void agregarUsuario(View v){
        Intent i = new Intent(this,agregarUsuarioC.class);
        startActivity(i);
    }

    public void agregarAsignatura(View v){
        Intent i = new Intent(this,agregarAsignatura.class);
        startActivity(i);
    }

    public void volverMain(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}