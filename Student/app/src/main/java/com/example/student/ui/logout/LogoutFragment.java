package com.example.student.ui.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student.LoginActivity;
import com.example.student.R;
import com.example.student.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;


public class LogoutFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_logout, container, false);


        firebaseAuth = FirebaseAuth.getInstance();

        sharedPreferences  = getActivity().getSharedPreferences("SharedPreference",MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(),LoginActivity.class));
                        //finish();

                        try {
                            firebaseAuth.signOut();
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.clear();
                            edit.apply();

                            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
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

        return root;
    }
}