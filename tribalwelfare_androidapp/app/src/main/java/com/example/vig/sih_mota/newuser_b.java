package com.example.vig.sih_mota;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class newuser_b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser_b);

        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.new_user);
        mPlayer.start();
    }

    public void heeeeey(View view) {
        Toast.makeText(this, "user id created", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),successfullyregistered_b.class);
        startActivity(intent);
    }
}
