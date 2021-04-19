package com.example.admin.Fragment;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.admin.Model.Placement_Model;
import com.example.admin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class PlacementFragment extends Fragment {

    @BindView(R.id.Year)
    TextInputLayout year;

    @BindView(R.id.Upload_Pdf)
    Button uploadpdf;

    @BindView(R.id.Upload)
    Button upload;

    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    //For Resume & Pdf
    // Folder path for Firebase Storage.
    String Pdf_Storage_Path = "All_Placement_Pdf_Uploads/";
    // PDF request code for onActivityResult() .
    int Pdf_Request_Code = 1;
    // Creating URI.
    Uri PdfFilepathUri;
    Uri PdfDownloadUri;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    String placementyear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_placement, container, false);
        ButterKnife.bind(this, root);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(getActivity());

        uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Selectpdf();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placementyear = year.getEditText().getText().toString();

                String key = firebaseDatabase.push().getKey();

                Placement_Model model = new Placement_Model(placementyear,PdfDownloadUri.toString(),key);
                firebaseDatabase.child("Placement").child(key).setValue(model);
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void Selectpdf() {
        // Creating intent.
        Intent intent = new Intent();

        // Setting intent type as image to select image from phone storage.
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Pdf"), Pdf_Request_Code);
    }

    //choosepdf
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //chooseimage
        if (requestCode == Pdf_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            PdfFilepathUri = data.getData();
            if (data.getData() != null) {
                //uploading the file
                Toast.makeText(getActivity(), "Pdf Selected...", Toast.LENGTH_SHORT).show();
                PdfUpload();
            } else {
                Toast.makeText(getActivity(), "Pdf Not Selected...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Creating Method to get the selected file Extension from File Path URI.
    private String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void PdfUpload() {
        if (PdfFilepathUri != null) {
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
                                    Toast.makeText(getActivity(), "Pdf Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
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
                            progressDialog.setTitle("Uploading Pdf... " + ((int) progress) + "%...");
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "Please Select Pdf...", Toast.LENGTH_LONG).show();
        }
    }
}