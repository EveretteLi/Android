package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Color extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        setTitle("Colors");


        String [] mEnglish = {
                "Red","Green","Brown","Gray","Black","White","Dusty Yellow","Mustard Yellow"
        };
        String [] mMiwok = {
                "weṭeṭṭi","chokokki","ṭakaakki","ṭopoppi","kululli","kelelli","ṭopiisә","chiwiiṭә"
        };
        int [] mID = {
                R.drawable.color_red,R.drawable.color_green,R.drawable.color_brown,R.drawable.color_gray,
                R.drawable.color_black,R.drawable.color_white,R.drawable.color_dusty_yellow,
                R.drawable.color_mustard_yellow
        };

        final int [] mSoundID = {
                R.raw.color_red,R.raw.color_green,R.raw.color_brown,R.raw.color_gray,
                R.raw.color_black,R.raw.color_black,R.raw.color_white,R.raw.color_dusty_yellow,
                R.raw.color_mustard_yellow
        };

        ArrayList<Word> listOfColor = new ArrayList<Word>();
        for(int i = 0; i < 8; i++){
            listOfColor.add(new Word(mEnglish[i],mMiwok[i],mID[i],mSoundID[i]));
        }

        WordAdapter itemList = new WordAdapter(this, listOfColor);
        ListView listView = (ListView) findViewById(R.id.list_for_color);
        listView.setAdapter(itemList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mp = MediaPlayer.create(Color.this,mSoundID[position]);
                mp.start();
            }
        });
    }
}
