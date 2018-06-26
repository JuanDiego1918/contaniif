package com.example.juandiego.contaniif.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.CategoriasVo;

import java.util.ArrayList;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriaHolder> implements  View.OnClickListener {

    View.OnClickListener listener;
    ArrayList<CategoriasVo> listaCategorias;
    private int selectedPosition = -1;

    public CategoriasAdapter(ArrayList<CategoriasVo> listaCategorias) {
        this.listaCategorias = listaCategorias;

    }


    @Override
    public CategoriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_categorias_adapter,null,false);
        view.setOnClickListener(this);
        return new CategoriaHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriaHolder holder, int position) {
        holder.letraM.setText(listaCategorias.get(position).getLetraMa());
        holder.palabra.setText(listaCategorias.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
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

    public class CategoriaHolder extends RecyclerView.ViewHolder {
        TextView letraM;
        TextView palabra;
        public CategoriaHolder(View itemView) {
            super(itemView);
            letraM=itemView.findViewById(R.id.letraMayuscula);
            palabra=itemView.findViewById(R.id.palabra);
        }
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }
}
