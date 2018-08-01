package com.example.juandiego.contaniif.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.PreguntasVo;
import com.example.juandiego.contaniif.principal.Pantalla_empezar;

import java.util.ArrayList;

public class PreguntasSeleccionMultiple extends RecyclerView.Adapter<PreguntasSeleccionMultiple.UsuariosHolder> implements  View.OnClickListener,View.OnFocusChangeListener{

    ArrayList<PreguntasVo> listaUsuarios;
    private View.OnClickListener listener;
    public PreguntasSeleccionMultiple(ArrayList<PreguntasVo> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

    }

    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_preguntas_seleccion_multiple,null,false);
        view.setOnClickListener(this);
        return new UsuariosHolder(view);
    }

    @Override
    public void onViewDetachedFromWindow(UsuariosHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(final UsuariosHolder holder, int position) {

        //holder.preguntaa.setText(listaUsuarios.get(position).getPregunta().toString());
        holder.respuesta.setText(listaUsuarios.get(position).getOpciones().toString());
        holder.respuesta.setOnCheckedChangeListener(null);
        holder.respuesta.setChecked(listaUsuarios.get(position).isCheck());
        holder.respuesta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listaUsuarios.get(holder.getAdapterPosition()).setCheck(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    public class UsuariosHolder extends RecyclerView.ViewHolder {
        //TextView preguntaa;
        // RadioGroup grupoRadio;
        // Fragment fragment;
        CheckBox respuesta;
        //int i = 0;
        // boolean seleccion = false;

        public UsuariosHolder(View itemView) {
            super(itemView);
            respuesta = itemView.findViewById(R.id.respuestaaaa);
        }

    }
}