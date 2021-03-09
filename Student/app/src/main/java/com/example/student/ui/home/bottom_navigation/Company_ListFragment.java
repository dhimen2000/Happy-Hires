package com.example.student.ui.home.bottom_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.student.R;
import com.example.student.ui.home.bottom_navigation.recycleradapter.Apply_Jobs_RecyclerViewAdapter;
import com.example.student.ui.home.bottom_navigation.recycleradapter.Company_List_RecyclerViewAdapter;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Apply_Jobs_RecyclerViewModel;
import com.example.student.ui.home.bottom_navigation.recyclermodel.Company_List_RecyclerViewModel;


public class Company_ListFragment extends Fragment {

    RecyclerView recyclerView_Companylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Company_List_RecyclerViewModel[] companyListRecyclerViewModels = new Company_List_RecyclerViewModel[]{
                new Company_List_RecyclerViewModel("Software Company","99******77"),
                new Company_List_RecyclerViewModel("Business Company","88******77"),
                new Company_List_RecyclerViewModel("Construction Company","99******77"),
                new Company_List_RecyclerViewModel("Hardware Company","77******25"),
                new Company_List_RecyclerViewModel("Software Company","99******77"),
                new Company_List_RecyclerViewModel("Business Company","88******77"),
                new Company_List_RecyclerViewModel("Construction Company","99******77"),
                new Company_List_RecyclerViewModel("Hardware Company","77******25"),
                new Company_List_RecyclerViewModel("Software Company","99******77"),
                new Company_List_RecyclerViewModel("Business Company","88******77"),
                new Company_List_RecyclerViewModel("Construction Company","99******77"),
                new Company_List_RecyclerViewModel("Hardware Company","77******25")
        };

        View root = inflater.inflate(R.layout.fragment_company__list, container, false);
        recyclerView_Companylist = root.findViewById(R.id.recycler_company_list);

        Company_List_RecyclerViewAdapter companyListRecyclerViewAdapter = new Company_List_RecyclerViewAdapter(companyListRecyclerViewModels);
        recyclerView_Companylist.setHasFixedSize(true);
        recyclerView_Companylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_Companylist.setAdapter(companyListRecyclerViewAdapter);

        return root;
    }
}