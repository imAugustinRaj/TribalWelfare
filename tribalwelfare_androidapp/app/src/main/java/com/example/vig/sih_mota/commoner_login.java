package com.example.vig.sih_mota;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class commoner_login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commoner_login);
        mAuth = FirebaseAuth.getInstance();
        MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.commoner_login);
        mPlayer.start();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),email_register.class);
                startActivity(intent);
            }
        });

    }
    public void signin(final View view){
        EditText email=(EditText)findViewById(R.id.email);
        final EditText passsword=(EditText)findViewById(R.id.password);
        Button btn =(Button)findViewById(R.id.login_btn);
        final ProgressBar prog=(ProgressBar)findViewById(R.id.progress);
        String mail= email.getText().toString().trim();
        String pass= passsword.getText().toString().trim();

        if(TextUtils.isEmpty(mail)) {
            email.setError("User Name Required");
            return;
        }
        if(TextUtils.isEmpty(pass)) {
            passsword.setError("Password Required");
            return;
        }




        prog.setVisibility(view.VISIBLE);
        btn.setVisibility(View.INVISIBLE);
        mAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Button btn =(Button)findViewById(R.id.login_btn);
                        prog.setVisibility(view.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),screen4.class);
                            startActivity(intent);

                            prog.setVisibility(view.INVISIBLE);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                            btn.setVisibility(View.VISIBLE);
                            // prog.setVisibility(view.INVISIBLE);
                        }

                    }
                });


    }
}
