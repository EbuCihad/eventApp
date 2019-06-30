package com.example.ebuci.etkinlik;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class postClass extends ArrayAdapter<String>{

    private  final ArrayList<String> etkinlikimage;
    private final ArrayList<String> etkinlikAciklama;
    private final ArrayList<String> etkinlikBaslik;
    private final ArrayList<String> usermail;
    private final ArrayList<String> etkinlikTarih;
    private final ArrayList<String> etkinlikSaat;
    private final ArrayList<String> etkinlikKategori;
    private final ArrayList<String> etkinlikFiyat;
    private final Activity context;

    public postClass(ArrayList<String> etkinlikimage, ArrayList<String> etkinlikAciklama, ArrayList<String> etkinlikBaslik, ArrayList<String> usermail,ArrayList<String> etkinlikTarih,ArrayList<String> etkinlikSaat,ArrayList<String> etkinlikKategori,ArrayList<String> etkinlikFiyat, Activity context) {
        super(context,R.layout.custom_view,usermail);
        this.etkinlikimage = etkinlikimage;
        this.etkinlikAciklama = etkinlikAciklama;
        this.etkinlikBaslik = etkinlikBaslik;
        this.usermail = usermail;
        this.etkinlikTarih=etkinlikTarih;
        this.etkinlikSaat=etkinlikSaat;
        this.etkinlikKategori=etkinlikKategori;
        this.etkinlikFiyat=etkinlikFiyat;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);
        final int hiza=position;

        TextView baslikText=customView.findViewById(R.id.baslik);
        TextView aciklamaText=customView.findViewById(R.id.aciklama);
        ImageView imageView=customView.findViewById(R.id.detailBaslik);
        Button biletAl=(Button) customView.findViewById(R.id.biletAlButon);
        Button ayrinti=(Button) customView.findViewById(R.id.ayrintiButon);
        biletAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),satin_al.class);
                intent.putExtra("fiyat",etkinlikFiyat.get(hiza));
                context.startActivity(intent);
            }
        });
        ayrinti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ayrinti_activity.class);
                intent.putExtra("baslik",etkinlikBaslik.get(hiza));
                intent.putExtra("aciklama",etkinlikAciklama.get(hiza));
                intent.putExtra("tarih",etkinlikTarih.get(hiza));
                intent.putExtra("saat",etkinlikSaat.get(hiza));
                intent.putExtra("kategori",etkinlikKategori.get(hiza));
                intent.putExtra("email",usermail.get(hiza));
                intent.putExtra("urlimage",etkinlikimage.get(hiza));
                /*
                try{
                    byte [] encodeByte=Base64.decode(etkinlikimage.get(hiza), Base64.DEFAULT);
                    Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                    globals globals= com.example.ebuci.etkinlik.globals.getInstance();
                    globals.setData(bitmap);
                }catch(Exception e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
                */


                context.startActivity(intent);

            }
        });

        aciklamaText.setText(etkinlikAciklama.get(position));
        baslikText.setText(etkinlikBaslik.get(position));
        Picasso.get().load(etkinlikimage.get(position)).into(imageView);


        return customView;
    }
}
