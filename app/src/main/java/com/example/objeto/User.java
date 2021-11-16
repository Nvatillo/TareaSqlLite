package com.example.objeto;

public class User {
    private String nombre= "";
    private String apePaterno= "";
    private String apeMaterno = "";
    private String email = "";
    private int edad = 0;
    private String direccion = "";
    private String genero = "";
    private long telefono = 0;
    private int id_usuario = 0;

    public User(){
    }
    public User(String nombre, String apeMaterno, String apePaterno,String email,int edad,String direccion,String genero,long telefono,int id_usuario){
        this.nombre = nombre;
        this.apeMaterno = apeMaterno;
        this.apePaterno = apePaterno;
        this.email = email;
        this.edad = edad;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
