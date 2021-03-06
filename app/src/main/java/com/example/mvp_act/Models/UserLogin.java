package com.example.mvp_act.Models;

import com.example.mvp_act.Presents.LoginPresent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserLogin {

        public static String username;
        public static String token;
        public static String id;

    //private static String url = "http://192.168.1.74:8000/api/v1/login";
    private static String url = "http://26dbcd4b.ngrok.io/api/v1/login";

    public void login(String username, String password , final LoginPresent view){
        String usuario = username;
        String contraseña = password;
        RequestParams params = new RequestParams();
        params.put("username", usuario);
        params.put("password", contraseña);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post( url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    String respuesta = new String(responseBody);
                    if(respuesta.equals("404")){
                        view.view.loginAlert("Usuario NO Encontrado");
                        //Toast.makeText(v, "Usuario NO Encontrado", Toast.LENGTH_LONG).show();
                    }else{
                        view.view.loginAlert("Usuario Correcto");
                        //Toast.makeText(getApplicationContext(), "Usuario Correcto", Toast.LENGTH_LONG).show();
                        try {
                            JSONObject response =new JSONObject(new String(responseBody));
                            String token = response.getString("token");
                            String username = response.getString("username");
                            view.view.accesSuccest(token,username);
                            /*Intent intent = new Intent(Login2.this , Tabla.class);
                            intent.putExtra(EXTRA_MESSAGE, username);
                            intent.putExtra(EXTRA_MESSAGE2, token);
                            startActivity(intent);*/



                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                JSONArray response = null;
                try {
                    response = new JSONArray(new String(responseBody));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (statusCode == 404) {

                    //Toast.makeText(getApplicationContext(), "404 ! respuesta: "+ rs, Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    //Toast.makeText(getApplicationContext(),rs, Toast.LENGTH_LONG).show();
                    view.view.loginAlert("404 ! respuesta: "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getApplicationContext(), "500 !", Toast.LENGTH_LONG).show();
                    view.view.loginAlert("500 !");
                } else if (statusCode == 403) {
                    //Toast.makeText(getApplicationContext(), "403 !", Toast.LENGTH_LONG).show();
                    view.view.loginAlert("403 !");
                } else {
                    //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    view.view.loginAlert(error.toString());
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
    }
}
