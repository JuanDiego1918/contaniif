package com.example.juandiego.contaniif.principal;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.adapter.AdapterDrag;
import com.example.juandiego.contaniif.adapter.PaginacionNumeroAdapter;
import com.example.juandiego.contaniif.adapter.PreguntasAdapter;
import com.example.juandiego.contaniif.adapter.PreguntasImagenesAdapter;
import com.example.juandiego.contaniif.adapter.PreguntasImagenesAdapterDrag;
import com.example.juandiego.contaniif.adapter.PreguntasSeleccionMultiple;
import com.example.juandiego.contaniif.entidades.GestionPreguntas;
import com.example.juandiego.contaniif.entidades.NumeroVo;
import com.example.juandiego.contaniif.entidades.PreguntasDragVo;
import com.example.juandiego.contaniif.entidades.PreguntasVo;
import com.example.juandiego.contaniif.entidades.RecyclerViewOnClickListener;
import com.example.juandiego.contaniif.entidades.VolleySingleton;
import com.example.juandiego.contaniif.interfaces.Puente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pantalla_empezar_drag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pantalla_empezar_drag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pantalla_empezar_drag extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Pantalla_empezar_drag() {
        // Required empty public constructor
    }

    View view;
    Activity activity;
    Puente puente;

    int numeroArray = 0;
    int numero;
    int correctas = 0;
    Button btnContinuar2, btnContinuar;
    AdapterDrag miAdapter;
    ArrayList<PreguntasDragVo> respuestaCompleta;
    ArrayList<String> respuestaCorrecta;

    PreguntasImagenesAdapterDrag miPreguntasImagenesAdapter;
    ArrayList<PreguntasDragVo> list;
    RecyclerView PreguntasTexto, Imagenes;
    PreguntasDragVo miVo;
    HashMap copia;
    JsonObjectRequest jsonObjectRequest;
    //////////////////////////////////////////////////////////////////////////
    String retroBuena;
    boolean isCheked = false;

    public boolean getIsCheked() {
        return isCheked;
    }

    public void setIsCheked(boolean isCheked) {
        this.isCheked = isCheked;
    }

    public int getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(int tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    int tipoPregunta;
    String urlImagen;

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    String retroMala;
    String resultado;
    int puntage;

    public int getPuntage() {
        return puntage;
    }

    public void setPuntage(int puntage) {
        this.puntage = puntage;
    }

    public String getRetroBuena() {
        return retroBuena;
    }

    public void setRetroBuena(String retroBuena) {
        this.retroBuena = retroBuena;
    }

    public String getRetroMala() {
        return retroMala;
    }

    public void setRetroMala(String retroMala) {
        this.retroMala = retroMala;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    int i = reiniciar;
    ProgressDialog progreso;
    private static long START_TIME_IN_MILLIS;
    private static final int reiniciar = 0;
    public static android.os.CountDownTimer CountDownTimer;
    private long mTimeLeftInMillis;
    ProgressBar mProgressBar;
    long mInitialTime;

    ArrayList<NumeroVo> listanumero;
    NumeroVo miNumeroVo;
    //int numero = 3;
    RecyclerView miRecyclerNumero;

    ArrayList<String> listaImagenes;
    PreguntasAdapter adapter;
    PreguntasImagenesAdapter adapter2;
    PreguntasSeleccionMultiple adapter3;
    ScrollView miScroll;
    ImageView img;
    GestionPreguntas gestionPreguntas;
    PreguntasAdapter preguntasAdap;
    Fragment fragment;
    TextView pregunta;
    TextView puntajeRespuestaBuena;
    TextView puntajeRespuestaMala;
    String informacion;
    String informacion2;
    ProgressDialog dialog;
    RecyclerView recyclerViewUsuarios;
    ArrayList<PreguntasVo> listaPreguntas;
    RequestQueue request;
    Dialog myDialogBuena;
    Dialog myDialogMala;
    ArrayList<String> listaSeleccionada;
    int correctoSeleccionMultiple = 0;

    ////////////////////////////////////////////////////////////////////////////////

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pantalla_empezar_drag.
     */
    // TODO: Rename and change types and number of parameters
    public static Pantalla_empezar_drag newInstance(String param1, String param2) {
        Pantalla_empezar_drag fragment = new Pantalla_empezar_drag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pantalla_empezar_drag, container, false);

        respuestaCorrecta = new ArrayList<>();
        respuestaCompleta = new ArrayList<>();
        list = new ArrayList<>();

        myDialogBuena = new Dialog(getContext());
        myDialogMala = new Dialog(getContext());
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        mProgressBar.setProgress(i);
        pregunta = view.findViewById(R.id.campoPregunta);
        Imagenes = view.findViewById(R.id.primero);
        PreguntasTexto = view.findViewById(R.id.segundo);
        btnContinuar2 = view.findViewById(R.id.btnContinuar2);
        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setVisibility(View.INVISIBLE);
        miRecyclerNumero = view.findViewById(R.id.recyclerNumeros);
        miRecyclerNumero.setLayoutManager(new LinearLayoutManager(getContext()));
        miRecyclerNumero.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));

        listanumero = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            miNumeroVo = new NumeroVo();
            miNumeroVo.setNumeroPagina(i);
            listanumero.add(miNumeroVo);
        }

        PaginacionNumeroAdapter miNumeroAdapter = new PaginacionNumeroAdapter(listanumero, getContext());
        miRecyclerNumero.setAdapter(miNumeroAdapter);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] bar = list.get(0).getRespuesta().split("&&");
                for (String foobar : bar) {
                    respuestaCorrecta.add(foobar);
                }
                for (int i = 0; i < respuestaCompleta.size(); i++) {
                    if (respuestaCorrecta.contains(respuestaCompleta.get(i).getRespuesta())) {
                        correctas++;
                    }
                }
                if (correctas == 4) {
                    setResultado("correcto");
                } else {
                    setResultado("incorrecto");
                }
                comparar();
            }
        });
        cargarWebService();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            puente = (Puente) this.activity;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (CountDownTimer != null) {
            CountDownTimer.cancel();
        }
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error  " + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        list = new ArrayList<>();
        miVo = null;
        JSONArray json = response.optJSONArray("pregunta");
        JSONObject jsonObject = null;
        final ArrayList<String> lista = new ArrayList<>();
        try {
            ///
            jsonObject = json.getJSONObject(i);
            for (int i = 0; i < json.length(); i++) {
                jsonObject = json.getJSONObject(i);
                miVo = new PreguntasDragVo();
                miVo.setId(jsonObject.getInt("id"));
                miVo.setPregunta(jsonObject.getString("pregunta"));
                miVo.setCategoria(jsonObject.getInt("categoria"));
                miVo.setPuntaje(jsonObject.getInt("puntaje"));
                miVo.setTiempoDemora(jsonObject.getInt("tiempo"));
                miVo.setTipo(jsonObject.getInt("tipopregunta"));
                lista.add(jsonObject.getString("opcion"));
                miVo.setOpciones(jsonObject.getString("opcion"));
                miVo.setRespuesta(jsonObject.getString("respuesta"));
                miVo.setRetobuena(jsonObject.getString("retrobuena"));
                miVo.setRetromala(jsonObject.getString("retromala"));
                list.add(miVo);
            }

            for (int i = 0; i < list.size(); i++) {
                String[] bar = lista.get(i).split("&%");
                for (String foobar : bar) {
                    list.get(i).setRuta(String.format(foobar));
                    list.get(i).setPalabra(bar[0]);
                }
            }


            setRetroMala(miVo.getRetromala());
            setRetroBuena(miVo.getRetobuena());
            setTipoPregunta(miVo.getTipo());
            setPuntage(miVo.getPuntaje());
            pregunta.setText(miVo.getPregunta());
            informacion = miVo.getRespuesta();
            informacion2 = miVo.getOpciones();

            mInitialTime = DateUtils.DAY_IN_MILLIS * 0 +
                    DateUtils.HOUR_IN_MILLIS * 0 +
                    DateUtils.MINUTE_IN_MILLIS * 0 +
                    DateUtils.SECOND_IN_MILLIS * miVo.getTiempoDemora();
            START_TIME_IN_MILLIS = miVo.getTiempoDemora() * 1000;
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            starTime();
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            if (getTipoPregunta() == 1) {
                fragment = new Pantalla_empezar();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            } else if (getTipoPregunta() == 2) {
                fragment = new Pantalla_empezar();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            } else if (getTipoPregunta() == 3) {
                fragment = new Pantalla_empezar();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            } else if (getTipoPregunta() == 4) {
                miAdapter = new AdapterDrag(list, getContext());
                miPreguntasImagenesAdapter = new PreguntasImagenesAdapterDrag(list, getContext());
                Imagenes.setAdapter(miPreguntasImagenesAdapter);
                PreguntasTexto.setAdapter(miAdapter);


                miPreguntasImagenesAdapter.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        numero = -1;
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
                        view.startDrag(data, dragShadowBuilder, view, 0);
                        numero = Imagenes.getChildAdapterPosition(view);
                        return true;
                    }
                });

                miAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getRuta() == list.get(PreguntasTexto.getChildAdapterPosition(view)).getImg()) {
                                list.get(i).setMostrar(false);
                                respuestaCompleta.remove(list.get(PreguntasTexto.getChildAdapterPosition(view)));
                                Imagenes.setAdapter(miPreguntasImagenesAdapter);
                            }
                        }
                        list.get(PreguntasTexto.getChildAdapterPosition(view)).setImg(null);
                        PreguntasTexto.setAdapter(miAdapter);
                    }
                });

                miAdapter.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        int dragEvent = event.getAction();
                        final View view = (View) event.getLocalState();

                        switch (dragEvent) {
                /*case DragEvent.ACTION_DRAG_ENTERED:
                    Toast.makeText(getApplicationContext(), "ENTERED" + list.get(PreguntasTexto.getChildAdapterPosition(view)).getPalabra(), Toast.LENGTH_SHORT).show();
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Toast.makeText(getApplicationContext(), "EXITED" + list.get(PreguntasTexto.getChildAdapterPosition(view)).getPalabra(), Toast.LENGTH_SHORT).show();
                    break;*/
                            case DragEvent.ACTION_DROP:
                                if (list.get(numero).isMostrar() == false) {
                                    list.get(PreguntasTexto.getChildAdapterPosition(v)).setImg(list.get(numero).getRuta());
                                    btnContinuar.setVisibility(View.VISIBLE);
                                    btnContinuar2.setVisibility(View.INVISIBLE);
                                    /////////////////////////////////////////////////////////////////////////////////////////////////////
                                    miVo = new PreguntasDragVo();
                                    miVo.setId(numeroArray);
                                    miVo.setPalabra(list.get(PreguntasTexto.getChildAdapterPosition(v)).getPalabra());
                                    miVo.setRuta(list.get(numero).getRuta());
                                    miVo.setRespuesta(list.get(PreguntasTexto.getChildAdapterPosition(v)).getPalabra() + "&%" + list.get(numero).getRuta());
                                    respuestaCompleta.add(miVo);
                                    numeroArray++;
                                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                                    PreguntasTexto.setAdapter(miAdapter);
                                    list.get(numero).setMostrar(true);
                                    Imagenes.setAdapter(miPreguntasImagenesAdapter);
                                } else {
                                    Toast.makeText(getContext(), "No se puede cambiar", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                        return true;
                    }
                });
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" + " " + response, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void cargarWebService() {
        PreguntasTexto.setLayoutManager(new LinearLayoutManager(getContext()));
        Imagenes.setLayoutManager(new LinearLayoutManager(getContext()));
        String ip = getContext().getString(R.string.ip2);
        String url = "http://" + ip + "/apolunios/wsConsultaPreguntaPrueba1.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void starTime() {
        CountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                i++;
                mProgressBar.setProgress(100);
            }
        }.start();
    }

    private void updateCountDownText() {
        Log.v("Log_tag", "Tick of Progress" + i + mInitialTime);
        i++;
        mProgressBar.setProgress((int) ((int) i * 100 / (mInitialTime / 1000)));
    }

    private void comparar() {
        resetTimer();
        if (getResultado().equalsIgnoreCase("correcto")) {
            showPopup(getRetroBuena());
        } else {
            showPopup2(getRetroMala());
        }
    }

    private void resetTimer() {
        CountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        i = reiniciar;
        updateCountDownText();
        starTime();
    }

    public void showPopup(String retorno) {
        Button retroBuena;
        TextView txtRetroBuena;

        myDialogBuena.setContentView(R.layout.popup_rcorrecta);
        puntajeRespuestaBuena = myDialogBuena.findViewById(R.id.campoPuntajeCorrecto);
        puntajeRespuestaBuena.setText("+" + String.valueOf(getPuntage()));
        txtRetroBuena = myDialogBuena.findViewById(R.id.campoRetroBuena);
        txtRetroBuena.setText(retorno);

        retroBuena = myDialogBuena.findViewById(R.id.btnRetroBuena);
        retroBuena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogBuena.dismiss();
                cargarWebService();
                btnContinuar.setVisibility(View.INVISIBLE);
                btnContinuar2.setVisibility(View.VISIBLE);
            }
        });

        myDialogBuena.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogBuena.show();
    }


    public void showPopup2(String retorno) {
        Button retroMala;
        TextView txtRetroMala;

        myDialogMala.setContentView(R.layout.popup_rincorrecta);
        puntajeRespuestaMala = myDialogMala.findViewById(R.id.campoPuntajeIncorrecto);
        //puntajeRespuestaMala.setText("+"+String.valueOf(getPuntage()));
        txtRetroMala = myDialogMala.findViewById(R.id.campoRetroMala);
        txtRetroMala.setText(retorno);
        retroMala = myDialogMala.findViewById(R.id.btnRetroMala);


        retroMala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogMala.dismiss();
                cargarWebService();
                btnContinuar.setVisibility(View.INVISIBLE);
                btnContinuar2.setVisibility(View.VISIBLE);

//                listanumero.get(numero).setColor("#e91e63");
                numero++;
                PaginacionNumeroAdapter miNumeroAdapter = new PaginacionNumeroAdapter(listanumero, getContext());
                miRecyclerNumero.setAdapter(miNumeroAdapter);
            }
        });

        myDialogMala.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogMala.show();

    }
}
