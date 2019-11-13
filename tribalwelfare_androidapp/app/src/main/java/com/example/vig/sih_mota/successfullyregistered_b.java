package com.example.vig.sih_mota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class successfullyregistered_b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfullyregistered_b);
    }

    public void heeeeeey(View view) {
        Intent intent = new Intent(getApplicationContext(),artisan_lgin.class);
        startActivity(intent);
    }
}
