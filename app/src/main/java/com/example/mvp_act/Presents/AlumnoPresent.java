package com.example.mvp_act.Presents;

import com.example.mvp_act.Models.Alumno;

import java.util.ArrayList;

public class AlumnoPresent {
    public View view;

    public AlumnoPresent(View view){
        this.view = view;

    }

    public interface View{
        void regresar();
        void tablaAlert(String text);
        void cargarValores(Alumno alumno);
    }

    public void crear(Alumno alumno){ Alumno.crear(this, alumno);}

    public void editar(Alumno alumno){ Alumno.actualizacion(this, alumno);}

    public void getAlumno(int id){ Alumno.getAlumno2(id, this);}

}
