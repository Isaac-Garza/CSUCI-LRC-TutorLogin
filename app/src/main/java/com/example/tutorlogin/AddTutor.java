package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTutor extends AppCompatActivity {

    EditText tutorId, tutorName, tutorRole, tutorSubjects;
    Button addTutor;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    ValueEventListener responseListener;

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
                TutorModel tutorModel;
                tutorModel = new TutorModel(tutorId.getText().toString(), tutorName.getText().toString(), tutorRole.getText().toString(), tutorSubjects.getText().toString());

                boolean success = addOne(tutorModel);

                if(!success) {
                    Toast.makeText(AddTutor.this, "Empty Field(s) or Duplicate ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddTutor.this, "Tutor added " + success, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddTutor.this, AdminHub.class);
                    startActivity(i);
                }
            }
        });
    }

    private boolean addOne(TutorModel tutorModel) {
        boolean success = false;
        if(!tutorModel.getName().isEmpty() &&
                !tutorModel.getId().isEmpty() &&
                !tutorModel.getRole().isEmpty() &&
                !tutorModel.getSubject().isEmpty()) {

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Tutor");

            String keyValue = reference.child(tutorModel.getId()).getKey();
            if(!(keyValue !=null && keyValue.equals(tutorModel.getId()))) {
                reference.child(tutorModel.getId()).setValue(tutorModel);
                success = true;
            }
        }

        return success;
    }
}