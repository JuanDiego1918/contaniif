package com.example.juandiego.contaniif.entidades;

public class VideoVo {

    private String titulo;
    private String descripcion;
    private String enlace;

    public VideoVo(String titulo, String descripcion, String enlace) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.enlace = enlace;
    }

    public VideoVo() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}
