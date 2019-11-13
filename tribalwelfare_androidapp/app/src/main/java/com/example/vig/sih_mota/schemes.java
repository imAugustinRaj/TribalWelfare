package com.example.vig.sih_mota;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class schemes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.schemes);
        mPlayer.start();
    }
}
