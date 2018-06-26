package com.example.juandiego.contaniif.eventos;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.adapter.PaginacionNumeroAdapter;
import com.example.juandiego.contaniif.entidades.EventoVo;
import com.example.juandiego.contaniif.entidades.NumeroVo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventosActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public  static ArrayList<EventoVo> listaEventos;
    public static ArrayList<NumeroVo> listaNumero;
    EventoVo miEventoVo;
    NumeroVo miNumeroVo;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    public static RecyclerView recyclerViewNumero;
    public static PaginacionNumeroAdapter miNumeroAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        listaEventos=new ArrayList<>();
        listaNumero=new ArrayList<>();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        recyclerViewNumero=findViewById(R.id.numeroPaginacion);

        cargarWebService();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ppal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_recargar) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarWebService() {
        recyclerViewNumero.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewNumero.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
        request= Volley.newRequestQueue(getApplication());
        String url="http://"+getApplicationContext().getString(R.string.ip)+"/apolunios/eventos.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplication(),"NO se pudo Consultar:"+error.toString(), Toast.LENGTH_LONG).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json=response.optJSONArray("eventos");
        try {
            JSONObject jsonObject=null;
            for (int i=0;i<json.length();i++){
                miEventoVo=new EventoVo();
                miNumeroVo=new NumeroVo();
                miNumeroVo.setNumeroPagina(i+1);
                jsonObject=json.getJSONObject(i);
                miEventoVo.setNombre(jsonObject.optString("nombre"));
                miEventoVo.setFecha(jsonObject.optString("fecha"));
                miEventoVo.setLugar(jsonObject.optString("lugar"));
                miEventoVo.setDescripcion(jsonObject.optString("descripcion"));
                miEventoVo.setImage(jsonObject.optString("id"));
                listaEventos.add(miEventoVo);
                listaNumero.add(miNumeroVo);
                //Toast.makeText(getApplicationContext(),"Desc "+jsonObject.optString("id"),Toast.LENGTH_SHORT).show();
            }
            // Set up the ViewPager with the sections adapter.
            mViewPager.setAdapter(mSectionsPagerAdapter);

            miNumeroAdapter=new PaginacionNumeroAdapter(listaNumero);
            recyclerViewNumero.setAdapter(miNumeroAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
        }

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        TextView fecha,descripcion,lugar,nombre;
        ImageView img;
        RequestQueue request;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            funciona();
            return fragment;
        }

        private static void funciona() {
            Log.v("hola","ENTRA");
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_eventos, container, false);

            nombre=rootView.findViewById(R.id.nombreEvento);
            fecha=rootView.findViewById(R.id.fechaEvento);
            descripcion=rootView.findViewById(R.id.descripEvento);
            img=rootView.findViewById(R.id.ImagenEvento);
            lugar=rootView.findViewById(R.id.lugarEvento);
            request= Volley.newRequestQueue(getContext());


            mostrarImg(listaEventos.get(getArguments().getInt(ARG_SECTION_NUMBER)).getImage());
            nombre.setText(listaEventos.get(getArguments().getInt(ARG_SECTION_NUMBER)).getNombre());
            descripcion.setText(listaEventos.get(getArguments().getInt(ARG_SECTION_NUMBER)).getDescripcion());
            fecha.setText(listaEventos.get(getArguments().getInt(ARG_SECTION_NUMBER)).getFecha());
            lugar.setText(listaEventos.get(getArguments().getInt(ARG_SECTION_NUMBER)).getLugar());

            return rootView;
        }

        private void mostrarImg(String rutaImagen) {
            String ip=getContext().getString(R.string.ip);

            String urlImagen="http://"+ip+rutaImagen;
            ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    img.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(),"Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                }
            });
            request.add(imageRequest);
        }

        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(final int position) {
                // getItem is called to instantiate the fragment for the given page.

                // Return a PlaceholderFragment (defined as a static inner class below).
                return PlaceholderFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return listaNumero.size();
            }
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(final int position) {
            // getItem is called to instantiate the fragment for the given page.

            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return listaNumero.size();
        }
    }
}