package com.example.spotifyclone;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    ImageView btnPlay, btnNext, btnPrev, btnForward, btnBackward, coverImg;
    TextView songTitle, tvStart, tvStop;
    SeekBar seekMusic;
    String sName;
    public static final String EXTRA_NAME= "song_name";

    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<File> mySongs;
    Thread updateSeekBar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home ){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);

//        getSupportActionBar().setTitle("Now Playing");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        songTitle = findViewById(R.id.tvSongName);
        tvStart = findViewById(R.id.tv_music_start);
        tvStop = findViewById(R.id.tv_music_tt);

        coverImg = findViewById(R.id.img_music_sc);

        btnPlay = findViewById(R.id.img_music_play);
        btnNext = findViewById(R.id.img_music_next);
        btnPrev = findViewById(R.id.img_music_previous);
        btnBackward = findViewById(R.id.img_music_backward);
        btnForward = findViewById(R.id.img_music_forward);

        seekMusic = findViewById(R.id.sb_music_sp);

        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
        String songName = i.getStringExtra("song name");
        position = bundle.getInt("pos", 0);
        songTitle.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(position).toString());
        sName =  mySongs.get(position).getName();
        songTitle.setText(sName);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer.start();

        updateSeekBar = new Thread(){
            @Override
            public void run(){
                int totalDuration =  mediaPlayer.getDuration();
                int currentPosition = 0;

                while (currentPosition<totalDuration){
                    try {
                        sleep(500);
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekMusic.setProgress(currentPosition);
                    }
                    catch (InterruptedException | IllegalStateException e ){
                        e.printStackTrace();
                    }
                }
            }
        };
        seekMusic.setMax(mediaPlayer.getDuration());
        updateSeekBar.start();
        seekMusic.getProgressDrawable().setColorFilter(getResources().getColor(R.color.primary_cyan), PorterDuff.Mode.MULTIPLY);
        seekMusic.getThumb().setColorFilter(getResources().getColor(R.color.fade_white),PorterDuff.Mode.SRC_IN);

        seekMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        String endTime = createTime(mediaPlayer.getDuration());
        songTitle.setText(endTime);

        final Handler h = new Handler();
        final int delay = 1000;

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = createTime(mediaPlayer.getCurrentPosition());
                songTitle.setText(sName);
                h.postDelayed(this, delay);
            }
        }, delay);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    btnPlay.setBackgroundResource(R.drawable.play_icon);
                }
                else {
                    mediaPlayer.start();
                    btnPlay.setBackgroundResource(R.drawable.pause_icon);
                }
            }
        });

        //next Listener
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnNext.performClick();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position+1)%mySongs.size());
                Uri u = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sName = mySongs.get(position).getName();
                // With this code to extract the song name without the file extension
                sName = mySongs.get(position).getName().replaceFirst("[.][^.]+$", "");
                songTitle.setText(sName);
                mediaPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.pause_icon);
                startAnimation(coverImg);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position-1)<0)?(mySongs.size()-1):(position-1);

                Uri u = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sName = mySongs.get(position).getName();
                // With this code to extract the song name without the file extension
                sName = mySongs.get(position).getName().replaceFirst("[.][^.]+$", "");
                songTitle.setText(sName);
                mediaPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.pause_icon);
                startAnimation(coverImg);
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
                }

            }
        });

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
                }
            }
        });

    }

    public void startAnimation(View v){
        ObjectAnimator objAnimator = ObjectAnimator.ofFloat(coverImg,"rotation",0f,360f);
        objAnimator.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objAnimator);
        animatorSet.start();

    }

    public String createTime(int duration){
        String time = "";
        int min = duration/1000/60;
        int sec = duration/1000%60;

        time+=min+"";

        if (sec<10){
            time+= 0;
        }
        time+=sec;
        return time;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}