package com.example.company.ui.Home.bottom_nav;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.company.Company_data_cardviewActivity;
import com.example.company.R;
import com.example.company.ui.Home.bottom_nav.RecyclerView_Adapter.Companies_List_Adapter;
import com.example.company.ui.Home.bottom_nav.Recyclerview_Model.Companies_list_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Companies_ListFragment extends Fragment {

private List<Companies_list_Model> ListData;

DatabaseReference databaseReference;

RecyclerView recyclerView;
Companies_List_Adapter companiesListAdapter;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     //   ListData = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_companies_list, container, false);



        recyclerView = root.findViewById(R.id.companies_list_rvl);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ListData = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Companies_list_Model l=npsnapshot.getValue(Companies_list_Model.class);
                        ListData.add(l);

                    }

                    companiesListAdapter = new Companies_List_Adapter(ListData);
                    recyclerView.setAdapter(companiesListAdapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}