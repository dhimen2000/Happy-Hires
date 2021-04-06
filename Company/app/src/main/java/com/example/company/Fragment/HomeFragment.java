package com.example.company.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.company.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
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

                    case R.id.apply_job:
                    selectedFragment=new Job_ApplicationFragment();
                    break;
                    case R.id.accepted_list:
                        selectedFragment=new Accepted_reqFragment();
            }

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };



}