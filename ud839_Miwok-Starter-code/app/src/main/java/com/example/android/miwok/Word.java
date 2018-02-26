package com.example.android.miwok;

import java.util.ArrayList;

/**
 * Created by LYP_Ev on 1/18/17.
 */

public class Word {

    private String english;
    private String miwok;
    private int ID;
    private int soundID;

    public Word(String english,String miwok,int id,int soundID){
        this.english = english;
        this.miwok = miwok;
        this.ID = id;
        this.soundID = soundID;
    }

    public String getEnglish(){return this.english;}
    public String getMiwok(){return this.miwok;}
    public int getId(){return this.ID;}
    public int getSoundID(){return this.soundID;}


}
