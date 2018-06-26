package com.example.juandiego.contaniif.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.GestionPreguntas;

import java.util.ArrayList;

public class PaginadorPreguntas extends RecyclerView.Adapter<PaginadorPreguntas.PreguntasHolder> implements  View.OnClickListener,View.OnFocusChangeListener{


    Context context;
    static String colores;

    public static String getColores() {
        return colores;
    }

    public static void setColores(String colores) {
        PaginadorPreguntas.colores = colores;
    }

    static int contador;
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }


    static int contador2;
    public int getContador2() {
        return contador2;
    }

    public void setContador2(int contador2) {
        this.contador2 = contador2;
    }



    static String col;
    public String col() {
        return col;
    }

    public void col(String col) {
        this.col = col;
    }








    static int contador3;
    public int getContador3() {
        return contador3;
    }

    public void setContador3(int contador3) {
        this.contador3 = contador3;
    }


    ArrayList<GestionPreguntas> ListaNmeros;
    private View.OnClickListener listener;
    private int selectedPosition = 1;
    public PaginadorPreguntas(ArrayList<GestionPreguntas> listaNmeros, Context context1) {
        ListaNmeros = listaNmeros;
        context = context1;
    }

    @Override
    public PreguntasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_numero_preguntas,null,false);
        view.setOnClickListener(this);
        return new PreguntasHolder(view);
    }

    @Override
    public void onBindViewHolder(PreguntasHolder holder, int position) {
        holder.numero.setText(ListaNmeros.get(position).getNumeroPregunt().toString());

        for (int i = 0;i<getContador();i++){
            if (i == position && getContador2()==1) {
                holder.numero.setTextColor(Color.GREEN);
            }else if (i == position && getContador2()==2) {
                holder.numero.setTextColor(Color.RED);
                //holder.numero.setTextColor(Color.parseColor(ListaNmeros.get(position).getColor()));
                 // Toast.makeText(context,"Cantidad de numeror" + ListaNmeros.get(position).getColor(),Toast.LENGTH_LONG).show();
            }
        }

       /* for (int i = 0;i<getContador();i++){
            if (i>=ListaNmeros.size()) {

            }
        }*/



    }

    @Override
    public int getItemCount() {
        return ListaNmeros.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    public class PreguntasHolder extends RecyclerView.ViewHolder {
        TextView numero;
        public PreguntasHolder(View itemView) {
            super(itemView);
            numero = itemView.findViewById(R.id.txtNumeroPregunta);
        }
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }


    public static void contadorr(){
        contador++;
    }


    public  void colores(int position, String color){
        ListaNmeros.get(position).setColor(color);
    }
}
