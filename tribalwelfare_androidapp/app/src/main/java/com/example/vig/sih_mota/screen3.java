package com.example.vig.sih_mota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        final ImageView scheme = (ImageView)findViewById(R.id.imageView13);
        ImageView view_sl = (ImageView)findViewById(R.id.imageView15);
        ImageView quories = (ImageView)findViewById(R.id.imageView14);

        scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),screen2.class);
                startActivity(intent);


            }
        });

        view_sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImagesActivity.class);
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
