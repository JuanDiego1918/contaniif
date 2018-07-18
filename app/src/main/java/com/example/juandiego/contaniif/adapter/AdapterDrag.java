package com.example.juandiego.contaniif.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.PreguntasDragVo;
import com.example.juandiego.contaniif.entidades.VolleySingleton;

import java.util.ArrayList;

public class AdapterDrag extends RecyclerView.Adapter<AdapterDrag.AdapterHolder> implements View.OnClickListener, View.OnDragListener {

    ArrayList<PreguntasDragVo> lista;
    View.OnClickListener listener;
    Context context;
    View.OnDragListener onDragListener;

    public AdapterDrag(ArrayList<PreguntasDragVo> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDrag.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_contenido, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnDragListener(this);
        view.setOnClickListener(this);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        if (lista.get(position).getImg() != null) {
            cargarImagenWebService(lista.get(position).getImg(), holder);
        } else {
            holder.imagen.setImageResource(R.drawable.borde_punteado);
        }
        holder.palabra.setText(lista.get(position).getPalabra());
    }

    private void cargarImagenWebService(String rutaImagen, final AdapterHolder holder) {
        String ip = context.getString(R.string.ip2);
        String urlImagen = "http://" + ip + rutaImagen;
        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error al cargar la imagen" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public void setOnDragListener(View.OnDragListener onDragListener) {
        this.onDragListener=onDragListener;
    }


    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if (onDragListener!=null){
            onDragListener.onDrag(view,dragEvent);
        }
        return true;
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

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView palabra;
        ImageView imagen;

        public AdapterHolder(View itemView) {
            super(itemView);
            palabra = itemView.findViewById(R.id.preguntasRespuestaRelacion);
            imagen = itemView.findViewById(R.id.imagenesRespuestaRelacion);
        }
    }
}
