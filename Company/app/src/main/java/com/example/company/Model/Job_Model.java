package com.example.company.Model;

import android.widget.TextView;

public class Job_Model {

    String companyname;
    String companyemail;
    String jobtitle;
    String requirements;
    String percentagecriteria;
    String salaryrange;
    String joblastdate;
    String jobattachmenturl;
    String jobid;

    public Job_Model(String companyemail, String companyname, String jobtitle, String joblastdate, String salaryrange, String percentagecriteria, String requirements, String jobattachmenturl, String jobid) {
        this.companyemail = companyemail;
        this.companyname = companyname;
        this.jobtitle = jobtitle;
        this.joblastdate = joblastdate;
        this.salaryrange = salaryrange;
        this.percentagecriteria = percentagecriteria;
        this.requirements = requirements;
        this.jobattachmenturl = jobattachmenturl;
        this.jobid = jobid;
    }

    public Job_Model() {
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

    public String getJoblastdate() {
        return joblastdate;
    }

    public void setJoblastdate(String joblastdate) {
        this.joblastdate = joblastdate;
    }

    public String getSalaryrange() {
        return salaryrange;
    }

    public void setSalaryrange(String salaryrange) {
        this.salaryrange = salaryrange;
    }

    public String getPercentagecriteria() {
        return percentagecriteria;
    }

    public void setPercentagecriteria(String percentagecriteria) {
        this.percentagecriteria = percentagecriteria;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
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
