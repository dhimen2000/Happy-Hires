package com.example.student.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.student.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    String user;
    Animation animBlink;

    @BindView(R.id.introduction)
    TextView blink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.introanim);
        blink.setVisibility(View.VISIBLE);
        blink.startAnimation(animBlink);

        firebaseAuth = FirebaseAuth.getInstance();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (firebaseAuth.getCurrentUser()  != null) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception e)
                {

                }

            }
        },3000);
    }
}