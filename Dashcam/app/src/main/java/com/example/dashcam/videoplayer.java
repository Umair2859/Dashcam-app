package com.example.dashcam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

public class videoplayer extends AppCompatActivity {
    private VideoView videoView;
    private SeekBar seekBar;
    int currentPosition = 0;
    private String MEDIA_PATH = Environment.getExternalStorageDirectory().toString() + "/Movies/dashcamrec/";


    private Handler mHandler = new Handler();
    ImageView imgplps;

    @RequiresApi(api = Build.VERSION_CODES.N)

public void forward(View view){
    currentPosition=videoView.getCurrentPosition();
    currentPosition+=5000;
    videoView.seekTo(currentPosition);
    setProgress(currentPosition);

    }
    public void backword(View view){
        currentPosition=videoView.getCurrentPosition();
        currentPosition-=5000;
        videoView.seekTo(currentPosition);
        setProgress(currentPosition);

    }



    public void PlayButton(View view) {

        if (videoView.isPlaying()) {
            videoView.pause();
            imgplps = findViewById(R.id.btnplps);
            imgplps.setImageResource(R.drawable.play_white_btn);
            seekBar.setProgress(videoView.getCurrentPosition() / 1000);

        } else {
            videoView.start();
            imgplps = findViewById(R.id.btnplps);
            imgplps.setImageResource(R.drawable.paus_white_btn);
        }


        new CountDownTimer(videoView.getDuration(), 1) {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long l) {
                seekBar.setProgress(videoView.getCurrentPosition()/1000, true);

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        getSupportActionBar().hide();
        Intent i = getIntent();
        String url = i.getStringExtra("title");
        String fileName = url;
        File f = new File(MEDIA_PATH, fileName);

        Log.d("b", "onCreate: " + fileName);
        // Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();
        MediaPlayer mp = MediaPlayer.create(this, Uri.fromFile(f));
        int duration = mp.getDuration();
        mp.release();




        long du = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        videoView = findViewById(R.id.videoView);
        seekBar = findViewById(R.id.seekBar2);
        TextView txt = findViewById(R.id.duration);
        txt.setText(String.valueOf(du + "Seconds"));

        videoView.setVideoPath(String.valueOf(f));
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                seekBar.setMax(videoView.getDuration() / 1000);



            }
        });

       videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgplps = findViewById(R.id.btnplps);
                imgplps.setImageResource(R.drawable.play_white_btn);
                seekBar.setProgress(0);


            }
        });








        videoplayer.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (videoView != null) {
                    int mCurrentPosition = videoView.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

    }


}