package com.example.student.Model;

public class Apply_Jobs_Model {


    String CompanyEmail;
    String CompanyName;
    String StudentEmail;
    String StudentName;
    String StudentNumber;
    String JobTitle;
    String Status;
    String Applyid;
    String jobid;

    public Apply_Jobs_Model() {

    }

    public Apply_Jobs_Model(String companyEmail, String companyName, String studentEmail, String studentName, String studentNumber, String jobTitle, String status, String applyid, String jobid) {
        CompanyEmail = companyEmail;
        CompanyName = companyName;
        StudentEmail = studentEmail;
        StudentName = studentName;
        StudentNumber = studentNumber;
        JobTitle = jobTitle;
        Status = status;
        Applyid = applyid;
        this.jobid = jobid;
    }


    public String getCompanyEmail() {
        return CompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        CompanyEmail = companyEmail;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getApplyid() {
        return Applyid;
    }

    public void setApplyid(String applyid) {
        Applyid = applyid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }








}
