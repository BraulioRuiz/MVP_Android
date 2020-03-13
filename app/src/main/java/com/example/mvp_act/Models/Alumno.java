package com.example.mvp_act.Models;


import com.example.mvp_act.Presents.Tabla_AlumnosPresent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Alumno {
    public String id;
    public String nombre;
    public String apellidos;
    public String edad;
    public String sexo;
    public String direccion;
    public String carrera;
    private static ArrayList <Alumno> alumnos;
    private static Alumno alumno;

    private static String url = "http://192.168.1.72:8000/api/v1/";
    @Override
    public String toString(){
        return "nombre: "+ nombre + " ID: " + id;
    }

    public static void cargarTabla(final Tabla_AlumnosPresent view){
        String token = UserLogin.token;
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
                        view.view.asignarTabla(alumnos);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    String rs = new String(responseBody);
                    //Toast.makeText(getActivity(),"404 ! "+ rs, Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("404 ! "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("500 ! "+ rs);
                } else if (statusCode == 403) {
                    //Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("403 ! "+ rs);
                } else {
                    //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert(rs);
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });


    }

    public static void getAlumno(int id, final Tabla_AlumnosPresent view){
        String token = UserLogin.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get( url + "alumno/alumno_detalle/"+ id, new AsyncHttpResponseHandler() {
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
                        view.view.dialogAlumno(alumno);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    String rs = new String(responseBody);
                    //Toast.makeText(getActivity(),"404 ! "+ rs, Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("404 ! "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("500 ! "+ rs);
                } else if (statusCode == 403) {
                    //Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert("403 ! "+ rs);
                } else {
                    //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    view.view.tablaAlert(rs);
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
    }

}
