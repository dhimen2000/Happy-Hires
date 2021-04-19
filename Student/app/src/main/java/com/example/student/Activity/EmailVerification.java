package com.example.student.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.student.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailVerification extends AppCompatActivity {

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

//    @BindView(R.id.Email)
//    EditText email;

    @BindView(R.id.Email)
    TextInputLayout email;
    @BindView(R.id.Pass)
    TextInputLayout pass;
    @BindView(R.id.Confirm_pass)
    TextInputLayout confirm_pass;

    @BindView(R.id.Email_Verification_back)
    ImageView backbtn;

//    @BindView(R.id.Pass)
//    EditText pass;
//    @BindView(R.id.Confirm_pass)
//    EditText Confirm_pass;

    @BindView(R.id.Email_Verification)
    Button verify;

    String Email,Pass,Confirm_pass;

    FirebaseAuth firebaseAuth;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("SharedPreference",MODE_PRIVATE);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword() | !validateConfirmPassword()) {
                    return;
                }
//                else {
//                    String input = "Email: " + email.getEditText().getText().toString();
//                    input += "\n";
//                    input += "Password: " + pass.getEditText().getText().toString();
//                    input += "\n";
//                    input += "Confirm Password: " + confirm_pass.getEditText().getText().toString();
//                    Toast.makeText(EmailVerification.this, input, Toast.LENGTH_SHORT).show();
//                }

//                Email = email.getEditText().getText().toString();
//                Pass = pass.getEditText().getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(EmailVerification.this, "Please check Your Email for verification & Complete Profile", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor edit = sharedPreferences.edit();
                                    edit.putString("Email", Email);
                                    edit.putString("Password", Pass);
                                    Log.d("pass",Pass);
                                    edit.apply();
                                    startActivity(new Intent(EmailVerification.this, RegisterActivity.class));
                                }
                            }
                        });
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
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailVerification.this, LoginActivity.class));
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