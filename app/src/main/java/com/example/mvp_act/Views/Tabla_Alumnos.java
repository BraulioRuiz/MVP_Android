package com.example.mvp_act.Views;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;

import com.example.mvp_act.Models.Alumno;
import com.example.mvp_act.Models.UserLogin;
import com.example.mvp_act.Presents.Tabla_AlumnosPresent;
import com.example.mvp_act.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tabla_Alumnos extends Fragment implements Tabla_AlumnosPresent.View {

    private TableLayout tl;
    private TextView tvNombres;
    private TextView tvApellidos;
    private TextView tvEdad;
    private Button btnAlumno;
    private Button btnEliminar;
    private ViewGroup root;
    private Button btnActualizar;

    private Tabla_AlumnosPresent present;
    public Tabla_Alumnos() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.fragment_tabla__alumnos, null);
        tl = (TableLayout) root.findViewById(R.id.tbAlumno);
        //token = getArguments().getString("token");
        System.out.println(UserLogin.token);
        present = new Tabla_AlumnosPresent(this);
        present.cargarTabla();
        //getAlumnos();
        return root;
    }
    @Override
    public void tablaAlert(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void asignarTabla(ArrayList<Alumno> alumnos) {
        int count = tl.getChildCount();
        if(count>1){
            for (int i = 1; i < count; i++) {
                View child = tl.getChildAt(i);
                tl.removeView(child);
            }
        }
        for(int p =0; p < alumnos.size(); p++){
            TableRow tr1 = new TableRow(root.getContext());
            System.out.println(alumnos.get(p).nombre);
            tr1.setClickable(true);
            TableRow.LayoutParams layoutId = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams layoutNombre = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

            tvNombres = new TextView(getActivity());
            tvNombres.setText(alumnos.get(p).nombre);
            tvNombres.setGravity(Gravity.CENTER);
            tvNombres.setTextColor(Color.WHITE);
            tvNombres.setPadding(25, 3, 25, 3);
            tvNombres.setLayoutParams(layoutNombre);
            tr1.addView(tvNombres);

            tvApellidos = new TextView(getActivity());
            tvApellidos.setText(alumnos.get(p).apellidos);
            tvApellidos.setGravity(Gravity.CENTER);
            tvApellidos.setTextColor(Color.WHITE);
            tvApellidos.setPadding(25, 3, 25, 3);
            tvApellidos.setLayoutParams(layoutNombre);
            tr1.addView(tvApellidos);

            tvEdad = new TextView(getActivity());
            tvEdad.setText(alumnos.get(p).edad);
            tvEdad.setGravity(Gravity.CENTER);
            tvEdad.setTextColor(Color.WHITE);
            tvEdad.setPadding(25, 3, 25, 3);
            tvEdad.setLayoutParams(layoutNombre);
            tr1.addView(tvEdad);

            btnAlumno = new Button(getActivity());
            btnAlumno.setText("Info");
            btnAlumno.setGravity(Gravity.CENTER);
            btnAlumno.setBackgroundColor(Color.parseColor("#4E7C5B"));
            btnAlumno.setTag(alumnos.get(p).id);
            btnAlumno.setTextColor(Color.WHITE);
            btnAlumno.setHeight(30);
            btnAlumno.setWidth(100);
            tr1.addView(btnAlumno);


            btnAlumno.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    int id = Integer.parseInt(view.getTag().toString());
                    Toast.makeText(getContext(), "Alumno seleccionado "+ id, Toast.LENGTH_LONG).show();
                    present.getAlumno(id);
                    //getAlumno(id);
                }
            });

            btnActualizar = new Button(getActivity());
            btnActualizar.setText("Editar");
            btnActualizar.setGravity(Gravity.CENTER);
            btnActualizar.setBackgroundColor(Color.parseColor("#28297A"));
            btnActualizar.setTag(alumnos.get(p).id);
            btnActualizar.setTextColor(Color.WHITE);
            btnActualizar.setHeight(30);
            btnActualizar.setWidth(100);
            tr1.addView(btnActualizar);


            btnActualizar.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    int id = Integer.parseInt(view.getTag().toString());
                    Toast.makeText(getContext(), "Alumno seleccionado "+ id, Toast.LENGTH_LONG).show();
                    ActualizarCrearAlumno.accion = 1;
                    ActualizarCrearAlumno.id = id;
                    Intent intent = new Intent(getActivity() , ActualizarCrearAlumno.class);
                    startActivity(intent);

                }
            });


            btnEliminar = new Button(getActivity());
            btnEliminar.setText("Borrar");
            btnEliminar.setGravity(Gravity.CENTER);
            btnEliminar.setBackgroundColor(Color.parseColor("#FF383E"));
            btnEliminar.setTag(alumnos.get(p).id);
            btnEliminar.setTextColor(Color.WHITE);
            btnEliminar.setHeight(30);
            btnEliminar.setWidth(100);
            tr1.addView(btnEliminar);


            btnEliminar.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    int id = Integer.parseInt(view.getTag().toString());
                    Toast.makeText(getContext(), "Alumno seleccionado "+ id, Toast.LENGTH_LONG).show();
                    present.eliminarid(id);

                }
            });
            tl.addView(tr1);
        }
    }

    @Override
    public void dialogAlumno(Alumno alumno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alumno")
                .setMessage("Nombre: "+ alumno.nombre +
                        " \n Apellido: " + alumno.apellidos +
                        " \n Edad: "+ alumno.edad+
                        " \n Sexo: "+ alumno.sexo+
                        " \n carrera: "+ alumno.carrera)
        ;
        builder.create().show();
    }

    /*public void getAlumnos(){
        //int childCount = tl.getChildCount();
        // Remove all rows except the first one
        //if (childCount > 1) {
          //  tl.removeViews(1, childCount - 1);
        //}
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get(url + "alumno/alumno_lista/", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){

                    try {
                        alumnos = new ArrayList<Alumno>();
                        ArrayList <Alumno> filas = new ArrayList <Alumno> ();
                        JSONArray response =new JSONArray(new String(responseBody));
                        for (int i =0; i < response.length(); i++){
                            Alumno alumno = new Alumno();
                            alumno.nombre = response.getJSONObject(i).getString("nombre");
                            alumno.apellidos = response.getJSONObject(i).getString("apellidos");
                            alumno.edad = response.getJSONObject(i).getString("edad");
                            alumno.sexo = response.getJSONObject(i).getString("sexo");
                            alumno.carrera = response.getJSONObject(i).getString("carrera");
                            alumno.direccion = response.getJSONObject(i).getString("direccion");
                            alumno.id = response.getJSONObject(i).getString("id");

                            alumnos.add(alumno);

                        }
                        System.out.println(getActivity());
                        for(int p =0; p < alumnos.size(); p++){
                            TableRow tr1 = new TableRow(root.getContext());
                            System.out.println(alumnos.get(p).nombre);
                            tr1.setClickable(true);
                            TableRow.LayoutParams layoutId = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                            TableRow.LayoutParams layoutNombre = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                            tvNombres = new TextView(getActivity());
                            tvNombres.setText(alumnos.get(p).nombre);
                            tvNombres.setGravity(Gravity.CENTER);
                            tvNombres.setTextColor(Color.BLACK);
                            tvNombres.setPadding(100, 10, 100, 10);
                            tvNombres.setLayoutParams(layoutNombre);
                            tr1.addView(tvNombres);

                            tvApellidos = new TextView(getActivity());
                            tvApellidos.setText(alumnos.get(p).apellidos);
                            tvApellidos.setGravity(Gravity.CENTER);
                            tvApellidos.setTextColor(Color.BLACK);
                            tvApellidos.setPadding(100, 10, 100, 10);
                            tvApellidos.setLayoutParams(layoutNombre);
                            tr1.addView(tvApellidos);

                            tvEdad = new TextView(getActivity());
                            tvEdad.setText(alumnos.get(p).edad);
                            tvEdad.setGravity(Gravity.CENTER);
                            tvEdad.setTextColor(Color.BLACK);
                            tvEdad.setPadding(100, 10, 100, 10);
                            tvEdad.setLayoutParams(layoutNombre);
                            tr1.addView(tvEdad);

                            btnAlumno = new Button(getActivity());
                            btnAlumno.setText("Informacion");
                            btnAlumno.setGravity(Gravity.CENTER);
                            btnAlumno.setBackgroundColor(Color.GREEN);
                            btnAlumno.setTag(alumnos.get(p).id);
                            btnAlumno.setTextColor(Color.BLACK);
                            tr1.addView(btnAlumno);


                            btnAlumno.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int id = Integer.parseInt(view.getTag().toString());
                                    Toast.makeText(getContext(), "Alumno seleccionado "+ id, Toast.LENGTH_LONG).show();
                                    getAlumno(id);
                                }
                            });
                            tl.addView(tr1);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    Toast.makeText(getActivity(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getActivity(),rs, Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                } else if (statusCode == 403) {
                    Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });

    }*/

    /*public void getAlumno(int id){

        //final Alumno alumno;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get(getActivity(), url + "alumno/alumno_detalle/"+ id, new AsyncHttpResponseHandler() {
            AlertDialog.Builder builder;
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {

                        JSONObject response =new JSONObject(new String(responseBody));
                        final Alumno alumno = new Alumno();
                        alumno.nombre = response.getString("nombre");
                        alumno.apellidos = response.getString("apellidos");
                        alumno.edad = response.getString("edad");
                        alumno.sexo = response.getString("sexo");
                        alumno.carrera = response.getString("carrera");
                        alumno.direccion = response.getString("direccion");
                        alumno.id = response.getString("id");
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Alumno")
                                .setMessage("Nombre: "+ alumno.nombre +
                                        " \n Apellido: " + alumno.apellidos +
                                        " \n Edad: "+ alumno.edad+
                                        " \n Sexo: "+ alumno.sexo+
                                        " \n carrera: "+ alumno.carrera)
                        ;
                        builder.create().show();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    Toast.makeText(getActivity(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getActivity(),rs, Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                } else if (statusCode == 403) {
                    Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });

    }*/


}
