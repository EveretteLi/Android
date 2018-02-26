package com.example.android.audiotexter;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button mPlayButton;
    Button mPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);


        mPlayButton = (Button) findViewById(R.id.play);
        mPauseButton = (Button) findViewById(R.id.pause);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this,"done",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mediaPlayer.pause();
            }
        });
    }
}
