package com.example.admin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.Model.Feedback_Student_Model;
import com.example.admin.Model.Student_Model;
import com.example.admin.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class StudentFeedback_Adapter extends RecyclerView.Adapter<StudentFeedback_Adapter.ViewHolder> {

    List<Feedback_Student_Model> StudentFeedbackList;

    public StudentFeedback_Adapter(List<Feedback_Student_Model> studentFeedbackList) {
        StudentFeedbackList = studentFeedbackList;
    }

    @NonNull
    @Override
    public StudentFeedback_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.studentfeedbackdesign, parent, false);
        StudentFeedback_Adapter.ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentFeedback_Adapter.ViewHolder holder, int position) {
        Feedback_Student_Model model = StudentFeedbackList.get(position);
        holder.studentname.getEditText().setText(model.getStudent_Name());
        holder.studentemail.getEditText().setText(model.getStudent_Email());
        holder.ratingBarstudent.setRating(model.getRating());
        holder.ratingBarstudent.setEnabled(false);
        holder.studentfeedback.getEditText().setText(model.getFeedback());
    }

    @Override
    public int getItemCount() {
        return StudentFeedbackList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextInputLayout studentname;
        TextInputLayout studentemail;
        RatingBar ratingBarstudent;
        TextInputLayout studentfeedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.studentname = (TextInputLayout) itemView.findViewById(R.id.Feedbackstudentname);
            this.studentemail = (TextInputLayout) itemView.findViewById(R.id.Feedbackstudentemail);
            this.ratingBarstudent = (RatingBar) itemView.findViewById(R.id.ratingBarstudent);
            this.studentfeedback = (TextInputLayout) itemView.findViewById(R.id.Feedbackstudent);
        }
    }
}
