package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AdminHub extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    ListView listView;
    ArrayList<TutorModel> list = new ArrayList<>();
//    ArrayAdapter adapter;
    Button addButton;
    HashMap<String, String> nameDolphinId = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hub);

        addButton=findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        listView =findViewById(R.id.employee_list);

        TutorModel isaac = new TutorModel("002497222", "Isaac Garza", "LEAD", "MATH");
        TutorModel gearge = new TutorModel("002497333", "Gearge Flank", "PLTL", "COMP");
        TutorModel moka = new TutorModel("002497444", "Moka Kazoo", "LRC", "PHYS");

        list.add(isaac);
        list.add(gearge);
        list.add(moka);

        TutorListAdapter adapter = new TutorListAdapter(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminHub.this, EditTutor.class);
                intent.putExtra("Tutor", list.get(position));

                startActivity(intent);
            }
        });



    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, AddTutor.class);
        startActivity(i);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}