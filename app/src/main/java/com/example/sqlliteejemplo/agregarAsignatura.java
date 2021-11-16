package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Asignatura;
import com.example.sqllite.esquemas.Usuario;

public class agregarAsignatura extends AppCompatActivity {

    private TextView codigo,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_asignatura);

        codigo = findViewById(R.id.Codigo);
        descripcion = findViewById(R.id.Descripcion);
    }

    public void insertAsignatura(View v){
        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDPROGRAMA",null,1);

        ContentValues datosAsignatura = new ContentValues();

        datosAsignatura.put(Asignatura.Esquema.CODIGO,codigo.getText().toString());
        datosAsignatura.put(Asignatura.Esquema.DESCRIPCION,descripcion.getText().toString());

        long id_insertada= instancia.insertTabla(datosAsignatura,Asignatura.Esquema.TABLE_NAME);

        codigo.setText("");
        descripcion.setText("");
        Toast.makeText(this, "id insertado "+id_insertada, Toast.LENGTH_SHORT).show();

    }

    public void volver(View v){
        Intent i = new Intent(this,Asignaturas.class);
        startActivity(i);
    }

}