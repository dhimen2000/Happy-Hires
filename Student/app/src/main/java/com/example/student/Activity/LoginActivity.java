package com.example.student.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.student.Model.RegisterModel;
import com.example.student.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

//    @BindView(R.id.Login_email) EditText email_login;
//    @BindView(R.id.Login_password) EditText pass_login;

    @BindView(R.id.Login_email)
    TextInputLayout email_login;
    @BindView(R.id.Login_password)
    TextInputLayout pass_login;

    @BindView(R.id.Login_submit) Button btnlogin;

    @BindView(R.id.Login_signup)
    TextView btnsignup;

    @BindView(R.id.Login_forget)
    TextView forget;

    String Email_login,Pass_login;
    private String Name;

    FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences preferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        try
        {
            edit.clear();
            edit.apply();
        }
        catch (Exception e)
        {
             Log.d("e",e.getMessage());
        }

        firebaseAuth = firebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword()) {
                    return;
                }

//                Email_login = email_login.getEditText().getText().toString();
//                Pass_login = pass_login.getEditText().getText().toString();

                firebaseAuth.signInWithEmailAndPassword(Email_login,Pass_login).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Query query = firebaseDatabase.child("Student").orderByChild("email").equalTo(Email_login);
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        RegisterModel model = dataSnapshot.getValue(RegisterModel.class);
                                        Name = model.getName();
                                    }
                                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                    edit.putString("Email_Login", Email_login);
                                    edit.putString("NAME", Name);
                                    edit.apply();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Data Not Find...", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

//                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                        if (user.isEmailVerified())
//                        {
//
//                        }else {
//                            Toast.makeText(LoginActivity.this, "Please Verify Your Email...", Toast.LENGTH_SHORT).show();
//                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EmailVerification.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateEmail() {
        Email_login = email_login.getEditText().getText().toString().trim();
        if (Email_login.isEmpty()) {
            email_login.setError("Field can't be empty");
            return false;
        } else {
            email_login.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        Pass_login = pass_login.getEditText().getText().toString().trim();
        if (Pass_login.isEmpty()) {
            pass_login.setError("Field can't be empty");
            return false;
        } else {
            pass_login.setError(null);
            return true;
        }
    }


    public void onBackPressed() {
        moveTaskToBack(true);
    }
}