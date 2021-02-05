package com.smartwebart.kingofquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2500l;
    private ImageView mAppLogoView;
    private TextView description;
    private String user;

    Animation topAnim, bottomAnim;

    private Handler mDelayHandler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove Title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        init();



        startAnimation();
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }

    private void init() {
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animator);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animator);
        mAppLogoView = findViewById(R.id.logo);
        description = findViewById(R.id.description);
    }

    private void startAnimation() {
        mAppLogoView.startAnimation(topAnim);
        description.startAnimation(bottomAnim);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}