package com.example.vig.sih_mota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class welcome_b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_b);

        final ImageView scheme = (ImageView)findViewById(R.id.imageView60);
        ImageView upload = (ImageView)findViewById(R.id.imageView47);
        ImageView quories = (ImageView)findViewById(R.id.imageView48);

        scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(),schemes.class);
            startActivity(intent);


            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),screen2.class);
                startActivity(intent);



            }
        });

        quories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);



            }
        });

    }


}
