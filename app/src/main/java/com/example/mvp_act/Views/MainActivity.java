package com.example.mvp_act.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_act.Presents.MainActivityPresent;
import com.example.mvp_act.R;

public class MainActivity extends AppCompatActivity implements MainActivityPresent.View {

    private  MainActivityPresent present;
    private TextView myTextView;
    private TextView txtTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.name);
        txtTexto = findViewById(R.id.dato);

        present = new MainActivityPresent(this);
        //present.update("Hola mundo");
        myTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                present.update(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void updateUserTextView(String name){
       // Toast.makeText(this, "nombre: " + name, Toast.LENGTH_LONG).show();
       // myTextView.setText(name);
        txtTexto.setText(name);

    }
}
