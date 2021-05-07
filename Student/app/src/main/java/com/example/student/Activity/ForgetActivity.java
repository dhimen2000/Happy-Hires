package com.example.student.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.student.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetActivity extends AppCompatActivity {
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

    @BindView(R.id.FP_Email)
    TextInputLayout fp_email;
    @BindView(R.id.FP_Pass)
    TextInputLayout fp_pass;
    @BindView(R.id.FP_Confirm_pass)
    TextInputLayout fp_confirm_pass;

    @BindView(R.id.password_back)
    ImageView fp_backbtn;

    @BindView(R.id.Forgetpasswordbtn)
    Button done;

    String FP_Email,FP_Pass,FP_Confirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validatePassword() | !validateConfirmPassword()) {
                    return;
                }

            }
        });


        fp_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetActivity.this, LoginActivity.class));
            }
        });
    }

    private boolean validateEmail() {
        FP_Email = fp_email.getEditText().getText().toString().trim();
        if (FP_Email.isEmpty()) {
            fp_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(FP_Email).matches()) {
            fp_email.setError("Please enter a valid email address");
            return false;
        } else {
            fp_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        FP_Pass = fp_pass.getEditText().getText().toString().trim();
        if (FP_Pass.isEmpty()) {
            fp_pass.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(FP_Pass).matches()) {
            fp_pass.setError("Password too weak");
            return false;
        } else {
            fp_pass.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        FP_Pass = fp_pass.getEditText().getText().toString().trim();
        FP_Confirm_pass = fp_confirm_pass.getEditText().getText().toString().trim();
        if (FP_Confirm_pass.isEmpty()) {
            fp_confirm_pass.setError("Field can't be empty");
            return false;
        } else if (!FP_Pass.equals(FP_Confirm_pass)) {
            fp_confirm_pass.setError("Password Not matching");
            return false;
        } else {
            fp_confirm_pass.setError(null);
            return true;
        }

    }

}