package com.example.student.Model;

public class Feedback_Model {

    String Student_Name;
    String Student_Email;
    Float Rating;
    String feedback;
    String id;

    public Feedback_Model() {
    }

    public Feedback_Model(String student_Name, String student_Email, Float rating, String feedback, String id) {
        Student_Name = student_Name;
        Student_Email = student_Email;
        Rating = rating;
        this.feedback = feedback;
        this.id = id;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getStudent_Email() {
        return Student_Email;
    }

    public void setStudent_Email(String student_Email) {
        Student_Email = student_Email;
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
