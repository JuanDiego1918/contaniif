package com.example.juandiego.contaniif.actividades;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.acercade.AcercaDeNosotros;
import com.example.juandiego.contaniif.acercade.MisionVision;
import com.example.juandiego.contaniif.acercade.Pantalla_acercade;
import com.example.juandiego.contaniif.configuracion.PantallaConfiguracion;
import com.example.juandiego.contaniif.entidades.NumeroVo;
import com.example.juandiego.contaniif.eventos.EventosActivity;
import com.example.juandiego.contaniif.interfaces.AllFragments;
import com.example.juandiego.contaniif.interfaces.Puente;
import com.example.juandiego.contaniif.mi_rendimiento.MiRendimiento;
import com.example.juandiego.contaniif.principal.PantallaPrincipal;
import com.example.juandiego.contaniif.principal.Pantalla_empezar;
import com.example.juandiego.contaniif.principal.PrimerFragment;
import com.example.juandiego.contaniif.teoria.Pantalla_teoria;
import com.example.juandiego.contaniif.videos.CategoriasVideosFragment;
import com.example.juandiego.contaniif.videos.VideosActivity;

import java.util.ArrayList;

public class ActivityContenedora extends AppCompatActivity implements AllFragments, Puente {

    android.support.v4.app.Fragment miFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedora);

        int numeroPantalla = 0;


        Bundle miBundle = this.getIntent().getBundleExtra("pantalla");
        if (miBundle != null) {
            numeroPantalla = miBundle.getInt("numeroPantalla");
        } else {
            miFragment = new CategoriasVideosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activityContenedora, miFragment).commit();
        }


        //Toast.makeText(getApplicationContext(), "numero " + numeroPantalla, Toast.LENGTH_SHORT).show();
        cambioPantalla(numeroPantalla);

        ///////////////////////

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
///////////////////////
    }

    private void cambioPantalla(int numeroPantalla) {
        switch (numeroPantalla) {
            case 1:
                miFragment = new PrimerFragment();
                break;
            case 2:
                miFragment = new CategoriasVideosFragment();
                break;
            case 4:
                miFragment = new Pantalla_teoria();
                break;
            case 5:
                miFragment = new PantallaConfiguracion();
                break;
            case 6:
                miFragment = new Pantalla_acercade();
                break;
            case 7:
                miFragment = new MiRendimiento();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.activityContenedora, miFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void pantalla(int numero) {
        switch (numero) {
            case 1:
                finish();
                break;
        }
    }

    @Override
    public void acercade(int numero) {
        switch (numero) {
            case 1:
                miFragment = new AcercaDeNosotros();
                break;
            case 2:
                miFragment = new MisionVision();
                break;

            case 3:
                miFragment = new Pantalla_acercade();
                break;
            case 4:
                miFragment = new MisionVision();
                break;
            case 5:
                miFragment = new Pantalla_acercade();
                break;
            case 6:
                miFragment = new AcercaDeNosotros();
                break;

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.activityContenedora, miFragment).commit();
    }


    @Override
    public void numero(String tipo) {
        Bundle miBundle = new Bundle();
        miBundle.putString("id", tipo);
        Intent miIntent = new Intent(getApplicationContext(), VideosActivity.class);
        miIntent.putExtras(miBundle);
        startActivity(miIntent);
    }

    @Override
    public void reinciar(int numeroPregunta, int tipo, ArrayList<String> lista) {

        Bundle datos = new Bundle();
        Bundle miBundle=new Bundle();
        datos.putInt("numeroPregunta", numeroPregunta);
        datos.putStringArrayList("color",lista);
        miBundle.putBundle("Todo",datos);
        miFragment = new Pantalla_empezar();
        miFragment.setArguments(miBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activityContenedora, miFragment).commit();
    }
}
