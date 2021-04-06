package com.example.company.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.company.Activity.LoginActivity;
import com.example.company.R;

import com.google.firebase.auth.FirebaseAuth;




public class LogoutFragment extends Fragment {



    private FirebaseAuth firebaseAuth;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        //NavigationView navigationView = root.findViewById(R.id.nav_logout);
        firebaseAuth = FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                        //finish();
                        firebaseAuth.signOut();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


        return root;
    }
}