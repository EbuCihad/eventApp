package com.example.ebuci.etkinlik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class feedActivity extends AppCompatActivity {
    ArrayList<String> userEmailsFromFB;
    ArrayList<String> etkinlikImageFromFB;
    ArrayList<String> etkinlikAciklamaFromFB;
    ArrayList<String> etkinlikBaslikFromFB;
    ArrayList<String> etkinlikTarihFromFB;
    ArrayList<String> etkinlikSaatFromFB;
    ArrayList<String> etkinlikkategoriFromFB;
    ArrayList<String> etkinlikFiyatFromFB;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ListView listView;
    postClass adapter;
    private String seciliEtiket;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);
        menuInflater.inflate(R.menu.add_filtre,menu);
        menuInflater.inflate(R.menu.exit,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_post)
        {
            Intent ıntent=new Intent(getApplicationContext(),uploadActivity.class);
            startActivity(ıntent);

        }
        if(item.getItemId()==R.id.add_filtre)
        {
            Intent ıntent=new Intent(getApplicationContext(),filreActivity.class);
            startActivity(ıntent);
        }
        if(item.getItemId()==R.id.exit)
        {
            Intent ıntent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(ıntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Intent ıntent=getIntent();
        final String seciliEtiket=ıntent.getStringExtra("seciliEtiket");
        setdata(seciliEtiket);
        userEmailsFromFB=new ArrayList<String>();
        etkinlikAciklamaFromFB=new ArrayList<String>();
        etkinlikBaslikFromFB=new ArrayList<String>();
        etkinlikImageFromFB=new ArrayList<String>();
        etkinlikTarihFromFB=new ArrayList<String>();
        etkinlikSaatFromFB=new ArrayList<String>();
        etkinlikkategoriFromFB=new ArrayList<String>();
        etkinlikFiyatFromFB=new ArrayList<String>();





        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();

        listView=findViewById(R.id.listView);

        adapter=new postClass(etkinlikImageFromFB,etkinlikAciklamaFromFB,etkinlikBaslikFromFB,userEmailsFromFB,etkinlikTarihFromFB,etkinlikSaatFromFB,etkinlikkategoriFromFB,etkinlikFiyatFromFB,this);
        listView.setAdapter(adapter);
        getDataFromFirebase();
    }
    public void  setdata(String seciliEtiket)
    {
        this.seciliEtiket=seciliEtiket;
    }
    public String getdata()
    {
        return seciliEtiket;
    }
    protected  void getDataFromFirebase()
    {
        DatabaseReference newReference=firebaseDatabase.getReference("post");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // System.out.println("children"+dataSnapshot.getChildren());
               // System.out.println("key"+dataSnapshot.getKey());
               // System.out.println("value"+dataSnapshot.getValue());

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    HashMap<String,String> hashMap=(HashMap<String, String>)ds.getValue();

                    if (seciliEtiket==null)
                    {
                        System.out.println("DİKKAT"+seciliEtiket);
                        userEmailsFromFB.add(hashMap.get("useremail"));
                        etkinlikImageFromFB.add(hashMap.get("downloadurl"));
                        etkinlikAciklamaFromFB.add(hashMap.get("aciklama"));
                        etkinlikBaslikFromFB.add(hashMap.get("baslik"));
                        etkinlikTarihFromFB.add(hashMap.get("tarih"));
                        etkinlikSaatFromFB.add(hashMap.get("saat"));
                        etkinlikkategoriFromFB.add(hashMap.get("etiket"));
                        etkinlikFiyatFromFB.add(hashMap.get("fiyat"));

                        adapter.notifyDataSetChanged();

                    }
                    else if(seciliEtiket!=null && seciliEtiket.equals(hashMap.get("etiket"))) {

                            System.out.println("DİKKAT"+seciliEtiket);
                            userEmailsFromFB.add(hashMap.get("useremail"));
                            etkinlikImageFromFB.add(hashMap.get("downloadurl"));
                            etkinlikAciklamaFromFB.add(hashMap.get("aciklama"));
                            etkinlikBaslikFromFB.add(hashMap.get("baslik"));
                            etkinlikTarihFromFB.add(hashMap.get("tarih"));
                            etkinlikSaatFromFB.add(hashMap.get("saat"));
                            etkinlikkategoriFromFB.add(hashMap.get("etiket"));
                            etkinlikFiyatFromFB.add(hashMap.get("fiyat"));

                            adapter.notifyDataSetChanged();
                     }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
