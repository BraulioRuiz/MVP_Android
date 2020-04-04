package com.example.mvp_act.Models;


import com.example.mvp_act.Presents.AlumnoPresent;
import com.example.mvp_act.Presents.Tabla_AlumnosPresent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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

    //private static String url = "http://192.168.1.74:8000/api/v1/";
    private static String url = "http://26dbcd4b.ngrok.io/api/v1/";

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

    public static void getAlumno2(int id, final AlumnoPresent view){

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
                        view.view.cargarValores(alumno);

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
                    //view.view.tablaAlert("404 ! "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    //view.view.tablaAlert("500 ! "+ rs);
                } else if (statusCode == 403) {
                    //Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    //view.view.tablaAlert("403 ! "+ rs);
                } else {
                    //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    //view.view.tablaAlert(rs);
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
    }

    public static void eliminacion(int id, final Tabla_AlumnosPresent view){
        AsyncHttpClient client = new AsyncHttpClient();
        String token = UserLogin.token;
        client.addHeader("Authorization", "Token "+ token);
        client.delete(url + "alumno/alumno_detalle/"+id , new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        view.view.tablaAlert("Eliminacion exitosa");
                        //Toast.makeText(principal, "Eliminacion exitosa", Toast.LENGTH_LONG).show();
                        cargarTabla(view);
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

    public static void crear(final AlumnoPresent view, Alumno alumno){
        RequestParams params = new RequestParams();

        String nombre = alumno.nombre;
        String apellidos = alumno.apellidos;
        String edad = alumno.edad;
        String sexo = alumno.sexo;
        String direccion = alumno.direccion;
        String carrera = alumno.carrera;

        params.put("nombre", nombre);
        params.put("apellidos", apellidos);
        params.put("edad", edad);
        params.put("sexo", sexo);
        params.put("direccion", direccion);
        params.put("carrera", carrera);

        String token = UserLogin.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.post(url + "alumno/alumno_lista/",params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        view.view.tablaAlert("Creacion exitosa");
                        //Toast.makeText(principal, "Creacion exitosa", Toast.LENGTH_LONG).show();
                        view.view.regresar();
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

    public static void actualizacion(final AlumnoPresent view, Alumno alumno){
        RequestParams params = new RequestParams();
        String id = alumno.id;
        String nombre = alumno.nombre;
        String apellidos = alumno.apellidos;
        String edad = alumno.edad;
        String sexo = alumno.sexo;
        String direccion = alumno.direccion;
        String carrera = alumno.carrera;

        params.put("nombre", nombre);
        params.put("apellidos", apellidos);
        params.put("edad", edad);
        params.put("sexo", sexo);
        params.put("direccion", direccion);
        params.put("carrera", carrera);

        String token = UserLogin.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.put(url + "alumno/alumno_detalle/"+id ,params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        view.view.tablaAlert("Actualizacion exitosa");
                        //Toast.makeText(principal, "Actualizacion exitosa", Toast.LENGTH_LONG).show();
                        view.view.regresar();
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
