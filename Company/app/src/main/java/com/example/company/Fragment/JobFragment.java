package com.example.company.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.R;
import com.example.company.Activity.Add_jobs;
import com.example.company.Adapter.Job_Adapter;
import com.example.company.Model.Job_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobFragment extends Fragment {

    private List<Job_Model> jodlistdata = new ArrayList<>();

    DatabaseReference databaseReference;

   private RecyclerView recyclerView;
   private Job_Adapter jobAdapter;

    // Creating Progress dialog
    ProgressDialog progressDialog;


FloatingActionButton addjobs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_jobs, container, false);

        addjobs=root.findViewById(R.id.button_add_jobs);
        recyclerView = root.findViewById(R.id.rcl_jobs);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//
//        //Assign activity this to progress dialog.
//        progressDialog = new ProgressDialog(getActivity());
//        // Setting up message in Progress dialog.
//        progressDialog.setMessage("Loading Data....");
//        // Showing progress dialog.
//        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("JobsAvailable").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        Job_Model model = postSnapshot.getValue(Job_Model.class);
                        jodlistdata.add(model);
                    }

                    jobAdapter = new Job_Adapter(jodlistdata);
                    recyclerView.setAdapter(jobAdapter);


                    // Hiding the progress dialog.
                   // progressDialog.dismiss();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hiding the progress dialog.
               // progressDialog.dismiss();
            }
        });

        addjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Add_jobs.class);
                Toast.makeText(getActivity(), "Add Appropriate Jobs", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        //Intent refresh = new Intent(getActivity().getApplicationContext(), JobFragment.class);
        //startActivity(refresh);//Start the same Activity
        //finish(); //finish Activity.
        return root;
    }
}