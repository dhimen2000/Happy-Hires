package com.example.company.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.company.Fragment.HomeFragment;
import com.example.company.Fragment.Job_ApplicationFragment;
import com.example.company.Model.Job_Applications_Model;
import com.example.company.Model.Student_List_Model;
import com.example.company.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Jobreqdata extends AppCompatActivity {

    Button jobaccept, jobreject;
    Context context;

    DatabaseReference databaseReference;

    String Email;
    //String Pass;
    String Name;
    String Dob;
    String Gender;
    String Number;
    String Address;
    String ImageUrl;
    String S_School;
    String S_Board;
    String S_Percentage;
    String S_Year;
    String H_School;
    String H_Board;
    String H_Percentage;
    String H_Year;
    String G_College;
    String G_Branch;
    String G_Enrollmentno;
    String G_Sem1;
    String G_Year1;
    String G_Sem2;
    String G_Year2;
    String G_Sem3;
    String G_Year3;
    String G_Sem4;
    String G_Year4;
    String G_Sem5;
    String G_Year5;
    String G_Sem6;
    String G_Year6;
    String G_Sem7;
    String G_Year7;
    String G_Sem8;
    String G_Year8;
    String Key;
    String ResumeUrl;
    String PdfUrl;

    CircleImageView profileimage;
    TextView name;
    TextView dob;
    TextView gender;
    TextView number;
    TextView address;
    TextView email;
    TextView secondary_school;
    TextView secondary_board;
    TextView secondary_percentage;
    TextView secondary_year;
    TextView highersecondary_school;
    TextView highersecondary_board;
    TextView highersecondary_percentage;
    TextView highersecondary_year;
    TextView graduation_college;
    TextView graduation_branch;
    TextView graduation_enrollmentno;
    TextView graduation_sem1;
    TextView graduation_year1;
    TextView graduation_sem2;
    TextView graduation_year2;
    TextView graduation_sem3;
    TextView graduation_year3;
    TextView graduation_sem4;
    TextView graduation_year4;
    TextView graduation_sem5;
    TextView graduation_year5;
    TextView graduation_sem6;
    TextView graduation_year6;
    TextView graduation_sem7;
    TextView graduation_year7;
    TextView graduation_sem8;
    TextView graduation_year8;
    Button checkResume;


    SharedPreferences sharedPreferences;


    String Student_Email, Student_Email1;
    private String CompanyEmail;
    private String CompanyName;
    private String StudentEmail;
    private String StudentName;
    private String StudentNumber;
    private String JobTitle;
    private String Applyid;
    String Status;
    String New_Status;
    String New_Status1;

   // EditText test1;

    String job, id, cemail, cname, c_address,new_jobid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_jobreqdata);

        Intent intent1 = getIntent();
        Student_Email = intent1.getStringExtra("Student Email");
        job = intent1.getStringExtra("job");
        cemail = intent1.getStringExtra("Comapny Email");
        cname = intent1.getStringExtra("Comapny Name");
        id = intent1.getStringExtra("id");
        new_jobid = intent1.getStringExtra("Jobid");


        profileimage = findViewById(R.id.Profile_profilepic1);
        name = findViewById(R.id.std_name1);
        dob = findViewById(R.id.std_dob1);
        gender = findViewById(R.id.std_gender1);
        number = findViewById(R.id.std_number1);
        address = findViewById(R.id.std_address1);
        email = findViewById(R.id.std_email1);
        secondary_school = findViewById(R.id.std_Secondary_School1);
        secondary_board = findViewById(R.id.std_Secondary_Board1);
        secondary_percentage = findViewById(R.id.std_Secondary_Percentage1);
        secondary_year = findViewById(R.id.std_Secondary_Year1);
        highersecondary_school = findViewById(R.id.std_HigherSecondary_School1);
        highersecondary_board = findViewById(R.id.std_HigherSecondary_Board1);
        highersecondary_percentage = findViewById(R.id.std_HigherSecondary_Percentage1);
        highersecondary_year = findViewById(R.id.std_HigherSecondary_Year1);
        graduation_college = findViewById(R.id.std_Graduation_College1);
        graduation_branch = findViewById(R.id.std_Graduation_Branch1);
        graduation_enrollmentno = findViewById(R.id.std_Graduation_Enrollmentno1);
        graduation_sem1 = findViewById(R.id.std_Graduation_Sem11);
        graduation_year1 = findViewById(R.id.std_Graduation_Year11);
        graduation_sem2 = findViewById(R.id.std_Graduation_Sem21);
        graduation_year2 = findViewById(R.id.std_Graduation_Year21);
        graduation_sem3 = findViewById(R.id.std_Graduation_Sem31);
        graduation_year3 = findViewById(R.id.std_Graduation_Year31);
        graduation_sem4 = findViewById(R.id.std_Graduation_Sem41);
        graduation_year4 = findViewById(R.id.std_Graduation_Year41);
        graduation_sem5 = findViewById(R.id.std_Graduation_Sem51);
        graduation_year5 = findViewById(R.id.std_Graduation_Year51);
        graduation_sem6 = findViewById(R.id.std_Graduation_Sem61);
        graduation_year6 = findViewById(R.id.std_Graduation_Year61);
        graduation_sem7 = findViewById(R.id.std_Graduation_Sem71);
        graduation_year7 = findViewById(R.id.std_Graduation_Year71);
        graduation_sem8 = findViewById(R.id.std_Graduation_Sem81);
        graduation_year8 = findViewById(R.id.std_Graduation_Year81);
        checkResume = findViewById(R.id.std_CheckResume1);



        jobaccept = findViewById(R.id.confirmjob);
        jobreject = findViewById(R.id.canceljob);


        databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference.child("Student").orderByChild("email").equalTo(Student_Email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        Student_List_Model model = dataSnapshot.getValue(Student_List_Model.class);
                        Name = model.getName();
                        Dob = model.getDob();
                        Gender = model.getGender();
                        Address = model.getAddress();
                        Email=model.getEmail();
                        Number = model.getNumber();
                        ImageUrl = model.getImageUrl();
                        //Glide.with(context).load(model.getImageUrl()).into(profileimage);
                        S_School = model.getS_School();
                        S_Board = model.getS_Board();
                        S_Percentage = model.getS_Percentage();
                        S_Year = model.getS_Year();
                        H_School = model.getH_School();
                        H_Board = model.getH_Board();
                        H_Percentage = model.getH_Percentage();
                        H_Year = model.getH_Year();
                        G_College = model.getG_College();
                        G_Branch = model.getG_Branch();
                        G_Enrollmentno = model.getG_Enrollmentno();
                        G_Sem1 = model.getG_Sem1();
                        G_Sem2 = model.getG_Sem2();
                        G_Sem3 = model.getG_Sem3();
                        G_Sem4 = model.getG_Sem4();
                        G_Sem5 = model.getG_Sem5();
                        G_Sem6 = model.getG_Sem6();
                        G_Sem7 = model.getG_Sem7();
                        G_Sem8 = model.getG_Sem8();
                        G_Year1 = model.getG_Year1();
                        G_Year2 = model.getG_Year2();
                        G_Year3 = model.getG_Year3();
                        G_Year4 = model.getG_Year4();
                        G_Year5 = model.getG_Year5();
                        G_Year6 = model.getG_Year6();
                        G_Year7 = model.getG_Year7();
                        G_Year8 = model.getG_Year8();
                        Key = model.getKey();
                        ResumeUrl=model.getResumeUrl();
                        // PdfUrl=model.getPdfUrl();
                    }

                    name.setText(Name);
                    dob.setText(Dob);
                    gender.setText(Gender);
                    address.setText(Address);
                    email.setText(Email);
                    number.setText(Number);
                    secondary_school.setText(S_School);
                    secondary_board.setText(S_Board);
                    secondary_percentage.setText(S_Percentage);
                    secondary_year.setText(S_Year);
                    highersecondary_school.setText(H_School);
                    highersecondary_board.setText(H_Board);
                    highersecondary_percentage.setText(H_Percentage);
                    highersecondary_year.setText(H_Year);
                    graduation_branch.setText(G_Branch);
                    graduation_college.setText(G_College);
                    graduation_enrollmentno.setText(G_Enrollmentno);
                    graduation_sem1.setText(G_Sem1);
                    graduation_sem2.setText(G_Sem2);
                    graduation_sem3.setText(G_Sem3);
                    graduation_sem4.setText(G_Sem4);
                    graduation_sem5.setText(G_Sem5);
                    graduation_sem6.setText(G_Sem6);
                    graduation_sem7.setText(G_Sem7);
                    graduation_sem8.setText(G_Sem8);
                    graduation_year1.setText(G_Year1);
                    graduation_year2.setText(G_Year2);
                    graduation_year3.setText(G_Year3);
                    graduation_year4.setText(G_Year4);
                    graduation_year5.setText(G_Year5);
                    graduation_year6.setText(G_Year6);
                    graduation_year7.setText(G_Year7);
                    graduation_year8.setText(G_Year8);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



