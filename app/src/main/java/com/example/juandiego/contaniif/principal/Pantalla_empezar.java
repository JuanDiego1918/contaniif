package com.example.juandiego.contaniif.principal;
//nichee

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.adapter.PaginacionNumeroAdapter;
import com.example.juandiego.contaniif.adapter.PreguntasAdapter;
import com.example.juandiego.contaniif.adapter.PreguntasImagenesAdapter;
import com.example.juandiego.contaniif.adapter.PreguntasSeleccionMultiple;
import com.example.juandiego.contaniif.entidades.GestionPreguntas;
import com.example.juandiego.contaniif.entidades.NumeroVo;
import com.example.juandiego.contaniif.entidades.PreguntasVo;
import com.example.juandiego.contaniif.entidades.RecyclerViewOnClickListener;
import com.example.juandiego.contaniif.entidades.VolleySingleton;
import com.example.juandiego.contaniif.interfaces.Puente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pantalla_empezar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pantalla_empezar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pantalla_empezar extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int puntos;

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    String credenciales;

    public String getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }

    int tiempoCapturado;
    ArrayList<String> listaPre;
    String retroBuena;

    int contador = 0;

    int numeroPregunta;

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

    private OnFragmentInteractionListener mListener;

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
    int numero = 3;
    RecyclerView miRecyclerNumero;

    ArrayList<String> listaImagenes;
    PreguntasAdapter adapter;
    PreguntasImagenesAdapter adapter2;
    PreguntasSeleccionMultiple adapter3;
    ScrollView miScroll;
    ImageView img;
    Activity activity;
    GestionPreguntas gestionPreguntas;
    PreguntasAdapter preguntasAdap;
    Fragment fragment;
    Puente puente;
    Button btnContinuar;
    Button btnContinuar2;
    TextView pregunta;
    TextView puntajeRespuestaBuena;
    TextView puntajeRespuestaMala;
    String informacion;
    String informacion2;
    ProgressDialog dialog;
    StringRequest stringRequest;
    RecyclerView recyclerViewUsuarios;
    ArrayList<PreguntasVo> listaPreguntas;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    Dialog myDialogBuena;
    Dialog myDialogMala,MyDialogFinal;
    ArrayList<String> listaSeleccionada;
    int correctoSeleccionMultiple = 0;
    PreguntasVo preguntas;
    ArrayList<String> listaColores;

    public Pantalla_empezar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter instruuno.
     * @param param2 Parameter instrudos.
     * @return A new instance of fragment Pantalla_empezar.
     */
    // TODO: Rename and change types and number of parameters
    public static Pantalla_empezar newInstance(String param1, String param2) {
        Pantalla_empezar fragment = new Pantalla_empezar();
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

        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_pantalla_empezar, container, false);
        request = Volley.newRequestQueue(getContext());
        miScroll = vista.findViewById(R.id.scroll);

        myDialogBuena = new Dialog(getContext());
        myDialogMala = new Dialog(getContext());
        MyDialogFinal=new Dialog(getContext());

        btnContinuar2 = vista.findViewById(R.id.btnContinuar2);
        btnContinuar = vista.findViewById(R.id.btnContinuar);
        btnContinuar.setVisibility(View.INVISIBLE);


        miRecyclerNumero = vista.findViewById(R.id.recyclerNumeros);
        miRecyclerNumero.setLayoutManager(new LinearLayoutManager(getContext()));
        miRecyclerNumero.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        mProgressBar = (ProgressBar) vista.findViewById(R.id.progressbar);
        mProgressBar.setProgress(i);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comparar();
            }
        });

        pregunta = vista.findViewById(R.id.campoPregunta);
        recyclerViewUsuarios = vista.findViewById(R.id.recyclerPreguntasss);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUsuarios.setHasFixedSize(true);
        //request = Volley.newRequestQueue(getContext());

        if (getIsCheked() == true) {
            btnContinuar.setVisibility(View.VISIBLE);
            btnContinuar2.setVisibility(View.INVISIBLE);
        }

        cargarCredenciales();
        cargarWebservices();

        //fragmentBolas = vista.findViewById(R.id.fragmentBolitas);
        //fragmentPregunta = vista.findViewById(R.id.fragmentPregunta1);

        //miFragment = new FragmentBolitas();
        //getFragmentManager().beginTransaction().replace(R.id.fragmentBolitas,miFragment).commit();


        // miFragment = new FragmentPregunta1();
        // getFragmentManager().beginTransaction().replace(R.id.fragmentPregunta1,miFragment).commit();

        listaColores=new ArrayList<>();
        Bundle todo;
        Bundle miBundle = getArguments();
        if (miBundle != null) {
            todo=miBundle.getBundle("Todo");
            numeroPregunta=todo.getInt("numeroPregunta");
            listaColores=todo.getStringArrayList("color");
            Toast.makeText(getContext(), "j " + numeroPregunta, Toast.LENGTH_SHORT).show();
        } else {
            numeroPregunta = 0;
        }
        listanumero = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            miNumeroVo = new NumeroVo();
            miNumeroVo.setNumeroPagina(i);
            listanumero.add(miNumeroVo);
        }

        for (int i=0;i<listaColores.size();i++){
            listanumero.get(i).setColor(listaColores.get(i));
        }
        PaginacionNumeroAdapter miNumeroAdapter = new PaginacionNumeroAdapter(listanumero, getContext());
        miRecyclerNumero.setAdapter(miNumeroAdapter);
        return vista;
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

    private void resetTimer() {
        CountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        i = reiniciar;

    }

    private void updateCountDownText() {
        Log.v("Log_tag", "Tick of Progress" + i + mInitialTime);
        i++;
        mProgressBar.setProgress((int) ((int) i * 100 / (mInitialTime / 1000)));
    }

    private void cargarWebservices() {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Cargando..");
//        dialog.show();

        String ip = getContext().getString(R.string.ip);
        //String url = "http://" + ip + "wsPreguntasTipo1.php";
        //String url = "http://" + ip + "/apolunios/wsConsultaPreguntaPrueba1.php";
        String url = "http://" + ip + "/wsConsultaPreguntaPrueba1.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
//        request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
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
                revisar(true);
            }
        });

        myDialogBuena.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogBuena.show();
    }

    private void revisar(boolean revisar) {
        if (revisar == true) {
          int puntos = puntage;
            if (tiempoCapturado >preguntas.getTiempoDemora()) {
                puntos = (puntage*75)/100;
            }
            //enviarDatosPuntaje(puntos);
            listaColores.add("#45cc28");
        }else{
            listaColores.add("#ed2024");
        }
        if (numeroPregunta < 10) {
            puente.reinciar(numeroPregunta,1,listaColores);
        } else {
            Button finalizar;

            MyDialogFinal.setContentView(R.layout.popup_terminar_preguntas);
            //puntajeRespuestaMala.setText("+"+String.valueOf(getPuntage()));
            finalizar = MyDialogFinal.findViewById(R.id.btnFinal);


            finalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialogFinal.dismiss();
                    puente.pantalla(1);
                }
            });

            MyDialogFinal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            MyDialogFinal.show();
        }
        btnContinuar.setVisibility(View.INVISIBLE);
        btnContinuar2.setVisibility(View.VISIBLE);
    }




    ///////////////////////////////VICTOR/////////////////////////////////


    private void cargarCredenciales() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        String credenciales = preferences.getString("correo","No existe el valor");
        setCredenciales(credenciales);

    }

    private void enviarDatosPuntaje(int puntos) {

        setPuntos(puntos);
        String url;
        url = "http://"+getContext().getString(R.string.ip2)+"/apolunios/registroEstadisticas.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")) {
//                    Toast.makeText(getContext(), "Registro de puntaje exitoso", Toast.LENGTH_SHORT).show();
                } else {
  //                  Toast.makeText(getContext(),"Puntaje no registrado", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();
                Toast.makeText(getContext(), "No se pudo registrar el puntaje" + error.toString(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String idusuario = getCredenciales();
                int idpregunta = preguntas.getId();
                int tiempo = tiempoCapturado;
                int puntaje = getPuntos();

                Map<String, String> parametros = new HashMap<>();
                parametros.put("idusuario", idusuario);
                parametros.put("idpregunta", Integer.toString(idpregunta));
                parametros.put("tiempo", Integer.toString(tiempo));
                parametros.put("puntaje", Integer.toString(puntaje));
                return parametros;
            }
        };

        request.add(stringRequest);
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
                revisar(false);
            }
        });

        myDialogMala.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogMala.show();

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
        CountDownTimer.cancel();
        mListener = null;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error " + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        preguntas = null;
        JSONArray json = response.optJSONArray("pregunta");
        JSONObject jsonObject = null;
        listaPreguntas = new ArrayList<PreguntasVo>();
        listaImagenes = new ArrayList<String>();

        try {

            for (int i = 0; i < json.length(); i++) {
                jsonObject = json.getJSONObject(i);
                preguntas = new PreguntasVo();
                preguntas.setId(jsonObject.getInt("id"));
                preguntas.setPregunta(jsonObject.getString("pregunta"));
                preguntas.setCategoria(jsonObject.getInt("categoria"));
                preguntas.setPuntaje(jsonObject.getInt("puntaje"));
                preguntas.setTiempoDemora(jsonObject.getInt("tiempo"));
                preguntas.setTipo(jsonObject.getInt("tipopregunta"));
                preguntas.setOpciones(jsonObject.getString("opcion"));
                preguntas.setRespuesta(jsonObject.getString("respuesta"));
                preguntas.setRetobuena(jsonObject.getString("retrobuena"));
                preguntas.setRetromala(jsonObject.getString("retromala"));
                preguntas.setRutaImagen(jsonObject.getString("opcion"));

                listaImagenes.add(preguntas.getOpciones());
                //Toast.makeText(getContext(),"lista url" + listaImagenes,Toast.LENGTH_LONG).show();

                listaPreguntas.add(preguntas);
                //setUrlImagen(preguntas.getOpciones());
                dialog.hide();
            }
            setRetroMala(preguntas.getRetromala());
            setRetroBuena(preguntas.getRetobuena());
            setTipoPregunta(preguntas.getTipo());
            setPuntage(preguntas.getPuntaje());
            pregunta.setText(preguntas.getPregunta());
            informacion = preguntas.getRespuesta();
            informacion2 = preguntas.getOpciones();

            if (numeroPregunta != 0) {
                resetTimer();
            }

            mInitialTime = DateUtils.DAY_IN_MILLIS * 0 +
                    DateUtils.HOUR_IN_MILLIS * 0 +
                    DateUtils.MINUTE_IN_MILLIS * 0 +
                    DateUtils.SECOND_IN_MILLIS * preguntas.getTiempoDemora();
            START_TIME_IN_MILLIS = preguntas.getTiempoDemora() * 1000;
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            starTime();

        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" + " " + response, Toast.LENGTH_LONG).show();
            dialog.hide();
        }

        if (getTipoPregunta() == 3) {
            adapter2 = new PreguntasImagenesAdapter(listaPreguntas, getContext());
            recyclerViewUsuarios.setAdapter(adapter2);
            recyclerViewUsuarios.addOnItemTouchListener(new RecyclerViewOnClickListener(getContext(), new RecyclerViewOnClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    btnContinuar.setVisibility(View.VISIBLE);
                    btnContinuar2.setVisibility(View.INVISIBLE);

                    String enviaPregunta = listaPreguntas.get(recyclerViewUsuarios.getChildAdapterPosition(view)).getOpciones();
                    String enviaRespuesta = informacion;

                    if (enviaPregunta.equalsIgnoreCase(informacion)) {
                        setResultado("correcto");
                    } else {
                        setResultado("incorrecto");
                    }

                    adapter2.setSelectedPosition(position);
                }
            }));

        } else if (getTipoPregunta() == 1) {
            adapter = new PreguntasAdapter(listaPreguntas);
            recyclerViewUsuarios.setAdapter(adapter);
            recyclerViewUsuarios.addOnItemTouchListener(new RecyclerViewOnClickListener(getContext(), new RecyclerViewOnClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    btnContinuar.setVisibility(View.VISIBLE);
                    btnContinuar2.setVisibility(View.INVISIBLE);

                    String enviaPregunta = listaPreguntas.get(recyclerViewUsuarios.getChildAdapterPosition(view)).getOpciones();
                    String enviaRespuesta = informacion;

                    if (enviaPregunta.equalsIgnoreCase(informacion)) {
                        setResultado("correcto");
                    } else {
                        setResultado("incorrecto");
                    }

                    adapter.setSelectedPosition(position);
                }
            }));
        } else if (getTipoPregunta() == 2) {
            final ArrayList<String> listaRespuesta = new ArrayList<>();
            String[] bar = preguntas.getRespuesta().split("#&");
            for (String foobar : bar) {
                listaRespuesta.add(String.format(foobar));
            }


            btnContinuar.setVisibility(View.VISIBLE);
            btnContinuar2.setVisibility(View.INVISIBLE);
            adapter3 = new PreguntasSeleccionMultiple(listaPreguntas);
            recyclerViewUsuarios.setAdapter(adapter3);
            listaSeleccionada = new ArrayList<>();
            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (PreguntasVo preguntasVo : listaPreguntas) {
                        if (preguntasVo.isCheck()) {
                            if (stringBuilder.length() > 0)
                                stringBuilder.append(", ");
                            listaSeleccionada.add(preguntasVo.getOpciones());
                        }
                    }
                    if (listaSeleccionada.size() == listaRespuesta.size()) {
                        for (int i = 0; i < listaSeleccionada.size(); i++) {
                            if (listaRespuesta.contains(listaSeleccionada.get(i))) {
                                correctoSeleccionMultiple++;
                            }
                        }
                        if (correctoSeleccionMultiple == listaRespuesta.size()) {
                            setResultado("correcto");
                        } else {
                            setResultado("incorrecto");
                        }
                    } else {
                        setResultado("incorrecto");
                    }
                    comparar();
                }
            });
        } else if (getTipoPregunta() == 4) {
            cargarWebservices();
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


    private void comparar() {
        if (getResultado().equalsIgnoreCase("correcto")) {
            showPopup(getRetroBuena());
        } else {
            showPopup2(getRetroMala());
        }
        tiempoCapturado = i;
        numeroPregunta++;
    }

}