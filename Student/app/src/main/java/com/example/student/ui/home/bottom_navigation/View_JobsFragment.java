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
import com.example.student.ui.home.bottom_navigation.recycleradapter.Company_List_RecyclerViewAdapter;
import com.example.student.ui.home.bottom_navigation.recycleradapter.View_Jobs_RecyclerViewAdapter;
import com.example.student.ui.home.bottom_navigation.recyclermodel.View_Jobs_RecyclerViewModel;


public class View_JobsFragment extends Fragment {

    RecyclerView recyclerView_Joblist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View_Jobs_RecyclerViewModel [] viewJobsRecyclerViewModels = new View_Jobs_RecyclerViewModel[]{
                new View_Jobs_RecyclerViewModel("Android Jobs","15"),
                new View_Jobs_RecyclerViewModel("Python Jobs","20"),
                new View_Jobs_RecyclerViewModel("C# Jobs","30"),
                new View_Jobs_RecyclerViewModel("IOS Jobs","25"),
                new View_Jobs_RecyclerViewModel("Android Jobs","15"),
                new View_Jobs_RecyclerViewModel("Python Jobs","20"),
                new View_Jobs_RecyclerViewModel("C# Jobs","30"),
                new View_Jobs_RecyclerViewModel("IOS Jobs","25"),
                new View_Jobs_RecyclerViewModel("Android Jobs","15"),
                new View_Jobs_RecyclerViewModel("Python Jobs","20"),
                new View_Jobs_RecyclerViewModel("C# Jobs","30"),
                new View_Jobs_RecyclerViewModel("IOS Jobs","25")
        };

        View root = inflater.inflate(R.layout.fragment_view__jobs, container, false);
        recyclerView_Joblist = root.findViewById(R.id.recyclerview_view_jobs);

        View_Jobs_RecyclerViewAdapter viewJobsRecyclerViewAdapter = new View_Jobs_RecyclerViewAdapter(viewJobsRecyclerViewModels);
        recyclerView_Joblist.setHasFixedSize(true);
        recyclerView_Joblist.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_Joblist.setAdapter(viewJobsRecyclerViewAdapter);

        return root;

    }
}