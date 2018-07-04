package com.example.juandiego.contaniif.videos;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.adapter.CategoriasAdapter;
import com.example.juandiego.contaniif.entidades.CategoriasVo;
import com.example.juandiego.contaniif.interfaces.Puente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriasVideosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriasVideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasVideosFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CategoriasVideosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriasVideosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriasVideosFragment newInstance(String param1, String param2) {
        CategoriasVideosFragment fragment = new CategoriasVideosFragment();
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
    RecyclerView recyclerView;
    ArrayList<CategoriasVo> listasCategorias;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    Puente puente;
    Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_categorias_videos, container, false);


        recyclerView=view.findViewById(R.id.recycler_categoria);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        request = Volley.newRequestQueue(getContext());
        cargarWebservices();

        return view;
    }

    private void cargarWebservices() {
        String url = "http://"+getContext().getString(R.string.ip)+"VideosCategorias.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
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

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"NO se pudo Consultar:"+error.toString(), Toast.LENGTH_LONG).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        listasCategorias=new ArrayList<>();
        JSONArray json=response.optJSONArray("categorias");
        try {
            JSONObject jsonObject=null;
            for (int i=0;i<json.length();i++){
                CategoriasVo categoriasVo=new CategoriasVo();
                jsonObject=json.getJSONObject(i);
                categoriasVo.setId(jsonObject.optString("id"));
                String nombre,mayuscula;
                nombre=jsonObject.optString("nombre");
                mayuscula=jsonObject.optString("nombre");
                categoriasVo.setLetraMa(mayuscula.substring(0,1));
                categoriasVo.setNombre(nombre.substring(1,nombre.length()));
                listasCategorias.add(categoriasVo);
                //Toast.makeText(getApplicationContext(),"Desc "+jsonObject.optString("id"),Toast.LENGTH_SHORT).show();
            }
            CategoriasAdapter miCategoriasAdapter=new CategoriasAdapter(listasCategorias);
            recyclerView.setAdapter(miCategoriasAdapter);
            miCategoriasAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    puente.numero(listasCategorias.get(recyclerView.getChildAdapterPosition(v)).getId());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
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
}
