package com.example.ebuci.etkinlik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class satin_al extends AppCompatActivity {

    EditText kartNo;
    EditText adSoyad;
    EditText ay;
    EditText yil;
    EditText gKod;
    TextView toplamFiyat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_satin_al);
        kartNo=(EditText)findViewById(R.id.kartNo);
        adSoyad=(EditText)findViewById(R.id.adSoyad);
        ay=(EditText)findViewById(R.id.ay);
        yil=(EditText)findViewById(R.id.yil);
        gKod=(EditText)findViewById(R.id.gKod);
        toplamFiyat=(TextView)findViewById(R.id.toplamFiyat);

        Intent intent=getIntent();
        String fiyat=intent.getStringExtra("fiyat");
        toplamFiyat.setText("Toplam Fiyat "+fiyat+"TL");
    }
}
