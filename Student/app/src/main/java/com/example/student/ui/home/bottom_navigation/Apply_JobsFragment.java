package com.example.student.ui.home.bottom_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student.R;
import com.example.student.ui.home.bottom_navigation.recycleradapter.Apply_Jobs_RecyclerViewAdapter;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Apply_Jobs_RecyclerViewModel;


public class Apply_JobsFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Apply_Jobs_RecyclerViewModel [] applyJobsRecyclerViewModel = new Apply_Jobs_RecyclerViewModel[]{
                new Apply_Jobs_RecyclerViewModel("Spectrics Solution","spectricsolution@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("TCS","tcs@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Gateway Group","gatewaygroup@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Matrix Solution","Matrixsolution@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Spectrics Solution","spectricsolution@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("TCS","tcs@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Gateway Group","gatewaygroup@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Matrix Solution","Matrixsolution@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Spectrics Solution","spectricsolution@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("TCS","tcs@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Gateway Group","gatewaygroup@gmail.com"),
                new Apply_Jobs_RecyclerViewModel("Matrix Solution","Matrixsolution@gmail.com")
        };
        View root = inflater.inflate(R.layout.fragment_apply__jobs, container, false);
        recyclerView = root.findViewById(R.id.recycler_apply_jobs);

        Apply_Jobs_RecyclerViewAdapter applyJobsRecyclerViewAdapter = new Apply_Jobs_RecyclerViewAdapter(applyJobsRecyclerViewModel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(applyJobsRecyclerViewAdapter);

        return root;
    }
}