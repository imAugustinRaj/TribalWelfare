package com.example.vig.sih_mota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
    }

    public void schemes(View view) {
        Intent intent = new Intent(getApplicationContext(),schemes.class);
        startActivity(intent);
    }

    public void buy(View view) {
        Intent intent = new Intent(getApplicationContext(),ImagesActivity2.class);
        startActivity(intent);
    }
}
