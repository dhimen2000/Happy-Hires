package com.example.company.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.company.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    String user;
    Handler handler;

    Animation animBlink;

    @BindView(R.id.introduction)
    TextView blink;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.introanim);
        blink.setVisibility(View.VISIBLE);
        blink.startAnimation(animBlink);


        handler=new Handler();
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
                    }
                }
                catch (Exception e)
                {

                }

            }
        },3000);

    }
}