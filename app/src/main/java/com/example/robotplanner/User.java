package com.example.robotplanner;

public class User {
    private String FirstName;
    private String LastName;
    private String Date;
    private String Description;

    public User(String fName,String lName, String date, String description){
        FirstName = fName;
        LastName = lName;
        Date = date;
        Description = description;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}