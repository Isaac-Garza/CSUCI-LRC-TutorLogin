package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
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

        addTutor = findViewById(R.id.add_tutor_button);
        tutorName = findViewById(R.id.tutor_name);
        tutorRole = findViewById(R.id.tutor_role);
        tutorSubjects = findViewById(R.id.tutor_subject);

        addTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check to see if empty string works

                TutorModel tutorModel;

                try {
                    tutorModel = new TutorModel(tutorName.getText().toString(), tutorRole.getText().toString(), tutorSubjects.getText().toString());
                    Toast.makeText(AddTutor.this, tutorModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(AddTutor.this, "Error Adding Tutor", Toast.LENGTH_SHORT).show();
                    tutorModel = new TutorModel("error", "error", "error");

                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddTutor.this);

                boolean success = databaseHelper.addOne(tutorModel);

                Toast.makeText(AddTutor.this, "Success!!" + success, Toast.LENGTH_SHORT).show();

            }
        });

    }
}