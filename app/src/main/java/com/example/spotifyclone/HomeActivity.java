package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView tv_greetings, tvAllSongs;
    ImageView cvTrend, cvHip, cvHind, cvEng, cvPop, cvPod, btnPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlaying = findViewById(R.id.currentPlaying); // Add this line to properly initialize btnPlaying

        cvTrend = findViewById(R.id.cvTrending);
        cvHip = findViewById(R.id.cvRap);
        cvHind = findViewById(R.id.cvHindi);
        cvEng = findViewById(R.id.cvEnglish);
        cvPod= findViewById(R.id.cvPodcasts);
        cvPop = findViewById(R.id.cvPop);

//          This Block of code consist of the greeting function
        String user = getIntent().getStringExtra("User");
//        //  Sets the greeting text with "Hey" in default color and the user name in your chosen color
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String greeting = "Hey ";
        builder.append(greeting);
        int start;
        start = builder.length();
        builder.append(user);
        int end;
        end = builder.length();
        builder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.fade_white)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_greetings = findViewById(R.id.tv_greetings);
        tv_greetings.setText(builder);


        cvTrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();

            }
        });

        cvHip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();
            }
        });

        cvHind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();
            }
        });

        cvEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();
            }
        });

        cvPod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();
            }
        });

        cvPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();
            }
        });

        tvAllSongs = findViewById(R.id.tvAllSongs);
        tvAllSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TracksActivity.class);
                startActivity(i);
                finish();

                Toast.makeText(HomeActivity.this,"Please Wait While fetching the Songs",Toast.LENGTH_LONG).show();
            }
        });

    }

}
