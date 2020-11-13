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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AddTutor extends AppCompatActivity {

    EditText tutorId, tutorName, tutorRole, tutorSubjects, tutorUserName, tutorPassword;

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
        tutorUserName = findViewById(R.id.username);
        tutorPassword = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        addTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dolphinID = tutorId.getText().toString().trim();
                String name = tutorName.getText().toString().trim();
                String role = tutorRole.getText().toString().trim();
                String subject = tutorSubjects.getText().toString().trim();
                String userName = tutorUserName.getText().toString().trim();
                String password = tutorPassword.getText().toString().trim();

                if(TextUtils.isEmpty(dolphinID)) {
                    tutorId.setError("ID is required!");
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

                if(TextUtils.isEmpty(userName)) {
                    tutorUserName.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    tutorPassword.setError("Password is required!");
                    return;
                }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Tutor").child(dolphinID);

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            Toast.makeText(AddTutor.this, "Error: Duplicate Tutor ID", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                firebaseAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            TutorModel tutorModel;
                            tutorModel = new TutorModel(tutorId.getText().toString(), tutorName.getText().toString(), tutorRole.getText().toString(), tutorSubjects.getText().toString());

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