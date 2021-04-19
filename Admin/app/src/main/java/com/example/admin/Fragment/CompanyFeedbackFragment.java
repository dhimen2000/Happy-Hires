package com.example.admin.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.Adapter.CompanyFeedback_Adapter;
import com.example.admin.Adapter.StudentFeedback_Adapter;
import com.example.admin.Model.Feedback_Company_Model;
import com.example.admin.Model.Feedback_Student_Model;
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

public class CompanyFeedbackFragment extends Fragment {

    @BindView(R.id.companyfeedback_recyclerview)
    RecyclerView Company_feedback_recyclerview;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of Companymodel class.
    private List<Feedback_Company_Model> companyfeedback = new ArrayList<>();

    CompanyFeedback_Adapter adapter;

    StorageReference storageReference;
    DatabaseReference firebaseDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_company_feedback, container, false);
        ButterKnife.bind(this, root);

        Company_feedback_recyclerview.setHasFixedSize(true);
        Company_feedback_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseDatabase.child("Company-Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        Feedback_Company_Model model = postSnapshot.getValue(Feedback_Company_Model.class);
                        companyfeedback.add(model);
                    }

                    adapter = new CompanyFeedback_Adapter(companyfeedback);
                    Company_feedback_recyclerview.setAdapter(adapter);

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