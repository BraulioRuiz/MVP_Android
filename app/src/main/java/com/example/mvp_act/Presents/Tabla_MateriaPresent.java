package com.example.mvp_act.Presents;

import com.example.mvp_act.Models.Carrera;

import java.util.ArrayList;

public class Tabla_MateriaPresent {

    public View view;

    public Tabla_MateriaPresent(View view){
        this.view = view;
    }

    public interface View{
        void tablaAlert(String text);
        void asignarTabla(ArrayList<Carrera> carreras);
        void dialogCarrera(Carrera carrera);
    }

    public void getCarreras(){
        Carrera.getCarreras(this);
    }

    public void getCarrera(int id){
        Carrera.getCarrera(id, this);
    }

}
