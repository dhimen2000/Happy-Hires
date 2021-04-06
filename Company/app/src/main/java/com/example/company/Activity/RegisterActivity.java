package com.example.company.Activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.company.Model.Register_Model;
import com.example.company.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    EditText name_register, number_register, email_register, password_register, address_register, website_register, confirm_password_register;
    ImageView imageView;
    Button btnregister;
    String name;
    String number;
    String email;
    String password;
    String address;
    String website;
    String confirm_password;
    private String id;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    // Folder path for Firebase Storage.
    String Storage_Path = "All_Image_Uploads/";

    // Root Database Name for Firebase Database.
    String Database_Path = "All_Image_Uploads_Database";

    // Creating button.
    Button ChooseButton, UploadButton;
    Button pdf;

    // Creating EditText.
    EditText ImageName;

    // Creating ImageView.
    ImageView SelectImage;

    // Creating URI.
    Uri FilePathUri;
    Uri imageuri1;
    String Storage_path = "All_Pdf_Uploads/";
    StorageReference filepath;
    String messagePushID;
    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;


    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;
    int pdf_code = 1;
    int resultCode1 = 1;


    ProgressDialog progressDialog;
    String imgurl, pdfurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_register);
        btnregister = findViewById(R.id.Register_submit);
        name_register = findViewById(R.id.Register_name);
        number_register = findViewById(R.id.Register_number);
        email_register = findViewById(R.id.Register_email);
        website_register = findViewById(R.id.Register_website);
        address_register = findViewById(R.id.Register_address);
        password_register = findViewById(R.id.Register_password);
        confirm_password_register = findViewById(R.id.Register_confirmpassword);
        SelectImage = findViewById(R.id.imageViewpic);
        ChooseButton = findViewById(R.id.choose_image);
        //UploadButton = findViewById(R.id.upload_image);
        pdf = findViewById(R.id.upload_pdf);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(RegisterActivity.this);
        // dialog =new ProgressDialog(RegisterActivity.this);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, pdf_code);

            }
        });

        ChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);


            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (email_register.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "enter email address", Toast.LENGTH_SHORT).show();
//                }






                if (validateUsername()| !validateEmail()| !validatePassword()| !validateConfirmPassword()| !validateImage()| !validateNumber()| !validateAddress()) {


//                    String input = "Name :" + name_register.getText().toString();
//                    input += "\n";
//                    input += "Email: " + email_register.getText().toString();
//                    input += "\n";
//                    input += "Image " + SelectImage.toString();
//                    input += "\n";
//                    input += "Number: " + number_register.getText().toString();
//                    input += "\n";
//                    input += "Address: " + address_register.getText().toString();
//                    input += "\n";
//                    input += "Password: " + password_register.getText().toString();
//                    input += "\n";
//                    input += "Password: " + confirm_password_register.getText().toString();
//                    Toast.makeText(getApplicationContext(), input, Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
//                    if (email_register.getText().toString().trim().matches(emailPattern)) {
//                        Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
//                    }
                    UploadImageFileToFirebaseStorage();
                }
            }
            private boolean validateImage() {
                if (imageuri1 == null) {
                    Toast.makeText(RegisterActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    return true;
                }
            }
            private boolean validateUsername() {
                name = name_register.getText().toString().trim();
                if (name.isEmpty()) {
                    name_register.setError("Field can't be empty");
                    return false;
                } else if (name.length() > 15) {
                    name_register.setError("Username too long");
                    return false;
                } else {
                    name_register.setError(null);
                    return true;
                }
            }

            private boolean validateEmail() {
                email = email_register.getText().toString().trim();
                if (email.isEmpty()) {
                    email_register.setError("Field can't be empty");
                    return false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_register.setError("Please enter a valid email address");
                    return false;
                } else {
                    email_register.setError(null);
                    return true;
                }
            }
            private boolean validatePassword() {
                password = password_register.getText().toString().trim();
                if (password.isEmpty()) {
                    password_register.setError("Field can't be empty");
                    return false;
                } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
                    password_register.setError("Password too weak");
                    return false;
                } else {
                    password_register.setError(null);
                    return true;
                }
            }
            private boolean validateConfirmPassword() {
                password = password_register.getText().toString().trim();
                confirm_password= confirm_password_register.getText().toString().trim();
                if(!password.equals(confirm_password)) {
                    Toast.makeText(RegisterActivity.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else {
                    password_register.setError(null);
                    return true;
                }
            }
            private boolean validateNumber() {
                number = number_register.getText().toString().trim();
                if (number.isEmpty()) {
                    number_register.setError("Field can't be empty");
                    return false;
                } else if (number.length() > 10) {
                    number_register.setError("Number too long");
                    return false;
                } else if (number.length() < 10) {
                    number_register.setError("Number too Short");
                    return false;
                } else {
                    number_register.setError(null);
                    return true;
                }
            }

            private boolean validateAddress() {
                address = address_register.getText().toString().trim();
                if (address.isEmpty()) {
                    address_register.setError("Field can't be empty");
                    return false;
                } else if (address.length() > 50) {
                    address_register.setError("Address too long");
                    return false;
                } else {
                    address_register.setError(null);
                    return true;
                }
            }
//            private boolean validateAddress() {
//            }
//
//            private boolean validateNumber() {
//            }
//
//            private boolean validateImage() {
//            }
//
//            private boolean validateConfirmPassword() {
//            }
//
//            private boolean validatePassword() {
//            }
//
//            private boolean validateEmail() {
//            }
//
//            private boolean validateUsername() {
//            }
        });

    }


    ProgressDialog dialog;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                ChooseButton.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }

        else
        {
            imageuri1 = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri1);

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                ChooseButton.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            final String timestamp = "" + System.currentTimeMillis();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            messagePushID = timestamp;
            filepath = storageReference.child(Storage_path + messagePushID + "." + "pdf");
            filepath.putFile(imageuri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "PDF Uploaded Successfully ", Toast.LENGTH_LONG).show();

                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            pdfurl = uri.toString();


                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference2nd.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    progressDialog.dismiss();


                                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                                    imgurl = uri.toString();
                                    Log.d("img",imgurl);

                                }
                            });



                            name = name_register.getText().toString();
                            number = number_register.getText().toString();
                            email =email_register.getText().toString();
                            address=address_register.getText().toString();
                            website=website_register.getText().toString();
                            password=password_register.getText().toString();
                            confirm_password=confirm_password_register.getText().toString();

//                            if (validateUsername()| !validateEmail()| !validatePassword()| !validateConfirmPassword()| !validateImage()| !validateNumber()| !validateAddress()) {
//                                return;
//                            }
//                            String input = "Name :" + name_register.getText().toString();
//                            input += "\n";
//                            input += "Email: " + email_register.getText().toString();
//                            input += "\n";
//                            input += "Image " + SelectImage.toString();
//                            input += "\n";
//                            input += "Number: " + number_register.getText().toString();
//                            input += "\n";
//                            input += "Address: " + address_register.getText().toString();
//                            input += "\n";
//                            input += "Password: " + password_register.getText().toString();
//                            input += "\n";
//                            input += "Password: " + confirm_password_register.getText().toString();
//                            Toast.makeText(getApplicationContext(), input, Toast.LENGTH_SHORT).show();

                            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {


                                    id = firebaseDatabase.push().getKey();
                                    Register_Model registerModel= new Register_Model(name,number,email,address,website,password,id,imgurl,pdfurl);
                                    firebaseDatabase.child("Company").child(id).setValue(registerModel);
                                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
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

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(RegisterActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }

    }



}