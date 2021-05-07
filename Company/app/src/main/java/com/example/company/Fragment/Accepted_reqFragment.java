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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.company.Model.Schedule_Appoinment_Model;
import com.example.company.R;
import com.example.company.Adapter.Accepeted_request_Adapter;
import com.example.company.Model.Accepted_req_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Accepted_reqFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Schedule_Appoinment_Model> ListData1 = new ArrayList<>();
    DatabaseReference databaseReference;

String company_email;

   Accepeted_request_Adapter accepetedRequestAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_job_applications, container, false);

        recyclerView =root.findViewById(R.id.Schedule_appoiment);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        company_email = sh.getString("E-mail", "");
        Log.d("hello", company_email);

        databaseReference = FirebaseDatabase.getInstance().getReference();


       Query query= databaseReference.child("ScheduleAppoinment").orderByChild("companyemail").equalTo(company_email);
               query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Schedule_Appoinment_Model l=npsnapshot.getValue(Schedule_Appoinment_Model.class);
                        if(l.getStatus().equals("On-Hold"))
                        ListData1.add(l);

                    }

                    accepetedRequestAdapter = new Accepeted_request_Adapter(getActivity(),ListData1);
                    recyclerView.setAdapter(accepetedRequestAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
    }
}