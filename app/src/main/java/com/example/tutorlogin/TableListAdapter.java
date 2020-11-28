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

public class TableListAdapter extends ArrayAdapter<TableModel> {
    private static final String TAG = "TableListAdapter";

    private Context mContext;
    int mResource;

    public TableListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TableModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String subject = getItem(position).getSubject();
        String tableNumber = getItem(position).getTableNumber();

        TableModel studentTable = new TableModel(tableNumber, subject);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTableNumber = convertView.findViewById(R.id.text1);
        TextView tvSubject = convertView.findViewById(R.id.text2);

        tvTableNumber.setText(tableNumber);
        tvSubject.setText(subject);

        return convertView;
    }
}


