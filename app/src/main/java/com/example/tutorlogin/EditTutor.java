package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTutor extends AppCompatActivity {

    TextView editTutorTitle;
    EditText edId, edTutorName, edRole, edSubjects, edEmail, edUserID;
    Button editTutor, removeTutor;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tutor);

        final TutorModel selectedTutor = getIntent().getParcelableExtra("Tutor");

        editTutorTitle = findViewById(R.id.selected_tutor);
        editTutorTitle.setText(selectedTutor.getName());

        edId = findViewById(R.id.id_field);
        edId.setText(selectedTutor.getId());

        edTutorName = findViewById(R.id.name_field);
        edTutorName.setText(selectedTutor.getName());

        edRole = findViewById(R.id.role_field);
        edRole.setText(selectedTutor.getRole());

        edSubjects = findViewById(R.id.subject_field);
        edSubjects.setText(selectedTutor.getSubject());


        editTutor = findViewById(R.id.edit_button);
        // use intent that was passed here to find tutor being edited, then replace with new object
        editTutor.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                TutorModel tutorModel;
                tutorModel = new TutorModel(edId.getText().toString(), edTutorName.getText().toString(), edRole.getText().toString(), edSubjects.getText().toString(), edEmail.getText().toString());


                boolean success;
                success = addOne(tutorModel, selectedTutor);

                if(!success) {
                    Toast.makeText(EditTutor.this, "Unable to Update Tutor", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditTutor.this, "Tutor Updated " + success, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditTutor.this, AdminHub.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        removeTutor = findViewById(R.id.remove_button);
        removeTutor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Tutor");

                reference.child(edId.getText().toString()).removeValue();

                Toast.makeText(EditTutor.this, "Tutor Removed!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditTutor.this, AdminHub.class);
                startActivity(i);
            }
        });


    }


    private boolean addOne(TutorModel updatedTutorModel, TutorModel selectedTutor) {

        boolean success = false;
        if(!updatedTutorModel.getName().isEmpty() &&
                !updatedTutorModel.getId().isEmpty() &&
                !updatedTutorModel.getRole().isEmpty() &&
                !updatedTutorModel.getSubject().isEmpty()) {

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Tutor");
            reference.child(selectedTutor.getId()).setValue(updatedTutorModel);
            success = true;
        }

        return success;
    }
}