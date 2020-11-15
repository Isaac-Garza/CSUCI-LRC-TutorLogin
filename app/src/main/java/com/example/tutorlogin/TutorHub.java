package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TutorHub extends AppCompatActivity implements View.OnClickListener{

    Button lrcSchedule, stemSchedule, logoutButton;
    TextView tutorName;

    ListView studentList, tutorList;
    TutorModel currentTutor;
    ArrayList<TutorModel> tutorAvailableList = new ArrayList<>();
    ArrayList<TableModel> studentTableList = new ArrayList<>();
    TutorListAdapter tutorAdapter;
    TableListAdapter tableAdapter;

    FirebaseAuth firebaseAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference studentReference;
    DatabaseReference loggedInReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_hub);
        rootNode = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        lrcSchedule = findViewById(R.id.lrc_schedule_button);
        stemSchedule = findViewById(R.id.stem_schedule_button);
        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUser = firebaseAuth.getCurrentUser().getUid();
                firebaseAuth.signOut();

                reference = rootNode.getReference("Logged-In").child(currentUser);
                reference.removeValue();

                Intent intent = new Intent(TutorHub.this, TutorLogin.class);
                startActivity(intent);
                finish();
            }
        });

        tutorName = findViewById(R.id.tutorName);
        String identifier = firebaseAuth.getCurrentUser().getUid();
        reference = rootNode.getReference("Tutor").child(identifier);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TutorModel tutorModel = snapshot.getValue(TutorModel.class);
                tutorName.setText(tutorModel.getName());
                DatabaseReference newRef = rootNode.getReference("Logged-In").child(tutorModel.getUserID());
                newRef.setValue(tutorModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        studentList = findViewById(R.id.student_queue_list);
        tutorList = findViewById(R.id.tutor_queue_list);

        rootNode = FirebaseDatabase.getInstance();

        loggedInReference = rootNode.getReference("Logged-In");
        studentReference = rootNode.getReference("Table");

        tutorAdapter = new TutorListAdapter(this, R.layout.list_item, tutorAvailableList);
        tutorList.setAdapter(tutorAdapter);

        tableAdapter = new TableListAdapter(this, R.layout.list_item, studentTableList);
        studentList.setAdapter(tableAdapter);

        studentReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                studentTableList.add(snapshot.getValue(TableModel.class));
                tableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loggedInReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Modify What tutor gets in here

                currentTutor = new TutorModel();
                currentTutor = snapshot.getValue(TutorModel.class);
                currentTutor.setLogged_in(true);
//
//                loggedInReference = rootNode.getReference("Logged-In").child(currentTutor.getUserID());
//                loggedInReference.setValue(currentTutor);

                tutorAvailableList.add(currentTutor);
                tutorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}