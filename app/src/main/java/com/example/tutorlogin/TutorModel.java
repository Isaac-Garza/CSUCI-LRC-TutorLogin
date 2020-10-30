package com.example.tutorlogin;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TutorModel implements Parcelable {
    private String id;
    private String name;
    private String role;
    private String subject;
    public FirebaseDatabase rootNode;
    public DatabaseReference reference;
//    private String days;

    // constructor

//    public TutorModel(String name, String role, String subject, String days) {
    public TutorModel(String id, String name, String role, String subject){
        this.id = id;
        this.name = name;
        this.role = role;
        this.subject = subject;
//        this.days = days;
    }

    public TutorModel() {

    }

    //toString

    protected TutorModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        role = in.readString();
        subject = in.readString();
    }

    public static final Creator<TutorModel> CREATOR = new Creator<TutorModel>() {
        @Override
        public TutorModel createFromParcel(Parcel in) {
            return new TutorModel(in);
        }

        @Override
        public TutorModel[] newArray(int size) {
            return new TutorModel[size];
        }
    };

    @Override
    public String toString() {
        return "TutorModel{" +
                "ID='" + id + "'" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", subject='" + subject + '\'' +
//                ", days='" + days + '\'' +
                '}';
    }


    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

//    public String getDays() {
//        return days;
//    }
//
//    public void setDays(String days) {
//        this.days = days;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(role);
        dest.writeString(subject);
    }
}
