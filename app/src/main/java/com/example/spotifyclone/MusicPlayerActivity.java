package com.example.spotifyclone;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView tv_song_name,tv_start_time,tv_total_time;
    ImageView img_song_cover,icon_previous,icon_backward,icon_play,icon_forward,icon_next;
    SeekBar sb_song_progress;
    MediaPlayer mMediaPlayer;
    private int currentIndex = 0;

    private static int sTime = 0, eTime = 0, oTime = 0, bTime = 5000, fTime = 5000;

    Handler h = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        tv_song_name = findViewById(R.id.tv_music_sn);
        tv_start_time = findViewById(R.id.tv_music_start);
        tv_total_time = findViewById(R.id.tv_music_tt);

        img_song_cover = findViewById(R.id.img_music_sc);
        icon_previous = findViewById(R.id.img_music_previous);
        icon_backward = findViewById(R.id.img_music_backward);
        icon_play = findViewById(R.id.img_music_play);
        icon_forward = findViewById(R.id.img_music_forward);
        icon_next = findViewById(R.id.img_music_next);

        sb_song_progress = findViewById(R.id.sb_music_sp);

        ArrayList<Integer> songArrayList = new ArrayList<>();
        songArrayList.add(0,R.raw.perfect);
        songArrayList.add(1,R.raw.say_you_wont_let_go);
        songArrayList.add(2,R.raw.lonely_city);
        songArrayList.add(3,R.raw.before_you_go);
        songArrayList.add(4,R.raw.the_night_we_meet);
        songArrayList.add(5,R.raw.harry_styles_falling);
        songArrayList.add(6,R.raw.falling_like_the_stars);
        songArrayList.add(7,R.raw.mashup_top_10_acoustic);

        mMediaPlayer = MediaPlayer.create(MusicPlayerActivity.this,songArrayList.get(currentIndex));

        icon_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer != null && mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.pause();
                    icon_play.setImageResource(R.drawable.play_icon);
                }
                else
                {
                    mMediaPlayer.start();
                    icon_play.setImageResource(R.drawable.pause_icon);
                }

                eTime = mMediaPlayer.getDuration(); //total duration 3 min 25 sec
                sTime = mMediaPlayer.getCurrentPosition(); //current position

                if(oTime == 0)
                {
                    sb_song_progress.setMax(eTime);
                    oTime = 1;
                }

                tv_start_time.setText(String.format("%d min,%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                tv_total_time.setText(String.format("%d min,%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                sb_song_progress.setProgress(sTime);
                h.postDelayed(UpdateSongTime,1000);

                songName();
            }
        });

        icon_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0)
                {
                    currentIndex--;
                }
                else
                {
                    currentIndex = songArrayList.size() -1;
                }
                if (mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                if (mMediaPlayer != null)
                {
                    icon_play.setImageResource(R.drawable.pause_icon);
                }

                mMediaPlayer = MediaPlayer.create(MusicPlayerActivity.this,songArrayList.get(currentIndex));

                eTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();
                oTime = 0;
                if(oTime == 0)
                {
                    sb_song_progress.setMax(eTime);
                    oTime = 1;
                }

                tv_start_time.setText(String.format("%d min,%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                tv_total_time.setText(String.format("%d min,%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                ));

                sb_song_progress.setProgress(sTime);
                h.postDelayed(UpdateSongTime,1000);

                mMediaPlayer.start();
                songName();
            }
        });

        icon_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < songArrayList.size()-1)
                {
                    currentIndex++;
                }
                else
                {
                    currentIndex = 0;
                }
                if (mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                if (mMediaPlayer != null)
                {
                    icon_play.setImageResource(R.drawable.pause_icon);
                }

                mMediaPlayer = MediaPlayer.create(MusicPlayerActivity.this,songArrayList.get(currentIndex));
                mMediaPlayer.start();
                songName();
            }
        });

        icon_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sTime - bTime) > 0)
                {
                    sTime = sTime - bTime;
                    mMediaPlayer.seekTo(sTime);
                }
                else
                {
                    Toast.makeText(MusicPlayerActivity.this,"Cannot jump backward for 5 sec",Toast.LENGTH_SHORT).show();
                }
            }
        });

        icon_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sTime + bTime) < eTime)
                {
                    sTime = sTime + fTime;
                    mMediaPlayer.seekTo(sTime);
                }
                else
                {
                    Toast.makeText(MusicPlayerActivity.this,"Cannot jump forward for % sec",Toast.LENGTH_SHORT).show();
                }
            }
        });

        sb_song_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                {
                    mMediaPlayer.seekTo(progress);
                    sb_song_progress.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void songName() {
        if (currentIndex == 0)
        {
            tv_song_name.setText("Perfect - edSheran");
            img_song_cover.setImageResource(R.drawable.perfect_edsheran_cover);
        }
        if (currentIndex == 1)
        {
            tv_song_name.setText("Say you won't let go - James Arthur");
            img_song_cover.setImageResource(R.drawable.say_u_wont_let_go);
        }
        if (currentIndex == 2)
        {
            tv_song_name.setText("Lonely City - Mokita");
            img_song_cover.setImageResource(R.drawable.lonely_night);
        }
        if (currentIndex == 3)
        {
            tv_song_name.setText("Before You Go");
            img_song_cover.setImageResource(R.drawable.before_u_go);
        }
        if (currentIndex == 4)
        {
            tv_song_name.setText("The Night We Meet");
            img_song_cover.setImageResource(R.drawable.the_night_we_meet);
        }
        if (currentIndex == 5)
        {
            tv_song_name.setText("Falling - Harry Styles");
            img_song_cover.setImageResource(R.drawable.falling_harry_styles);
        }
        if (currentIndex == 6)
        {
            tv_song_name.setText("Falling Like the Stars - james Arthur");
            img_song_cover.setImageResource(R.drawable.falling_like_the_stars);
        }
        if (currentIndex == 7)
        {
            tv_song_name.setText("Romanticized Top 10 Songs Mashup");
            img_song_cover.setImageResource(R.drawable.mashup_cover_pic);
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mMediaPlayer.getCurrentPosition();
            tv_start_time.setText(String.format("%d min,%d sec",
                    TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));

            sb_song_progress.setProgress(sTime);
            h.postDelayed(this,1000);
        }

    };


}