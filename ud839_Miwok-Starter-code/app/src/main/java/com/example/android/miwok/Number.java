package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;
import static com.example.android.miwok.R.color.tan_background;
import static com.example.android.miwok.R.id.numbers;

public class Number extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        setTitle("Numbers");


        String[] mEnglish = {
                "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
        };
        String[] mMiwok = {
                "Lutti", "Oṭiiko", "Tolookosu", "Oyyiisa", "Massokka", "Temmokka", "Kenekaku", "Kawinṭa",
                "Wo'e", "Na'aacha"
        };
        int[] mID = {
                R.drawable.number_one, R.drawable.number_two, R.drawable.number_three,
                R.drawable.number_four, R.drawable.number_five, R.drawable.number_six,
                R.drawable.number_seven, R.drawable.number_eight, R.drawable.number_nine,
                R.drawable.number_ten
        };

        final int[] mSoundID = {
                R.raw.number_one, R.raw.number_two, R.raw.number_three, R.raw.number_four,
                R.raw.number_five, R.raw.number_six, R.raw.number_seven, R.raw.number_eight,
                R.raw.number_nine, R.raw.number_ten
        };


        ArrayList<Word> Words = new ArrayList<Word>();
        for (int i = 0; i < 10; i++) {
            Words.add(new Word(mEnglish[i], mMiwok[i], mID[i], mSoundID[i]));
        }

        WordAdapter itemsAdapter = new WordAdapter(this, Words);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mp = MediaPlayer.create(Number.this, mSoundID[position]);
                mp.start();
            }
        });
    }

    private void releaseMediaPlayer(MediaPlayer mMediaPlayer) {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }

    }
}



