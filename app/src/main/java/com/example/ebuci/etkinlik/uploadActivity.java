package com.example.ebuci.etkinlik;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class uploadActivity extends AppCompatActivity {

    EditText aciklamaText;
    EditText baslikText;
    ImageView fotoSecilen;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText tarihText;
    EditText saatText;
    EditText kategoriText;
    EditText biletFiyatText;


    private StorageReference mStorageRef;
    Uri selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        aciklamaText=(EditText)findViewById(R.id.aciklama);
        baslikText=(EditText)findViewById(R.id.baslik);
        fotoSecilen=(ImageView)findViewById(R.id.seciliFoto);
        tarihText=(EditText)findViewById(R.id.tarih);
        saatText=(EditText) findViewById(R.id.saat);
        kategoriText=(EditText)findViewById(R.id.kategori);
        biletFiyatText=(EditText)findViewById(R.id.biletFiyat);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();


    }

    public  void  fotoSec(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
            else
            {
                Intent ıntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(ıntent,2);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1)
        {
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Intent ıntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(ıntent,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==2 && resultCode==RESULT_OK && data!=null)
        {
            selected=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selected);
                fotoSecilen.setImageBitmap(bitmap);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void postOlustur(View view)
    {
        final UUID uuıd=UUID.randomUUID();
        String imageName="images/"+uuıd+".jpg";

        StorageReference storageReference=mStorageRef.child(imageName);
        storageReference.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL=taskSnapshot.getDownloadUrl().toString();
                FirebaseUser user=mAuth.getCurrentUser();
                String email=user.getEmail().toString();
                String etkinlikAciklama=aciklamaText.getText().toString();
                String etkinlikBaslik=baslikText.getText().toString();
                String etkinlikTarih=tarihText.getText().toString();
                String etkinlikSaat=saatText.getText().toString();
                String etkinlikKategori=kategoriText.getText().toString();
                String etkinlikFiyat=biletFiyatText.getText().toString();


                UUID uuıd1=UUID.randomUUID();
                String uuidString=uuıd1.toString();
                myRef.child("post").child(uuidString).child("useremail").setValue(email);
                myRef.child("post").child(uuidString).child("aciklama").setValue(etkinlikAciklama);
                myRef.child("post").child(uuidString).child("baslik").setValue(etkinlikBaslik);
                myRef.child("post").child(uuidString).child("downloadurl").setValue(downloadURL);
                myRef.child("post").child(uuidString).child("tarih").setValue(etkinlikTarih);
                myRef.child("post").child(uuidString).child("saat").setValue(etkinlikSaat);
                myRef.child("post").child(uuidString).child("etiket").setValue(etkinlikKategori);
                myRef.child("post").child(uuidString).child("fiyat").setValue(etkinlikFiyat);


                Toast.makeText(getApplicationContext(),"etkinlik paylaşıldı",Toast.LENGTH_LONG).show();

                Intent ıntent=new Intent(getApplicationContext(),feedActivity.class);
                startActivity(ıntent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
