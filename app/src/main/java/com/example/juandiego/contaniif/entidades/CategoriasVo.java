package com.example.juandiego.contaniif.entidades;

public class CategoriasVo {

    private String letraMa;
    private String nombre;
    private String id;


    public CategoriasVo() {
    }

    public CategoriasVo(String letraMa, String nombre, String id) {
        this.letraMa=letraMa;
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLetraMa() {
        return letraMa;
    }

    public void setLetraMa(String letraMa) {
        this.letraMa = letraMa;
    }
}
