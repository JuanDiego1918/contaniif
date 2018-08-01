package com.example.juandiego.contaniif.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.NumeroVo;

import java.util.ArrayList;

public class PaginacionNumeroAdapter extends RecyclerView.Adapter<PaginacionNumeroAdapter.NumeroHolder> implements View.OnClickListener,View.OnTouchListener{

    View.OnClickListener listener;
    View.OnTouchListener touchListener;
    ArrayList<NumeroVo> listaNumero;
    Context context;

    private int selectedPosition = 0;

    public PaginacionNumeroAdapter(ArrayList<NumeroVo> listaNumero,Context context) {
        this.listaNumero = listaNumero;
        this.context=context;
    }

    @Override
    public PaginacionNumeroAdapter.NumeroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_paginacion,null,false);
        view.setOnClickListener(this);
        view.setOnTouchListener(this);
        return new NumeroHolder(view);
    }

    @Override
    public void onBindViewHolder(PaginacionNumeroAdapter.NumeroHolder holder, final int position) {
        holder.numero.setText(String.valueOf(listaNumero.get(position).getNumeroPagina()));
        if (listaNumero.get(position).getColor()!=null){
            holder.numero.setTextColor(Color.parseColor(listaNumero.get(position).getColor()));
        }else{
            holder.numero.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return listaNumero.size();
    }
    public void setOnTouchListener(View.OnTouchListener touchListener){
        this.touchListener=touchListener;
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (touchListener!=null){
            touchListener.onTouch(v,event);
        }
        return false;
    }

    public class NumeroHolder extends RecyclerView.ViewHolder {
        TextView numero;
        public NumeroHolder(View itemView) {
            super(itemView);
            numero=itemView.findViewById(R.id.paginacion);

        }
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }
}