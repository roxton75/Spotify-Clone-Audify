package com.example.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView sub_tv;
    Animation fadeInAnim;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        editor = preferences.edit();

        logo = findViewById(R.id.img_logo_splash);
        sub_tv = findViewById(R.id.subtext_splash);

        fadeInAnim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fadein);

        logo.setAnimation(fadeInAnim);
        sub_tv.setAnimation(fadeInAnim);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
}