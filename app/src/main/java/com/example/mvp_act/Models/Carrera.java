package com.example.mvp_act.Models;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_act.Presents.Tabla_MateriaPresent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Carrera {
    public String id;
    public String nombre;

    private static ArrayList <Carrera> carreras;

    @Override
    public String toString(){
        return "nombre: "+ nombre + " ID: " + id;
    }

    private static String url = "http://192.168.1.74:8000/api/v1/";
    public static void getCarreras(final Tabla_MateriaPresent view){

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ UserLogin.token);
        client.get(url + "alumno/asignaturas/", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){

                    try {
                        carreras = new ArrayList<Carrera>();
                        JSONArray response =new JSONArray(new String(responseBody));
                        for (int i =0; i < response.length(); i++){
                            Carrera carrera = new Carrera();
                            carrera.nombre = response.getJSONObject(i).getString("nombre");
                            carrera.id = response.getJSONObject(i).getString("id");

                            carreras.add(carrera);

                        }
                        view.view.asignarTabla(carreras);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    /*Toast.makeText(getActivity(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getActivity(),rs, Toast.LENGTH_LONG).show();*/
                    view.view.tablaAlert("404 !");
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("500 !");
                } else if (statusCode == 403) {
                   // Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("403 !");
                } else {
                   // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    view.view.tablaAlert(error.toString());
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });

    }

    public static void getCarrera(int id, final Tabla_MateriaPresent view){

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ UserLogin.token);
        client.get(url + "alumno/asignaturas_detalle/"+ id, new AsyncHttpResponseHandler() {
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
                        Carrera carrera = new Carrera();
                        carrera.nombre = response.getString("nombre");
                        carrera.id = response.getString("id");

                        view.view.dialogCarrera(carrera);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    /*Toast.makeText(getActivity(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getActivity(),rs, Toast.LENGTH_LONG).show();*/
                    view.view.tablaAlert("404 !");
                } else if (statusCode == 500) {
                    //Toast.makeText(getActivity(), "500 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("500 !");
                } else if (statusCode == 403) {
                    // Toast.makeText(getActivity(), "403 !", Toast.LENGTH_LONG).show();
                    view.view.tablaAlert("403 !");
                } else {
                    // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    view.view.tablaAlert(error.toString());
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });

    }
}
