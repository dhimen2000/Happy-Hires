package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity2 extends AppCompatActivity {


    @BindView(R.id.Secondary)
    TextView secondary;
    @BindView(R.id.HigherSecondary)
    TextView highersecondary;
    @BindView(R.id.Graduation)
    TextView graduation;
    @BindView(R.id.Register_uploadResume)
    Button uploadresume;
    @BindView(R.id.Register_uploadpdf)
    Button uploadpdf;
    @BindView(R.id.Register_Accademic_back)
    Button back;
    @BindView(R.id.Register_register)
    Button register;


    //Secondary Details
    String S_School, S_Board, S_Percentage, S_Year;

    //HigherSecondary Details
    String H_School, H_Board, H_Percentage, H_Year;

    //Graduation Details
    String G_College, G_Branch, G_EnrollmentNo, G_Sem1, G_Year1, G_Sem2, G_Year2, G_Sem3, G_Year3, G_Sem4, G_Year4, G_Sem5, G_Year5, G_Sem6, G_Year6, G_Sem7, G_Year7, G_Sem8, G_Year8;


    //For Resume & Pdf
    // Folder path for Firebase Storage.
    String Pdf_Storage_Path = "All_Pdf_Uploads/";
    // PDF request code for onActivityResult() .
    int Resume_Request_Code = 1;
    int Pdf_Request_Code = 2;
    // Creating URI.
    Uri ResumeFilepathUri;
    Uri ResumeDownloadUri;
    Uri PdfFilepathUri;
    Uri PdfDownloadUri;
    ProgressDialog progressDialog ;

    //Data
    SharedPreferences sharedPreferences;
    String Email, Pass, Name, Dob, Gender, Number, Address, ImageDownloadUri;

    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);

        sharedPreferences  = getSharedPreferences("SharedPreference",MODE_PRIVATE);
        Email = sharedPreferences.getString("Email","");
        Pass = sharedPreferences.getString("Password","");
        Name = sharedPreferences.getString("Name","");
        Dob = sharedPreferences.getString("Dob","");
        Gender = sharedPreferences.getString("Gender","");
        Number = sharedPreferences.getString("Number","");
        Address = sharedPreferences.getString("Address","");
        ImageDownloadUri = sharedPreferences.getString("ImageUrl","");

        firebaseAuth = firebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(RegisterActivity2.this);

        secondary.setOnClickListener(new View.OnClickListener() {

            EditText secondary_school,secondary_board,secondary_percentage,secondary_year;
            Button save,clear;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity2.this);
                View view = getLayoutInflater().inflate(R.layout.secondarydialogbox,null);

                secondary_school = view.findViewById(R.id.Secondary_School);
                secondary_board = view.findViewById(R.id.Secondary_Board);
                secondary_percentage = view.findViewById(R.id.Secondary_Percentage);
                secondary_year = view.findViewById(R.id.Secondary_Year);
                save = view.findViewById(R.id.Secondary_btn_Save);
                clear = view.findViewById(R.id.Secondary_btn_Clear);
                alert.setView(view);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        S_School = secondary_school.getText().toString();
                        Log.d("S_school",S_School);
                        S_Board = secondary_board.getText().toString();
                        Log.d("S_Board",S_Board);
                        S_Percentage = secondary_percentage.getText().toString();
                        Log.d("S_Percentage",S_Percentage);
                        S_Year = secondary_year.getText().toString();
                        Log.d("S_Year",S_Year);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        highersecondary.setOnClickListener(new View.OnClickListener() {

            EditText highersecondary_school, highersecondary_board, highersecondary_percentage, highersecondary_year;
            Button save, clear;

            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity2.this);
                View root = getLayoutInflater().inflate(R.layout.highersecondarydialogbox, null);

                highersecondary_school = root.findViewById(R.id.HigherSecondary_School);
                highersecondary_board = root.findViewById(R.id.HigherSecondary_Board);
                highersecondary_percentage = root.findViewById(R.id.HigherSecondary_Percentage);
                highersecondary_year = root.findViewById(R.id.HigherSecondary_Year);
                save = root.findViewById(R.id.HigherSecondary_btn_Save);
                clear = root.findViewById(R.id.HigherSecondary_btn_Clear);

                alert.setView(root);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        H_School = highersecondary_school.getText().toString();
                        Log.d("H_school", H_School);
                        H_Board = highersecondary_board.getText().toString();
                        Log.d("H_Board", H_Board);
                        H_Percentage = highersecondary_percentage.getText().toString();
                        Log.d("H_Percentage", H_Percentage);
                        H_Year = highersecondary_year.getText().toString();
                        Log.d("H_Year", H_Year);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        graduation.setOnClickListener(new View.OnClickListener() {

            EditText graduation_college, graduation_branch, graduation_enrollmentno;
            EditText graduation_sem1, graduation_year1, graduation_sem2, graduation_year2, graduation_sem3, graduation_year3, graduation_sem4, graduation_year4;
            EditText graduation_sem5, graduation_year5, graduation_sem6, graduation_year6, graduation_sem7, graduation_year7, graduation_sem8, graduation_year8;
            Button save, clear;

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity2.this);
                View root = getLayoutInflater().inflate(R.layout.graduationdialogbox, null);

                graduation_college = root.findViewById(R.id.Graduation_College);
                graduation_branch = root.findViewById(R.id.Graduation_Branch);
                graduation_enrollmentno = root.findViewById(R.id.Graduation_Enrollmentno);
                graduation_sem1 = root.findViewById(R.id.Graduation_Sem1);
                graduation_year1 = root.findViewById(R.id.Graduation_Year1);
                graduation_sem2 = root.findViewById(R.id.Graduation_Sem2);
                graduation_year2 = root.findViewById(R.id.Graduation_Year2);
                graduation_sem3 = root.findViewById(R.id.Graduation_Sem3);
                graduation_year3 = root.findViewById(R.id.Graduation_Year3);
                graduation_sem4 = root.findViewById(R.id.Graduation_Sem4);
                graduation_year4 = root.findViewById(R.id.Graduation_Year4);
                graduation_sem5 = root.findViewById(R.id.Graduation_Sem5);
                graduation_year5 = root.findViewById(R.id.Graduation_Year5);
                graduation_sem6 = root.findViewById(R.id.Graduation_Sem6);
                graduation_year6 = root.findViewById(R.id.Graduation_Year6);
                graduation_sem7 = root.findViewById(R.id.Graduation_Sem7);
                graduation_year7 = root.findViewById(R.id.Graduation_Year7);
                graduation_sem8 = root.findViewById(R.id.Graduation_Sem8);
                graduation_year8 = root.findViewById(R.id.Graduation_Year8);
                save = root.findViewById(R.id.Graduation_btn_Save);
                clear = root.findViewById(R.id.Graduation_btn_Clear);

                alert.setView(root);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        G_College = graduation_college.getText().toString();
                        Log.d("G_College", G_College);
                        G_Branch = graduation_branch.getText().toString();
                        Log.d("G_Course", G_Branch);
                        G_EnrollmentNo = graduation_enrollmentno.getText().toString();
                        Log.d("G_Enrollment No.", G_EnrollmentNo);
                        G_Sem1 = graduation_sem1.getText().toString();
                        Log.d("G_Sem1", G_Sem1);
                        G_Year1 = graduation_year1.getText().toString();
                        Log.d("G_Year1", G_Year1);
                        G_Sem2 = graduation_sem2.getText().toString();
                        Log.d("G_Sem2", G_Sem2);
                        G_Year2 = graduation_year2.getText().toString();
                        Log.d("G_Year2", G_Year2);
                        G_Sem3 = graduation_sem3.getText().toString();
                        Log.d("G_Sem3", G_Sem3);
                        G_Year3 = graduation_year3.getText().toString();
                        Log.d("G_Year3", G_Year3);
                        G_Sem4 = graduation_sem4.getText().toString();
                        Log.d("G_Sem4", G_Sem4);
                        G_Year4 = graduation_year4.getText().toString();
                        Log.d("G_Year4", G_Year4);
                        G_Sem5 = graduation_sem5.getText().toString();
                        Log.d("G_Sem5", G_Sem5);
                        G_Year5 = graduation_year5.getText().toString();
                        Log.d("G_Year5", G_Year5);
                        G_Sem6 = graduation_sem6.getText().toString();
                        Log.d("G_Sem6", G_Sem6);
                        G_Year6 = graduation_year6.getText().toString();
                        Log.d("G_Year6", G_Year6);
                        G_Sem7 = graduation_sem7.getText().toString();
                        Log.d("G_Sem7", G_Sem7);
                        G_Year7 = graduation_year7.getText().toString();
                        Log.d("G_Year7", G_Year7);
                        G_Sem8 = graduation_sem8.getText().toString();
                        Log.d("G_Sem8", G_Sem8);
                        G_Year8 = graduation_year8.getText().toString();
                        Log.d("G_Year8", G_Year8);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        uploadresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectResume();
            }
        });

        uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selectpdf();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDocument();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity2.this,RegisterActivity.class));
            }
        });

    }

    private void SelectResume()
    {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Pdf"), Resume_Request_Code);
    }

    private void Selectpdf()
    {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Pdf"), Pdf_Request_Code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //ChooseResume
        if (requestCode == Resume_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            ResumeFilepathUri = data.getData();
            if (data.getData() != null)
            {
                //uploading the file
                Toast.makeText(this, "Resume Selected...", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Resume Not Selected...", Toast.LENGTH_SHORT).show();
            }
        }
        //ChoosePdf
        else
        {
            PdfFilepathUri = data.getData();
            if (data.getData() != null)
            {
                //uploading the file
                Toast.makeText(this, "File 2 Selected", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Creating Method to get the selected file Extension from File Path URI.
    private String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }

    // Creating UploadDocument method to upload documents on storage.
    private void UploadDocument() {
        if ( ResumeFilepathUri != null && PdfFilepathUri != null )
        {

            // Setting progressDialog Title.
            progressDialog.setTitle("Documents are Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating StorageReference.
            StorageReference storageReference1 = storageReference.child(Pdf_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(ResumeFilepathUri));

            // Creating second StorageReference.
            StorageReference storageReference2 = storageReference.child(Pdf_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(PdfFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2.putFile(PdfFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    PdfDownloadUri = uri;
                                    Toast.makeText(getApplicationContext(), "Pdf Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(RegisterActivity2.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(ResumeFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    ResumeDownloadUri = uri;
                                    Log.d("uri",ResumeDownloadUri.toString());

                                    progressDialog.dismiss();

                                    // Showing toast message after done uploading.
                                    String key =firebaseDatabase.push().getKey();
                                    @SuppressWarnings("VisibleForTests")
                                   RegisterModel model = new RegisterModel(Email,Pass,Name,Dob,Gender,Number,Address,
                                            ImageDownloadUri.toString(),S_School,
                                            S_Board,S_Percentage,S_Year,H_School,H_Board,H_Percentage,H_Year,G_College,G_Branch,
                                            G_EnrollmentNo, G_Sem1,G_Year1,
                                            G_Sem2,G_Year2,G_Sem3,G_Year3,G_Sem4,G_Year4,G_Sem5,G_Year5,G_Sem6,G_Year6,G_Sem7,
                                            G_Year7,G_Sem8,G_Year8,
                                            key,ResumeDownloadUri.toString(),PdfDownloadUri.toString());

//                                    Log.d("email",Email);
//                                    Log.d("email",Pass); Log.d("email",Name);
//                                    Log.d("email",Dob);
//                                    Log.d("email",Gender);
//                                    Log.d("email",Number);
//                                    Log.d("email",Address);
//                                    Log.d("email",ImageDownloadUri.toString());
//                                    Log.d("email",S_School);
//                                    Log.d("email",S_Board);
//                                    Log.d("email",S_Percentage);
//                                    Log.d("email",S_Year);
//                                    Log.d("email",H_School); Log.d("email",H_Board);
//                                    Log.d("email",H_Board);
//                                    Log.d("email",H_Percentage);
//                                    Log.d("email",H_Year);
//                                    Log.d("email",G_College); Log.d("email",G_Branch);
//                                    Log.d("email",G_EnrollmentNo);
//                                    Log.d("email",G_Sem1);
//                                    Log.d("email",G_Year1);
//                                    Log.d("email",G_Sem2); Log.d("email",G_Year2); Log.d("email",G_Sem3);
//                                    Log.d("email",G_Year3);
//                                    Log.d("email",G_Sem4);
//                                    Log.d("email",G_Year4);
//                                    Log.d("email",G_Sem5); Log.d("email",G_Year5);
//                                    Log.d("email",G_Sem6); Log.d("email",G_Year6); Log.d("email",G_Sem7);
//                                    Log.d("email",G_Year7); Log.d("email",G_Sem8); Log.d("email",G_Year8); Log.d("email",ResumeDownloadUri.toString()+PdfDownloadUri.toString());
//
//
//












                                    // Adding image upload id s child element into databaseReference.
                                    firebaseDatabase.child("Student").child(key).setValue(model);

                                    Toast.makeText(getApplicationContext(), "Resume Uploaded Successfully ", Toast.LENGTH_LONG).show();



                                    finish();
                                    Log.d("email",Email);
//                                    Log.d("email",Pass); Log.d("email",Name);
//                                    Log.d("email",Dob);
//                                    Log.d("email",Gender);
//                                    Log.d("email",Number);
//                                    Log.d("email",Address);
//                                    Log.d("email",ImageDownloadUri.toString());
//                                    Log.d("email",S_School);
//                                    Log.d("email",S_Board);
//                                    Log.d("email",S_Percentage);
//                                    Log.d("email",S_Year);
//                                    Log.d("email",H_School); Log.d("email",H_Board);
//                                    Log.d("email",H_Board);
//                                    Log.d("email",H_Percentage);
//                                    Log.d("email",H_Year);
//                                    Log.d("email",G_College); Log.d("email",G_Branch);
//                                    Log.d("email",G_EnrollmentNo);
//                                    Log.d("email",G_Sem1);
//                                    Log.d("email",G_Year1);
//                                    Log.d("email",G_Sem2); Log.d("email",G_Year2); Log.d("email",G_Sem3);
//                                    Log.d("email",G_Year3);
//                                    Log.d("email",G_Sem4);
//                                    Log.d("email",G_Year4);
//                                    Log.d("email",G_Sem5); Log.d("email",G_Year5);
//                                    Log.d("email",G_Sem6); Log.d("email",G_Year6); Log.d("email",G_Sem7);
//                                    Log.d("email",G_Year7); Log.d("email",G_Sem8); Log.d("email",G_Year8); Log.d("email",ResumeDownloadUri.toString()+PdfDownloadUri.toString());
//



                                    startActivity(new Intent(RegisterActivity2.this,LoginActivity.class));
                                    Log.d("success","success");
                                }
                            });
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(RegisterActivity2.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setTitle("Uploading Documents " + ((int) progress) + "%...");
                        }
                    });
        }
        else {
            Toast.makeText(RegisterActivity2.this, "Please Select Both Documents...", Toast.LENGTH_LONG).show();
        }
    }
}