package com.example.vig.sih_mota;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class artisan_lgin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artisan_lgin);
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.artisan_login);
        mPlayer.start();

        ImageView img = (ImageView)findViewById(R.id.imageView22);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),welcome_b.class);
                startActivity(intent);
            }
        });
    }




    public void next(View view) {
        Intent intent = new Intent(getApplicationContext(),welcome_b.class);
        startActivity(intent);
    }


}
