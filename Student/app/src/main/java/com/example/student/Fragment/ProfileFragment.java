package com.example.student.Fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.student.Activity.PdfcheckActivity;
import com.example.student.R;
import com.example.student.Model.RegisterModel;
import com.example.student.Activity.ResumecheckActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
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

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    @BindView(R.id.Profile_profilepic)
    CircleImageView profileimage;
    @BindView(R.id.Profile_profilebutton)
    ImageView profilebtn;
    @BindView(R.id.Profile_name)
    TextInputLayout name;
    @BindView(R.id.Profile_Dob)
    TextInputLayout dob;
    @BindView(R.id.Profile_Gender)
    TextInputLayout gender;
    @BindView(R.id.Profile_number)
    TextInputLayout number;
    @BindView(R.id.Profile_Address)
    TextInputLayout address;
    @BindView(R.id.Profile_Email)
    TextInputLayout email;


    @BindView(R.id.Profile_Secondary_School)
    TextInputLayout secondary_school;
    @BindView(R.id.Profile_Secondary_Board)
    TextInputLayout secondary_board;
    @BindView(R.id.Profile_Secondary_Percentage)
    TextInputLayout secondary_percentage;
    @BindView(R.id.Profile_Secondary_Year)
    TextInputLayout secondary_year;

    @BindView(R.id.Profile_HigherSecondary_School)
    TextInputLayout highersecondary_school;
    @BindView(R.id.Profile_HigherSecondary_Board)
    TextInputLayout highersecondary_board;
    @BindView(R.id.Profile_HigherSecondary_Percentage)
    TextInputLayout highersecondary_percentage;
    @BindView(R.id.Profile_HigherSecondary_Year)
    TextInputLayout highersecondary_year;

    @BindView(R.id.Profile_Graduation_College)
    TextInputLayout graduation_college;
    @BindView(R.id.Profile_Graduation_Branch)
    TextInputLayout graduation_branch;
    @BindView(R.id.Profile_Graduation_Enrollmentno)
    TextInputLayout graduation_enrollmentno;
    @BindView(R.id.Profile_Graduation_Sem1)
    EditText graduation_sem1;
    @BindView(R.id.Profile_Graduation_Year1)
    EditText graduation_year1;
    @BindView(R.id.Profile_Graduation_Sem2)
    EditText graduation_sem2;
    @BindView(R.id.Profile_Graduation_Year2)
    EditText graduation_year2;
    @BindView(R.id.Profile_Graduation_Sem3)
    EditText graduation_sem3;
    @BindView(R.id.Profile_Graduation_Year3)
    EditText graduation_year3;
    @BindView(R.id.Profile_Graduation_Sem4)
    EditText graduation_sem4;
    @BindView(R.id.Profile_Graduation_Year4)
    EditText graduation_year4;
    @BindView(R.id.Profile_Graduation_Sem5)
    EditText graduation_sem5;
    @BindView(R.id.Profile_Graduation_Year5)
    EditText graduation_year5;
    @BindView(R.id.Profile_Graduation_Sem6)
    EditText graduation_sem6;
    @BindView(R.id.Profile_Graduation_Year6)
    EditText graduation_year6;
    @BindView(R.id.Profile_Graduation_Sem7)
    EditText graduation_sem7;
    @BindView(R.id.Profile_Graduation_Year7)
    EditText graduation_year7;
    @BindView(R.id.Profile_Graduation_Sem8)
    EditText graduation_sem8;
    @BindView(R.id.Profile_Graduation_Year8)
    EditText graduation_year8;

    @BindView(R.id.Profile_CheckResume)
    Button CheckResume;
    @BindView(R.id.Profile_uploadResume)
    Button UploadResume;
    @BindView(R.id.Profile_Checkpdf)
    Button CheckPdf;
    @BindView(R.id.Profile_uploadpdf)
    Button UploadPdf;
    @BindView(R.id.Profile_Edit)
    Button Editbtn;

    //For Image
    // Folder path for Firebase Storage.
    String Image_Storage_Path = "All_Image_Uploads/";
    // Image request code for onActivityResult().
    int Image_Request_Code = 7;
    // Creating URI.
    Uri EditImageFilepathUri;
    Uri ImageDownloadUri;
    Uri EditImageDownloaduri;
    ProgressDialog progressDialog;

    //For Resume & Pdf
    // Folder path for Firebase Storage.
    String Pdf_Storage_Path = "All_Pdf_Uploads/";
    // PDF request code for onActivityResult() .
    int Resume_Request_Code = 1;
    int Pdf_Request_Code = 2;
    // Creating URI.
    Uri EditResumeFilepathUri;
    Uri ResumeDownloadUri;
    Uri EditResumeDownloadUri;
    Uri EditPdfFilepathUri;
    Uri PdfDownloadUri;
    Uri EditPdfDownloadUri;


    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private DatabaseReference firebaseDatabase;

    Context context;
    String Name, Dob, Gender, Number, Address, Email, E_mail, Pass, Key,Status;
    String Secondary_School, Secondary_Board, Secondary_Percentage, Secondary_Year;
    String HigherSecondary_School, HigherSecondary_Board, HigherSecondary_Percentage, HigherSecondary_Year;
    String Graduation_College, Graduation_Branch, Graduation_Enrollmentno;
    String Graduation_Sem1, Graduation_Sem2, Graduation_Sem3, Graduation_Sem4, Graduation_Sem5, Graduation_Sem6, Graduation_Sem7, Graduation_Sem8;
    String Graduation_Year1, Graduation_Year2, Graduation_Year3, Graduation_Year4, Graduation_Year5, Graduation_Year6, Graduation_Year7, Graduation_Year8;

    String Edit_Name, Edit_Dob, Edit_Gender, Edit_Number, Edit_Address, Edit_Email, Edit_Pass, Edit_Key;
    String Edit_Secondary_School, Edit_Secondary_Board, Edit_Secondary_Percentage, Edit_Secondary_Year;
    String Edit_HigherSecondary_School, Edit_HigherSecondary_Board, Edit_HigherSecondary_Percentage, Edit_HigherSecondary_Year;
    String Edit_Graduation_College, Edit_Graduation_Branch, Edit_Graduation_Enrollmentno;
    String Edit_Graduation_Sem1, Edit_Graduation_Sem2, Edit_Graduation_Sem3, Edit_Graduation_Sem4, Edit_Graduation_Sem5, Edit_Graduation_Sem6, Edit_Graduation_Sem7, Edit_Graduation_Sem8;
    String Edit_Graduation_Year1, Edit_Graduation_Year2, Edit_Graduation_Year3, Edit_Graduation_Year4, Edit_Graduation_Year5, Edit_Graduation_Year6, Edit_Graduation_Year7, Edit_Graduation_Year8;

    SharedPreferences sharedPreferences;

    String checkResume,checkPdf;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);
        progressDialog = new ProgressDialog(getActivity());
        sharedPreferences = this.getActivity().getSharedPreferences("SharedPreference", MODE_PRIVATE);
        E_mail = sharedPreferences.getString("Email_Login", "");


        firebaseAuth = firebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = firebaseDatabase.child("Student").orderByChild("email").equalTo(E_mail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        RegisterModel model = dataSnapshot.getValue(RegisterModel.class);

                        ImageDownloadUri = Uri.parse(model.getImageUrl().toString());
                        ResumeDownloadUri = Uri.parse(model.getResumeUrl().toString());
                        PdfDownloadUri = Uri.parse(model.getPdfUrl().toString());
                        Glide.with(getActivity()).load(model.getImageUrl()).into(profileimage);
                        Edit_Pass = model.getPass();
                        checkResume = model.getResumeUrl();
                        checkPdf = model.getPdfUrl();
                        Edit_Key = model.getKey();
                        Name = model.getName();
                        Dob = model.getDob();
                        Gender = model.getGender();
                        Number = model.getNumber();
                        Address = model.getAddress();
                        Email = model.getEmail();
                        Pass = model.getPass();
                       // Key = model.getKey();

                        Secondary_School = model.getS_School();
                        Secondary_Board = model.getS_Board();
                        Secondary_Percentage = model.getS_Percentage();
                        Secondary_Year = model.getS_Year();

                        HigherSecondary_School = model.getH_School();
                        HigherSecondary_Board = model.getH_Board();
                        HigherSecondary_Percentage = model.getH_Percentage();
                        HigherSecondary_Year = model.getH_Year();

                        Graduation_College = model.getG_College();
                        Graduation_Branch = model.getG_Branch();
                        Graduation_Enrollmentno = model.getG_Enrollmentno();
                        Graduation_Sem1 = model.getG_Sem1();
                        Graduation_Sem2 = model.getG_Sem2();
                        Graduation_Sem3 = model.getG_Sem3();
                        Graduation_Sem4 = model.getG_Sem4();
                        Graduation_Sem5 = model.getG_Sem5();
                        Graduation_Sem6 = model.getG_Sem6();
                        Graduation_Sem7 = model.getG_Sem7();
                        Graduation_Sem8 = model.getG_Sem8();
                        Graduation_Year1 = model.getG_Year1();
                        Graduation_Year2 = model.getG_Year2();
                        Graduation_Year3 = model.getG_Year3();
                        Graduation_Year4 = model.getG_Year4();
                        Graduation_Year5 = model.getG_Year5();
                        Graduation_Year6 = model.getG_Year6();
                        Graduation_Year7 = model.getG_Year7();
                        Graduation_Year8 = model.getG_Year8();

                        Status = model.getStatus();


                    }
                    name.getEditText().setText(Name);
                    dob.getEditText().setText(Dob);
                    gender.getEditText().setText(Gender);
                    number.getEditText().setText(Number);
                    address.getEditText().setText(Address);
                    email.getEditText().setText(Email);

                    secondary_school.getEditText().setText(Secondary_School);
                    secondary_board.getEditText().setText(Secondary_Board);
                    secondary_percentage.getEditText().setText(Secondary_Percentage);
                    secondary_year.getEditText().setText(Secondary_Year);

                    highersecondary_school.getEditText().setText(HigherSecondary_School);
                    highersecondary_board.getEditText().setText(HigherSecondary_Board);
                    highersecondary_percentage.getEditText().setText(HigherSecondary_Percentage);
                    highersecondary_year.getEditText().setText(HigherSecondary_Year);

                    graduation_college.getEditText().setText(Graduation_College);
                    graduation_branch.getEditText().setText(Graduation_Branch);
                    graduation_enrollmentno.getEditText().setText(Graduation_Enrollmentno);
                    graduation_sem1.setText(Graduation_Sem1);
                    graduation_sem2.setText(Graduation_Sem2);
                    graduation_sem3.setText(Graduation_Sem3);
                    graduation_sem4.setText(Graduation_Sem4);
                    graduation_sem5.setText(Graduation_Sem5);
                    graduation_sem6.setText(Graduation_Sem6);
                    graduation_sem7.setText(Graduation_Sem7);
                    graduation_sem8.setText(Graduation_Sem8);
                    graduation_year1.setText(Graduation_Year1);
                    graduation_year2.setText(Graduation_Year2);
                    graduation_year3.setText(Graduation_Year3);
                    graduation_year4.setText(Graduation_Year4);
                    graduation_year5.setText(Graduation_Year5);
                    graduation_year6.setText(Graduation_Year6);
                    graduation_year7.setText(Graduation_Year7);
                    graduation_year8.setText(Graduation_Year8);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        CheckResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResumecheckActivity.class);
                intent.putExtra("Resume", checkResume);
                startActivity(intent);
            }
        });

        CheckPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PdfcheckActivity.class);
                intent.putExtra("Pdf", checkPdf);
                startActivity(intent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selectimage();
            }
        });

        UploadResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectResume();
            }
        });

        UploadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selectpdf();
            }
        });

        Editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Edit_Name = name.getEditText().getText().toString();
                Edit_Dob = dob.getEditText().getText().toString();
                Edit_Gender = gender.getEditText().getText().toString();
                Edit_Number = number.getEditText().getText().toString();
                Edit_Address = address.getEditText().getText().toString();
                Edit_Email = email.getEditText().getText().toString();

                Edit_Secondary_School = secondary_school.getEditText().getText().toString();
                Edit_Secondary_Board = secondary_board.getEditText().getText().toString();
                Edit_Secondary_Percentage = secondary_percentage.getEditText().getText().toString();
                Edit_Secondary_Year = secondary_year.getEditText().getText().toString();

                Edit_HigherSecondary_School = highersecondary_school.getEditText().getText().toString();
                Edit_HigherSecondary_Board = highersecondary_board.getEditText().getText().toString();
                Edit_HigherSecondary_Percentage = highersecondary_percentage.getEditText().getText().toString();
                Edit_HigherSecondary_Year = highersecondary_year.getEditText().getText().toString();

                Edit_Graduation_College = graduation_college.getEditText().getText().toString();
                Edit_Graduation_Branch = graduation_branch.getEditText().getText().toString();
                Edit_Graduation_Enrollmentno = graduation_enrollmentno.getEditText().getText().toString();
                Edit_Graduation_Sem1 = graduation_sem1.getText().toString();
                Edit_Graduation_Sem2 = graduation_sem2.getText().toString();
                Edit_Graduation_Sem3 = graduation_sem3.getText().toString();
                Edit_Graduation_Sem4 = graduation_sem4.getText().toString();
                Edit_Graduation_Sem5 = graduation_sem5.getText().toString();
                Edit_Graduation_Sem6 = graduation_sem6.getText().toString();
                Edit_Graduation_Sem7 = graduation_sem7.getText().toString();
                Edit_Graduation_Sem8 = graduation_sem8.getText().toString();
                Edit_Graduation_Year1 = graduation_year1.getText().toString();
                Edit_Graduation_Year2 = graduation_year2.getText().toString();
                Edit_Graduation_Year3 = graduation_year3.getText().toString();
                Edit_Graduation_Year4 = graduation_year4.getText().toString();
                Edit_Graduation_Year5 = graduation_year5.getText().toString();
                Edit_Graduation_Year6 = graduation_year6.getText().toString();
                Edit_Graduation_Year7 = graduation_year7.getText().toString();
                Edit_Graduation_Year8 = graduation_year8.getText().toString();

                Uri finalimageurl;
                if (EditImageDownloaduri != null)
                {
                    finalimageurl = Uri.parse(EditImageDownloaduri.toString());
                    Log.d("URI1", EditImageDownloaduri.toString());
                }
                else
                    {
                    finalimageurl = Uri.parse(ImageDownloadUri.toString());
                }

                Uri finalresumeurl;
                if (EditResumeDownloadUri != null)
                {
                    finalresumeurl = Uri.parse(EditResumeDownloadUri.toString());
                    Log.d("URI1", EditResumeDownloadUri.toString());
                }
                else
                {
                    finalresumeurl = Uri.parse(ResumeDownloadUri.toString());
                }

                Uri finalpdfurl;
                if (EditPdfDownloadUri != null)
                {
                    finalpdfurl = Uri.parse(EditPdfDownloadUri.toString());
                    Log.d("URI1", EditPdfDownloadUri.toString());
                }
                else
                {
                    finalpdfurl = Uri.parse(PdfDownloadUri.toString());
                }


                RegisterModel registerModel = new RegisterModel(Edit_Email, Edit_Pass,Edit_Name, Edit_Dob, Edit_Gender, Edit_Number, Edit_Address, finalimageurl.toString(),  Edit_Secondary_School, Edit_Secondary_Board, Edit_Secondary_Percentage, Edit_Secondary_Year,
                        Edit_HigherSecondary_School, Edit_HigherSecondary_Board, Edit_HigherSecondary_Percentage, Edit_HigherSecondary_Year, Edit_Graduation_College, Edit_Graduation_Branch, Edit_Graduation_Enrollmentno, Edit_Graduation_Sem1, Edit_Graduation_Year1,
                        Edit_Graduation_Sem2, Edit_Graduation_Year2, Edit_Graduation_Sem3, Edit_Graduation_Year3, Edit_Graduation_Sem4, Edit_Graduation_Year4, Edit_Graduation_Sem5, Edit_Graduation_Year5, Edit_Graduation_Sem6, Edit_Graduation_Year6, Edit_Graduation_Sem7,
                        Edit_Graduation_Year7, Edit_Graduation_Sem8, Edit_Graduation_Year8, Edit_Key, finalresumeurl.toString(), finalpdfurl.toString(),Status);

                firebaseDatabase.child("Student").child(Edit_Key).setValue(registerModel);

                Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();

            }
        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        return root;
    }

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

    private String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //chooseimage
        if (requestCode == Image_Request_Code && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            EditImageFilepathUri = data.getData();
            try {
                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), EditImageFilepathUri);
                // Setting up bitmap selected image into ImageView.
                profileimage.setImageBitmap(bitmap);

                Toast.makeText(getActivity(), "New Image Selected", Toast.LENGTH_SHORT).show();

                if (EditImageFilepathUri != null) {

                    // Setting progressDialog Title.
                    progressDialog.setTitle("Image is Uploading...");

                    // Showing progressDialog.
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);

                    // Creating second StorageReference.
                    StorageReference storageReference1 = storageReference.child(Image_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(EditImageFilepathUri));

                    // Adding addOnSuccessListener to second StorageReference.
                    storageReference1.putFile(EditImageFilepathUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            EditImageDownloaduri = uri;
                                            progressDialog.dismiss();

                                            // Showing toast message after done uploading.
                                            Toast.makeText(getActivity(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                                            Log.d("Uri", EditImageDownloaduri.toString());
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
                                    Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getActivity(), "Please Select Image...", Toast.LENGTH_LONG).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //choosepdf
        else if (requestCode == Resume_Request_Code && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            EditResumeFilepathUri = data.getData();
            if (data.getData() != null)
                {
                Toast.makeText(getActivity(), "File 1 Selected", Toast.LENGTH_SHORT).show();
                uploadResume();
                }
            else
                {
                Toast.makeText(getActivity(), "No file chosen", Toast.LENGTH_SHORT).show();
                }
        }
        else if (requestCode == Pdf_Request_Code && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            EditPdfFilepathUri = data.getData();
            if (data.getData() != null) {
                Toast.makeText(getActivity(), "File 2 Selected", Toast.LENGTH_SHORT).show();
                uploadPdf();
            }
            else {
                Toast.makeText(getActivity(), "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
                Toast.makeText(getActivity(), "Please Select File", Toast.LENGTH_SHORT).show();
            }catch(Exception e)
            {
                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage() {
        if (EditImageFilepathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            // Creating second StorageReference.
            StorageReference storageReference1 = storageReference.child(Image_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(EditImageFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(EditImageFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    EditImageDownloaduri = uri;

                                    progressDialog.dismiss();

                                    // Showing toast message after done uploading.
                                    Toast.makeText(getActivity(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                                    Log.d("Uri", EditImageDownloaduri.toString());
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
                            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(getActivity(), "Please Select Image...", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadResume()
    {
        if (EditResumeFilepathUri != null) {
            // Setting progressDialog Title.
            progressDialog.setTitle("Resume Uploading...");

            // Showing progressDialog.
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            // Creating second StorageReference.
            StorageReference storageReference1 = storageReference.child(Pdf_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(EditResumeFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(EditResumeFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    EditResumeDownloadUri = uri;
                                    progressDialog.dismiss();

                                    Toast.makeText(getActivity(), "Resume Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                    Log.d("uri", EditResumeDownloadUri.toString());
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
                            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setTitle("Uploading Resume " + ((int) progress) + "%...");
                        }
                    });
        } else
        {
            Toast.makeText(getActivity(), "Please Select Resume...", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadPdf()
    {
        if (EditPdfFilepathUri != null) {
            // Setting progressDialog Title.
            progressDialog.setTitle("Pdf Uploading...");

            // Showing progressDialog.
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            // Creating second StorageReference.
            StorageReference storageReference1 = storageReference.child(Pdf_Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(EditPdfFilepathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference1.putFile(EditPdfFilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    EditPdfDownloadUri = uri;
                                    progressDialog.dismiss();

                                    Toast.makeText(getActivity(), "Pdf Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                    Log.d("uri", EditPdfDownloadUri.toString());
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
                            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setTitle("Uploading Pdf" + ((int) progress) + "%...");
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "Please Select Pdf...", Toast.LENGTH_LONG).show();
        }

    }



}