package com.example.juandiego.contaniif.entidades;

import android.graphics.Bitmap;

public class PreguntasConImagenVo {
    private Bitmap Image;

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    private String Pregunta;

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }
}
