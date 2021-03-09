package com.example.company.ui.Home.bottom_nav.Recyclerview_Model;

import android.widget.LinearLayout;

public class Companies_list_Model {
    String name;
    String number;
    String email;
    String address;
    String city;
    String password;
    String confirm_password;
    LinearLayout linearLayout;

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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

   Companies_list_Model(){

   }
    public Companies_list_Model(String name, String number, String email, String address, String city, String password, String confirm_password, LinearLayout linearLayout) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.password = password;
        this.confirm_password = confirm_password;
        this.linearLayout = linearLayout;
    }




}
