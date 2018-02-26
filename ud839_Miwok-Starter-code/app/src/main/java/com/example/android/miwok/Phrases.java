package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrass);
        setTitle("Phrases");

        String [] mEnglish = {
                "Where are you going?","What is your name?","My name is...","How are you feeling?",
                "I’m feeling good.","Are you coming?","Yes, I’m coming.","I’m coming.","Let’s go.",
                "Come here."
        };
        String [] mMiwok = {
                "minto wuksus","tinnә oyaase'nә","oyaaset...","michәksәs?","kuchi achit","әәnәs'aa?",
                "hәә’ әәnәm","әәnәm", "yoowutis","әnni'nem"
        };

        final int [] mSoundID = {
                R.raw.phrase_where_are_you_going,R.raw.phrase_what_is_your_name,R.raw.phrase_my_name_is,
                R.raw.phrase_how_are_you_feeling,R.raw.phrase_im_feeling_good,R.raw.phrase_are_you_coming,
                R.raw.phrase_yes_im_coming,R.raw.phrase_im_coming,R.raw.phrase_lets_go,R.raw.phrase_come_here,
        };

        ArrayList<Word> listOfPhrases = new ArrayList<Word>();
        for(int i = 0; i < 10; i++){
            listOfPhrases.add(new Word(mEnglish[i],mMiwok[i],0,mSoundID[i]));
        }

        WordAdapter PhrasesList = new WordAdapter(this, listOfPhrases);
        ListView itemList = (ListView) findViewById(R.id.list_of_phrases);
        itemList.setAdapter(PhrasesList);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mp = MediaPlayer.create(Phrases.this,mSoundID[position]);
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
