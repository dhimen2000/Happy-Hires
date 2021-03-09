package com.example.company.ui.Home.bottom_nav.Recyclerview_Model;

public class Student_List_Model {
    String name;
    String number;
    String Email;


    public Student_List_Model(String name, String number, String email) {
        this.name = name;
        this.number = number;
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }




}
