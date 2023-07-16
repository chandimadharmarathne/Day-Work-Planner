package com.example.dwp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

     String year;
     String day;
     String month;
     String description;
     String title;
    String bywhome;
    String time;
    String id;
    String phone;
    String phoneTeacher;
    String d;



    public User() {
    }



    public String getYear() {
        return year;
    }
    public String getDay() {
        return day;
    }
    public String getMonth() {
        return month;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public String getBywhome() {
        return bywhome;
    }
    public String getTime() {
        return time;
    }
    public String getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }
    public String getPhoneTeacher() {
        return phoneTeacher;
    }
    public String getD(){return d;}


}
