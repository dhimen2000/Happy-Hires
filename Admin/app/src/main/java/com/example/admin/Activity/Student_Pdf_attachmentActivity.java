package com.example.admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.admin.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Student_Pdf_attachmentActivity extends AppCompatActivity {

    String pdfurl;

    @BindView(R.id.idPDFView)
    PDFView pdfView;

    ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__pdf_attachment);

        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(Student_Pdf_attachmentActivity.this);
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();

        Intent intent = getIntent();
        pdfurl = intent.getStringExtra("Pdf");

        new Student_Pdf_attachmentActivity.RetrivePDFfromUrl().execute(pdfurl);
    }
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();

            // Hiding the progress dialog.
            progressDialog.dismiss();
        }
    }
}