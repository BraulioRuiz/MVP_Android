package com.example.mvp_act.Presents;

import com.example.mvp_act.Models.Alumno;

import java.util.ArrayList;

public class Tabla_AlumnosPresent {
    public View view;

    public Tabla_AlumnosPresent(View view){
        this.view = view;

    }

    public interface View{
        void tablaAlert(String text);
        void asignarTabla(ArrayList<Alumno> alumnos);
        void dialogAlumno(Alumno alumno);
    }

    public void cargarTabla(){
        Alumno.cargarTabla(this);
    }

    public void getAlumno(int id){
        Alumno.getAlumno(id, this);
    }


}
