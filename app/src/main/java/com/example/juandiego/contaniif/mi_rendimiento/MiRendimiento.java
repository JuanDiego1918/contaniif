package com.example.juandiego.contaniif.mi_rendimiento;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juandiego.contaniif.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MiRendimiento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MiRendimiento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiRendimiento extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String credenciales;
    String cojeComentario;

    public String getCojeComentario() {
        return cojeComentario;
    }

    public void setCojeComentario(String cojeComentario) {
        this.cojeComentario = cojeComentario;
    }

    public String getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }

    Dialog myDialogComent;
    LinearLayout comentario;
    RequestQueue request;
    //StringReqsuest stringRequest;
    StringRequest stringRequest;

    private OnFragmentInteractionListener mListener;

    public MiRendimiento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiRendimiento.
     */
    // TODO: Rename and change types and number of parameters
    public static MiRendimiento newInstance(String param1, String param2) {
        MiRendimiento fragment = new MiRendimiento();
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
        // Inflate the layout for this fragment}
        View vista=inflater.inflate(R.layout.fragment_mi_rendimiento_principal, container, false);
        cargarCredenciales();
        comentario=vista.findViewById(R.id.comentarios);
        myDialogComent= new Dialog(getContext());
        request = Volley.newRequestQueue(getContext());


        comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VentanaEmergente();
            }
        });
        return vista;
    }

    private void VentanaEmergente() {
        Button enviar;
        final EditText coment;

        myDialogComent.setContentView(R.layout.popup_comentarios);

        coment=myDialogComent.findViewById(R.id.comentariotxt);
        enviar = myDialogComent.findViewById(R.id.enviar);
        //comentario=coment.getText().toString();
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCojeComentario(coment.getText().toString());
                //Toast.makeText(getContext(),"Comentario "+coment.getText().toString(),Toast.LENGTH_SHORT).show();
                myDialogComent.dismiss();
                enviarDatosComentarios();
            }
        });

        myDialogComent.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogComent.show();
    }


    private void cargarCredenciales() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        String credenciales = preferences.getString("correo","No existe el valor");
        setCredenciales(credenciales);

    }
    private void enviarDatosComentarios() {

        /*Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.getTime();
        Toast.makeText(getContext(),"Comentario "+c.getTime(),Toast.LENGTH_SHORT).show();*/

        String url;
        url = "http://"+getContext().getString(R.string.ip2)+"/apolunios/registroComentario.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")) {
                    Toast.makeText(getContext(), "Registro de comentario exitoso", Toast.LENGTH_SHORT).show();
                } else {
                                     Toast.makeText(getContext(),"Comentario no registrado", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se pudo registrar el comentario" + error.toString(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String idusuario = getCredenciales();
                String comentario = getCojeComentario();
                String fechacomentario = "2018-08-01";

                Map<String, String> parametros = new HashMap<>();
                parametros.put("idusuario", idusuario);
                parametros.put("comentario", comentario);
                parametros.put("fechacomentario",fechacomentario);
                return parametros;
            }
        };

        request.add(stringRequest);
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
        mListener = null;
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
}
