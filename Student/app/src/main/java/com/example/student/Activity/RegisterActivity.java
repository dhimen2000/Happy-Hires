package com.example.student.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.student.Model.RegisterModel;
import com.example.student.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern Percentage_PATTERN =
            Pattern.compile("\\b(?<!\\.)(?!0+(?:\\.0+)?%)(?:\\d|[1-9]\\d|100)(?:(?<!100)\\.\\d+)?[%]?");

    //Basic Details
    //ImageView
    @BindView(R.id.profile_image)
    CircleImageView profilepic;
    @BindView(R.id.profilePictureButton)
    ImageView profilepicbtn;
    @BindView(R.id.Register_back)
    ImageView backimage;

    //TextInputLayout
    @BindView(R.id.Register_name)
    TextInputLayout name;
    @BindView(R.id.Register_Dob)
    TextInputLayout dob;
    //Radiogroup & Button
    @BindView(R.id.Register_radiogroup)
    RadioGroup radioGroup;
    @BindView(R.id.radio_male)
    RadioButton rbmale;
    @BindView(R.id.radio_female)
    RadioButton rbFemale;
    //TextInputLayout
    @BindView(R.id.Register_number)
    TextInputLayout number;
    @BindView(R.id.Register_Address)
    TextInputLayout address;

    //TextView
    @BindView(R.id.Secondary)
    Button secondary;
    @BindView(R.id.HigherSecondary)
    Button highersecondary;
    @BindView(R.id.Graduation)
    Button graduation;

    //Buttons
    @BindView(R.id.Register_uploadResume)
    Button uploadresume;
    @BindView(R.id.Register_uploadpdf)
    Button uploadpdf;
    @BindView(R.id.Register_register)
    Button register;

    //For Image
    // Folder path for Firebase Storage.
    String Image_Storage_Path = "All_Image_Uploads/";
    // Image request code for onActivityResult().
    int Image_Request_Code = 7;
    // Creating URI.
    Uri ImageFilepathUri;
    Uri ImageDownloadUri;

    //Basic Details
    String Name, Dob, Gender, Number, Address;
    //Branch, Email, Pass, Confirm_Pass;
    String Email, Pass;

    String Status;;


    //Secondary Details
    String S_School, S_Board, S_Percentage, S_Year;
    TextInputLayout secondary_school, secondary_board, secondary_percentage, secondary_year;

    //HigherSecondary Details
    String H_School, H_Board, H_Percentage, H_Year;
    TextInputLayout highersecondary_school, highersecondary_board, highersecondary_percentage, highersecondary_year;

    //Graduation Details
    String G_College, G_Branch, G_EnrollmentNo, G_Sem1, G_Year1, G_Sem2, G_Year2, G_Sem3, G_Year3, G_Sem4, G_Year4, G_Sem5, G_Year5, G_Sem6, G_Year6, G_Sem7, G_Year7, G_Sem8, G_Year8;
    TextInputLayout graduation_college, graduation_branch, graduation_enrollmentno;


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

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;

    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    ProgressDialog progressDialog;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Status = "Active";

        firebaseAuth = firebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(RegisterActivity.this);


