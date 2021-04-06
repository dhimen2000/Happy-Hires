package com.example.student.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.Adapter.Application_Jobs_Adapter;
import com.example.student.Model.Apply_Jobs_Model;
import com.example.student.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


public class Application_JobsFragment extends Fragment {

    @BindView(R.id.Application_job_recyclerview)
    RecyclerView Application_job_recyclerview;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of Companymodel class.
    private List<Apply_Jobs_Model> Applicationjoblist = new ArrayList<>();

    Application_Jobs_Adapter adapter;

    SharedPreferences sharedPreferences;

    String StudentE_mail;

    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_apply__jobs, container, false);
        ButterKnife.bind(this, root);

        sharedPreferences = this.getActivity().getSharedPreferences("SharedPreference", MODE_PRIVATE);
        StudentE_mail = sharedPreferences.getString("Email_Login", "");

        Application_job_recyclerview.setHasFixedSize(true);
        Application_job_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = firebaseDatabase.child("ApplicationForJob").orderByChild("studentEmail").equalTo(StudentE_mail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        Apply_Jobs_Model model = postSnapshot.getValue(Apply_Jobs_Model.class);
                        Applicationjoblist.add(model);
                    }
                    adapter = new Application_Jobs_Adapter(Applicationjoblist);
                    Application_job_recyclerview.setAdapter(adapter);

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