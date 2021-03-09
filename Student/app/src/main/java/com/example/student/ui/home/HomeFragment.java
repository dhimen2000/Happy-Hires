package com.example.student.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.student.R;
import com.example.student.ui.home.bottom_navigation.Apply_JobsFragment;
import com.example.student.ui.home.bottom_navigation.Company_ListFragment;
import com.example.student.ui.home.bottom_navigation.View_JobsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final BottomNavigationView bottomNavigationView = root.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Company_ListFragment()).commit();

        return root;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.bottom_company_list:
                    selectedFragment = new Company_ListFragment();
                    break;
                case R.id.bottom_view_jobs:
                    selectedFragment = new View_JobsFragment();
                    break;
                case R.id.bottom_apply_jobs:
                    selectedFragment = new Apply_JobsFragment();
                    break;
            }

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };
}