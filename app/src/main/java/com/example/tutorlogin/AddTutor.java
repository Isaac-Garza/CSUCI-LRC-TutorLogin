package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTutor extends AppCompatActivity {

    EditText tutorId, tutorName, tutorRole, tutorSubjects;
    Button addTutor;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutor);

        addTutor = findViewById(R.id.add_tutor_button);
        tutorId = findViewById(R.id.tutor_id);
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
//                    Toast.makeText(AddTutor.this, tutorModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(AddTutor.this, "Error Adding Tutor", Toast.LENGTH_SHORT).show();
                    tutorModel = new TutorModel("error", "error", "error");

                }

                addOne(tutorModel);
                Toast.makeText(AddTutor.this, "Tutor Added!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(AddTutor.this, AdminHub.class);
                startActivity(i);

            }
        });


    }

    private void addOne(TutorModel tutorModel) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Tutor");

        reference.child(tutorModel.getName()).setValue(tutorModel);

    }
}