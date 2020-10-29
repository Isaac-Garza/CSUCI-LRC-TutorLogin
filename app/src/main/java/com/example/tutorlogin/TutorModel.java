package com.example.tutorlogin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TutorModel {
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
}
