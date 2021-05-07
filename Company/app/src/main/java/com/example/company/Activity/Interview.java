package com.example.company.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.company.Model.Job_Applications_Model;
import com.example.company.Model.Schedule_Appoinment_Model;
import com.example.company.Model.Student_List_Model;
import com.example.company.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Interview extends AppCompatActivity {
    DatabaseReference firebaseDatabase;
    SharedPreferences sharedPreferences;
    String companyname,companyemail,studentname,studentemail,jobtitle,place,time,date,key,message,newstudentemail;
    TextView Companyname,Companyemail,Studentname,Studentemail,Jobtitle,Place,Date,Time,Message;
    Button Hired,Rejected;
    DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        Companyemail =findViewById(R.id.intcompanyemail);
        Companyname=findViewById(R.id.intcompanyname);
        Studentname =findViewById(R.id.intstudentname);
        Studentemail=findViewById(R.id.intstudentemail);
        Jobtitle  =findViewById(R.id.intjobtitle);
        Place =findViewById(R.id.intplace);
        Date =findViewById(R.id.intdate);
        Time  =findViewById(R.id.inttime);
        Message  =findViewById(R.id.intmessage);
        Hired=findViewById(R.id.inthired);
        Rejected=findViewById(R.id.intrejected);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        newstudentemail = intent.getStringExtra("Student Email");
        key=intent.getStringExtra("applyid");


       Query query= firebaseDatabase.child("ScheduleAppoinment").orderByChild("studentemail").equalTo(newstudentemail);
       query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Schedule_Appoinment_Model model = dataSnapshot.getValue(Schedule_Appoinment_Model.class);

                                    companyemail=model.getCompanyemail();
                                    companyname=model.getCompanyname();
                                    studentname=model.getStudentname();
                                    studentemail=model.getStudentemail();
                                    jobtitle=model.getJobtitle();
                                    place=model.getPlace();
                                    date=model.getDate();
                                    time=model.getTime();
                                    message=model.getMessage();
                                    key=model.getKey();
                    }

                                    Companyname.setText(companyname);
                                    Companyemail.setText(companyemail);
                                    Studentname.setText(studentname);
                                    Studentemail.setText(studentemail);
                                    Jobtitle.setText(jobtitle);
                                    Place.setText(place);
                                    Date.setText(date);
                                    Time.setText(time);
                                    Message.setText(message);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       Hired.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String accept = "Accepted";


               Schedule_Appoinment_Model model = new Schedule_Appoinment_Model(companyemail,companyname,jobtitle,studentname,studentemail,place,date,time,key,message,accept);
               firebaseDatabase.child("ScheduleAppoinment").child(key).setValue(model);
               Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();


               AlertDialog.Builder builder = new AlertDialog.Builder(Interview.this);
               builder.setTitle(R.string.app_name);
               builder.setIcon(R.mipmap.ic_launcher);
               builder.setMessage("Do you want to send a offer letter Via Mail?")
                       .setCancelable(false)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               Toast.makeText(Interview.this, "send mail", Toast.LENGTH_SHORT).show();
//                              Intent intent=new Intent(Interview.this,Offerletter.class);
//                              startActivity(intent);
                               Intent emailIntent = new Intent(Intent.ACTION_SEND);
                               emailIntent.setType("text/plain");
                               startActivity(emailIntent);

                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               dialog.cancel();
                           }
                       });
               AlertDialog alert = builder.create();
               alert.show();


           }
       });

       Rejected.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Rejected = "Rejected";
               Schedule_Appoinment_Model model = new Schedule_Appoinment_Model(companyemail,companyname,jobtitle,studentname,studentemail,place,date,time,key,message,Rejected);
               firebaseDatabase.child("ScheduleAppoinment").child(key).setValue(model);


//               Query query1= firebaseDatabase.child("ScheduleAppoinment").orderByChild("key").equalTo(key);
//               query1.addListenerForSingleValueEvent(new ValueEventListener() {
//                   @Override
//                   public void onDataChange(DataSnapshot dataSnapshot) {
//                       for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//                           appleSnapshot.getRef().removeValue();
//                           Toast.makeText(getApplicationContext(), "Reject Successfully", Toast.LENGTH_SHORT).show();
//
//                       }
//                   }
//
//                   @Override
//                   public void onCancelled(@NonNull DatabaseError error) {
//
//                   }
//
//
//               });
           }
       });

    }
}