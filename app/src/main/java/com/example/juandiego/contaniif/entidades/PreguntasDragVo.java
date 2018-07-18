package com.example.juandiego.contaniif.entidades;

public class PreguntasDragVo {

    private String palabra;
    private String ruta;
    private boolean mostrar;
    private String img;
    private int id;
    private int puntaje;
    private int tiempoDemora;
    private int tipo;
    private String opciones;
    private String respuesta;
    private String pregunta;
    private int categoria;
    private String retobuena;
    private String retromala;

    public PreguntasDragVo() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
