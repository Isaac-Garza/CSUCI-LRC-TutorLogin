package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTutor extends AppCompatActivity {

    EditText tutorName, tutorRole, tutorSubjects;
    Button addTutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutor);

        addTutor.findViewById(R.id.add_button);
        tutorName.findViewById(R.id.tutor_name);
        tutorRole.findViewById(R.id.tutor_role);
        tutorSubjects.findViewById(R.id.tutor_role);

        addTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TutorModel tutorModel = new TutorModel(tutorName.getText().toString(), tutorRole.getText().toString(), tutorSubjects.getText().toString());
                Toast.makeText(AddTutor.this, tutorModel.toString(), Toast.LENGTH_SHORT);
            }
        });

    }
}