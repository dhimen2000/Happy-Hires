package com.example.company;

import android.widget.ImageView;

public class Register_Model {
    String name;
    String number;
    String email;
    String address;
    String city;
    String password;

    public Register_Model(String name, String number, String email, String address, String city, String password, String confirm_password, String imgurl, String pdfurl) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.city = city;
        this.password = password;
        this.confirm_password = confirm_password;
        this.imgurl = imgurl;
        this.pdfurl = pdfurl;
    }

    String confirm_password;
    String imgurl;
    String pdfurl;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
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



    Register_Model(){
    }








}
