package com.example.admin.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Adapter.StudentFeedback_Adapter;
import com.example.admin.Adapter.Student_Adapter;
import com.example.admin.Model.Feedback_Student_Model;
import com.example.admin.Model.Student_Model;
import com.example.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentFeedbackFragment extends Fragment {

    @BindView(R.id.studentfeedback_recyclerview)
    RecyclerView Student_feedback_recyclerview;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of Companymodel class.
    private List<Feedback_Student_Model> studentfeedback = new ArrayList<>();

    StudentFeedback_Adapter adapter;

    StorageReference storageReference;
    DatabaseReference firebaseDatabase;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_feedback, container, false);
        ButterKnife.bind(this, root);

        Student_feedback_recyclerview.setHasFixedSize(true);
        Student_feedback_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseDatabase.child("Student_Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        Feedback_Student_Model model = postSnapshot.getValue(Feedback_Student_Model.class);
                        studentfeedback.add(model);
                    }

                    adapter = new StudentFeedback_Adapter(studentfeedback);
                    Student_feedback_recyclerview.setAdapter(adapter);

                    // Hiding the progress dialog.
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hiding the progress dialog.
                progressDialog.dismiss();
            }
        });

        return root;
    }

}