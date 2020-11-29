package com.example.tutorlogin;

import android.os.Parcel;
import android.os.Parcelable;

public class TutorModel implements Parcelable {
    private String id;
    private String name;
    private String subject;
    private String userID;
    private String email;
    private String password;
    private boolean logged_in;


    // constructor
    public TutorModel(String id, String name, String subject, String userID, String email, String password){
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.logged_in = false;
    }

    public TutorModel() {

    }

    //toString

    protected TutorModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        subject = in.readString();
        userID = in.readString();
        email = in.readString();
        password = in.readString();
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
                " name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", userID='" + userID + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getLoggedIn() {
        return this.logged_in;
    }

    public void setLogged_in(Boolean logged_in) {
        this.logged_in = logged_in;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(subject);
        dest.writeString(userID);
        dest.writeString(email);
        dest.writeString(password);
    }
}
