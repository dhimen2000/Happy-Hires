package com.example.company.ui.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.company.R;
import com.example.company.ui.Home.bottom_nav.Job_ApplicationsFragment;
import com.example.company.ui.Home.bottom_nav.Companies_ListFragment;
import com.example.company.ui.Home.bottom_nav.Student_ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final BottomNavigationView bottomNavigationView = root.findViewById(R.id.bottom_nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Student_ListFragment()).commit();


        return root;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.student_list:
                    selectedFragment=new Student_ListFragment();
                    break;
                case R.id.available_job:
                    selectedFragment=new Companies_ListFragment();
                    break;
                case R.id.apply_job:
                    selectedFragment=new Job_ApplicationsFragment();
                    break;
            }

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };
}