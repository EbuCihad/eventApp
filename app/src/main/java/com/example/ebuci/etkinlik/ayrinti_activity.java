package com.example.ebuci.etkinlik;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.zip.Inflater;

public class ayrinti_activity extends AppCompatActivity  {


    TextView baslikAyrinti;
    ImageView fotoAyrinti;
    TextView aciklamaAyrinti;
    TextView tarihAyrinti;
    TextView saatAyrinti;
    TextView kategoriAyrinti;
    TextView emailAyrinti;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ayrinti_activity);

        baslikAyrinti=(TextView) findViewById(R.id.textView6);
        fotoAyrinti=(ImageView) findViewById(R.id.imageView2);
        aciklamaAyrinti=(TextView) findViewById(R.id.textView8);
        tarihAyrinti=(TextView) findViewById(R.id.textView9);
        saatAyrinti=(TextView) findViewById(R.id.textView10);
        kategoriAyrinti=(TextView) findViewById(R.id.textView11);
        emailAyrinti=(TextView) findViewById(R.id.textView12);



        Intent intent=getIntent();

        String baslik=intent.getStringExtra("baslik");
        String aciklama=intent.getStringExtra("aciklama");
        String tarih=intent.getStringExtra("tarih");
        String saat=intent.getStringExtra("saat");
        String kategori=intent.getStringExtra("kategori");
        String email=intent.getStringExtra("email");
        String urlimage=intent.getStringExtra("urlimage");
       /*
        System.out.println(baslik);
        System.out.println(aciklama);
        System.out.println(tarih);
        System.out.println(saat);
        System.out.println(kategori);
        System.out.println(email);
*/
       baslikAyrinti.setText(baslik);
       aciklamaAyrinti.setText(aciklama);
       tarihAyrinti.setText("Tarih "+tarih);
       saatAyrinti.setText("Saat "+saat);
       kategoriAyrinti.setText("Kategori "+kategori);
       emailAyrinti.setText("Oluşturan "+email);
       Picasso.get().load(urlimage).into(fotoAyrinti);

        //globals globalsClass=globals.getInstance();
       //Bitmap bitmap=globalsClass.getData();
       //fotoAyrinti.setImageBitmap(bitmap);






    }


}
