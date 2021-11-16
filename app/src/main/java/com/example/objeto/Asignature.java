package com.example.objeto;

public class Asignature {

    private String codig ="";
    private String descripcion = "";
    private int id_asignatura = 0;
    public Asignature() {
    }

    public String getCodig() {
        return codig;
    }

    public void setCodig(String codig) {
        this.codig = codig;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public Asignature(String codig, String descripcion, int id_asignatura) {
        this.codig = codig;
        this.descripcion = descripcion;
        this.id_asignatura = id_asignatura;
    }
}
