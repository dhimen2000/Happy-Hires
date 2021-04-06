package com.example.company.Model;

import android.widget.ImageView;

public class Register_Model {
    String name;
    String number;
    String email;
    String address;
    String Website;
    String password;
    String id;
    String imgurl;
    String pdfurl;

    public Register_Model(String name, String number, String email, String address, String website, String password, String id, String imgurl, String pdfurl) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.Website = website;
        this.password = password;
        this.id = id;
        this.imgurl = imgurl;
        this.pdfurl = pdfurl;
    }
    Register_Model(){

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

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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


    }











