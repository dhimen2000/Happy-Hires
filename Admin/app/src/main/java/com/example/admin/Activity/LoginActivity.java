package com.example.admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.R;
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

    @BindView(R.id.Login_email)
    TextInputLayout email_login;
    @BindView(R.id.Login_password)
    TextInputLayout pass_login;

    @BindView(R.id.Login_submit)
    Button btnlogin;
//    @BindView(R.id.Login_signup)
//    Button btnsignup;

    String Email_login,Pass_login;

    FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = firebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword()) {
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(Email_login,Pass_login).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        btnsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
//                startActivity(intent);
//            }
//        });


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