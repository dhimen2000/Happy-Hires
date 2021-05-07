package com.example.student.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.student.Activity.HappyhiresdataActivity;
import com.example.student.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutusFragment extends Fragment {

    @BindView(R.id.aboutuscardview)
    CardView aboutus;
    @BindView(R.id.contactuscardview)
    CardView contactus;
    @BindView(R.id.mailuscardview)
    CardView mailus;
    @BindView(R.id.sharecardview)
    CardView share;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);
        ButterKnife.bind(this, root);

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HappyhiresdataActivity.class));
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            Integer.parseInt("123"));
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:7046636704")));
                }
            }
        });

        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Happy Hires");
                String app_url = "https://github.com/dhimen2000/Happy-Hires.git";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        return root;
    }
}