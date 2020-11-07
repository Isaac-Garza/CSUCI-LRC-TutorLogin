package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TutorHub extends AppCompatActivity implements View.OnClickListener{

    Button lrcSchedule, stemSchedule;

    ListView studentList, tutorList;
    ArrayList<TutorModel> tList = new ArrayList<>();
    ArrayList<TableModel> tableList = new ArrayList<>();
    TutorListAdapter tutorAdapter;
    TableListAdapter tableAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference studentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_hub);

        lrcSchedule = findViewById(R.id.lrc_schedule_button);
        stemSchedule = findViewById(R.id.stem_schedule_button);

        studentList = findViewById(R.id.student_queue_list);
        tutorList = findViewById(R.id.tutor_queue_list);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Tutor");
        studentReference = rootNode.getReference("Table");

        tutorAdapter = new TutorListAdapter(this, R.layout.list_item, tList);
        tutorList.setAdapter(tutorAdapter);

        tableAdapter = new TableListAdapter(this, R.layout.list_item, tableList);
        studentList.setAdapter(tableAdapter);

        studentReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                tableList.add(snapshot.getValue(TableModel.class));
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

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Modify What tutor gets in here
                tList.add(snapshot.getValue(TutorModel.class));
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