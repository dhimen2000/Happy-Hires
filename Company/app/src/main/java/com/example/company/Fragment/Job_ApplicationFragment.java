package com.example.company.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.company.R;
import com.example.company.Adapter.Job_Applications_Adapter;
import com.example.company.Model.Job_Applications_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Job_ApplicationFragment extends Fragment {

private List<Job_Applications_Model> ListData;

DatabaseReference databaseReference;

RecyclerView recyclerView;
Job_Applications_Adapter jobApplicationsAdapter;
    String company_email;
    SharedPreferences sharedPreferences;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     //   ListData = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_applyjob, container, false);


        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        company_email = sh.getString("E-mail", "");

        recyclerView = root.findViewById(R.id.companies_list_rvl);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ListData = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.d("email",company_email);


  Query query= databaseReference.child("ApplicationForJob").orderByChild("companyEmail").equalTo(company_email);
               query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Job_Applications_Model ld=npsnapshot.getValue(Job_Applications_Model.class);
                        ListData.add(ld);
                    }

                    jobApplicationsAdapter = new Job_Applications_Adapter(ListData);
                    recyclerView.setAdapter(jobApplicationsAdapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query query1= databaseReference.child("ApplicationForJob").orderByChild("status").equalTo("On Hold");
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Job_Applications_Model ld=npsnapshot.getValue(Job_Applications_Model.class);
                        ListData.add(ld);


                    }

                    jobApplicationsAdapter = new Job_Applications_Adapter(ListData);
                    recyclerView.setAdapter(jobApplicationsAdapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}