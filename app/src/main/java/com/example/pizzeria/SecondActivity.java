package com.example.pizzeria;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {

    TextView Rozmiar;
    TextView Ciasto;
    TextView Ilosc;
    TextView Cena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Rozmiar = findViewById(R.id.Rozmiar);
        Ciasto = findViewById(R.id.Ciasto);
        Ilosc = findViewById(R.id.Ilosc);
        Cena = findViewById(R.id.Cena);

        Intent intent = getIntent();
        if (intent != null) {

            boolean pizzaMala = intent.getBooleanExtra("KLUCZ_MALA", false);
            boolean pizzaSrednia = intent.getBooleanExtra("KLUCZ_SREDNIA", false);
            boolean pizzaDuza = intent.getBooleanExtra("KLUCZ_DUZA", false);
            boolean grubeCiasto = intent.getBooleanExtra("KLUCZ_GRUBE", false);
            String iloscSztukTekst = intent.getStringExtra("KLUCZ_ILOSC");

            String wybranyRozmiar = "Nie wybrano";
            double cenaZaSztuke = 0.0;
            int iloscPizz = 0;

            if (pizzaMala) {
                wybranyRozmiar = "Mała";
                cenaZaSztuke = 25.00;
            } else if (pizzaSrednia) {
                wybranyRozmiar = "Średnia";
                cenaZaSztuke = 35.00;
            } else if (pizzaDuza) {
                wybranyRozmiar = "Duża";
                cenaZaSztuke = 45.00;
            }

            String wybraneCiasto = "Cienkie";
            if (grubeCiasto) {
                wybraneCiasto = "Grube";
                cenaZaSztuke += 5.00;
            }

            if (iloscSztukTekst != null && !iloscSztukTekst.trim().isEmpty()) {
                try {
                    iloscPizz = Integer.parseInt(iloscSztukTekst);
                } catch (NumberFormatException e) {
                    iloscPizz = 0;
                }
            }
            double cenaCalkowita = cenaZaSztuke * iloscPizz;

            Rozmiar.setText(wybranyRozmiar);
            Ciasto.setText(wybraneCiasto);
            Ilosc.setText(""+iloscPizz);
            Cena.setText(String.format("%.2f zł", cenaCalkowita));

        }
    }
}