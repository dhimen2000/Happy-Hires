package com.example.student.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.Adapter.Company_List_Adapter;
import com.example.student.Model.Company_List_Model;
import com.example.student.R;
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


public class Company_ListFragment extends Fragment {

    @BindView(R.id.recycler_company_list)
    RecyclerView Company_List_recyclerview;

    @BindView(R.id.companysearchView)
    SearchView companysearch;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of Companymodel class.
    private List<Company_List_Model> list = new ArrayList<>();

    Company_List_Adapter adapter;

    StorageReference storageReference;
    DatabaseReference firebaseDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_company__list, container, false);
        ButterKnife.bind(this, root);


        Company_List_recyclerview.setHasFixedSize(true);
        Company_List_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(getActivity());
        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Data....");
        // Showing progress dialog.
        progressDialog.show();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseDatabase.child("Company").child("").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                        Company_List_Model model = postSnapshot.getValue(Company_List_Model.class);
                        list.add(model);
                    }

                    adapter = new Company_List_Adapter(getActivity(),list);
                    Company_List_recyclerview.setAdapter(adapter);

                    companysearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            if(list.contains(query)){
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