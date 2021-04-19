package com.example.admin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Model.Feedback_Company_Model;
import com.example.admin.Model.Feedback_Student_Model;
import com.example.admin.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class CompanyFeedback_Adapter extends RecyclerView.Adapter<CompanyFeedback_Adapter.ViewHolder> {

    List<Feedback_Company_Model> CompanyFeedbackList;

    public CompanyFeedback_Adapter(List<Feedback_Company_Model> companyFeedbackList) {
        CompanyFeedbackList = companyFeedbackList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.companyfeedbackdesign, parent, false);
        CompanyFeedback_Adapter.ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Feedback_Company_Model model = CompanyFeedbackList.get(position);
        holder.companyname.getEditText().setText(model.getCompany_name());
        holder.companyemail.getEditText().setText(model.getCompany_email());
        holder.ratingBarcompany.setRating(model.getRating());
        holder.ratingBarcompany.setEnabled(false);
        holder.companyfeedback.getEditText().setText(model.getFeedback());

    }

    @Override
    public int getItemCount() {
        return CompanyFeedbackList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextInputLayout companyname;
        TextInputLayout companyemail;
        RatingBar ratingBarcompany;
        TextInputLayout companyfeedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.companyname = (TextInputLayout) itemView.findViewById(R.id.Feedbackcompanyname);
            this.companyemail = (TextInputLayout) itemView.findViewById(R.id.Feedbackcompanyemail);
            this.ratingBarcompany = (RatingBar) itemView.findViewById(R.id.ratingBarcompany);
            this.companyfeedback = (TextInputLayout) itemView.findViewById(R.id.Feedbackcompany);
        }
    }
}
