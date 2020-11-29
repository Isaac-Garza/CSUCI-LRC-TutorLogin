package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTutor extends AppCompatActivity {

    TextView editTutorTitle;
    EditText edId, edTutorName, edSubjects, edEmail, edPassword;
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

        edSubjects = findViewById(R.id.subject_field);
        edSubjects.setText(selectedTutor.getSubject());

        edEmail = findViewById(R.id.email_field);
        edEmail.setText(selectedTutor.getEmail());

        edPassword = findViewById(R.id.password_field);
        edPassword.setText(selectedTutor.getPassword());

        editTutor = findViewById(R.id.edit_button);
        editTutor.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String dolphinID = edId.getText().toString().trim();
                String name = edTutorName.getText().toString().trim();
                String subject = edSubjects.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                if(TextUtils.isEmpty(dolphinID)) {
                    edId.setError("ID is required!");
                    return;
                }

                if(dolphinID.length() != 9) {
                    edId.setError("Dolphin ID be 9 characters long");
                    return;
                }

                if(TextUtils.isEmpty(name)) {
                    edTutorName.setError("Name is required!");
                    return;
                }

                if(TextUtils.isEmpty(subject)) {
                    edSubjects.setError("Subject(s) is required!");
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    edEmail.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    edPassword.setError("Password is required!");
                    return;
                }

                TutorModel tutorModel;
                tutorModel = new TutorModel(dolphinID, name, subject, selectedTutor.getUserID(), email, password);
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Tutor");
                reference.child(selectedTutor.getUserID()).setValue(tutorModel);

                Toast.makeText(EditTutor.this, "Tutor Updated!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditTutor.this, AdminHub.class);
                startActivity(i);
                finish();
            }
        });

        removeTutor = findViewById(R.id.remove_button);
        removeTutor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Tutor");

                reference.child(selectedTutor.getUserID()).removeValue();

                Toast.makeText(EditTutor.this, "Tutor Removed!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditTutor.this, AdminHub.class);
                startActivity(i);
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