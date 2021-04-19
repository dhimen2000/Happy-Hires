package com.example.student.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.student.Model.Interview_Model;
import com.example.student.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InterviewdataActivity extends AppCompatActivity {

    @BindView(R.id.Interview_company_name)
    TextInputLayout InterviewCompanyName;
    @BindView(R.id.Interview_company_email)
    TextInputLayout InterviewCompanyEmail;
    @BindView(R.id.Interview_job_name)
    TextInputLayout InterviewJobName;
    @BindView(R.id.Interview_date)
    TextInputLayout InterviewDate;
    @BindView(R.id.Interview_time)
    TextInputLayout InterviewTime;
    @BindView(R.id.Interview_place)
    TextInputLayout InterviewPlace;
    @BindView(R.id.Interview_formalmessage)
    TextInputLayout InterviewMessage;

    String Job_Title;

    String interviewCompanyName,interviewCompanyEmail,interviewJobName,interviewDate,interviewTime,interviewPlace,interviewMessage;

   // StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewdata);
        ButterKnife.bind(this);

//        sharedPreferences = this.getApplicationContext().getSharedPreferences("SharedPreference", MODE_PRIVATE);
//        Student_Email = sharedPreferences.getString("Email_Login", "");

        Intent intent = getIntent();
        Job_Title = intent.getStringExtra("Job Title For Interview");

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = firebaseDatabase.child("ScheduleAppoinment").orderByChild("jobtitle").equalTo(Job_Title);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Interview_Model model = dataSnapshot.getValue(Interview_Model.class);
                        interviewCompanyEmail = model.getCompanyemail();
                        interviewCompanyName = model.getCompanyname();
                        interviewJobName = model.getJobtitle();
                        interviewDate = model.getDate();
                        interviewTime = model.getTime();
                        interviewPlace = model.getPlace();
                        interviewMessage = model.getMessage();
                    }

                    InterviewCompanyEmail.getEditText().setText(interviewCompanyEmail);
                    InterviewCompanyName.getEditText().setText(interviewCompanyName);
                    InterviewJobName.getEditText().setText(interviewJobName);
                    InterviewDate.getEditText().setText(interviewDate);
                    InterviewTime.getEditText().setText(interviewTime);
                    InterviewPlace.getEditText().setText(interviewPlace);
                    InterviewMessage.getEditText().setText(interviewMessage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}