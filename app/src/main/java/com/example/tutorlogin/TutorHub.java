package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorHub extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_hub);
        Button addTutorButton = findViewById(R.id.add_button);

        addTutorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i =

    }
}