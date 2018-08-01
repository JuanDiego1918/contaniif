package com.example.juandiego.contaniif.entidades;

import android.graphics.drawable.Drawable;

public class NumeroVo {


    private int numeroPagina;
    private String color;
    private int img;

    public NumeroVo() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
