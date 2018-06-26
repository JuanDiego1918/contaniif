package com.example.juandiego.contaniif.entidades;

public class GestionPreguntas{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getNumeroPregunt() {
        return numeroPregunt;
    }

    public void setNumeroPregunt(String numeroPregunt) {
        this.numeroPregunt = numeroPregunt;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    private int id;
    private int categoria;
    private int puntaje;
    private int tiempoDemora;
    private int tipo;

    private String numeroPregunt;
    private String opciones;
    private String color;
    private String respuesta;
    private String retobuena;
    private String retromala;
    private String pregunta;
    private String resultado;



}
