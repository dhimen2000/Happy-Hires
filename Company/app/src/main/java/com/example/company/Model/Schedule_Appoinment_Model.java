package com.example.company.Model;

public class Schedule_Appoinment_Model {
    String companyname;
    String companyemail;
    String  jobtitle;
    String studentname;
    String Studentemail;
    String place;
    String time;
    String date;
    String key;
    String Message;

    public Schedule_Appoinment_Model(String companyname, String companyemail, String jobtitle, String studentname, String studentemail, String place, String time, String date, String key, String message) {
        this.companyname = companyname;
        this.companyemail = companyemail;
        this.jobtitle = jobtitle;
        this.studentname = studentname;
        Studentemail = studentemail;
        this.place = place;
        this.time = time;
        this.date = date;
        this.key = key;
        Message = message;
    }
    Schedule_Appoinment_Model(){

    }
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentemail() {
        return Studentemail;
    }

    public void setStudentemail(String studentemail) {
        Studentemail = studentemail;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


    }






