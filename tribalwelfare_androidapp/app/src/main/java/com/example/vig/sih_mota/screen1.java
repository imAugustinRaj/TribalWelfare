package com.example.vig.sih_mota;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.welcome);
        mPlayer.start();

        ImageView imageView = (ImageView)findViewById(R.id.imageView7);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(),commoner_login.class);
             startActivity(intent);
            }
        });
    }

    public void artisan(View view) {
        Intent intent = new Intent(getApplicationContext(),newuser_olduser_b.class);
        startActivity(intent);
    }

    public void comm(View view) {
        Intent intent = new Intent(getApplicationContext(),commoner_login.class);
        startActivity(intent);
    }

    public void mota(View view) {
        Intent intent = new Intent(getApplicationContext(),mota_login.class);
        startActivity(intent);
    }
}
