package com.example.juandiego.contaniif.registro;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juandiego.contaniif.R;
import com.example.juandiego.contaniif.entidades.DepartamentosVo;
import com.example.juandiego.contaniif.entidades.PreguntasVo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    int posicion;
    int accion;
    String genero;
    String municipio;
    String departamento;

    boolean seleccionaGenero;
    boolean seleccionaDepartamento;

    public boolean isSeleccionaImagen() {
        return seleccionaImagen;
    }

    public void setSeleccionaImagen(boolean seleccionaImagen) {
        this.seleccionaImagen = seleccionaImagen;
    }

    boolean seleccionaImagen;
    boolean seleccionaMunicipio;

    public boolean isSeleccionaGenero() {
        return seleccionaGenero;
    }

    public void setSeleccionaGenero(boolean seleccionaGenero) {
        this.seleccionaGenero = seleccionaGenero;
    }

    public boolean isSeleccionaDepartamento() {
        return seleccionaDepartamento;
    }

    public void setSeleccionaDepartamento(boolean seleccionaDepartamento) {
        this.seleccionaDepartamento = seleccionaDepartamento;
    }

    public boolean isSeleccionaMunicipio() {
        return seleccionaMunicipio;
    }

    public void setSeleccionaMunicipio(boolean seleccionaMunicipio) {
        this.seleccionaMunicipio = seleccionaMunicipio;
    }

    public boolean isSeleccionaFecha() {
        return seleccionaFecha;
    }

    public void setSeleccionaFecha(boolean seleccionaFecha) {
        this.seleccionaFecha = seleccionaFecha;
    }

    boolean seleccionaFecha;

    int validacionGenero;
    int validacionMunicipio;
    int validacionDepartamento;

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
    public int getValidacionGenero() {
        return validacionGenero;
    }

    public void setValidacionGenero(int validacionGenero) {
        this.validacionGenero = validacionGenero;
    }

    public int getValidacionMunicipio() {
        return validacionMunicipio;
    }

    public void setValidacionMunicipio(int validacionMunicipio) {
        this.validacionMunicipio = validacionMunicipio;
    }

    public int getValidacionDepartamento() {
        return validacionDepartamento;
    }

    public void setValidacionDepartamento(int validacionDepartamento) {
        this.validacionDepartamento = validacionDepartamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ///////
    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios
    private String path;//almacena la ruta de la imagen
    File fileImagen;
    Bitmap bitmap;
    private final int MIS_PERMISOS = 100;
    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;
    ///////-Elementos del layout
    Spinner listaDepartamentos, listaMunicipios, listaGenero;
    EditText campoNombre, campoApellido, campoFechaNacimiento, campoCorreo;
    ImageView imagenUsuario, imagenCamara;
    Button btnRegistro;
    //////-Listas
    ArrayList<String> ArrayDepartamentos;
    ArrayList<String> ArrayMunicipios;
    ArrayList<String> ArrayGenero;
    //////-Para pedir los datos a la base de datos
    RequestQueue request;
    RequestQueue request2;
    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;
    /////
    ProgressDialog progreso;
    /////

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_registro, container, false);
        request = Volley.newRequestQueue(getContext());
        request2 = Volley.newRequestQueue(getContext());
        ArrayGenero = new ArrayList<>();
        ArrayGenero.add("Seleccioar genero");
        ArrayGenero.add("Masculino");
        ArrayGenero.add("Femenino");
        //
        ArrayDepartamentos = new ArrayList<>();
        ArrayDepartamentos.add("Seleccione su departamento");
        ArrayDepartamentos.add("Antioquia");
        ArrayDepartamentos.add("Atlántico");
        ArrayDepartamentos.add("Bogotá");
        ArrayDepartamentos.add("Bolívar");
        ArrayDepartamentos.add("Boyacá");
        ArrayDepartamentos.add("Caldas");
        ArrayDepartamentos.add("Caquetá");
        ArrayDepartamentos.add("Cauca");
        ArrayDepartamentos.add("Cesar");
        ArrayDepartamentos.add("Córdoba");
        ArrayDepartamentos.add("Cundinamarca");
        ArrayDepartamentos.add("Chocó");
        ArrayDepartamentos.add("Huila");
        ArrayDepartamentos.add("La Guajira");
        ArrayDepartamentos.add("Magdalena");
        ArrayDepartamentos.add("Meta");
        ArrayDepartamentos.add("Nariño");
        ArrayDepartamentos.add("Norte de Santander");
        ArrayDepartamentos.add("Quindío");
        ArrayDepartamentos.add("Risaralda");
        ArrayDepartamentos.add("Santander");
        ArrayDepartamentos.add("Sucre");
        ArrayDepartamentos.add("Tolima");
        ArrayDepartamentos.add("Valle del Cauca");
        ArrayDepartamentos.add("Arauca");
        ArrayDepartamentos.add("Casanare");
        ArrayDepartamentos.add("Putumayo");
        ArrayDepartamentos.add("San Andrés y Providencia");
        ArrayDepartamentos.add("Amazonas");
        ArrayDepartamentos.add("Guainía");
        ArrayDepartamentos.add("Guaviare");
        ArrayDepartamentos.add("Vaupés");
        ArrayDepartamentos.add("Vichada");
        //
        listaDepartamentos = vista.findViewById(R.id.spinnerDepartamentoRegistro);
        ArrayAdapter<CharSequence> adapterDepartamentos = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ArrayDepartamentos);
        listaDepartamentos.setAdapter(adapterDepartamentos);
        listaDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    setPosicion(position);
                    setDepartamento(ArrayDepartamentos.get(position));
                    //Toast.makeText(getContext(), "posicion" + getPosicion(), Toast.LENGTH_SHORT).show();
                    cargarListaMunicipios();
                    setValidacionDepartamento(2);
                    setSeleccionaDepartamento(true);
                } else {
                    setSeleccionaDepartamento(false);
                    setValidacionDepartamento(10);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //--
        listaMunicipios = vista.findViewById(R.id.spinnerCuidadRegistro);

        //--
        listaGenero = vista.findViewById(R.id.spinnerGeneroRegistro);
        ArrayAdapter<CharSequence> adapterGenero = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ArrayGenero);
        listaGenero.setAdapter(adapterGenero);
        listaGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    setGenero(ArrayGenero.get(i));
                    setValidacionGenero(2);
                    setSeleccionaGenero(true);
                } else {
                    setSeleccionaGenero(false);
                    setValidacionGenero(10);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //
        campoNombre = vista.findViewById(R.id.campoNombreRegistro);
        campoApellido = vista.findViewById(R.id.campoApellidosRegistro);
        campoCorreo = vista.findViewById(R.id.campoCorreoRegistro);
        campoFechaNacimiento = vista.findViewById(R.id.campoFechaRegistro);
        //
        imagenUsuario = vista.findViewById(R.id.imagenUsuario);
        imagenCamara = vista.findViewById(R.id.imagenCamara);
        imagenCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcionesCapturaFoto();
            }
        });
        //
        btnRegistro = vista.findViewById(R.id.btnRegistrar);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (campoNombre.getText().equals("") || campoApellido.getText().equals("")|| seleccionaGenero==false || seleccionaDepartamento==false || seleccionaMunicipio==false || seleccionaImagen==false ){
                    Toast.makeText(getContext(),"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Noebe llenar todos los campos",Toast.LENGTH_SHORT).show();
                    registrarUsuarioss();
                }

            }
        });
        return vista;
    }

    private void cargarListaMunicipios() {
        String ip=getContext().getString(R.string.ip);
        String url = "http://"+getContext().getString(R.string.ip2)+"/apolunios/traerMunicipio.php?pas="+getPosicion();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        setAccion(1);
    }

    private void opcionesCapturaFoto() {
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    setSeleccionaImagen(true);
                    abrirCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        setSeleccionaImagen(true);
                        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                        setSeleccionaImagen(false);
                    }
                }
            }
        });
        builder.show();
    }

    private void abrirCamara() {
        setSeleccionaImagen(true);
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();
        if (isCreada==false){
            isCreada=miFile.mkdirs();//por si la variable no fue creada, se crea de nuevo
        }
        if (isCreada==true){
            Long consecutivo= System.currentTimeMillis()/100;//aqui iba un 100, por si no funciona el codigo este es el error
            String nombre=consecutivo.toString()+".jpg";

            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;//indicamos la ruta de almacenamiento

            fileImagen=new File(path);

            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));

            ////
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities=getContext().getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(getContext(),authorities,fileImagen);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else
            {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
            }
            startActivityForResult(intent,COD_FOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                Uri miPath=data.getData();
                if (data.getData()==null){
                    Toast.makeText(getContext(),"No se eligio ninguna imagen",Toast.LENGTH_SHORT).show();
                }
                imagenUsuario.setImageURI(miPath);

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),miPath);
                    imagenUsuario.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
