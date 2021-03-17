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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailVerification extends AppCompatActivity {

    @BindView(R.id.Email)
    EditText email;
    @BindView(R.id.Pass)
    EditText pass;
    @BindView(R.id.Confirm_pass)
    EditText Confirm_pass;

    @BindView(R.id.Email_Verification)
    Button verify;

    String Email,Pass;

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
                Email = email.getText().toString();
                Pass = pass.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(EmailVerification.this, "Please check Your Email for verification", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor edit = sharedPreferences.edit();
                                    edit.putString("Email", Email);
                                    edit.putString("Password", Pass);
                                    Log.d("pass",Pass);
                                    edit.apply();
                                    startActivity(new Intent(EmailVerification.this,RegisterActivity.class));
                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EmailVerification.this, "Email Used...!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}