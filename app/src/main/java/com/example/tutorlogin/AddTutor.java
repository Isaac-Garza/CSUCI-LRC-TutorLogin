package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddTutor extends AppCompatActivity {

    private EditText tutorId, tutorName, tutorSubjects, tutorEmail, tutorPassword;
    private Button addTutor, cancelbutton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutor);

        addTutor = findViewById(R.id.add_tutor_button);
        cancelbutton = findViewById(R.id.cancel_button);
        tutorId = findViewById(R.id.tutor_id);
        tutorName = findViewById(R.id.tutor_name);
        tutorSubjects = findViewById(R.id.tutor_subject);
        tutorEmail = findViewById(R.id.username);
        tutorPassword = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTutor.this, AdminHub.class);
                startActivity(intent);
                finish();
            }
        });

        addTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dolphinID = tutorId.getText().toString().trim();
                String name = tutorName.getText().toString().trim();
                String subject = tutorSubjects.getText().toString().trim();
                final String email = tutorEmail.getText().toString().trim();
                final String password = tutorPassword.getText().toString().trim();

                String[] lrcSubjects = {"MATH","CHEM","BUS","ECON","BIO","PHY","PHYCH","SOCI","HEALTH SCI","COMP","NURSING"};
                String[] allSubjects = subject.split(", ");

                boolean found = false;
                for(int allSubjectIndex = 0; allSubjectIndex < allSubjects.length; allSubjectIndex++) {
                    for (int indexLRC = 0; indexLRC < lrcSubjects.length && !found; indexLRC++) {
                        if(allSubjects[allSubjectIndex].equals(lrcSubjects[indexLRC])) {
                            found = true;
                        }
                    }
                    if(!found) {
                        String error = "ERROR: ";
                        if(!allSubjects[allSubjectIndex].contains(",")) {
                            tutorSubjects.setError(error + "Separate Subjects by Commas or Check Spacing");
                        }
                        else if (allSubjects[allSubjectIndex].substring(allSubjects[allSubjectIndex].length()-1).equals(",")) {
                            tutorSubjects.setError(error + "Remove Comma at End");
                        }
                        else {
                            tutorSubjects.setError("Subject(s) Misspelled: " + allSubjects[allSubjectIndex]);
                        }
                        return;
                    }
                    found = false;
                }

                if(TextUtils.isEmpty(dolphinID)) {
                    tutorId.setError("ID is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    tutorPassword.setError("Password is required!");
                    return;
                }

                if(dolphinID.length() != 9) {
                    tutorId.setError("Dolphin ID be 9 characters long");
                    return;
                }

                if(TextUtils.isEmpty(name)) {
                    tutorName.setError("Name is required!");
                    return;
                }

                if(TextUtils.isEmpty(subject)) {
                    tutorSubjects.setError("Subject(s) is required!");
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    tutorEmail.setError("Email is required!");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(tutorEmail.getText().toString(), tutorPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            String createdUserID = task.getResult().getUser().getUid();
                            TutorModel tutorModel;
                            tutorModel = new TutorModel(tutorId.getText().toString(), tutorName.getText().toString(), tutorSubjects.getText().toString(), createdUserID, email, password);

                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("Tutor").child(createdUserID);
                            reference.setValue(tutorModel);

                            Toast.makeText(AddTutor.this, "Tutor Created!", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(AddTutor.this, AdminHub.class);
                            startActivity(i);
                            finish();

                        }
                        else {
                            Toast.makeText(AddTutor.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }




        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AdminHub.class);
        startActivity(intent);
        finish();
    }
}