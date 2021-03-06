package com.example.juandiego.contaniif.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.PreguntasVo;

import java.util.ArrayList;

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.UsuariosHolder> implements  View.OnClickListener,View.OnFocusChangeListener{

    private int selectedPosition = -1;

    ArrayList<PreguntasVo> listaUsuarios;
    private View.OnClickListener listener;
    public PreguntasAdapter(ArrayList<PreguntasVo> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

    }

    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_usuarios_adapter,null,false);
        view.setOnClickListener(this);
        return new UsuariosHolder(view);
    }

    @Override
    public void onViewDetachedFromWindow(UsuariosHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {

        //holder.preguntaa.setText(listaUsuarios.get(position).getPregunta().toString());
        holder.respuesta.setText(listaUsuarios.get(position).getOpciones().toString());

        if (selectedPosition == position) {
            holder.respuesta.setBackgroundColor(Color.parseColor("#C4CDDA"));
        } else {
            //if selected position is not equal to that mean view is not selected so change the cardview color to white back again
            holder.respuesta.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
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
        TextView respuesta;
        //int i = 0;
       // boolean seleccion = false;

    public UsuariosHolder(View itemView) {
        super(itemView);
        respuesta = itemView.findViewById(R.id.respuestaaaa);
    }

    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }
}