checkResume.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(getApplicationContext(), View_Attachment_CheckResume.class);
        intent.putExtra("Attachment_Check Resume",ResumeUrl);
        startActivity(intent);

    }
});


        jobaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appo = new Intent(Jobreqdata.this, Schedule_Appoinment.class);
                appo.putExtra("Student_Name", Name);
                appo.putExtra("Student_Email", Student_Email);
                appo.putExtra("Company_Name", cname);
                appo.putExtra("Job_Title", job);


                String accept = "Accepted";
                String Rejected = "Rejected";

                Job_Applications_Model model = new Job_Applications_Model(cemail, cname, Student_Email, Name, Number, job, accept, id,new_jobid);
                databaseReference.child("ApplicationForJob").child(id).setValue(model);
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();


                AlertDialog.Builder builder = new AlertDialog.Builder(Jobreqdata.this);
                    builder.setTitle(R.string.app_name);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("Do you want to Schedule the Appoinment?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(Jobreqdata.this, "set date and time", Toast.LENGTH_SHORT).show();
                                    startActivity(appo);
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


                jobreject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Rejected = "Rejected";
                        Job_Applications_Model model = new Job_Applications_Model(cemail, cname, Student_Email, Name, Number, job, Rejected, id,new_jobid);
                        databaseReference.child("ApplicationForJob").child(id).setValue(model);
                        Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }



