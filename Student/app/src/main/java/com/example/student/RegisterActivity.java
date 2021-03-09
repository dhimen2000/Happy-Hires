package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity {
    //Basic Details
    @BindView(R.id.Register_name)
    EditText name;
    @BindView(R.id.Register_number)
    EditText number;
    @BindView(R.id.Register_Dob)
    EditText dob;
    @BindView(R.id.Register_Gender)
    EditText gender;
    @BindView(R.id.Register_email)
    EditText email;
    @BindView(R.id.Register_password)
    EditText pass;
    @BindView(R.id.Register_Confirm_password)
    EditText confirm_pass;

    //Register Button
    @BindView(R.id.Register_submit)
    Button btnregister;

    @BindView(R.id.Secondary)
    TextView secondary;
    @BindView(R.id.HigherSecondary)
    TextView highersecondary;
    @BindView(R.id.Graduation)
    TextView graduation;

    //Basic Details
    String Name, Number, Dob, Gender, Email, Pass, Confirm_Pass;

    //Secondary Details
    String S_School, S_Board, S_Percentage, S_Year;

    //HigherSecondary Details
    String H_School, H_Board, H_Percentage, H_Year;

    //Graduation Details
    String G_College, G_Course, G_Sem1, G_Year1, G_Sem2, G_Year2, G_Sem3, G_Year3, G_Sem4, G_Year4, G_Sem5, G_Year5, G_Sem6, G_Year6, G_Sem7, G_Year7, G_Sem8, G_Year8;

    FirebaseAuth firebaseAuth;

    DatabaseReference firebaseDatabase;

    final Calendar myCalendar = Calendar.getInstance();

    //private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        firebaseAuth = firebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //For DOB
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
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        secondary.setOnClickListener(new View.OnClickListener() {

            EditText secondary_school,secondary_board,secondary_percentage,secondary_year;
            Button save,clear;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                View view = getLayoutInflater().inflate(R.layout.secondarydialogbox,null);

                secondary_school = view.findViewById(R.id.Secondary_School);
                secondary_board = view.findViewById(R.id.Secondary_Board);
                secondary_percentage = view.findViewById(R.id.Secondary_Percentage);
                secondary_year = view.findViewById(R.id.Secondary_Year);
                save = view.findViewById(R.id.Secondary_btn_Save);
                clear = view.findViewById(R.id.Secondary_btn_Clear);
                alert.setView(view);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        S_School = secondary_school.getText().toString();
                        Log.d("S_school",S_School);
                        S_Board = secondary_board.getText().toString();
                        Log.d("S_Board",S_Board);
                        S_Percentage = secondary_percentage.getText().toString();
                        Log.d("S_Percentage",S_Percentage);
                        S_Year = secondary_year.getText().toString();
                        Log.d("S_Year",S_Year);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        highersecondary.setOnClickListener(new View.OnClickListener() {

            EditText highersecondary_school, highersecondary_board, highersecondary_percentage, highersecondary_year;
            Button save, clear;

            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                View root = getLayoutInflater().inflate(R.layout.highersecondarydialogbox, null);

                highersecondary_school = root.findViewById(R.id.HigherSecondary_School);
                highersecondary_board = root.findViewById(R.id.HigherSecondary_Board);
                highersecondary_percentage = root.findViewById(R.id.HigherSecondary_Percentage);
                highersecondary_year = root.findViewById(R.id.HigherSecondary_Year);
                save = root.findViewById(R.id.HigherSecondary_btn_Save);
                clear = root.findViewById(R.id.HigherSecondary_btn_Clear);

                alert.setView(root);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        H_School = highersecondary_school.getText().toString();
                        Log.d("H_school", H_School);
                        H_Board = highersecondary_board.getText().toString();
                        Log.d("H_Board", H_Board);
                        H_Percentage = highersecondary_percentage.getText().toString();
                        Log.d("H_Percentage", H_Percentage);
                        H_Year = highersecondary_year.getText().toString();
                        Log.d("H_Year", H_Year);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        graduation.setOnClickListener(new View.OnClickListener() {

            EditText graduation_college, graduation_course;
            EditText graduation_sem1, graduation_year1, graduation_sem2, graduation_year2, graduation_sem3, graduation_year3, graduation_sem4, graduation_year4;
            EditText graduation_sem5, graduation_year5, graduation_sem6, graduation_year6, graduation_sem7, graduation_year7, graduation_sem8, graduation_year8;
            Button save, clear;

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                View root = getLayoutInflater().inflate(R.layout.graduationdialogbox, null);

                graduation_college = root.findViewById(R.id.Graduation_College);
                graduation_course = root.findViewById(R.id.Graduation_Course);
                graduation_sem1 = root.findViewById(R.id.Graduation_Sem1);
                graduation_year1 = root.findViewById(R.id.Graduation_Year1);
                graduation_sem2 = root.findViewById(R.id.Graduation_Sem2);
                graduation_year2 = root.findViewById(R.id.Graduation_Year2);
                graduation_sem3 = root.findViewById(R.id.Graduation_Sem3);
                graduation_year3 = root.findViewById(R.id.Graduation_Year3);
                graduation_sem4 = root.findViewById(R.id.Graduation_Sem4);
                graduation_year4 = root.findViewById(R.id.Graduation_Year4);
                graduation_sem5 = root.findViewById(R.id.Graduation_Sem5);
                graduation_year5 = root.findViewById(R.id.Graduation_Year5);
                graduation_sem6 = root.findViewById(R.id.Graduation_Sem6);
                graduation_year6 = root.findViewById(R.id.Graduation_Year6);
                graduation_sem7 = root.findViewById(R.id.Graduation_Sem7);
                graduation_year7 = root.findViewById(R.id.Graduation_Year7);
                graduation_sem8 = root.findViewById(R.id.Graduation_Sem8);
                graduation_year8 = root.findViewById(R.id.Graduation_Year8);
                save = root.findViewById(R.id.Graduation_btn_Save);
                clear = root.findViewById(R.id.Graduation_btn_Clear);

                alert.setView(root);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        G_College = graduation_college.getText().toString();
                        Log.d("G_College", G_College);
                        G_Course = graduation_course.getText().toString();
                        Log.d("G_Course", G_Course);
                        G_Sem1 = graduation_sem1.getText().toString();
                        Log.d("G_Sem1", G_Sem1);
                        G_Year1 = graduation_year1.getText().toString();
                        Log.d("G_Year1", G_Year1);
                        G_Sem2 = graduation_sem2.getText().toString();
                        Log.d("G_Sem2", G_Sem2);
                        G_Year2 = graduation_year2.getText().toString();
                        Log.d("G_Year2", G_Year2);
                        G_Sem3 = graduation_sem3.getText().toString();
                        Log.d("G_Sem3", G_Sem3);
                        G_Year3 = graduation_year3.getText().toString();
                        Log.d("G_Year3", G_Year3);
                        G_Sem4 = graduation_sem4.getText().toString();
                        Log.d("G_Sem4", G_Sem4);
                        G_Year4 = graduation_year4.getText().toString();
                        Log.d("G_Year4", G_Year4);
                        G_Sem5 = graduation_sem5.getText().toString();
                        Log.d("G_Sem5", G_Sem5);
                        G_Year5 = graduation_year5.getText().toString();
                        Log.d("G_Year5", G_Year5);
                        G_Sem6 = graduation_sem6.getText().toString();
                        Log.d("G_Sem6", G_Sem6);
                        G_Year6 = graduation_year6.getText().toString();
                        Log.d("G_Year6", G_Year6);
                        G_Sem7 = graduation_sem7.getText().toString();
                        Log.d("G_Sem7", G_Sem7);
                        G_Year7 = graduation_year7.getText().toString();
                        Log.d("G_Year7", G_Year7);
                        G_Sem8 = graduation_sem8.getText().toString();
                        Log.d("G_Sem8", G_Sem8);
                        G_Year8 = graduation_year8.getText().toString();
                        Log.d("G_Year8", G_Year8);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = name.getText().toString();
                Number = number.getText().toString();
                Dob = dob.getText().toString();
                Log.d("DOB", Dob);
                Gender = gender.getText().toString();
                Email = email.getText().toString();
                Pass = pass.getText().toString();
                Confirm_Pass = confirm_pass.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(Email, Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(RegisterActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                        RegisterModel registerModel = new RegisterModel(Name, Number, Email, Pass);
                        firebaseDatabase.child("User").push().setValue(registerModel);
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //FOR DOB
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }

//    //Secondary School
//    @BindView(R.id.Secondary_School) EditText secondary_school;
//    @BindView(R.id.Secondary_Board) EditText secondary_board;
//    @BindView(R.id.Secondary_Percentage) EditText secondary_percentage;
//    @BindView(R.id.Secondary_Year) EditText secondary_year;
//    @BindView(R.id.Secondary_btn_Save) Button secondary_save;
//    @BindView(R.id.Secondary_btn_Clear) Button secondary_clear;
//
//    @OnClick(R.id.Secondary)
//    public void Secondaryclick()
//    {
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
//        View root = getLayoutInflater().inflate(R.layout.secondarydialogbox,null);
//        ButterKnife.bind(this,root);
//        alert.setView(root);
//
//        final AlertDialog alertDialog = alert.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//
//        secondary_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                S_School = secondary_school.getText().toString();
//                Log.d("school",S_School);
//                S_Board = secondary_board.getText().toString();
//                S_Percentage = secondary_percentage.getText().toString();
//                S_Year = secondary_year.getText().toString();
//                alertDialog.dismiss();
//            }
//        });
//
//        secondary_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.show();
//
//
//    }


}