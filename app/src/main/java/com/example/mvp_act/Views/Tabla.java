package com.example.mvp_act.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvp_act.Models.UserLogin;
import com.example.mvp_act.R;

public class Tabla extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);
        //tl = (TableLayout) findViewById(R.id.tbAlumno);
        /*Intent intent = getIntent();
        user = intent.getStringExtra(Login2.EXTRA_MESSAGE);
        token = intent.getStringExtra(Login2.EXTRA_MESSAGE2);*/

        Toast.makeText(getApplicationContext(), "Username: "+ UserLogin.username, Toast.LENGTH_LONG).show();
        if(UserLogin.username.equals("admin")){
            Tabla_Alumnos f_tabla = new Tabla_Alumnos();

            /*Bundle bundle = new Bundle();
            bundle.putString("token", UserLogin.token);
            f_tabla.setArguments(bundle);*/

            //getSupportFragmentManager().beginTransaction().add(R.id.fragment_tabla, f_tabla).commit();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_tabla, f_tabla);
            transaction.replace(R.id.fragment_tabla, f_tabla);
            transaction.commit();
        }else{
            Tabla_Materia f_tabla = new Tabla_Materia();
            //getSupportFragmentManager().beginTransaction().add(R.id.fragment_tabla, f_tabla).commit();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_tabla, f_tabla);
            transaction.commit();
        }
        Button btn_Crear = findViewById(R.id.btcrearAlumno);

        btn_Crear.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Toast.makeText(Tabla.this, "Creacion seleccionado ", Toast.LENGTH_LONG).show();
                ActualizarCrearAlumno.accion = 0;
                Intent intent = new Intent(Tabla.this , ActualizarCrearAlumno.class);
                startActivity(intent);

            }
        });

    }


}
