package com.example.tutorlogin;

public class TutorModel {
    private String name;
    private String role;
    private String subject;
    private String days;

    // constructor

//    public TutorModel(String name, String role, String subject, String days) {
    public TutorModel(String name, String role, String subject) {
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
