package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTutor extends AppCompatActivity {

    TextView editTutorTitle;
    EditText edTutorName, edRole, edSubjects;
    Button editTutor;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tutor);

        TutorModel selectedTutor = getIntent().getParcelableExtra("Tutor");

        editTutorTitle = findViewById(R.id.selected_tutor);
        editTutorTitle.setText(selectedTutor.getName());

        edTutorName = findViewById(R.id.name_field);
        edTutorName.setText(selectedTutor.getName());

        edRole = findViewById(R.id.role_field);
        edRole.setText(selectedTutor.getRole());

        edSubjects = findViewById(R.id.subject_field);
        edSubjects.setText(selectedTutor.getSubject());

//        editTutor.setOnClickListener(new View.OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v) {
//
//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("Tutor");
//                reference.child(tutorModel.getId()).setValue(tutorModel);
//                success = true;
//
//
//            }
//        });


    }
}