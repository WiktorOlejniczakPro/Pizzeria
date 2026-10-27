package com.example.pizzeria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
    CheckBox Grube;
    RadioButton Mala;
    RadioButton Srednia;
    RadioButton Duza;
    EditText IloscPizz;
    Spinner Spinner;

    ArrayList<String> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Mala = findViewById(R.id.Mala);
        Srednia = findViewById(R.id.Srednia);
        Duza = findViewById(R.id.Duza);
        Grube = findViewById(R.id.Grube);
        IloscPizz = findViewById(R.id.IloscPizz);
        Spinner = findViewById(R.id.Spinner);
        Button myButton = findViewById(R.id.buttonNavigate);

        courses.add("Mała 25zł");
        courses.add("Średnia 35zł");
        courses.add("Duża 45zł");

        if(Spinner != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    courses
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner.setAdapter(adapter);
        };

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean czyMala = false;
                boolean czySrednia = false;
                boolean czyDuza = false;

                if (Mala != null && Srednia != null && Duza != null) {
                    czyMala = Mala.isChecked();
                    czySrednia = Srednia.isChecked();
                    czyDuza = Duza.isChecked();
                } else if (Spinner != null) {
                    int pozycja = Spinner.getSelectedItemPosition();
                    czyMala = (pozycja == 0);
                    czySrednia = (pozycja == 1);
                    czyDuza = (pozycja == 2);
                }

                boolean czyGrube = (Grube != null) && Grube.isChecked();
                String ilosc = (IloscPizz != null) ? IloscPizz.getText().toString() : "";

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("KLUCZ_MALA", czyMala);
                intent.putExtra("KLUCZ_SREDNIA", czySrednia);
                intent.putExtra("KLUCZ_DUZA", czyDuza);
                intent.putExtra("KLUCZ_GRUBE", czyGrube);
                intent.putExtra("KLUCZ_ILOSC", ilosc);
                startActivity(intent);
            }
        });
    }
}