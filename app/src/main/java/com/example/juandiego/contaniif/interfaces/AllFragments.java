package com.example.juandiego.contaniif.interfaces;


import com.example.juandiego.contaniif.acercade.AcercaDeNosotros;
import com.example.juandiego.contaniif.acercade.MisionVision;
import com.example.juandiego.contaniif.acercade.Pantalla_acercade;
import com.example.juandiego.contaniif.configuracion.PantallaConfiguracion;
import com.example.juandiego.contaniif.mi_rendimiento.MiRendimiento;
import com.example.juandiego.contaniif.principal.PantallaPrincipal;
import com.example.juandiego.contaniif.principal.Pantalla_empezar;
import com.example.juandiego.contaniif.principal.Pantalla_empezar_drag;
import com.example.juandiego.contaniif.principal.PrimerFragment;
import com.example.juandiego.contaniif.principal.SinConexionInternet;
import com.example.juandiego.contaniif.teoria.Pantalla_teoria;
import com.example.juandiego.contaniif.videos.CategoriasVideosFragment;

public interface AllFragments
        extends Pantalla_empezar.OnFragmentInteractionListener
        ,Pantalla_teoria.OnFragmentInteractionListener
        ,Pantalla_acercade.OnFragmentInteractionListener,
        PantallaPrincipal.OnFragmentInteractionListener,
        MisionVision.OnFragmentInteractionListener,
        AcercaDeNosotros.OnFragmentInteractionListener,
        SinConexionInternet.OnFragmentInteractionListener,
        CategoriasVideosFragment.OnFragmentInteractionListener,
        PrimerFragment.OnFragmentInteractionListener,
        PantallaConfiguracion.OnFragmentInteractionListener,
        MiRendimiento.OnFragmentInteractionListener,
        Pantalla_empezar_drag.OnFragmentInteractionListener{
}
