package com.example.company.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.company.Model.Register_Model;
import com.example.company.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText email;
    EditText password;
    String Email,Password;
    Button btnlogin;
    Button btnsignup;
    SharedPreferences sharedpreferences;
    DatabaseReference databaseReference;
    String CompanyName,CompanyEmail,CompanyNumber,CompanyAddress,Companywebsite;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    //"(?=.*[0-9])" +         //at least 1 digit
//                    //"(?=.*[a-z])" +         //at least 1 lower case letter
//                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                    "(?=.*[a-zA-Z])" +      //any letter
//                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                    "(?=\\S+$)" +           //no white spaces
//                    ".{4,}" +               //at least 4 characters
//                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_login);
        email =findViewById(R.id.Login_email);
        password=findViewById(R.id.Login_password);
         btnlogin=findViewById(R.id.Login_submit);
         btnsignup=findViewById(R.id.Login_signup);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = email.getText().toString();
                Password=password.getText().toString();

            //    myEdit.commit();
                Password=password.getText().toString();

                if (!validateEmail()| !validatePassword() ) {
                    return;
                }
//                String input = "Email: " + email.getText().toString();
//                input += "\n";
//                input += "Password: " + password.getText().toString();
//                input += "\n";
//                Toast.makeText(getApplicationContext(), input, Toast.LENGTH_SHORT).show();

                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Query query = databaseReference.child("Company");
                        query.orderByChild("email").equalTo(Email).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.exists())
                                {
                                    for(DataSnapshot dataSnapshot :snapshot.getChildren())
                                    {
                                        Register_Model registerModel = dataSnapshot.getValue(Register_Model.class);
                                        CompanyName = registerModel.getName();
                                        CompanyEmail=registerModel.getEmail();
                                        CompanyNumber=registerModel.getNumber();
                                        CompanyAddress=registerModel.getAddress();
                                        Companywebsite=registerModel.getWebsite();



                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("E-mail", CompanyEmail);
                                        myEdit.putString("C-name", CompanyName);
                                        myEdit.putString("C-number", CompanyNumber);
                                        myEdit.putString("C-address", CompanyAddress);
                                        myEdit.putString("C-city", Companywebsite);
                                        myEdit.apply();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("Email",Email);
                                        Toast.makeText(LoginActivity.this, "login", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);

                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


            private boolean validateEmail() {
                Email = email.getText().toString().trim();
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
                Password = password.getText().toString().trim();
                if (Password.isEmpty()) {
                    password.setError("Field can't be empty");
                    return false;

                } else {
                    password.setError(null);
                    return true;
                }
            }

        });



        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}