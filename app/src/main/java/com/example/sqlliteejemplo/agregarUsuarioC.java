package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Asignatura;
import com.example.sqllite.esquemas.Usuario;

public class agregarUsuarioC extends AppCompatActivity {

    private TextView nombre,apellidoPaterno,apellidoMaterno,email,edad,telefono,direccion;
    private RadioButton mas,fem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario_c);

        nombre = findViewById(R.id.Nombre);
        apellidoPaterno = findViewById(R.id.ApellidoPaterno);
        apellidoMaterno = findViewById(R.id.ApellidoMaterno);
        email = findViewById(R.id.email);
        edad = findViewById(R.id.edad);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);
        mas = findViewById(R.id.masculino);
        fem = findViewById(R.id.femenino);
    }

    public void insertUsuario(View v){
        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDPROGRAMA",null,1);

        ContentValues datosUsuario = new ContentValues();

        datosUsuario.put(Usuario.Esquema.NOMBRE,nombre.getText().toString());
        datosUsuario.put(Usuario.Esquema.APEPATERNO,apellidoPaterno.getText().toString());
        datosUsuario.put(Usuario.Esquema.APEMATERNO,apellidoMaterno.getText().toString());
        datosUsuario.put(Usuario.Esquema.DIRECCION,direccion.getText().toString());
        datosUsuario.put(Usuario.Esquema.EDAD,edad.getText().toString());
        datosUsuario.put(Usuario.Esquema.EMAIL,email.getText().toString());
        datosUsuario.put(Usuario.Esquema.GENERO,mas.isChecked() ? "MASCULINO" : "FEMENINO");
        datosUsuario.put(Usuario.Esquema.TELEFONO,telefono.getText().toString());

        long id_insertada= instancia.insertTabla(datosUsuario,Usuario.Esquema.TABLE_NAME);

        nombre.setText("");
        apellidoPaterno.setText("");
        apellidoMaterno.setText("");
        email.setText("");
        edad.setText("");
        telefono.setText("");
        direccion.setText("");
        mas.setChecked(false);
        fem.setChecked(false);
        Toast.makeText(this, "id insertado "+id_insertada, Toast.LENGTH_SHORT).show();


    }

    public void volver(View v){
        Intent i = new Intent(this, Asignaturas.class);
        startActivity(i);
    }
}