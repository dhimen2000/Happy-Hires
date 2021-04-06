package com.example.company.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.company.Model.Job_Model;
import com.example.company.R;
import com.example.company.Model.Register_Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_jobs extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    StorageReference storageReference;
    SharedPreferences sharedPreferences;


    String Pdf_Storage_Path = "All_Pdf_Uploads_Jobs/";
    int Pdf_Request_Code = 1;
    Uri PdfFilepathUri;
    Uri PdfDownloadUri;
    ProgressDialog progressDialog;


    Button save,clear;
String new_companyemail;
String new_companyname;
String companyemail;
String companyname;
String company_email;
String company_name;
String jobtitle;
String requirements;
String percentagecriteria;
String salaryrange;
String joblastdate;
String jobattachmenturl;
String jobid;
EditText Companyemail,Companyname,Jobtitle,Requirements,Percentagecriteria,Salaryrange,Joblastdate;
Button Jobattachmenturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_add_jobs);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        company_email = sh.getString("E-mail", "");
        company_name = sh.getString("C-name", "");

        progressDialog = new ProgressDialog(Add_jobs.this);


        Companyemail=findViewById(R.id.companyemail);
        Companyname=findViewById(R.id.companyname);
        Jobtitle=findViewById(R.id.jobtitle);
        Requirements=findViewById(R.id.requirements);
        Percentagecriteria=findViewById(R.id.percentagecriteria);
        Salaryrange=findViewById(R.id.salaryrange);
        Joblastdate=findViewById(R.id.joblastdate);
        Jobattachmenturl=findViewById(R.id.jobattachmenturl);


        save=findViewById(R.id.save_job);
        clear=findViewById(R.id.clear_job);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        storageReference = FirebaseStorage.getInstance().getReference();


        Query query = firebaseDatabase.child("Company").orderByChild("email").equalTo(company_email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        Register_Model model = dataSnapshot.getValue(Register_Model.class);
                        companyname = model.getName();
                        companyemail = model.getEmail();
                    }
                    Companyemail.setText(company_email);
                    Companyname.setText(company_name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Jobattachmenturl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selectpdf();
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == clear){

                      //Companyemail.setText("");
                      //Companyname.setText("");
                      Jobtitle.setText("");
                      Requirements.setText("");
                      Percentagecriteria.setText("");
                      Salaryrange.setText("");
                      Joblastdate.setText("");
                }
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new_companyname = Companyname.getText().toString();
                new_companyemail = Companyemail.getText().toString();
                jobtitle=Jobtitle.getText().toString();
                requirements=Requirements.getText().toString();
                percentagecriteria=Percentagecriteria.getText().toString();
                salaryrange=Salaryrange.getText().toString();
                joblastdate=Joblastdate.getText().toString();

                jobid = firebaseDatabase.push().getKey();
               Job_Model jobModel= new Job_Model(new_companyemail,new_companyname,jobtitle,joblastdate,salaryrange,percentagecriteria,requirements,PdfDownloadUri.toString(),jobid);
                firebaseDatabase.child("JobsAvailable").child(jobid).setValue(jobModel);

                Toast.makeText(Add_jobs.this, "success", Toast.LENGTH_SHORT).show();

               // Intent back = new Intent(Add_jobs.this,JobFragment.class);
                startActivity(getIntent());
                //startActivity(back);
                finish();
            }
        });

    }

    private void Selectpdf() {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Pdf"), Pdf_Request_Code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //ChooseResume
        if (requestCode == Pdf_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            PdfFilepathUri = data.getData();
            if (data.getData() != null) {
                //uploading the file
                Toast.makeText(this, "File Selected", Toast.LENGTH_SHORT).show();
                PdfUpload();
            } else {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Creating Method to get the selected file Extension from File Path URI.
    private String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void PdfUpload()
    {
        if ( PdfFilepathUri != null ) {
            // Setting progressDialog Title.
            progressDialog.setTitle("Pdf Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference1 = storageReference.child(Pdf_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(PdfFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(PdfFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    PdfDownloadUri = uri;
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Pdf Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(Add_jobs.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setTitle("Uploading Pdf... " + ((int) progress) + "%...");
                        }
                    });
        } else
        {
            Toast.makeText(Add_jobs.this, "Please Select File...", Toast.LENGTH_LONG).show();
        }
    }

}