//        sharedPreferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
//        edit = sharedPreferences.edit();

        try{

            sharedPreferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
            Email = sharedPreferences.getString("Email", "");
            Pass = sharedPreferences.getString("Password", "");

        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
//
////        try {
////            sharedPreferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
////            Email = sharedPreferences.getString("Email", "");
////            Pass = sharedPreferences.getString("Password", "");
////            Name = sharedPreferences.getString("Name", "");
////            Dob = sharedPreferences.getString("Dob", "");
////            Gender = sharedPreferences.getString("Gender", "");
////            Number = sharedPreferences.getString("Number", "");
////            Address = sharedPreferences.getString("Address", "");
////            Imageuri = sharedPreferences.getString("ImageUrl", "");
////
////                try {
////                    if( Imageuri.equals("") ) {
////                        profilepic.setImageResource(R.drawable.image);
////                    }
////                    else {
////                        Glide.with(getApplicationContext()).load(Imageuri).into(profilepic);
////                    }
////                } catch (Exception e) {
////                    Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
////                }
////
////            if(Gender.equals("Male"))
////            {
////                rbmale.setChecked(true);
////            }
////            else //if(Gender == "Female")
////            {
////                rbFemale.setChecked(true);
////            }
//////            else {
//////
//////            }
//
//
//            name.getEditText().setText(Name);
//            dob.getEditText().setText(Dob);
//            number.getEditText().setText(Number);
//            address.getEditText().setText(Address);
//
//
////        }catch (Exception e)
////            {
////        }


        profilepicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selectimage();
            }
        });

        //For DOB
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dob.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("Helllo","Hello");
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_male:
                        Gender = rbmale.getText().toString();
                        break;
                    case R.id.radio_female:
                        Gender = rbFemale.getText().toString();
                        break;
                }
            }
        });

        secondary.setOnClickListener(new View.OnClickListener() {

            Button save, clear;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                View view = getLayoutInflater().inflate(R.layout.secondarydialogbox, null);

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

                        if (!s_school() | !s_board() | !s_percentage() | !s_year()) {
                            return;
                        }

                        S_School = secondary_school.getEditText().getText().toString();
                        S_Board = secondary_board.getEditText().getText().toString();
                        S_Percentage = secondary_percentage.getEditText().getText().toString();
                        S_Year = secondary_year.getEditText().getText().toString();
//
//                        edit.putString("S_School",S_School);
//                        edit.putString("S_Board",S_Board);
//                        edit.putString("S_Percentage",S_Percentage);
//                        edit.apply();

                        Log.d("S_school", S_School);
                        Log.d("S_Board", S_Board);
                        Log.d("S_Percentage", S_Percentage);
                        Log.d("S_Year", S_Year);

                        alertDialog.dismiss();
                    }

                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(v == clear){
                            secondary_school.getEditText().setText("");
                            secondary_board.getEditText().setText("");
                            secondary_percentage.getEditText().setText("");
                            secondary_year.getEditText().setText("");
                        }
                        //alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        highersecondary.setOnClickListener(new View.OnClickListener() {

            Button save, clear;

            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
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

                        if (!h_school() | !h_board() | !h_percentage() | !h_year()) {
                            return;
                        }

                        H_School = highersecondary_school.getEditText().getText().toString();
                        Log.d("H_school", H_School);
                        H_Board = highersecondary_board.getEditText().getText().toString();
                        Log.d("H_Board", H_Board);
                        H_Percentage = highersecondary_percentage.getEditText().getText().toString();
                        Log.d("H_Percentage", H_Percentage);
                        H_Year = highersecondary_year.getEditText().getText().toString();
                        Log.d("H_Year", H_Year);
                        alertDialog.dismiss();
                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(v == clear){
                            highersecondary_school.getEditText().setText("");
                            highersecondary_board.getEditText().setText("");
                            highersecondary_percentage.getEditText().setText("");
                            highersecondary_year.getEditText().setText("");
                        }
                        //alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        graduation.setOnClickListener(new View.OnClickListener() {

            EditText graduation_sem1, graduation_year1,graduation_sem2, graduation_year2, graduation_sem3, graduation_year3, graduation_sem4, graduation_year4;
            EditText graduation_sem5, graduation_year5, graduation_sem6, graduation_year6, graduation_sem7, graduation_year7, graduation_sem8, graduation_year8;
            Button save, clear;

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
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

                        G_College = graduation_college.getEditText().getText().toString();
                        Log.d("G_College", G_College);
                        G_Branch = graduation_branch.getEditText().getText().toString();
                        Log.d("G_Course", G_Branch);
                        G_EnrollmentNo = graduation_enrollmentno.getEditText().getText().toString();
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

                if (!validateImage() | !validateName() | !validateDate() | !validateGender() | !validateNumber() | !validateAddress() | !validateResume() | !validatePdf()) {
                    return;
                }

                Name = name.getEditText().getText().toString();
                Dob = dob.getEditText().getText().toString();
                Number = number.getEditText().getText().toString();
                Address = address.getEditText().getText().toString();


                String key = firebaseDatabase.push().getKey();
                RegisterModel model = new RegisterModel(Email, Pass, Name, Dob, Gender, Number, Address, ImageDownloadUri.toString(), S_School, S_Board, S_Percentage,
                        S_Year, H_School, H_Board, H_Percentage, H_Year, G_College, G_Branch, G_EnrollmentNo, G_Sem1, G_Year1,
                        G_Sem2, G_Year2, G_Sem3, G_Year3, G_Sem4, G_Year4, G_Sem5, G_Year5, G_Sem6, G_Year6, G_Sem7,
                        G_Year7, G_Sem8, G_Year8, key, ResumeDownloadUri.toString(), PdfDownloadUri.toString(),Status);

                firebaseDatabase.child("Student").child(key).setValue(model);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

//                edit.putString("Name", Name);
//                edit.putString("Dob", Dob);
//                edit.putString("Gender", Gender);
//                edit.putString("Number", Number);
//                edit.putString("Address", Address);
//                edit.putString("ImageUrl", ImageDownloadUri.toString());
//                edit.apply();


//                Log.d("Name", Name);
//                Log.d("DOB", Dob);
//                Log.d("Gender", Gender);
//                Log.d("Number", Number);
//                Log.d("Address", Address);
//                Log.d("uri",ImageDownloadUri.toString());

            //    startActivity(new Intent(RegisterActivity.this, RegisterActivity2.class));
            }
        });

        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    //FOR DOB
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.getEditText().setText(sdf.format(myCalendar.getTime()));
    }

    //For UploadImage
    private void Selectimage() {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
    }

    private void SelectResume() {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Resume"), Resume_Request_Code);
    }

    private void Selectpdf() {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Pdf"), Pdf_Request_Code);
    }

    //chooseimage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //chooseimage
        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            ImageFilepathUri = data.getData();
            try {
                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageFilepathUri);
                // Setting up bitmap selected image into ImageView.
                profilepic.setImageBitmap(bitmap);
                // After selecting image print Toast.
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
                // After selecting image change choose button above text.
                //ChooseImage.setText("Image Selected");
                UploadImage();
            } catch (IOException e) {
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Resume_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            ResumeFilepathUri = data.getData();
            if (data.getData() != null) {
                //uploading the file
                Toast.makeText(this, "Resume Selected...", Toast.LENGTH_SHORT).show();
                Resumeupload();
            } else {
                Toast.makeText(this, "Resume Not Selected...", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Pdf_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            PdfFilepathUri = data.getData();
            if (data.getData() != null) {
                //uploading the file
                Toast.makeText(this, "File 2 Selected", Toast.LENGTH_SHORT).show();
                PdfUpload();
            } else {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            }catch(Exception e)
            {
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void UploadImage()
    {
        if (ImageFilepathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            // Creating second StorageReference.
            StorageReference storageReference1 = storageReference.child(Image_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(ImageFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(ImageFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    ImageDownloadUri = uri;

                                    progressDialog.dismiss();

                                    // Showing toast message after done uploading.
                                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

//                                    @SuppressWarnings("VisibleForTests")
//                                    RegisterModel model = new RegisterModel(Name, Dob, Gender, Number, Address, ImageDownloadUri.toString());
                                    // Adding image upload id s child element into databaseReference.
//                                    firebaseDatabase.child("User").push().setValue(model);

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
                            Toast.makeText(RegisterActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setTitle("Uploading Image " + ((int) progress) + "%...");
                        }
                    });
        } else {
            Toast.makeText(RegisterActivity.this, "Please Select Image...", Toast.LENGTH_LONG).show();
        }
    }

    private void Resumeupload()
    {
        if ( ResumeFilepathUri != null ) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Resume Uploading...");

            // Showing progressDialog.
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            // Creating StorageReference.
            StorageReference storageReference1 = storageReference.child(Pdf_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(ResumeFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(ResumeFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ResumeDownloadUri = uri;
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Resume Uploaded Successfully ", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(RegisterActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setTitle("Uploading Resume... " + ((int) progress) + "%...");
                        }
                    });
        } else
        {
            Toast.makeText(RegisterActivity.this, "Please Select Resume...", Toast.LENGTH_LONG).show();
        }
    }

    private void PdfUpload()
    {
        if ( PdfFilepathUri != null ) {
            // Setting progressDialog Title.
            progressDialog.setTitle("Pdf Uploading...");

            // Showing progressDialog.
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

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
                            Toast.makeText(RegisterActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(RegisterActivity.this, "Please Select Resume...", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateImage() {
        if (ImageFilepathUri == null) {
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateDate() {
        Dob = dob.getEditText().getText().toString().trim();
        if (Dob.isEmpty()) {
            dob.setError("Field can't be empty");
            return false;
        } else {
            dob.setError(null);
            return true;
        }
    }
    private boolean validateGender() {
        if(Gender == null)
        {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateName() {
        Name = name.getEditText().getText().toString().trim();
        if (Name.isEmpty()) {
            name.setError("Field can't be empty");
            return false;
        } else if (Name.length() > 15) {
            name.setError("Name too long");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }
    private boolean validateNumber() {
        Number = number.getEditText().getText().toString().trim();
        if (Number.isEmpty()) {
            number.setError("Field can't be empty");
            return false;
        } else if (Number.length() > 10) {
            number.setError("Number too long");
            return false;
        } else if (Number.length() < 10) {
            number.setError("Number too Short");
            return false;
        } else {
            number.setError(null);
            return true;
        }
    }
    private boolean validateAddress() {
        Address = address.getEditText().getText().toString().trim();
        if (Address.isEmpty()) {
            address.setError("Field can't be empty");
            return false;
        } else if (Address.length() > 50) {
            address.setError("Address too long");
            return false;
        } else {
            address.setError(null);
            return true;
        }
    }

    private boolean s_school() {
        S_School = secondary_school.getEditText().getText().toString().trim();
        if (S_School.isEmpty()) {
            secondary_school.setError("Field can't be empty");
            return false;
        } else if (S_School.length() > 25) {
            secondary_school.setError("School Name too long");
            return false;
        } else {
            secondary_school.setError(null);
            return true;
        }
    }
    private boolean s_board() {
        S_Board = secondary_board.getEditText().getText().toString().trim();
        if (S_Board.isEmpty()) {
            secondary_board.setError("Field can't be empty");
            return false;
        } else if (S_Board.length() > 10) {
            secondary_board.setError("Board Name too long");
            return false;
        } else {
            secondary_board.setError(null);
            return true;
        }
    }
    private boolean s_percentage() {
        S_Percentage = secondary_percentage.getEditText().getText().toString().trim();
        if (S_Percentage.isEmpty()) {
            secondary_percentage.setError("Field can't be empty");
            return false;
        } else if (!Percentage_PATTERN.matcher(S_Percentage).matches()) {
            secondary_percentage.setError("Please Enter Valid Percentage");
            return false;
        } else {
            secondary_percentage.setError(null);
            return true;
        }
    }
    private boolean s_year() {
        S_Year = secondary_year.getEditText().getText().toString().trim();
        if (S_Year.isEmpty()) {
            secondary_year.setError("Field can't be empty");
            return false;
        } else if (S_Year.length() > 4 ) {
            secondary_year.setError("Please Enter Valid Year");
            return false;
        } else {
            secondary_year.setError(null);
            return true;
        }
    }

    private boolean h_school() {
        H_School = highersecondary_school.getEditText().getText().toString().trim();
        if (H_School.isEmpty()) {
            highersecondary_school.setError("Field can't be empty");
            return false;
        } else if (H_School.length() > 25) {
            highersecondary_school.setError("School Name too long");
            return false;
        } else {
            highersecondary_school.setError(null);
            return true;
        }
    }
    private boolean h_board() {
        H_Board = highersecondary_board.getEditText().getText().toString().trim();
        if (H_Board.isEmpty()) {
            highersecondary_board.setError("Field can't be empty");
            return false;
        } else if (H_Board.length() > 10) {
            highersecondary_board.setError("Board Name too long");
            return false;
        } else {
            highersecondary_board.setError(null);
            return true;
        }
    }
    private boolean h_percentage() {
        H_Percentage = highersecondary_percentage.getEditText().getText().toString().trim();
        if (H_Percentage.isEmpty()) {
            highersecondary_percentage.setError("Field can't be empty");
            return false;
        } else if (!Percentage_PATTERN.matcher(H_Percentage).matches()) {
            highersecondary_percentage.setError("Please Enter Valid Percentage");
            return false;
        } else {
            highersecondary_percentage.setError(null);
            return true;
        }
    }
    private boolean h_year() {
        H_Year = highersecondary_year.getEditText().getText().toString().trim();
        if (H_Year.isEmpty()) {
            highersecondary_year.setError("Field can't be empty");
            return false;
        } else if (H_Year.length() > 4 ) {
            highersecondary_year.setError("Please Enter Valid Year");
            return false;
        } else {
            highersecondary_year.setError(null);
            return true;
        }
    }

    private boolean validateResume() {
        if (ResumeFilepathUri == null) {
            Toast.makeText(this, "Please Select Resume", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePdf() {
        if (PdfFilepathUri == null) {
            Toast.makeText(this, "Please Select PDF", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }



}