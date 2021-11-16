package com.example.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.objeto.Asignature;
import com.example.objeto.User;
import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Asignatura;
import com.example.sqllite.esquemas.Usuario;
import com.example.sqlliteejemplo.Asignaturas;
import com.example.sqlliteejemplo.EditarUsuario;
import com.example.sqlliteejemplo.R;

import java.util.ArrayList;

public class AdapterAsignaturas extends RecyclerView.Adapter<AdapterAsignaturas.AsignaturasHolder> {

    private ArrayList<Asignature> asignaturasDesplegar;

    @NonNull
    @Override
    public AdapterAsignaturas.AsignaturasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.asignaturascard,parent,false);
        AdapterAsignaturas.AsignaturasHolder holder = new AdapterAsignaturas.AsignaturasHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAsignaturas.AsignaturasHolder holder, int position) {
        Asignature item = asignaturasDesplegar.get(position);


        holder.codigo.setText(item.getCodig());
        holder.descripcion.setText(item.getDescripcion());
        holder.eliminar.setId(item.getId_asignatura());


        holder.eliminar.setId(item.getId_asignatura());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condicion = "id_asignatura=?";
                String valores[] = {"" + item.getId_asignatura()};
                int cant_regs_eliminados = 0;

                OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"BDPROGRAMA",null,1);
                cant_regs_eliminados = instancia.borrarRegistro(Asignatura.Esquema.TABLE_NAME,condicion,valores);
                if (cant_regs_eliminados>0){
                    Toast.makeText(view.getContext(), "Asignatura eliminado", Toast.LENGTH_SHORT).show();
                    AdapterAsignaturas.this.asignaturasDesplegar.remove(holder.getAdapterPosition());
                    AdapterAsignaturas.this.notifyDataSetChanged();

                }else{
                    Toast.makeText(view.getContext(), "Error eliminado Asignatura", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return asignaturasDesplegar.size();
    }

    public AdapterAsignaturas(ArrayList<Asignature> asignaturasDesplegarrin){
        asignaturasDesplegar = asignaturasDesplegarrin;
    }

    public static class AsignaturasHolder extends RecyclerView.ViewHolder {

        public TextView codigo;
        public TextView descripcion;
        public Button eliminar;


        public AsignaturasHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo);
            eliminar = itemView.findViewById(R.id.eliminar);
            descripcion = itemView.findViewById(R.id.descripcion);

        }
    }


}
