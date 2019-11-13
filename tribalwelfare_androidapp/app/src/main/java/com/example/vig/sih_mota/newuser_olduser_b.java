package com.example.vig.sih_mota;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class newuser_olduser_b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser_olduser_b);

        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.old_user);
        mPlayer.start();
    }

    public void heeey(View view) {
        Intent intent = new Intent(getApplicationContext(),newuser_b.class);
        startActivity(intent);
    }

    public void hy(View view) {
        Intent intent = new Intent(getApplicationContext(),artisan_lgin.class);
        startActivity(intent);
    }
}
