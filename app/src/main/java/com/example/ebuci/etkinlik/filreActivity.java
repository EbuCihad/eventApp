package com.example.ebuci.etkinlik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class filreActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filre);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroupButton);
        button=(Button)findViewById(R.id.filtrele);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int secilenId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton) findViewById(secilenId);
                String seciliEtiket=radioButton.getText().toString();
                Intent ıntent=new Intent(getApplicationContext(),feedActivity.class);
                ıntent.putExtra("seciliEtiket",seciliEtiket);
                startActivity(ıntent);
            }
        });



    }
}
