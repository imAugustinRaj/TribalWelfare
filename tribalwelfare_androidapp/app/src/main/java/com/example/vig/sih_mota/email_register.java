package com.example.vig.sih_mota;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class email_register extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);
        mAuth = FirebaseAuth.getInstance();
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.email_register);
        mPlayer.start();

        ImageView imageView = (ImageView)findViewById(R.id.imageView26);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               set();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    public void set(){


        final ProgressBar pro = (ProgressBar)findViewById(R.id.email_prog);
        pro.setVisibility(View.VISIBLE);
        EditText mail= (EditText)findViewById(R.id.uname);
        EditText pass1 = (EditText)findViewById(R.id.pass1);
        EditText pass2 = (EditText)findViewById(R.id.pass2);

        String mail1= mail.getText().toString().trim();
        String pass3 = pass1.getText().toString();
        String pass4 = pass2.getText().toString();


        if(TextUtils.isEmpty(mail1)) {
            mail.setError("User Name Required");
            return;
        }
        if(TextUtils.isEmpty(pass3)) {
            pass1.setError("Password Required");
            return;
        }
        if(TextUtils.isEmpty(pass4)) {
            pass2.setError("Retype password Password");
            return;
        }




        if (!pass3.equals(pass4))
             {
            Toast.makeText(this, "Password not mathch", Toast.LENGTH_SHORT).show();
            return;
        }
      boolean a=  Patterns.EMAIL_ADDRESS.matcher(mail1).matches();
        if(a==false) {
            Toast.makeText(this, "Enter the valid Email ID", Toast.LENGTH_SHORT).show();
        }
        mAuth.createUserWithEmailAndPassword(mail.getText().toString().trim(), pass1.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(email_register.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(email_register.this, screen1.class);
                            startActivity(intent);
                            pro.setVisibility(View.INVISIBLE);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            pro.setVisibility(View.INVISIBLE);

                        }

                        // ...
                    }
                });
    }

}
