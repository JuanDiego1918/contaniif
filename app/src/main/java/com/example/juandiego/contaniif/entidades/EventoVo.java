package com.example.juandiego.contaniif.entidades;

public class EventoVo {

    private String nombre;
    private String Image;
    private String fecha;
    private String lugar;
    private String descripcion;

    public EventoVo() {
    }

    public EventoVo(String nombre, String image, String fecha, String lugar, String descripcion) {
        this.nombre=nombre;
        Image = image;
        this.fecha = fecha;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
