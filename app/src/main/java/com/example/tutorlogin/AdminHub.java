package com.example.tutorlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdminHub extends AppCompatActivity {

    ListView listView;
    List list = new ArrayList();

    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hub);

        listView =(ListView) findViewById(R.id.employee_list);

        list.add("Employee1");
        list.add("Employee2");
        list.add("Employee3");
        list.add("Employee4");
        list.add("Employee5");
        list.add("Employee6");
        list.add("Employee7");
        list.add("Employee8");
        list.add("Employee9");
        list.add("Employee10");

        adapter = new ArrayAdapter(AdminHub.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}