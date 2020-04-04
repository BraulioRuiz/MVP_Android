package com.example.mvp_act.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp_act.Models.Alumno;
import com.example.mvp_act.Presents.AlumnoPresent;
import com.example.mvp_act.R;

public class ActualizarCrearAlumno extends AppCompatActivity implements AlumnoPresent.View {

    private EditText textoId;
    private EditText textoNombre;
    private EditText textoApellidos;
    private EditText textoDireccion;
    private EditText textoSexo;
    private EditText textoEdad;
    private EditText textoCarrera;
    public static int accion=0;
    public static int id=0;
    private AlumnoPresent present;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_crear_alumno);
        textoId = findViewById(R.id.txtID);
        textoNombre = findViewById(R.id.txtNombre);
        textoApellidos = findViewById(R.id.txtApellidos);
        textoDireccion = findViewById(R.id.txtDireccion);
        textoSexo = findViewById(R.id.txtSexo);
        textoEdad = findViewById(R.id.txtEdad);
        present = new AlumnoPresent(this);
        bar = findViewById(R.id.progressBar);

        textoCarrera = findViewById(R.id.txtCarrera);
        Button btn_Accion = findViewById(R.id.btcaccion);



        if(accion==1){
            present.getAlumno(id);
        }


        btn_Accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accion == 0){
                    if(!textoNombre.getText().toString().equals("") || !textoApellidos.getText().toString().equals("")
                            || !textoDireccion.getText().toString().equals("") || !textoSexo.getText().toString().equals("")
                            || !textoEdad.getText().toString().equals("") || !textoCarrera.getText().toString().equals("")){
                        Alumno alumno = new Alumno();
                        alumno.nombre = textoNombre.getText().toString();
                        alumno.apellidos = textoApellidos.getText().toString();
                        alumno.direccion = textoDireccion.getText().toString();
                        alumno.carrera = textoCarrera.getText().toString();
                        alumno.edad = textoEdad.getText().toString();
                        alumno.sexo = textoSexo.getText().toString();
                        present.crear(alumno);
                        bar.setVisibility(View.VISIBLE);
                    }else{
                        tablaAlert("Espacios Vacios");
                    }

                }else{
                    if(!textoNombre.getText().toString().equals("") || !textoApellidos.getText().toString().equals("")
                            || !textoDireccion.getText().toString().equals("") || !textoSexo.getText().toString().equals("")
                            || !textoEdad.getText().toString().equals("") || !textoCarrera.getText().toString().equals("")){
                        Alumno alumno = new Alumno();
                        alumno.id = textoId.getText().toString();
                        alumno.nombre = textoNombre.getText().toString();
                        alumno.apellidos = textoApellidos.getText().toString();
                        alumno.direccion = textoDireccion.getText().toString();
                        alumno.carrera = textoCarrera.getText().toString();
                        alumno.edad = textoEdad.getText().toString();
                        alumno.sexo = textoSexo.getText().toString();
                        present.editar(alumno);
                        bar.setVisibility(View.VISIBLE);
                    }else{
                        tablaAlert("Espacios Vacios");
                    }

                }



            }
        });


    }

    @Override
    public void regresar() {
        bar.setVisibility(View.GONE);
        bar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(ActualizarCrearAlumno.this , Login2.class);
        startActivity(intent);
    }

    @Override
    public void tablaAlert(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void cargarValores(Alumno alumno) {
        textoId.setText(alumno.id);
        textoNombre.setText(alumno.nombre);
        textoApellidos.setText(alumno.apellidos);
        textoDireccion.setText(alumno.direccion);
        textoCarrera.setText(alumno.carrera);
        textoEdad.setText(alumno.edad);
        textoSexo.setText(alumno.sexo);
    }
}
