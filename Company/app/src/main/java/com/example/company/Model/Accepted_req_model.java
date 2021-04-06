package com.example.company.Model;

public class Accepted_req_model {
    String companyEmail;
    String companyName;
    String studentEmail;
    String studentName;
    String studentNumber;
    String jobTitle;
    String status;
    String applyid;
    String jobid;


    public Accepted_req_model(String companyEmail, String companyName, String studentEmail, String studentName, String studentNumber, String jobTitle, String status, String applyid, String jobid) {
        this.companyEmail = companyEmail;
        this.companyName = companyName;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.jobTitle = jobTitle;
        this.status = status;
        this.applyid = applyid;
        this.jobid = jobid;
    }
    Accepted_req_model(){

    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }











}
