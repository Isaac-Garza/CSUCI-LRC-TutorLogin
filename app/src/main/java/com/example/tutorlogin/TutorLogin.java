package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorLogin extends AppCompatActivity implements View.OnClickListener{

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_login);

        submitButton = findViewById(R.id.login);

        submitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(this,TutorHub.class);
        startActivity(i);
    }
}