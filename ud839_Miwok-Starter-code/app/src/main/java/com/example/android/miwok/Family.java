package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        setTitle("Family Members");

        String [] mEnglish = {
                "Father","Mother","Son","Doughter","Older Brother","Younger Brother",
                "Older Sister","Young Sister","Grandmother","Grandfather"
        };
        String [] mMiwok = {
                "әpә","әṭa","angsi","tune","taachi","chalitti","teṭe","kolliti",
                "ama","paapa"
        };
        int [] mID = {
                R.drawable.family_father,R.drawable.family_mother,R.drawable.family_son,
                R.drawable.family_daughter,R.drawable.family_older_brother,R.drawable.family_younger_brother,
                R.drawable.family_older_sister, R.drawable.family_younger_sister,R.drawable.family_grandmother,
                R.drawable.family_grandfather,
        };

        final int [] mSoundID = {
                R.raw.family_father,R.raw.family_mother,R.raw.family_son,R.raw.family_daughter,
                R.raw.family_daughter,R.raw.family_older_brother,R.raw.family_younger_brother,
                R.raw.family_older_sister,R.raw.family_younger_sister,R.raw.family_grandmother,
                R.raw.family_grandfather
        };

        ArrayList<Word> listOfFamily = new ArrayList<Word>();
        for(int i = 0; i < 10; i++){
            listOfFamily.add(new Word(mEnglish[i], mMiwok[i],mID[i],mSoundID[i]));
        }

        WordAdapter familyList = new WordAdapter(this, listOfFamily);
        ListView itemList = (ListView) findViewById(R.id.family_list);
        itemList.setAdapter(familyList);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mp = MediaPlayer.create(Family.this,mSoundID[position]);
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
