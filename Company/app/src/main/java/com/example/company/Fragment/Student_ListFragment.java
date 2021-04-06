package com.example.company.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.company.R;
import com.example.company.Adapter.Student_List_Adapter;
import com.example.company.Model.Student_List_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Student_ListFragment extends Fragment {


    private List<Student_List_Model> ListData1 = new ArrayList<>();

    // Creating Progress dialog
    ProgressDialog progressDialog;

    DatabaseReference databaseReference;
SearchView searchView;
    RecyclerView recyclerView;
    Student_List_Adapter studentListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_student__list, container, false);
        recyclerView = root.findViewById(R.id.Student_list_recycler);
        searchView=root.findViewById(R.id.studentsearchView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Student_List_Model l=npsnapshot.getValue(Student_List_Model.class);
                        ListData1.add(l);

                    }

                    studentListAdapter = new Student_List_Adapter(getActivity(),ListData1);
                    recyclerView.setAdapter(studentListAdapter);

                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {

                            if(ListData1.contains(query)){
                                studentListAdapter.getFilter().filter(query);
                                studentListAdapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(getActivity(), "No Match found",Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                               studentListAdapter.getFilter().filter(newText);
                            return false;
                        }
                    });


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

    private MenuInflater getMenuInflater() {
        return  null;
    }
}