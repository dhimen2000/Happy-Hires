package com.example.admin.Model;

public class Company_Model {

    String name;
    String Website;
    String address;
    String number;
    String email;
    String id;
    String imgurl;
    String pdfurl;

    public Company_Model() {
    }

    public Company_Model(String name, String website, String address, String number, String email, String id, String imgurl, String pdfurl) {
        this.name = name;
        Website = website;
        this.address = address;
        this.number = number;
        this.email = email;
        this.id = id;
        this.imgurl = imgurl;
        this.pdfurl = pdfurl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
