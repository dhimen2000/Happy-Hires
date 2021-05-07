package com.example.company.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.company.Activity.LoginActivity;
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

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                firebaseAuth = FirebaseAuth.getInstance();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    firebaseAuth.signOut();
                                    Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                }catch (Exception e)
                                {
                                    Log.d("e",e.getMessage());
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);


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