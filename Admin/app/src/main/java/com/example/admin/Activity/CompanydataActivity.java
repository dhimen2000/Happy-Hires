package com.example.admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.example.admin.R;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompanydataActivity extends AppCompatActivity {

    @BindView(R.id.Company_profilepic)
    CircleImageView companyprofileimage;
    @BindView(R.id.Company_name)
    TextInputLayout companyname;
    @BindView(R.id.Company_email)
    TextInputLayout companyemail;
    @BindView(R.id.Company_number)
    TextInputLayout companynumber;
    @BindView(R.id.Company_website)
    TextInputLayout companywebsite;
    @BindView(R.id.Company_address)
    TextInputLayout comapnyaddress;
    @BindView(R.id.Company_attachment)
    Button companyattachment;

    String Companyname,Companyemail,Companynumber,Companywebsite,Comapnyaddress,Companyattachment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companydata);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        Glide.with(getApplicationContext()).load(intent.getStringExtra("Image")).into(companyprofileimage);
        Companyname = intent.getStringExtra("Name");
        Companyemail = intent.getStringExtra("Email");
        Companynumber = intent.getStringExtra("Number");
        Companywebsite = intent.getStringExtra("Website");
        Comapnyaddress = intent.getStringExtra("Address");
        Companyattachment = intent.getStringExtra("Attachment");

        companyname.getEditText().setText(Companyname);
        companyemail.getEditText().setText(Companyemail);
        companynumber.getEditText().setText(Companynumber);
        companywebsite.getEditText().setText(Companywebsite);
        comapnyaddress.getEditText().setText(Comapnyaddress);

        companyattachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CompanydataActivity.this, CompanyattachmentActivity.class);
                intent1.putExtra("CompanyAttachment",Companyattachment);
                startActivity(intent1);
            }
        });


    }
}