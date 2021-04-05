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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    //Basic Details
    //ImageView
    @BindView(R.id.profile_image)
    CircleImageView profilepic;
    @BindView(R.id.profilePictureButton)
    ImageView profilepicbtn;

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
    //Buttons
    @BindView(R.id.Register_back)
    Button btnback;
    @BindView(R.id.Register_next)
    Button btnnext;

    //For Image
    // Folder path for Firebase Storage.
    String Image_Storage_Path = "All_Image_Uploads/";
    // Image request code for onActivityResult().
    int Image_Request_Code = 7;
    // Creating URI.
    Uri ImageFilepathUri;
    Uri ImageDownloadUri;
    ProgressDialog progressDialog;

    //Basic Details
    String Name, Dob, Gender, Number, Address;
    //Branch, Email, Pass, Confirm_Pass;

    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DatabaseReference firebaseDatabase;
    SharedPreferences.Editor edit;

    SharedPreferences sharedPreferences;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        firebaseAuth = firebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        sharedPreferences = getSharedPreferences("SharedPreference", MODE_PRIVATE);
        edit = sharedPreferences.edit();

        progressDialog = new ProgressDialog(RegisterActivity.this);

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

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (!validateImage()) {
//                    return;
//                }

                if (!validateImage() | !validateName() | !validateDate() | !validateGender() | !validateNumber() | !validateAddress()) {
                    return;
                }

                Name = name.getEditText().getText().toString();
                Dob = dob.getEditText().getText().toString();
                Number = number.getEditText().getText().toString();
                Address = address.getEditText().getText().toString();

                edit.putString("Name", Name);
                edit.putString("Dob", Dob);
                edit.putString("Gender", Gender);
                edit.putString("Number", Number);
                edit.putString("Address", Address);
                edit.putString("ImageUrl", ImageDownloadUri.toString());
                edit.apply();

//                Log.d("Name", Name);
//                Log.d("DOB", Dob);
//                Log.d("Gender", Gender);
//                Log.d("Number", Number);
//                Log.d("Address", Address);
//                Log.d("uri",ImageDownloadUri.toString());

                startActivity(new Intent(RegisterActivity.this, RegisterActivity2.class));
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


//        btnregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Name = name.getText().toString();
//                Number = number.getText().toString();
//                Dob = dob.getText().toString();
//                Log.d("DOB", Dob);
//                Gender = gender.getText().toString();
//                Email = email.getText().toString();
//                Pass = pass.getText().toString();
//                Confirm_Pass = confirm_pass.getText().toString();
//
//                firebaseAuth.createUserWithEmailAndPassword(Email, Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        Toast.makeText(RegisterActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
//                        RegisterModel registerModel = new RegisterModel(Name, Number, Email, Pass);
//                        firebaseDatabase.child("User").push().setValue(registerModel);
//                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(RegisterActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

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
                e.printStackTrace();
            }
        }
    }

    private String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void UploadImage() {
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

    private boolean validateGender()
    {
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


}