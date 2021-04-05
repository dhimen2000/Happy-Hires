package com.example.student.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.student.Model.Apply_Jobs_Model;
import com.example.student.Model.RegisterModel;
import com.example.student.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobdataActivity extends AppCompatActivity {

    @BindView(R.id.Jobdata_company_email)
    EditText JobCompanyEmail;
    @BindView(R.id.Jobdata_company_name)
    EditText JobComapnyName;
    @BindView(R.id.Jobdata_name)
    EditText JobTitle;
    @BindView(R.id.Jobdata_description)
    EditText JobDescription;
    @BindView(R.id.Jobdata_percentagecriteria)
    EditText JobPercentagecriteria;
    @BindView(R.id.Jobdata_salaryrange)
    EditText JobSalaryrange;
    @BindView(R.id.Jobdata_lastdate)
    EditText Joblastdate;

    @BindView(R.id.Jobdata_attachment)
    Button JobAttachment;
    @BindView(R.id.Jobdata_apply)
    Button JobApply;

    String jobcompanyemail, jobcomapnyname ,jobtitle, jobdescription, jobpercentagecriteria, jobsalaryrange, joblastdate, jobattachment;

    String Student_Email,Status;
    String New_Student_Email,New_Student_Name,New_Student_Number,New_JobCompanyEmail,New_JobTitle,Apply_key,jobid;
    String Applied_job_status;


    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdata);
        ButterKnife.bind(this);

        Status = "On Hold";

        sharedPreferences = this.getApplicationContext().getSharedPreferences("SharedPreference", MODE_PRIVATE);
        Student_Email = sharedPreferences.getString("Email_Login", "");

        Intent intent = getIntent();
        jobcompanyemail = intent.getStringExtra("Job_Company_Email");
        jobcomapnyname = intent.getStringExtra("Job_Company_Name");
        jobtitle = intent.getStringExtra("Job_Title");
        jobdescription = intent.getStringExtra("Job_Description");
        jobpercentagecriteria = intent.getStringExtra("Job_Percentagecriteria");
        jobsalaryrange = intent.getStringExtra("Job_Salaryrange");
        joblastdate = intent.getStringExtra("Job_Lastdate");
        jobattachment = intent.getStringExtra("Job_Pdf");
        jobid = intent.getStringExtra("jobid");

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        JobCompanyEmail.setText(jobcompanyemail);
        JobComapnyName.setText(jobcomapnyname);
        JobTitle.setText(jobtitle);
        JobDescription.setText(jobdescription);
        JobPercentagecriteria.setText(jobpercentagecriteria);
        JobSalaryrange.setText(jobsalaryrange);
        Joblastdate.setText(joblastdate);

        JobAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(JobdataActivity.this, JobattachmentcheckActivity.class);
                intent1.putExtra("JobAttachment",jobattachment);
                startActivity(intent1);
            }
        });

        Query query = firebaseDatabase.child("Student").orderByChild("email").equalTo(Student_Email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        RegisterModel model = dataSnapshot.getValue(RegisterModel.class);
                        New_Student_Email = model.getEmail();
                        New_Student_Name = model.getName();
                        New_Student_Number = model.getNumber();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(JobdataActivity.this, "Data not Selected", Toast.LENGTH_SHORT).show();
            }
        });


        try {
            Query query1 = firebaseDatabase.child("ApplicationForJob").orderByChild("jobid").equalTo(jobid);
            query1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Apply_Jobs_Model model = dataSnapshot.getValue(Apply_Jobs_Model.class);
                            Applied_job_status = model.getStatus();

                            switch (Applied_job_status){
                                case "On Hold":
                                    JobApply.setText("You Already Applied For This Role...");
                                    JobApply.setEnabled(false);
                                    break;
                                case "Accepted":
                                    JobApply.setText("You are Selected For This Role...");
                                    JobApply.setEnabled(false);
                                    break;
                                case "Rejected":
                                    JobApply.setText("You are Rejected For This Role...");
                                    JobApply.setEnabled(false);
                                    break;
                                default:
                                    JobApply.setVisibility(View.VISIBLE);
                                    break;
                            }
//
//                            if (Applied_job_status.equals("On Hold") ) {
//                                JobApply.setText("You Already Applied For This Role...");
//                                JobApply.setEnabled(false);
//                            }else if (Applied_job_status.equals("Accepted") ) {
//                                JobApply.setText("You are Selected For This Role...");
//                                JobApply.setEnabled(false);
//                            } else if (Applied_job_status.equals("Rejected") ) {
//                                JobApply.setText("You are Rejected For This Role...");
//                                JobApply.setEnabled(false);
//                            }else {
//                                JobApply.setVisibility(View.VISIBLE);
//                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        catch (Exception e)
        {

        }

        JobApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                New_JobCompanyEmail = JobCompanyEmail.getText().toString();
                New_JobTitle = JobTitle.getText().toString();

                Apply_key = firebaseDatabase.push().getKey();
                Apply_Jobs_Model applyJobsModels = new Apply_Jobs_Model(New_JobCompanyEmail,jobcomapnyname,New_Student_Email,New_Student_Name,New_Student_Number,New_JobTitle,Status,Apply_key,jobid);
                firebaseDatabase.child("ApplicationForJob").child(Apply_key).setValue(applyJobsModels);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

            }
        });


    }
}