package com.example.pizzeria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button button;
    CheckBox Grube;
    RadioButton Mala;
    RadioButton Srednia;
    RadioButton Duza;
    EditText IloscPizz;

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

        Button myButton = findViewById(R.id.buttonNavigate);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean czyMala = Mala.isChecked();
                boolean czySrednia = Srednia.isChecked();
                boolean czyDuza = Duza.isChecked();
                boolean czyGrube = Grube.isChecked();
                String ilosc = IloscPizz.getText().toString();

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