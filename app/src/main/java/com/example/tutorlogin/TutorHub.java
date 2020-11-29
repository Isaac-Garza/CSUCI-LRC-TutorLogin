package com.example.tutorlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TutorHub extends AppCompatActivity {

    Button logoutButton, scheduleButton, courseByTutorButton;
    TextView tutorName;

    ListView studentList, tutorList;
    TutorModel currentTutor;
    ArrayList<TutorModel> tutorAvailableList = new ArrayList<>();
    ArrayList<TableModel> studentTableList = new ArrayList<>();
    TutorListAdapter tutorAdapter;
    TableListAdapter tableAdapter;

    NotificationManager notificationManager;
    Uri alarmSound;

    FirebaseAuth firebaseAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference studentReference;
    DatabaseReference loggedInReference;

    HashMap<String, Boolean> tutorSubject = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_hub);
        rootNode = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        tutorSubject.put("MATH", false);
        tutorSubject.put("CHEM", false);
        tutorSubject.put("BUS/ECON", false);
        tutorSubject.put("BIO", false);
        tutorSubject.put("PHY", false);
        tutorSubject.put("PHYCH/SOCI", false);
        tutorSubject.put("HEALTH SCI", false);
        tutorSubject.put("COMP", false);
        tutorSubject.put("NURSING", false);
//
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        scheduleButton = findViewById(R.id.lrc_schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://docs.google.com/spreadsheets/d/1JRq3Gza7bNDe88ue_-m44NhMtGP_tyS6ZB8-jfb43wU/"));
                startActivity(intent);
            }
        });

        courseByTutorButton = findViewById(R.id.course_by_tutor_button);
        courseByTutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.csuci.edu/learningresourcecenter/coursesavailablefortutoringfall20.pdf"));
                startActivity(intent);
            }
        });


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


                String[] separatedSubjects = tutorModel.getSubject().split(", ");
                for (String tutorSubjects : separatedSubjects) {
                    tutorSubject.put(tutorSubjects, true);
                    // Use hash to figure out what subject tutor has
                }
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
                TableModel newTableModel = new TableModel();
                newTableModel.setTableNumber(snapshot.getKey());
                newTableModel.setSubject(snapshot.getValue().toString());

                if(tutorSubject.get(newTableModel.getSubject())) {
                    studentTableList.add(newTableModel);
                    alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    MediaPlayer mediaPlayer = MediaPlayer. create (getApplicationContext(), alarmSound);
                    mediaPlayer.start();
                    Toast.makeText(TutorHub.this, newTableModel.getTableNumber() + "  Needs Assistance", Toast.LENGTH_SHORT).show();
                    tableAdapter.notifyDataSetChanged();
                }


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
                currentTutor = new TutorModel();
                currentTutor = snapshot.getValue(TutorModel.class);

                if(!firebaseAuth.getUid().equals(currentTutor.getUserID())) {
                    currentTutor.setLogged_in(true);
                    tutorAvailableList.add(currentTutor);
                    tutorAdapter.notifyDataSetChanged();
                }
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
    public void onBackPressed() {

    }
}