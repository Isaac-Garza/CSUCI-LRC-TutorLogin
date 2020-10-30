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


//        nameDolphinId.put("Isaac Garza", "002497222");
//        nameDolphinId.put("George Florez", "002497333");
//        nameDolphinId.put("Mika Bean","002497444");
//
//        List<HashMap<String, String>> listItems = new ArrayList<>();
//        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
//                new String[]{"First Line", "Second Line"},
//                new int[]{R.id.text1, R.id.text2});
//
//        Iterator iterator = nameDolphinId.entrySet().iterator();
//        while (iterator.hasNext())
//        {
//            HashMap<String, String> resultMap = new HashMap<>();
//            Map.Entry pair = (Map.Entry) iterator.next();
//            resultMap.put("First Line", pair.getKey().toString());
//            resultMap.put("Second Line", pair.getKey().toString());
//            listItems.add(resultMap);
//        }

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
                intent.putExtra("TutorName", (Serializable) list.get(position));
//                intent.putExtra("TutorName", (Parcelable) list.get(position));
                startActivity(intent);
            }
        });



//        adapter = new ArrayAdapter(AdminHub.this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);

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