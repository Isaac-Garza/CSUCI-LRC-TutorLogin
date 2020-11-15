package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AddTutor extends AppCompatActivity {

    EditText tutorId, tutorName, tutorRole, tutorSubjects, tutorEmail, tutorPassword;

    Button addTutor;

    FirebaseAuth firebaseAuth;
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
        tutorEmail = findViewById(R.id.username);
        tutorPassword = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        addTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dolphinID = tutorId.getText().toString().trim();
                String name = tutorName.getText().toString().trim();
                String role = tutorRole.getText().toString().trim();
                String subject = tutorSubjects.getText().toString().trim();
                String email = tutorEmail.getText().toString().trim();
                String password = tutorPassword.getText().toString().trim();

                if(TextUtils.isEmpty(dolphinID)) {
                    tutorId.setError("ID is required!");
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

                if(TextUtils.isEmpty(role)) {
                    tutorRole.setError("Role(s) is required!");
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

                if(TextUtils.isEmpty(password)) {
                    tutorPassword.setError("Password is required!");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(tutorEmail.getText().toString(), tutorPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            String createdUserID = task.getResult().getUser().getUid();
                            TutorModel tutorModel;
                            tutorModel = new TutorModel(tutorId.getText().toString(), tutorName.getText().toString(), tutorRole.getText().toString(), tutorSubjects.getText().toString(), createdUserID);

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
}