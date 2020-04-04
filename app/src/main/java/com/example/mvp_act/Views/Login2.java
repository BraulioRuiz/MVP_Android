package com.example.mvp_act.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.example.mvp_act.Models.UserLogin;
import com.example.mvp_act.Presents.LoginPresent;
import com.example.mvp_act.R;

public class Login2 extends AppCompatActivity implements LoginPresent.View {
    EditText username;
    EditText password;
    ProgressBar bar;


    private LoginPresent present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        present = new LoginPresent(this);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        bar = findViewById(R.id.progressBar);
        Button btn_aceptar = findViewById(R.id.aceptar);

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                present.login(username.getText().toString(),password.getText().toString());
                bar.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public void loginAlert(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        bar.setVisibility(View.GONE);
        bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void accesSuccest(String token, String Username){
        String token1 = token;
        String username1 = Username;
        UserLogin.token = token1;
        UserLogin.username = username1;
        bar.setVisibility(View.GONE);
        bar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(Login2.this , Tabla.class);
        /*intent.putExtra(EXTRA_MESSAGE, username1);
        intent.putExtra(EXTRA_MESSAGE2, token1);*/
        startActivity(intent);
    }


    /*public void login(){
        String usuario = username.getText().toString();
        String contraseña = password.getText().toString();
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
                        Toast.makeText(getApplicationContext(), "Usuario NO Encontrado", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario Correcto", Toast.LENGTH_LONG).show();
                        try {
                            JSONObject response =new JSONObject(new String(responseBody));
                            String token = response.getString("token");
                            String username = response.getString("username");

                            Intent intent = new Intent(Login2.this , Tabla.class);
                            intent.putExtra(EXTRA_MESSAGE, username);
                            intent.putExtra(EXTRA_MESSAGE2, token);
                            startActivity(intent);



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
                    Toast.makeText(getApplicationContext(), "404 !", Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    Toast.makeText(getApplicationContext(),rs, Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "500 !", Toast.LENGTH_LONG).show();
                } else if (statusCode == 403) {
                    Toast.makeText(getApplicationContext(), "403 !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
    }*/

}
