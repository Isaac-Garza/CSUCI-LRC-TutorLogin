package com.example.tutorlogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TutorListAdapter extends ArrayAdapter<TutorModel> {
    private static final String TAG = "TutorListAdapter";

    private Context mContext;
    int mResource;

    public TutorListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TutorModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String id = getItem(position).getId();
        String role = getItem(position).getRole();
        String subject = getItem(position).getSubject();
        Boolean loggedIn = getItem(position).getLoggedIn();

        TutorModel tutor = new TutorModel(id, name, role, subject);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView mainTextView = convertView.findViewById(R.id.text1);
        TextView subTextView = convertView.findViewById(R.id.text2);

        mainTextView.setText(name);

        if(loggedIn) {
            subTextView.setText(subject);
        }
        else {
            subTextView.setText(id);
        }

        return convertView;
    }
}
