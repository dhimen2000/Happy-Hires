package com.example.company.ui.Home.bottom_nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.company.R;
import com.example.company.ui.Home.bottom_nav.RecyclerView_Adapter.Student_List_Adapter;
import com.example.company.ui.Home.bottom_nav.Recyclerview_Model.Student_List_Model;


public class Student_ListFragment extends Fragment {


    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Student_List_Model[] studentListModels = new Student_List_Model[] {
                new Student_List_Model("shyam","8160437611","spshyam93@gmail.com"),
                new Student_List_Model("dhimen","7046636704","dhimen@gmail.com"),
                new Student_List_Model("Nilay","1234567890","nilay@gmail.com"),
                new Student_List_Model("shyam","8160437611","spshyam93@gmail.com"),
                new Student_List_Model("dhimen","7046636704","dhimen@gmail.com"),
                new Student_List_Model("Nilay","1234567890","nilay@gmail.com"),
                new Student_List_Model("shyam","8160437611","spshyam93@gmail.com"),
                new Student_List_Model("dhimen","7046636704","dhimen@gmail.com"),
                new Student_List_Model("Nilay","1234567890","nilay@gmail.com"),
                new Student_List_Model("shyam","8160437611","spshyam93@gmail.com"),
                new Student_List_Model("dhimen","7046636704","dhimen@gmail.com"),
                new Student_List_Model("Nilay","1234567890","nilay@gmail.com"),
                new Student_List_Model("shyam","8160437611","spshyam93@gmail.com"),
                new Student_List_Model("dhimen","7046636704","dhimen@gmail.com"),
                new Student_List_Model("Nilay","1234567890","nilay@gmail.com"),
        };
        View root = inflater.inflate(R.layout.fragment_student__list, container, false);
        recyclerView = root.findViewById(R.id.Student_list_recycler);
        Student_List_Adapter adapter= new Student_List_Adapter(studentListModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        return root;
    }
}