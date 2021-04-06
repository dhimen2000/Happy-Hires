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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.admin.Adapter.Company_Adapter;
import com.example.admin.Adapter.Student_Adapter;
import com.example.admin.Model.Company_Model;
import com.example.admin.Model.Student_Model;
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

public class StudentListFragment extends Fragment {

    @BindView(R.id.studentlist_recyclerview)
    RecyclerView Student_List_recyclerview;

    @BindView(R.id.studentsearchView)
    SearchView studentsearch;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of Companymodel class.
    private List<Student_Model> studentlist = new ArrayList<>();

    Student_Adapter adapter;

    StorageReference storageReference;
    DatabaseReference firebaseDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_student_list, container, false);
        ButterKnife.bind(this, root);

        Student_List_recyclerview.setHasFixedSize(true);
        Student_List_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseDatabase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        Student_Model model = postSnapshot.getValue(Student_Model.class);
                        studentlist.add(model);
                    }

                    adapter = new Student_Adapter(getActivity(),studentlist);
                    Student_List_recyclerview.setAdapter(adapter);

                    studentsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            if(studentlist.contains(query)){
                                adapter.getFilter().filter(query);
                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(getActivity(), "No Match found",Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            adapter.getFilter().filter(newText);
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
}