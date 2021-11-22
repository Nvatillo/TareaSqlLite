package com.example.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.objeto.User;
import com.example.sqllite.OperacionesCRUD;
import com.example.sqllite.esquemas.Usuario;
import com.example.sqlliteejemplo.EditarUsuario;
import com.example.sqlliteejemplo.ListaUsuarioAsignatura;
import com.example.sqlliteejemplo.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UsuarioHolder> {

    private ArrayList<User> usuarioDesplegar;

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewejemplo,parent,false);
       UsuarioHolder holder = new UsuarioHolder(item);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        User item = usuarioDesplegar.get(position);
        if (item.getGenero().toUpperCase().equals("MASCULINO")){
            holder.avatar.setImageResource(R.drawable.avatar_mas);
        }else
            holder.avatar.setImageResource(R.drawable.avatar_fem);
        holder.nombre.setText(item.getId_usuario()+": "
                    +item.getNombre()+" "
                    +item.getApePaterno()+" "
                    +item.getApeMaterno());

        holder.rut.setText(item.getEmail());
        holder.detalle.setId(item.getId_usuario());
        holder.eliminar.setId(item.getId_usuario());
        holder.editar.setId(item.getId_usuario());

        holder.eliminar.setId(item.getId_usuario());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condicion = "id_usuario=?";
                String valores[] = {"" + item.getId_usuario()};
                int cant_regs_eliminados = 0;

                OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"BDPROGRAMA",null,1);
                cant_regs_eliminados = instancia.borrarRegistro(Usuario.Esquema.TABLE_NAME,condicion,valores);
                if (cant_regs_eliminados>0){
                    Toast.makeText(view.getContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    AdapterUser.this.usuarioDesplegar.remove(holder.getAdapterPosition());
                    AdapterUser.this.notifyDataSetChanged();

                }else{
                    Toast.makeText(view.getContext(), "Error eliminado usuario", Toast.LENGTH_SHORT).show();
                }
            }


        });

        holder.editar.setId(item.getId_usuario());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editarUsuario = new Intent(view.getContext(), EditarUsuario.class);

                editarUsuario.putExtra("id",item.getId_usuario());
                editarUsuario.putExtra("nombre",item.getNombre().toString());
                editarUsuario.putExtra("apepaterno",item.getApePaterno().toString());
                editarUsuario.putExtra("apematerno",item.getApeMaterno().toString());
                editarUsuario.putExtra("email",item.getEmail().toString());
                editarUsuario.putExtra("edad",item.getEdad());
                editarUsuario.putExtra("telefono",item.getTelefono());
                editarUsuario.putExtra("direccion",item.getDireccion().toString());
                editarUsuario.putExtra("genero",item.getGenero().toString());

                view.getContext().startActivity(editarUsuario);
            }
        });


        holder.detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userAsignaturas =new Intent(view.getContext(), ListaUsuarioAsignatura.class);
                userAsignaturas.putExtra("id",item.getId_usuario());
                view.getContext().startActivity(userAsignaturas);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarioDesplegar.size();
    }

    public AdapterUser(ArrayList<User> usuarioDesplegarin){
        usuarioDesplegar = usuarioDesplegarin;
    }

    public static class UsuarioHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView nombre;
        public TextView rut;
        public Button eliminar;
        public Button detalle;
        public Button editar;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nombre = itemView.findViewById(R.id.nombre);
            rut = itemView.findViewById(R.id.rut);
            eliminar = itemView.findViewById(R.id.eliminar);
            detalle = itemView.findViewById(R.id.detalle);
            editar = itemView.findViewById(R.id.editar);
        }
    }



}
