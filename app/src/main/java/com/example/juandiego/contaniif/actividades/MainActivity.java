package com.example.juandiego.contaniif.actividades;
///jhsakjdh
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.acercade.AcercaDeNosotros;
import com.example.juandiego.contaniif.acercade.MisionVision;
import com.example.juandiego.contaniif.acercade.Pantalla_acercade;
import com.example.juandiego.contaniif.configuracion.PantallaConfiguracion;
import com.example.juandiego.contaniif.eventos.EventosActivity;
import com.example.juandiego.contaniif.interfaces.AllFragments;
import com.example.juandiego.contaniif.interfaces.Puente;
import com.example.juandiego.contaniif.mi_rendimiento.MiRendimiento;
import com.example.juandiego.contaniif.principal.PantallaPrincipal;
import com.example.juandiego.contaniif.principal.Pantalla_empezar;
import com.example.juandiego.contaniif.principal.PrimerFragment;
import com.example.juandiego.contaniif.principal.SinConexionInternet;
import com.example.juandiego.contaniif.registro.Registro;
import com.example.juandiego.contaniif.teoria.Pantalla_teoria;
import com.example.juandiego.contaniif.videos.CategoriasVideosFragment;
import com.example.juandiego.contaniif.videos.VideosActivity;

public class MainActivity extends AppCompatActivity implements AllFragments, Puente{android.support.v4.app.Fragment miFragment=null;

    int numerooo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ConnectivityManager con = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();


        if (networkInfo!=null && networkInfo.isConnected()){
            //miFragment=new Registro();
           // miFragment=new PantallaPrincipal();
            //getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
            cargarCredenciales();

        }else{

            miFragment = new SinConexionInternet();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

    }

    private void cargarCredenciales() {
        SharedPreferences preferences = getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        String credenciales = preferences.getString("correo","No existe el valor");
       if (credenciales!="No existe el valor"){
           miFragment=new PantallaPrincipal();
           getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
       }else {
           miFragment=new Registro();
           getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
       }
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


        if (id == R.id.action_recargar) {
            //Fragment fragment = new PantallaPrincipal();

            switch (numerooo){

                case 0:

                    miFragment=new PantallaPrincipal();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
                    break;

                case 1:
                    miFragment=new Pantalla_acercade();
                    numerooo = 0;
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
                    break;
            }

            //miFragment=new PantallaPrincipal();
            //getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void pantalla(int cambiar) {
        switch (cambiar){
            case 1:miFragment=new PrimerFragment();
                numerooo = 0;
                break;
            case 2:
                miFragment=new CategoriasVideosFragment();
                numerooo = 0;
                break;
            case 3:
                Intent intent = new Intent(getApplicationContext(),EventosActivity.class);
                startActivity(intent);
                numerooo = 0;
                break;
            case 4:miFragment=new Pantalla_teoria();
                numerooo = 0;
                break;
            case 5:
                miFragment=new PantallaConfiguracion();
                numerooo = 0;
                break;
            case 6:miFragment=new Pantalla_acercade();
                numerooo = 0;
                break;
            case 7:miFragment=new MiRendimiento();
                numerooo = 0;
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
    }

    @Override
    public void acercade(int numero) {
        switch (numero){
            case 1:miFragment=new AcercaDeNosotros();
            numerooo = 1;
                break;
            case 2:miFragment=new MisionVision();
            numerooo = 1;
                break;

            case 3:miFragment=new Pantalla_acercade();
                numerooo = 1;
                break;

            case 4:miFragment=new MisionVision();
                numerooo = 1;
                break;

            case 5:miFragment=new Pantalla_acercade();
                numerooo = 1;
                break;


            case 6:miFragment=new AcercaDeNosotros();
                numerooo = 1;
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
    }

    @Override
    public void numero(String tipo) {
        Bundle miBundle=new Bundle();
        miBundle.putString("id",tipo);
        Intent miIntent=new Intent(getApplicationContext(),VideosActivity.class);
        miIntent.putExtras(miBundle);
        startActivity(miIntent);
    }

    @Override
    public void reinciar(int numeroPregunta) {
        Bundle miBundle=new Bundle();

        miBundle.putInt("numeroPregunta",numeroPregunta);
        miFragment=new Pantalla_empezar();
        miFragment.setArguments(miBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
