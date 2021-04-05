package com.example.student.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.student.R;
import com.example.student.Adapter.View_Jobs_Adapter;
import com.example.student.Model.View_Jobs_Model;
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


public class View_JobsFragment extends Fragment {

    @BindView(R.id.recyclerview_view_jobs)
    RecyclerView recyclerView_Joblist;

    ProgressDialog progressDialog;
    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    View_Jobs_Adapter adapter;

    private List<View_Jobs_Model> joblistdata = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_view__jobs, container, false);
        ButterKnife.bind(this, root);

        recyclerView_Joblist.setHasFixedSize(true);
        recyclerView_Joblist.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseDatabase.child("JobsAvailable").child("").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        View_Jobs_Model model = postSnapshot.getValue(View_Jobs_Model.class);
                        joblistdata.add(model);
                    }

                    adapter = new View_Jobs_Adapter(joblistdata);
                    recyclerView_Joblist.setAdapter(adapter);

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