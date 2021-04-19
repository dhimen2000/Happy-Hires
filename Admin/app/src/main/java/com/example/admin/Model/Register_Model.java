package com.example.admin.Model;

public class Register_Model {
    String Email;
    String Pass;

    public Register_Model() {

    }

    public Register_Model(String email, String pass) {
        Email = email;
        Pass = pass;
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
