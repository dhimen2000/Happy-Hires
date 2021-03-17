package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.Login_email) EditText email_login;
    @BindView(R.id.Login_password) EditText pass_login;

    @BindView(R.id.Login_submit) Button btnlogin;
    @BindView(R.id.Login_signup) Button btnsignup;

    String Email_login,Pass_login;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        try {
            edit.clear();
            edit.apply();
            String Email = preferences.getString("Email","");
            Log.d("error",Email);
            Log.d("error", "error");
        }catch (Exception e)
        {
         Log.d("e",e.getMessage());
        }
//        email_login = findViewById(R.id.Login_email);
//        pass_login = findViewById(R.id.Login_password);
//        btnlogin = findViewById(R.id.Login_submit);
//        btnsignup = findViewById(R.id.Login_signup);

        firebaseAuth = firebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email_login = email_login.getText().toString();
                Pass_login = pass_login.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(Email_login,Pass_login).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        edit.putString("Email_Login", Email_login);
                        edit.apply();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
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

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,EmailVerification.class);
                startActivity(intent);
            }
        });
    }
}