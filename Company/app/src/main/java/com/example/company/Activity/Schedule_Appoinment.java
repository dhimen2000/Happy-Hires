package com.example.company.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.company.Fragment.Accepted_reqFragment;
import com.example.company.Model.Schedule_Appoinment_Model;
import com.example.company.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Schedule_Appoinment extends AppCompatActivity {
String companyname,companyemail,studentname,studentemail,jobtitle,place,time,date1,key,message;
EditText Companyname,Companyemail,Studentname,Studentemail,Jobtitle,Place,Date,Time,Message;
Button sechudleappoinment;
    private Calendar calendar;
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_schedule__appoinment);
Companyname=findViewById(R.id.appcompanyname);
Companyemail=findViewById(R.id.appcompanyemail);
Studentname=findViewById(R.id.appstudentname);
Studentemail=findViewById(R.id.appstudentemail);
Jobtitle=findViewById(R.id.appjobtitle);
Place=findViewById(R.id.appplace);
Time=findViewById(R.id.apptime);
Date=findViewById(R.id.appdate);
Message=findViewById(R.id.appmessage);

sechudleappoinment=findViewById(R.id.scheduleappo);

 calendar = Calendar.getInstance();





        sharedPreferences  = this.getApplicationContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        place = sharedPreferences.getString("C-address", "");
        companyemail = sharedPreferences.getString("E-mail", "");

        Intent appo = getIntent();
        companyname = appo.getStringExtra("Company_Name");
        studentname = appo.getStringExtra("Student_Name");
        studentemail=appo.getStringExtra("Student_Email");
        jobtitle=appo.getStringExtra("Job_Title");


        Companyname.setText(companyname);
       Companyemail.setText(companyemail);
        Studentname.setText(studentname);
        Studentemail.setText(studentemail);
        Jobtitle.setText(jobtitle);
        Place.setText(place);

Time.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Schedule_Appoinment.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }
});

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel() {
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date.setText(sdf.format(myCalendar.getTime()));
            }

        };

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("Helllo","Hello");
                new DatePickerDialog(Schedule_Appoinment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

       sechudleappoinment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               databaseReference = FirebaseDatabase.getInstance().getReference();

               companyname=Companyname.getText().toString();
               companyemail=Companyemail.getText().toString();
               studentname=Studentname.getText().toString();
               studentemail=Studentemail.getText().toString();
               jobtitle=Jobtitle.getText().toString();
               place=Place.getText().toString();
               date1= Date.getText().toString();
               time=Time.getText().toString();
               message=Message.getText().toString();





               key = databaseReference.push().getKey();
               Schedule_Appoinment_Model model = new Schedule_Appoinment_Model(companyname,companyemail,jobtitle,studentname,studentemail,place,time,date1,key,message);
               databaseReference.child("ScheduleAppoinment").child(key).setValue(model);
               Toast.makeText(getApplication(), "Update Successfully", Toast.LENGTH_SHORT).show();

               //Intent intent= new Intent(Schedule_Appoinment.this, Accepted_reqFragment.class);
               //startActivity(intent);
           }
       });



    }


}