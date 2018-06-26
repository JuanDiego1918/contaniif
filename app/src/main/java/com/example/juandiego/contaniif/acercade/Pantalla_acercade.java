package com.example.juandiego.contaniif.acercade;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.interfaces.Puente;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pantalla_acercade.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pantalla_acercade#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pantalla_acercade extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageView imgMision,imgAcercaNosotros,fondoAcercade,fondoMision;
    TextView mision,acercaNosotros;
    Puente puente;
    Activity activity;

    public Pantalla_acercade() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter instruuno.
     * @param param2 Parameter instrudos.
     * @return A new instance of fragment Pantalla_acercade.
     */
    // TODO: Rename and change types and number of parameters
    public static Pantalla_acercade newInstance(String param1, String param2) {
        Pantalla_acercade fragment = new Pantalla_acercade();
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
        View vista = inflater.inflate(R.layout.fragment_pantalla_acercade, container, false);

        fondoAcercade = vista.findViewById(R.id.acercaNosotrosFondo);
        fondoAcercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.acercade(1);
            }
        });

        fondoMision = vista.findViewById(R.id.misionVisionFondo);
        fondoMision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.acercade(2);
            }
        });


        imgAcercaNosotros = vista.findViewById(R.id.imagenAcercaNosotros);
        imgAcercaNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.acercade(1);
            }
        });


        imgMision = vista.findViewById(R.id.imagenMision);
        imgMision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.acercade(2);
            }
        });

        mision = vista.findViewById(R.id.txtMisionVision);
        mision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.acercade(2);
            }
        });
        acercaNosotros = vista.findViewById(R.id.txtAcercade);
        acercaNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.acercade(1);
            }
        });
        return vista;
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

        if(context instanceof Activity){
            this.activity= (Activity) context;
            puente=(Puente) this.activity;
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