//                                imgFoto.setVisibility(View.INVISIBLE);
                            }
                        });

                bitmap= BitmapFactory.decodeFile(path);
                imagenUsuario.setImageBitmap(bitmap);

                break;
        }
        bitmap=redimensionarImagen(bitmap,600,800);
    }

    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {
        int ancho=bitmap.getWidth();
        int alto=bitmap.getHeight();
        if(ancho>anchoNuevo || alto>altoNuevo){
            float escalaAncho=anchoNuevo/ancho;
            float escalaAlto= altoNuevo/alto;
            Matrix matrix=new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);
            return Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);

        }else{
            return bitmap;
        }
    }

    private String convertirImgString(Bitmap bitmap) {
        ByteArrayOutputStream array=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte=array.toByteArray();
        String imagenString= Base64.encodeToString(imagenByte, Base64.DEFAULT);
        return imagenString;
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

    @Override
    public void onResponse(JSONObject response) {

        if (getAccion()==1){
//           progreso.hide();
            JSONArray json = response.optJSONArray("usuario");
            JSONObject jsonObject = null;
            ArrayMunicipios = new ArrayList<String>();
            try {
                ArrayMunicipios.add("Seleccione su municipio");
                for (int i = 0; i < json.length(); i++) {
                    jsonObject = json.getJSONObject(i);
                    //departamentos = new DepartamentosVo();
                    ArrayMunicipios.add(jsonObject.getString("municipio"));
                    //Toast.makeText(getContext(),"lista url" + response,Toast.LENGTH_LONG).show();
                }
                ArrayAdapter<CharSequence> adapterMunicipios=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,ArrayMunicipios);
                listaMunicipios.setAdapter(adapterMunicipios);
                listaMunicipios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i!=0){
                            setMunicipio(ArrayMunicipios.get(i));
                            setValidacionMunicipio(2);
                            setSeleccionaMunicipio(true);
                        }else {
                            setValidacionMunicipio(10);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" + " " + response, Toast.LENGTH_LONG).show();

            }
        }else if (getAccion()==2){
            progreso.hide();
            Toast.makeText(getContext(), "Se ha registrado exitosamente", Toast.LENGTH_LONG).show();
        }

    }

    private void traerMunicipio() {
        //Toast.makeText(getContext(), "Se trae el municipio", Toast.LENGTH_SHORT).show();
        String url = "http://"+getContext().getString(R.string.ip2)+"/apolunios/traerMunicipio.php?pas="+getPosicion();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        if (getAccion()==1){
            Toast.makeText(getContext(), "Se produjo un error al cargar la lista de departamentos " + error.toString(), Toast.LENGTH_LONG).show();
        }else if (getAccion()==2){
            Toast.makeText(getContext(), "No se pudo registrar " + error.toString(), Toast.LENGTH_LONG).show();
        }

    }


    private void registrarUsuarios() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        String url;
        url = "http://"+getContext().getString(R.string.ip2)+"/apolunios/registroUsuarios.php?nombres="+campoNombre.getText().toString()+"&apellidos="
                +campoApellido.getText().toString()+"&genero="+getGenero()+"&correo="
                +campoCorreo.getText().toString()+"&fechaNacimiento="
                +campoFechaNacimiento.getText().toString()+"&departamento="
                +getDepartamento()+"&municipio="+getMunicipio()+"&rutaImagen="+convertirImgString(bitmap);

        //Toast.makeText(getContext(),"No se eligio ninguna imagen" + convertirImgString(bitmap),Toast.LENGTH_SHORT).show();

        url=url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        setAccion(2);
    }



    private void registrarUsuarioss() {
        //progreso = new ProgressDialog(VentanaRegistro.this);
        //progreso.setMessage("Cargando...");
        //progreso.show();

        String url;
        url = "http://"+getContext().getString(R.string.ip2)+"/apolunios/registro.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")) {
                    Toast.makeText(getContext(), "SE REGISTRA " + response, Toast.LENGTH_SHORT).show();
                    android.support.v4.app.Fragment miFragment=null;
                    miFragment=new Registro();
                    getFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
                } else {
                    Toast.makeText(getContext(),"Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
                    /*progreso.hide();

                    txtDocumento.setText("");
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtFicha.setText("");
                    txtTelefono.setText("");
                    txtTalla.setText("");
                    txtPeso.setText("");
                    txtEdad.setText("");
                    imgFoto.setImageDrawable(getDrawable(R.drawable.camara));*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();
                Toast.makeText(getContext(), "No se pudo Registrar" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("RESULTADO", "NO SE REGISTRA desde onError " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String nombres = campoNombre.getText().toString();
                String apellidos = campoApellido.getText().toString();
                String genero = getGenero();
                String correo = campoCorreo.getText().toString();
                String fechaNacimiento = campoFechaNacimiento.getText().toString();
                String departamento = getDepartamento();
                String municipio = getMunicipio();
                String rutaImagen = convertirImgString(bitmap);

                /*String nombres = "aqui va la ruta de la imagen";
                String apellidos = "aqui va la ruta de la imagen";
                String genero = "aqui va la ruta de la imagen";
                String correo = "aqui va la ruta de la imagen";
                String fechaNacimiento = "aqui va la ruta de la imagen";
                String departamento = "aqui va la ruta de la imagen";
                String municipio = "aqui va la ruta de la imagen";
                String rutaImagen = "aqui va la ruta de la imagen";*/

                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombres", nombres);
                parametros.put("apellidos", apellidos);
                parametros.put("genero", genero);
                parametros.put("correo", correo);
                parametros.put("fechaNacimiento", fechaNacimiento);
                parametros.put("departamento", departamento);
                parametros.put("municipio", municipio);
                parametros.put("rutaImagen", rutaImagen);
                return parametros;
            }
        };

        /*if (campoNombre.getText().length()<1 || campoApellido.getText().length()<1 ||
                campoCorreo.getText().length()<1 || campoFechaNacimiento.getText().length()<1 || getValidacionMunicipio()==10||
                getValidacionDepartamento()==10 || getValidacionGenero()==10){

            Toast.makeText(getContext(),"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();
        }else {*/
        // url=url.replace(" ","%20");
        // jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);

        guardarCredenciales();
        request.add(stringRequest);
        //setAccion(2);
    }

    private void guardarCredenciales() {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("correo",campoCorreo.getText().toString());
        editor.commit();
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

