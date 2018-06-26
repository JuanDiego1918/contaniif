package com.example.juandiego.contaniif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.NumeroVo;

import java.util.ArrayList;

public class PaginacionNumeroAdapter extends RecyclerView.Adapter<PaginacionNumeroAdapter.NumeroHolder> implements View.OnClickListener,View.OnTouchListener{

    View.OnClickListener listener;
    View.OnTouchListener touchListener;
    ArrayList<NumeroVo> listaNumero;

    private int selectedPosition = 0;

    public PaginacionNumeroAdapter(ArrayList<NumeroVo> listaNumero) {
        this.listaNumero = listaNumero;
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

class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener (Context ctx){
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}

