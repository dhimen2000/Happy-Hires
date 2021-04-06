package com.example.student.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.student.Model.Feedback_Model;
import com.example.student.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class FeedbackFragment extends Fragment {

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.Feedback)
    TextInputLayout feedback;

    @BindView(R.id.Send_Feedback)
    Button send_feedback;

    private String Email,Name;
    String Feedback,feedbackkey;
    Float Rating;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_feedback, container, false);
        ButterKnife.bind(this, root);

        sharedPreferences = this.getActivity().getSharedPreferences("SharedPreference", MODE_PRIVATE);
        Email = sharedPreferences.getString("Email_Login", "");
        Name = sharedPreferences.getString("NAME", "");

        Log.d("EMAIL_ID",Email);
        Log.d("NAME_ID",Name);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Rating = ratingBar.getRating();
                Feedback = feedback.getEditText().getText().toString();

                feedbackkey = firebaseDatabase.push().getKey();
                Feedback_Model model = new Feedback_Model(Name,Email,Rating,Feedback,feedbackkey);
                firebaseDatabase.child("Student_Feedback").child(feedbackkey).setValue(model);
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}