package com.example.tutorlogin;

import android.os.Parcel;
import android.os.Parcelable;

public class TableModel implements Parcelable {

    private String tableNumber;
    private String subject;

    public TableModel(String tableNumber, String subject) {
        this.tableNumber = tableNumber;
        this.subject = subject;
    }

    public TableModel() {

    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    protected TableModel(Parcel in) {
        tableNumber = in.readString();
        subject = in.readString();
    }

    public static final Creator<TableModel> CREATOR = new Creator<TableModel>() {
        @Override
        public TableModel createFromParcel(Parcel in) {
            return new TableModel(in);
        }

        @Override
        public TableModel[] newArray(int size) {
            return new TableModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tableNumber);
        dest.writeString(subject);
    }
}
