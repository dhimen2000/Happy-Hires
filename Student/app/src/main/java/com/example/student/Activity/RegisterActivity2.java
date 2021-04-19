package com.example.student.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.student.R;

import butterknife.ButterKnife;

public class RegisterActivity2 extends AppCompatActivity {

//
//    //Secondary Details
//    String S_School, S_Board, S_Percentage, S_Year;
//    TextInputLayout secondary_school, secondary_board, secondary_percentage, secondary_year;
//
//    //HigherSecondary Details
//    String H_School, H_Board, H_Percentage, H_Year;
//    TextInputLayout highersecondary_school, highersecondary_board, highersecondary_percentage, highersecondary_year;
//
//    //Graduation Details
//    String G_College, G_Branch, G_EnrollmentNo, G_Sem1, G_Year1, G_Sem2, G_Year2, G_Sem3, G_Year3, G_Sem4, G_Year4, G_Sem5, G_Year5, G_Sem6, G_Year6, G_Sem7, G_Year7, G_Sem8, G_Year8;
//    TextInputLayout graduation_college, graduation_branch, graduation_enrollmentno;
//    TextInputLayout graduation_sem1, graduation_year1;
//
//    //For Resume & Pdf
//    // Folder path for Firebase Storage.
//    String Pdf_Storage_Path = "All_Pdf_Uploads/";
//    // PDF request code for onActivityResult() .
//    int Resume_Request_Code = 1;
//    int Pdf_Request_Code = 2;
//    // Creating URI.
//    Uri ResumeFilepathUri;
//    Uri ResumeDownloadUri;
//    Uri PdfFilepathUri;
//    Uri PdfDownloadUri;
//    ProgressDialog progressDialog;
//
//    //Data
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor edit;
//    String Email, Pass, Name, Dob, Gender, Number, Address, ImageDownloadUri,
//
//    FirebaseAuth firebaseAuth;
//    StorageReference storageReference;
//    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);

//        Status = "Active";
//
//        sharedPreferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
//        edit = sharedPreferences.edit();
//
//        try{
//            sharedPreferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
//            Email = sharedPreferences.getString("Email", "");
//            Pass = sharedPreferences.getString("Password", "");
//            Name = sharedPreferences.getString("Name", "");
//            Dob = sharedPreferences.getString("Dob", "");
//            Gender = sharedPreferences.getString("Gender", "");
//            Number = sharedPreferences.getString("Number", "");
//            Address = sharedPreferences.getString("Address", "");
//            ImageDownloadUri = sharedPreferences.getString("ImageUrl", "");
//
//            S_School = sharedPreferences.getString("S_School","");
//            secondary_school.getEditText().setText(S_School);
//
//        }catch (Exception e)
//        {
//
//        }
//
//
//        firebaseAuth = firebaseAuth.getInstance();
//
//        storageReference = FirebaseStorage.getInstance().getReference();
//
//        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
//
//        progressDialog = new ProgressDialog(RegisterActivity2.this);
//
//
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        //        UploadDocument();
//
//                if (!validateResume() | !validatePdf() ) {
//                    return;
//                }
//
//
//            }
//        });
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity2.this, RegisterActivity.class));
//            }
//        });
//
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //ChooseResume
//
//    }
//
//    // Creating Method to get the selected file Extension from File Path URI.
//    private String GetFileExtension(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        // Returning the file Extension.
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//
//
//
//


    }
}