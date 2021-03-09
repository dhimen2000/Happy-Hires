package com.example.student;

public class RegisterModel {

    String Name;
    String Number;
    String Email;
    String Pass;

    public RegisterModel(String name, String number, String email, String pass) {
        Name = name;
        Number = number;
        Email = email;
        Pass = pass;
    }

    public RegisterModel() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

}
