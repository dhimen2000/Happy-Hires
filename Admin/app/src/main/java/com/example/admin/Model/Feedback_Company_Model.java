package com.example.admin.Model;

public class Feedback_Company_Model {

    String Company_name;
    String Company_email;
    Float Rating;
    String feedback;
    String id;

    public Feedback_Company_Model(String company_name, String company_email, Float rating, String feedback, String id) {
        Company_name = company_name;
        Company_email = company_email;
        Rating = rating;
        this.feedback = feedback;
        this.id = id;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getCompany_email() {
        return Company_email;
    }

    public void setCompany_email(String company_email) {
        Company_email = company_email;
    }

    public Float getRating() {
        return Rating;
    }

    public void setRating(Float rating) {
        Rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
