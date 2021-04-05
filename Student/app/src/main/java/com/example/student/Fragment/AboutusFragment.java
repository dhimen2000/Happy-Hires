package com.example.student.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.student.R;


public class AboutusFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);
        final TextView textView = root.findViewById(R.id.text_aboutus);


        return root;

    }
    public boolean onBackPressed() {
        return false;
    }





}