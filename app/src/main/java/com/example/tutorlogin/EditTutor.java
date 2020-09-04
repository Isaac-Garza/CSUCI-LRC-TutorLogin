package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EditTutor extends AppCompatActivity {

    TextView editTutorTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tutor);

        Intent intent = getIntent();
        String selectedTutorTitle = intent.getStringExtra("TutorName");
        editTutorTitle.findViewById(R.id.Selected_Tutor);
        editTutorTitle.setText(selectedTutorTitle);


    }
}