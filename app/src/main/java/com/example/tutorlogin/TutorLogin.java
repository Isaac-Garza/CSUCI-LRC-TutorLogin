package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TutorLogin extends AppCompatActivity implements View.OnClickListener{

    final String adminID = "xB6UKHdlFTcef8Z47ILRBVOztEa2";

    TextView username, password;
    Button submitButton;

    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submitButton = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();

        if(fAuth.getCurrentUser() != null) {

            Intent intent;
            if (fAuth.getUid().equals(adminID)) {
                intent = new Intent(this, AdminHub.class);
            }
            else {
                intent = new Intent(this, TutorHub.class);
            }

            startActivity(intent);
            finish();
        }

        submitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        final String recievedUsername = username.getText().toString().trim();
        final String recievedPassword = password.getText().toString().trim();

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
                    progressDialog = new ProgressDialog(TutorLogin.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

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
                    finish();
                } 
                else {
                    Toast.makeText(TutorLogin.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        progressDialog.dismiss();
    }

}