package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TutorLogin extends AppCompatActivity implements View.OnClickListener{

    TextView username, password;
    Button submitButton;

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submitButton = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();

        submitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        final String recievedUsername = username.getText().toString().trim();
        String recievedPassword = password.getText().toString().trim();

        if(recievedUsername.equals("admin")) {
            Intent intent = new Intent(TutorLogin.this,AdminHub.class);
            startActivity(intent);
            finish();
        }

        if(TextUtils.isEmpty(recievedUsername)) {
            Toast.makeText(this, "Username is Required.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(recievedPassword)) {
            Toast.makeText(this, "Password is Required.", Toast.LENGTH_SHORT).show();
            return;
        }

        fAuth.signInWithEmailAndPassword(recievedUsername, recievedPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(TutorLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    if(recievedUsername.equals("admin")) {
                        intent = new Intent(TutorLogin.this,AdminHub.class);
                        startActivity(intent);
                    }
                    else {
                        intent = new Intent(TutorLogin.this,TutorHub.class);
                    }

                    startActivity(intent);
                } 
                else {
                    Toast.makeText(TutorLogin.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}