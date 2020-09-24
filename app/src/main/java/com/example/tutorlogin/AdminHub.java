package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class AdminHub extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hub);

//        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
//        listView = (ListView) findViewById(R.id.listviewID);
//        listView.setAdapter(whatever);

        addButton=findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

//        listView =(ListView) findViewById(R.id.employee_list);
//        String[] nameArray = {"Jake", "Bake", "Norm", "Shell"};
//        String[] jobDesc = {"EPT", "TUTOR", "EPT", "TUTOR"};
//
//        Integer[] imageArray = {R.drawable.profile_icon,
//                R.drawable.profile_icon,
//                R.drawable.profile_icon,
//                R.drawable.profile_icon};
//
//        list.add("Employee1");
//        list.add("Employee2");
//        list.add("Employee3");
//        list.add("Employee4");
//        list.add("Employee5");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminHub.this, EditTutor.class);
                intent.putExtra("TutorName", list.get(position).toString());
                startActivity(intent);
            }
        });



        adapter = new ArrayAdapter(AdminHub.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

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