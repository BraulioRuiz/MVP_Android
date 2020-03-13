package com.example.mvp_act.Views;




import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_act.Models.Carrera;
import com.example.mvp_act.Presents.Tabla_MateriaPresent;
import com.example.mvp_act.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tabla_Materia extends Fragment implements Tabla_MateriaPresent.View {

    private TableLayout tl;

    private TextView tvNombres;
    private Button btnCarrera;
    private TextView tvId;
    private String token;

    private ViewGroup root;

    private Tabla_MateriaPresent present;

    public Tabla_Materia() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_tabla__materia, null);
        tl = (TableLayout) root.findViewById(R.id.tbCarrera);
        present = new Tabla_MateriaPresent(this);
        //token = getArguments().getString("token");
        //System.out.println(token);
        present.getCarreras();
        return root;
    }

    @Override
    public void tablaAlert(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void asignarTabla(ArrayList<Carrera> carreras) {
        for(int p =0; p < carreras.size(); p++) {
            TableRow tr1 = new TableRow(getActivity());
            System.out.println(carreras.get(p).nombre);
            tr1.setClickable(true);
            TableRow.LayoutParams layoutId = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams layoutNombre = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

            tvNombres = new TextView(getActivity());
            tvNombres.setText(carreras.get(p).nombre);
            tvNombres.setGravity(Gravity.CENTER);
            tvNombres.setTextColor(Color.BLACK);
            tvNombres.setPadding(100, 10, 100, 10);
            tvNombres.setLayoutParams(layoutNombre);
            tr1.addView(tvNombres);

            tvId = new TextView(getActivity());
            tvId.setText(carreras.get(p).id);
            tvId.setGravity(Gravity.CENTER);
            tvId.setTextColor(Color.BLACK);
            tvId.setPadding(100, 10, 100, 10);
            tvId.setLayoutParams(layoutNombre);
            tr1.addView(tvId);

            btnCarrera = new Button(getActivity());
            btnCarrera.setText("Informacion");
            btnCarrera.setGravity(Gravity.CENTER);
            btnCarrera.setBackgroundColor(Color.GREEN);
            btnCarrera.setTag(carreras.get(p).id);
            btnCarrera.setTextColor(Color.BLACK);
            tr1.addView(btnCarrera);

            btnCarrera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Integer.parseInt(view.getTag().toString());
                    Toast.makeText(getContext(), "Carrera seleccionada " + id, Toast.LENGTH_LONG).show();
                    present.getCarrera(id);
                }
            });
            tl.addView(tr1);
        }
    }

    @Override
    public void dialogCarrera(Carrera carrera) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Carrera")
                .setMessage("Nombre: "+ carrera.nombre +
                        " \n ID: " + carrera.id)
        ;
        builder.create().show();
    }
}
