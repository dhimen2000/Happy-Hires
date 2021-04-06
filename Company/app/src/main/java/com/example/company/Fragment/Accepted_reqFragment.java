package com.example.company.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

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


public class Accepted_reqFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Accepted_req_model> ListData1 = new ArrayList<>();
    DatabaseReference databaseReference;
ImageView imageView;
String jobid;


   Accepeted_request_Adapter accepetedRequestAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_job_applications, container, false);

        recyclerView =root.findViewById(R.id.Schedule_appoiment);
//        Student_List_Adapter adapter= new Student_List_Adapter(studentListModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         imageView= (ImageView)root.findViewById(R.id.dustbin);
        databaseReference = FirebaseDatabase.getInstance().getReference();


       Query query= databaseReference.child("ApplicationForJob").orderByChild("status").equalTo("Accepted");
               query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Accepted_req_model l=npsnapshot.getValue(Accepted_req_model.class);
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
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("id",jobid);
//                databaseReference.child("ApplicationForJob").child(jobid).addListenerForSingleValueEvent(
//                        new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//                                    appleSnapshot.getRef().removeValue();
//
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//
//
//                        });
//            }
//        });

        return root;
    }
}