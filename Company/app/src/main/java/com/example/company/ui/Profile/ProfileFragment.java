package com.example.company.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.company.R;
import com.example.company.Register_Adapter;
import com.example.company.Register_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
//private List<Register_Model> ListData;
//
//DatabaseReference databaseReference;
//
//RecyclerView recyclerView;
//Register_Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

//        recyclerView = root.findViewById(R.id.profile_rvl);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        ListData = new ArrayList<>();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists())
//                {
//                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
//                        Register_Model l=npsnapshot.getValue(Register_Model.class);
//                        ListData.add(l);
//                    }
//
//                    adapter= new Register_Adapter(ListData);
//                    recyclerView.setAdapter(adapter);
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return root;

    }
    }