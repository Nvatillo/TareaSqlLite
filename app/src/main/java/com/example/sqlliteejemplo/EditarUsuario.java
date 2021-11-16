package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Usuario;

public class EditarUsuario extends AppCompatActivity {

    private EditText nom,apep,apem,email,edad,tele,dire;
    private RadioButton mas,fem;

    private int id_user_entrada = 0;
    private int edad_user_entrada = 0;
    private long telefono_user_entrada =0;
    private String nom_user_entrada = "";
    private String apep_user_entrada = "";
    private String apem_user_entrada = "";
    private String email_user_entrada ="";
    private String dire_user_entrada = "";
    private String genero_user_entrada = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        nom = findViewById(R.id.Nombre);
        apep = findViewById(R.id.ApellidoPaterno);
        apem = findViewById(R.id.ApellidoMaterno);
        email = findViewById(R.id.email);
        edad = findViewById(R.id.edad);
        tele = findViewById(R.id.telefono);
        dire = findViewById(R.id.direccion);
        mas = findViewById(R.id.masculino);
        fem = findViewById(R.id.femenino);

        if(null != this.getIntent()) {
            if (null != this.getIntent().getExtras()) {
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_user_entrada = parametrosEntrada.getInt("id");
                edad_user_entrada = parametrosEntrada.getInt("edad");
                telefono_user_entrada = parametrosEntrada.getLong("telefono");
                nom_user_entrada = parametrosEntrada.getString("nombre");
                apep_user_entrada = parametrosEntrada.getString("apepaterno");
                apem_user_entrada = parametrosEntrada.getString("apematerno");
                email_user_entrada = parametrosEntrada.getString("email");
                dire_user_entrada = parametrosEntrada.getString("direccion");
                genero_user_entrada = parametrosEntrada.getString("genero");

            }
        }

        nom.setText(nom_user_entrada);
        apep.setText(apep_user_entrada);
        apem.setText(apem_user_entrada);
        email.setText(email_user_entrada);
        edad.setText(""+edad_user_entrada);
        tele.setText(""+telefono_user_entrada);
        dire.setText(dire_user_entrada);

        if (genero_user_entrada.toUpperCase().equals("MASCULINO")){
            mas.setChecked(true);
            fem.setChecked(false);
        }else{
            mas.setChecked(false);
            fem.setChecked(true);
        }

    }

    public void volver(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void editarUsuario(View v){
        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDPROGRAMA",null,1);
        ContentValues datosNuevosusuario = new ContentValues();
        datosNuevosusuario.put("nombre",nom.getText().toString());
        datosNuevosusuario.put("apepaterno",apep.getText().toString());
        datosNuevosusuario.put("apematerno",apem.getText().toString());
        datosNuevosusuario.put("email",email.getText().toString());
        datosNuevosusuario.put("edad",edad.getText().toString());
        datosNuevosusuario.put("edad",edad.getText().toString());
        datosNuevosusuario.put("telefono",dire.getText().toString());

        if (mas.isChecked()){
            datosNuevosusuario.put("genero",mas.getText().toString());
        }
        if (fem.isChecked()){
            datosNuevosusuario.put("genero",fem.getText().toString());
        }

        datosNuevosusuario.put("telefono",tele.getText().toString());

        String codicion = "id_usuario=?";
        String valores[] = {id_user_entrada+""};
        int cantidad_actualizados = 0;
        cantidad_actualizados = instancia.actualizarRegistro(datosNuevosusuario,
                codicion,valores, Usuario.Esquema.TABLE_NAME);

        if(cantidad_actualizados > 0){
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Error actualizando usuario", Toast.LENGTH_LONG).show();
        }

    }
}