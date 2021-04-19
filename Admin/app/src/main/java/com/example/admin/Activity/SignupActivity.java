package com.example.admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.Model.Register_Model;
import com.example.admin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    @BindView(R.id.Email)
    TextInputLayout email;
    @BindView(R.id.Pass)
    TextInputLayout pass;
    @BindView(R.id.Confirm_pass)
    TextInputLayout confirm_pass;

    @BindView(R.id.Submit)
    Button Submit;

    String Email,Pass,Confirm_pass;

    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword() | !validateConfirmPassword()) {
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        String key = firebaseDatabase.push().getKey();
                        Register_Model model = new Register_Model(Email, Pass);
                        firebaseDatabase.child("Admin").child(key).setValue(model);
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();


                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Toast.makeText(EmailVerification.this, "Email Used...!!", Toast.LENGTH_SHORT).show();
                        email.setError("Email Used...");
                    }
                });

            }
        });

    }

    private boolean validateEmail() {
        Email = email.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        Pass = pass.getEditText().getText().toString().trim();
        if (Pass.isEmpty()) {
            pass.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(Pass).matches()) {
            pass.setError("Password too weak");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword(){
        Pass = pass.getEditText().getText().toString().trim();
        Confirm_pass = confirm_pass.getEditText().getText().toString().trim();
        if(Confirm_pass.isEmpty())
        {
            confirm_pass.setError("Field can't be empty");
            return false;
        }
        else if(!Pass.equals(Confirm_pass)) {
            confirm_pass.setError("Password Not matching");
            return false;
        }
        else {
            pass.setError(null);
            return true;
        }

    }
}