package com.example.company.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.company.Activity.LoginActivity;
import com.example.company.Activity.MainActivity;
import com.example.company.Model.Feedback_Model;
import com.example.company.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;


public class FeedbackFragment extends Fragment {

    android.content.SharedPreferences SharedPreferences;
    EditText feedback;
    Button send_feedback;
    String Feedback;
    RatingBar ratingbar;
    String id;
    String company_name;
    String company_email;
    float rating1;
android.content.SharedPreferences sharedPreferences;
    DatabaseReference firebaseDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_feedback3, container, false);
        feedback=root.findViewById(R.id.nav_feedback);
        send_feedback=root.findViewById(R.id.send_feedback);
        ratingbar= root.findViewById(R.id.ratingBar);
        ratingbar.setNumStars(5);

        sharedPreferences  = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);

        company_email = sharedPreferences.getString("E-mail", "");

        company_name = sharedPreferences.getString("C-name", "");



        firebaseDatabase= FirebaseDatabase.getInstance().getReference();
        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateUsername()) {

                }
                Feedback=feedback.getText().toString();
                String rating =String.valueOf(ratingbar.getRating());
                rating1=ratingbar.getRating();
                Toast.makeText(getActivity(), rating, Toast.LENGTH_LONG).show();



                id = firebaseDatabase.push().getKey();
                Feedback_Model feedbackModel= new Feedback_Model(Feedback,id,company_email,company_name,rating1);
                firebaseDatabase.child("Company-Feedback").child(id).setValue(feedbackModel);
                Toast.makeText(v.getContext(), "Thanks for Feedback", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));

           }

            private boolean validateUsername() {
                Feedback = feedback.getText().toString().trim();
                if (Feedback.isEmpty()) {
                    feedback.setError("Field can't be empty");
                    return false;
                } else if (Feedback.length() > 50) {
                    feedback.setError("Feedback not more the 50 alphabates");
                    return false;
                } else {
                    feedback.setError(null);
                    return true;
                }
            }
        });

        return root;
    }
}