package com.example.juandiego.contaniif.entidades;

import java.util.ArrayList;

public class PreguntasVo {


    private String rutaImagen;

    private boolean isCheck;

    private ArrayList<String> listaSeleccionada;

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    private String pregunta;
    private int categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int puntaje;
    private int tiempoDemora;
    private int tipo;

    private String arregloPregunta;

    public String getArregloPregunta() {
        return String.valueOf(new String[]{arregloPregunta});
    }

    public void setArregloPregunta(String arregloPregunta) {
        this.arregloPregunta = arregloPregunta;
    }

    private String opciones;
    private String respuesta;


    public String[] getRepuesta() {
        return repuesta;
    }

    public void setRepuesta(String[] repuesta) {
        this.repuesta = repuesta;
    }

    private String repuesta[];

    private String retobuena;
    private String retromala;

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTiempoDemora() {
        return tiempoDemora;
    }

    public void setTiempoDemora(int tiempoDemora) {
        this.tiempoDemora = tiempoDemora;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRetobuena() {
        return retobuena;
    }

    public void setRetobuena(String retobuena) {
        this.retobuena = retobuena;
    }

    public String getRetromala() {
        return retromala;
    }

    public void setRetromala(String retromala) {
        this.retromala = retromala;
    }

    public void setId(String pregunta) {
    }
    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

}
