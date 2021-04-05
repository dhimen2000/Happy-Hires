package com.example.student.Model;

public class View_Jobs_Model {

    String companyemail;
    String companyname;
    String jobtitle;
    String requirements;
    String percentagecriteria;
    String salaryrange;
    String joblastdate;
    String jobattachmenturl;
    String jobid;


    public View_Jobs_Model() {

    }

    public View_Jobs_Model(String companyemail, String companyname, String jobtitle, String requirements, String percentagecriteria, String salaryrange, String joblastdate, String jobattachmenturl, String jobid) {
        this.companyemail = companyemail;
        this.companyname = companyname;
        this.jobtitle = jobtitle;
        this.requirements = requirements;
        this.percentagecriteria = percentagecriteria;
        this.salaryrange = salaryrange;
        this.joblastdate = joblastdate;
        this.jobattachmenturl = jobattachmenturl;
        this.jobid = jobid;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPercentagecriteria() {
        return percentagecriteria;
    }

    public void setPercentagecriteria(String percentagecriteria) {
        this.percentagecriteria = percentagecriteria;
    }

    public String getSalaryrange() {
        return salaryrange;
    }

    public void setSalaryrange(String salaryrange) {
        this.salaryrange = salaryrange;
    }

    public String getJoblastdate() {
        return joblastdate;
    }

    public void setJoblastdate(String joblastdate) {
        this.joblastdate = joblastdate;
    }

    public String getJobattachmenturl() {
        return jobattachmenturl;
    }

    public void setJobattachmenturl(String jobattachmenturl) {
        this.jobattachmenturl = jobattachmenturl;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

}
