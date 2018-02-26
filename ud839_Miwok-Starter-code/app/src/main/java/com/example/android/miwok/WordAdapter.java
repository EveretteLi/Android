package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LYP_Ev on 1/18/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        Word crrentWord = getItem(position);

        TextView miwokWordView = (TextView) v.findViewById(R.id.miwok_word);
        miwokWordView.setText(crrentWord.getMiwok());

        TextView englishView = (TextView) v.findViewById(R.id.english_word);
        englishView.setText(crrentWord.getEnglish());

        ImageView picture = (ImageView) v.findViewById(R.id.image);
        picture.setImageResource(crrentWord.getId());

        return v;
    }
}

