package com.example.admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.Model.Student_Model;
import com.example.admin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentdataActivity extends AppCompatActivity {

    @BindView(R.id.Studentdata_profilepic)
    CircleImageView profileimage;
    @BindView(R.id.Studentdata_name)
    EditText name;
    @BindView(R.id.Studentdata_Dob)
    EditText dob;
    @BindView(R.id.Studentdata_Gender)
    EditText gender;
    @BindView(R.id.Studentdata_number)
    EditText number;
    @BindView(R.id.Studentdata_Address)
    EditText address;
    @BindView(R.id.Studentdata_Email)
    EditText email;


    @BindView(R.id.Studentdata_Secondary_School)
    EditText secondary_school;
    @BindView(R.id.Studentdata_Secondary_Board)
    EditText secondary_board;
    @BindView(R.id.Studentdata_Secondary_Percentage)
    EditText secondary_percentage;
    @BindView(R.id.Studentdata_Secondary_Year)
    EditText secondary_year;

    @BindView(R.id.Studentdata_HigherSecondary_School)
    EditText highersecondary_school;
    @BindView(R.id.Studentdata_HigherSecondary_Board)
    EditText highersecondary_board;
    @BindView(R.id.Studentdata_HigherSecondary_Percentage)
    EditText highersecondary_percentage;
    @BindView(R.id.Studentdata_HigherSecondary_Year)
    EditText highersecondary_year;

    @BindView(R.id.Studentdata_Graduation_College)
    EditText graduation_college;
    @BindView(R.id.Studentdata_Graduation_Branch)
    EditText graduation_branch;
    @BindView(R.id.Studentdata_Graduation_Enrollmentno)
    EditText graduation_enrollmentno;
    @BindView(R.id.Studentdata_Graduation_Sem1)
    EditText graduation_sem1;
    @BindView(R.id.Studentdata_Graduation_Year1)
    EditText graduation_year1;
    @BindView(R.id.Studentdata_Graduation_Sem2)
    EditText graduation_sem2;
    @BindView(R.id.Studentdata_Graduation_Year2)
    EditText graduation_year2;
    @BindView(R.id.Studentdata_Graduation_Sem3)
    EditText graduation_sem3;
    @BindView(R.id.Studentdata_Graduation_Year3)
    EditText graduation_year3;
    @BindView(R.id.Studentdata_Graduation_Sem4)
    EditText graduation_sem4;
    @BindView(R.id.Studentdata_Graduation_Year4)
    EditText graduation_year4;
    @BindView(R.id.Studentdata_Graduation_Sem5)
    EditText graduation_sem5;
    @BindView(R.id.Studentdata_Graduation_Year5)
    EditText graduation_year5;
    @BindView(R.id.Studentdata_Graduation_Sem6)
    EditText graduation_sem6;
    @BindView(R.id.Studentdata_Graduation_Year6)
    EditText graduation_year6;
    @BindView(R.id.Studentdata_Graduation_Sem7)
    EditText graduation_sem7;
    @BindView(R.id.Studentdata_Graduation_Year7)
    EditText graduation_year7;
    @BindView(R.id.Studentdata_Graduation_Sem8)
    EditText graduation_sem8;
    @BindView(R.id.Studentdata_Graduation_Year8)
    EditText graduation_year8;

    @BindView(R.id.Studentdata_CheckResume)
    Button CheckResume;
    @BindView(R.id.Studentdata_Checkpdf)
    Button CheckPdf;

    DatabaseReference firebaseDatabase;

    String Name, Dob, Gender, Number, Address, Email, E_mail, Pass;
    String Secondary_School, Secondary_Board, Secondary_Percentage, Secondary_Year;
    String HigherSecondary_School, HigherSecondary_Board, HigherSecondary_Percentage, HigherSecondary_Year;
    String Graduation_College, Graduation_Branch, Graduation_Enrollmentno;
    String Graduation_Sem1, Graduation_Sem2, Graduation_Sem3, Graduation_Sem4, Graduation_Sem5, Graduation_Sem6, Graduation_Sem7, Graduation_Sem8;
    String Graduation_Year1, Graduation_Year2, Graduation_Year3, Graduation_Year4, Graduation_Year5, Graduation_Year6, Graduation_Year7, Graduation_Year8;
    String checkResume,checkPdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdata);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        E_mail = intent.getStringExtra("Student_Email");

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = firebaseDatabase.child("Student").orderByChild("email").equalTo(E_mail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Student_Model model = dataSnapshot.getValue(Student_Model.class);


//                        ResumeDownloadUri = Uri.parse(model.getResumeUrl().toString());
//                        PdfDownloadUri = Uri.parse(model.getPdfUrl().toString());
//
                        Glide.with(getApplicationContext()).load(model.getImageUrl()).into(profileimage);
                        Name = model.getName();
                        Dob = model.getDob();
                        Gender = model.getGender();
                        Number = model.getNumber();
                        Address = model.getAddress();
                        Email = model.getEmail();

                        Secondary_School = model.getS_School();
                        Secondary_Board = model.getS_Board();
                        Secondary_Percentage = model.getS_Percentage();
                        Secondary_Year = model.getS_Year();

                        HigherSecondary_School = model.getH_School();
                        HigherSecondary_Board = model.getH_Board();
                        HigherSecondary_Percentage = model.getH_Percentage();
                        HigherSecondary_Year = model.getH_Year();

                        Graduation_College = model.getG_College();
                        Graduation_Branch = model.getG_Branch();
                        Graduation_Enrollmentno = model.getG_Enrollmentno();
                        Graduation_Sem1 = model.getG_Sem1();
                        Graduation_Sem2 = model.getG_Sem2();
                        Graduation_Sem3 = model.getG_Sem3();
                        Graduation_Sem4 = model.getG_Sem4();
                        Graduation_Sem5 = model.getG_Sem5();
                        Graduation_Sem6 = model.getG_Sem6();
                        Graduation_Sem7 = model.getG_Sem7();
                        Graduation_Sem8 = model.getG_Sem8();
                        Graduation_Year1 = model.getG_Year1();
                        Graduation_Year2 = model.getG_Year2();
                        Graduation_Year3 = model.getG_Year3();
                        Graduation_Year4 = model.getG_Year4();
                        Graduation_Year5 = model.getG_Year5();
                        Graduation_Year6 = model.getG_Year6();
                        Graduation_Year7 = model.getG_Year7();
                        Graduation_Year8 = model.getG_Year8();

                        checkResume = model.getResumeUrl();
                        checkPdf = model.getPdfUrl();
                    }
                    name.setText(Name);
                    dob.setText(Dob);
                    gender.setText(Gender);
                    number.setText(Number);
                    address.setText(Address);
                    email.setText(Email);

                    secondary_school.setText(Secondary_School);
                    secondary_board.setText(Secondary_Board);
                    secondary_percentage.setText(Secondary_Percentage);
                    secondary_year.setText(Secondary_Year);

                    highersecondary_school.setText(HigherSecondary_School);
                    highersecondary_board.setText(HigherSecondary_Board);
                    highersecondary_percentage.setText(HigherSecondary_Percentage);
                    highersecondary_year.setText(HigherSecondary_Year);

                    graduation_college.setText(Graduation_College);
                    graduation_branch.setText(Graduation_Branch);
                    graduation_enrollmentno.setText(Graduation_Enrollmentno);
                    graduation_sem1.setText(Graduation_Sem1);
                    graduation_sem2.setText(Graduation_Sem2);
                    graduation_sem3.setText(Graduation_Sem3);
                    graduation_sem4.setText(Graduation_Sem4);
                    graduation_sem5.setText(Graduation_Sem5);
                    graduation_sem6.setText(Graduation_Sem6);
                    graduation_sem7.setText(Graduation_Sem7);
                    graduation_sem8.setText(Graduation_Sem8);
                    graduation_year1.setText(Graduation_Year1);
                    graduation_year2.setText(Graduation_Year2);
                    graduation_year3.setText(Graduation_Year3);
                    graduation_year4.setText(Graduation_Year4);
                    graduation_year5.setText(Graduation_Year5);
                    graduation_year6.setText(Graduation_Year6);
                    graduation_year7.setText(Graduation_Year7);
                    graduation_year8.setText(Graduation_Year8);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        CheckResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentdataActivity.this,Student_Resume_attachmentActivity.class);
                intent.putExtra("Resume", checkResume);
                startActivity(intent);
            }
        });

        CheckPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentdataActivity.this, Student_Pdf_attachmentActivity.class);
                intent.putExtra("Pdf", checkPdf);
                startActivity(intent);
            }
        });


    }
}