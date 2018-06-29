package com.example.juandiego.contaniif.principal;

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
 * {@link PantallaPrincipal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PantallaPrincipal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PantallaPrincipal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ImageView empezar1,videos1,eventos1,teoria1,configuracion1,acercade1;
    ImageView rendimiento1,rendimiento3,empezar2,videos2,eventos2,teoria2,configuracion2,acercade2;
    TextView rendimiento2,rendimiento4,empezar3,videos3,eventos3,teoria3,configuracion3,acercade3;

    Puente puente;
    Fragment miFragment;
    Activity activity;
    public PantallaPrincipal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter instruuno.
     * @param param2 Parameter instrudos.
     * @return A new instance of fragment PantallaPrincipal.
     */
    // TODO: Rename and change types and number of parameters
    public static PantallaPrincipal newInstance(String param1, String param2) {
        PantallaPrincipal fragment = new PantallaPrincipal();
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
        View view =  inflater.inflate(R.layout.fragment_pantalla_principal, container, false);

        rendimiento1 = view.findViewById(R.id.rendimiento1);
        rendimiento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(7);
            }
        });

        rendimiento2 = view.findViewById(R.id.rendimiento2);
        rendimiento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(7);
            }
        });

        rendimiento3 = view.findViewById(R.id.rendimiento3);
        rendimiento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(7);
            }
        });

        rendimiento4 = view.findViewById(R.id.rendimiento4);
        rendimiento4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(7);
            }
        });

        empezar1 = view.findViewById(R.id.vinculoEmpezar1);
        empezar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(1);
            }
        });

        empezar2 = view.findViewById(R.id.vinculoEmpezar2);
        empezar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(1);
            }
        });

        empezar3 = view.findViewById(R.id.vinculoEmpezar3);
        empezar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(1);
            }
        });

        videos1 = view.findViewById(R.id.vinculoVideos1);
        videos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(2);
            }
        });

        videos2 = view.findViewById(R.id.vinculoVideos2);
        videos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(2);
            }
        });
        videos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(2);
            }
        });

        videos3 = view.findViewById(R.id.vinculoVideos3);
        videos3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(2);
            }
        });

        eventos1 = view.findViewById(R.id.vinculoEvento1);
        eventos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(3);
            }
        });

        eventos2 = view.findViewById(R.id.vinculoEvento2);
        eventos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(3);
            }
        });

        eventos3 = view.findViewById(R.id.vinculoEvento3);
        eventos3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(3);
            }
        });

        teoria1 = view.findViewById(R.id.vinculoTeoria1);
        teoria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(4);
            }
        });

        teoria2 = view.findViewById(R.id.vinculoTeoria2);
        teoria2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(4);
            }
        });

        teoria3 = view.findViewById(R.id.vinculoTeoria3);
        teoria3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(4);
            }
        });

        configuracion1 = view.findViewById(R.id.vinculoConfig1);
        configuracion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(5);
            }
        });

        configuracion2 = view.findViewById(R.id.vinculoConfig2);
        configuracion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(5);
            }
        });


        configuracion3 = view.findViewById(R.id.vinculoConfig3);
        configuracion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(5);
            }
        });

        acercade1 = view.findViewById(R.id.vinculoAcerca1);
        acercade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(6);
            }
        });

        acercade2 = view.findViewById(R.id.vinculoAcerca2);
        acercade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(6);
            }
        });

        acercade3 =view.findViewById(R.id.vinculoAcerca3);
        acercade3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puente.pantalla(6);
            }
        });



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


        if(context instanceof Activity){
            this.activity= (Activity) context;
            puente=(Puente) this.activity;
        }

        //if(context instanceof Activity){
         //   this.activity= (Activity) context;
       // }

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
