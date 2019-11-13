package com.example.vig.sih_mota;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class language extends AppCompatActivity {

    private TextToSpeech textToSpeechSystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.language);
        mPlayer.start();



    }





    public void assamese(View view){

        setLocale("as");
        Intent intent = new Intent(getApplicationContext(), screen1.class);
        startActivity(intent);
    }
    public void bengali(View view){
        setLocale("bn");
        Intent intent = new Intent(getApplicationContext(), screen1.class);
        startActivity(intent);
    }
    public void hindi(View view){
        setLocale("hi");
        Intent intent = new Intent(getApplicationContext(), screen1.class);
        startActivity(intent);
    }
    public void english(View view){
        setLocale("en");
        Intent intent = new Intent(getApplicationContext(), screen1.class);
        startActivity(intent);
    }
    private void setLocale(String lang) {

        String languageToLoad = lang; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_screen1);

    }
}
