package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.AdapterUser;
import com.example.objeto.User;
import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Asignatura;
import com.example.sqllite.esquemas.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mostrar;
    private RecyclerView milista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDPROGRAMA",null,1);

        String[] columnasUsuario = Usuario.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String[] valoresCondicion = {};

        List<ContentValues> usuariosObtenidos = instancia.obtenerDatos(columnasUsuario,condicion,valoresCondicion,"usuario");

        ArrayList<User> listaUsuario = new ArrayList<>();

        if (usuariosObtenidos==null){
            Toast.makeText(this, "no se obtuvieron usuarios", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0 ; i < usuariosObtenidos.size();i++){
                ContentValues auxiliar = usuariosObtenidos.get(i);
                User nuevoUsuario = new User();
                for (String key : auxiliar.keySet()){
                    switch (key.toString()){
                        case Usuario.Esquema.ID:
                            nuevoUsuario.setId_usuario(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Usuario.Esquema.NOMBRE:
                            nuevoUsuario.setNombre(auxiliar.get(key).toString());
                            break;
                        case Usuario.Esquema.APEPATERNO:
                            nuevoUsuario.setApePaterno(auxiliar.get(key).toString());
                            break;
                        case Usuario.Esquema.APEMATERNO:
                            nuevoUsuario.setApeMaterno(auxiliar.get(key).toString());
                            break;
                        case Usuario.Esquema.EMAIL:
                            nuevoUsuario.setEmail(auxiliar.get(key).toString());
                            break;
                        case Usuario.Esquema.EDAD:
                            nuevoUsuario.setEdad(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Usuario.Esquema.DIRECCION:
                            nuevoUsuario.setDireccion(auxiliar.get(key).toString());
                            break;
                        case Usuario.Esquema.GENERO:
                            nuevoUsuario.setGenero(auxiliar.get(key).toString());
                            break;
                        case Usuario.Esquema.TELEFONO:
                            nuevoUsuario.setTelefono(Long.parseLong(auxiliar.get(key).toString()));
                            break;

                    }
                }
                listaUsuario.add(nuevoUsuario);
            }
        }

        milista = findViewById(R.id.miLista);
        milista.setHasFixedSize(true);
        LinearLayoutManager manejador = new LinearLayoutManager(this);
        AdapterUser adaptador = new AdapterUser(listaUsuario);

        milista.setLayoutManager(manejador);
        milista.setAdapter(adaptador);
    }


    public void obtenerDatosSimple(View view){

        OperacionesCRUD oper = new OperacionesCRUD(this,
                "BDPROGRAMA",
                null,
                1);
        String[] columnasUsuario = Usuario.Esquema.ALLCOLUMNAS;
        String condicion = Usuario.Esquema.ID+"= ?";
        String[] valoresCondicion = {""};

        List<ContentValues> resultado = new ArrayList<ContentValues>();
        resultado = oper.obtenerDatos(columnasUsuario,condicion,valoresCondicion,Usuario.Esquema.TABLE_NAME);

        if (resultado == null){
            mostrar.setText("No se obtuvo nada");
        }else{
            mostrar.setText("");
            for (int i = 0 ; i < resultado.size(); i++){
                ContentValues aux = resultado.get(i);
                for (String key : aux.keySet()){
                    mostrar.append(key + ": " + aux.get(key.toString())+ ", ");

                }
                mostrar.append("\n\n");
            }
        }
    }

    public void asignaturaActivity(View v){
        Intent i = new Intent(this, Asignaturas.class);
        startActivity(i);
    }

    public void agregarUsuario(View v){
        Intent i = new Intent(this,agregarUsuarioC.class);
        startActivity(i);
    }

}