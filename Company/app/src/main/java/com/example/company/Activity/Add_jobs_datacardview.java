package com.example.company.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.company.Model.Job_Model;
import com.example.company.R;
import com.example.company.Activity.View_Attachment_Jobs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_jobs_datacardview extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    Button Update,Delete;

    String companyname;
    String companyemail;
    String jobtitle;
    String requirements;
    String percentagecriteria;
    String salaryrange;
    String joblastdate;
    String jobattachmenturl;
    String key1,jobid;

String editcompanyemail,editcompanyname,editjobtitle,editjobdiscription,editpercentage,editsalaryrange,editlastdate;
TextView Editcompanyemail,Editcompanyname,Editjobtitle,Editjobdiscription,Editpercentage,Editsalaryrange,Editlastdate;
Button Jobattachmenturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_add_jobs_datacardview);


        Editcompanyemail=findViewById(R.id.editcompanyemail);
        Editcompanyname=findViewById(R.id.editcompanyname);
        Editjobtitle =findViewById(R.id.editjobtitle);
        Editjobdiscription=findViewById(R.id.editjobdiscription);
        Editpercentage=findViewById(R.id.editpercentagecriteria);
        Editsalaryrange=findViewById(R.id.editsalaryrange);
        Editlastdate=findViewById(R.id.editlastdate);
        Jobattachmenturl=findViewById(R.id.editjobattachment);
        Update=findViewById(R.id.updatejob);
        Delete=findViewById(R.id.deletejob);

        Intent intent = getIntent();
        jobid =intent.getStringExtra("jid");

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

    firebaseDatabase.child("JobsAvailable").orderByChild("jobid").equalTo(jobid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Job_Model model = dataSnapshot.getValue(Job_Model.class);

                        editcompanyemail=model.getCompanyemail();
                        editcompanyname=model.getCompanyname();
                        editjobtitle=model.getJobtitle();
                        editjobdiscription=model.getRequirements();
                        editpercentage=model.getPercentagecriteria();
                        editsalaryrange=model.getSalaryrange();
                        editlastdate=model.getJoblastdate();
                        key1=model.getJobid();
                        jobattachmenturl=model.getJobattachmenturl();


                    }
                    Editcompanyemail.setText(editcompanyemail);
                    Editcompanyname.setText(editcompanyname);
                    Editjobtitle.setText(editjobtitle);
                    Editjobdiscription.setText(editjobdiscription);
                    Editpercentage.setText(editpercentage);
                    Editsalaryrange.setText(editsalaryrange);
                    Editlastdate.setText(editlastdate);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    Jobattachmenturl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(), View_Attachment_Jobs.class);
            intent.putExtra("Attachment_Jobs",jobattachmenturl);
            startActivity(intent);

        }
    });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                companyemail=Editcompanyemail.getText().toString();
                companyname=Editcompanyname.getText().toString();

                jobtitle=Editjobtitle.getText().toString();
                requirements=Editjobdiscription.getText().toString();
                percentagecriteria=Editpercentage.getText().toString();
                salaryrange=Editsalaryrange.getText().toString();
                joblastdate=Editlastdate.getText().toString();

                Job_Model model = new Job_Model(companyemail,companyname,jobtitle,joblastdate,salaryrange,percentagecriteria,requirements,jobattachmenturl,jobid);


                firebaseDatabase.child("JobsAvailable").child(jobid).setValue(model);

                Toast.makeText(getApplication(), "Update Successfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("id",jobid);

                firebaseDatabase.child("JobsAvailable").child(jobid).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                    finish();
                                    startActivity(getIntent());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }


                        });
            }
        });



    }


}

