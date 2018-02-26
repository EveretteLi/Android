package com.example.android.prcticeset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.ID;


public class MainActivity extends AppCompatActivity {
    private int score;
    private int scoreA = 0;
    private int scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

    /**
     * get the score of team.
     * @param ID
     * @return
     */
    public void display(int ID){
        TextView scoreView = (TextView) findViewById(ID);
        if(score < 100) {
            String text = "" +score;
            scoreView.setTextSize(112f);
            scoreView.setText(text);
            scoreView.setText(text);
        }
        else{
            String text = "" +score;
            scoreView.setTextSize(80.0f);
            scoreView.setText(text);
        }
    }


    public void addThree(View Btn){
        int ID = Btn.getId();
        if(ID == R.id.a_1 || ID ==R.id.a_2 || ID ==R.id.a_3){
            scoreA = scoreA + 3;
            score = scoreA;
            display(R.id.team_a_point);
        }
        else{
            scoreB = scoreB +3;
            score = scoreB;
            display(R.id.team_b_point);
        }



    }

    public void addTwo(View Btn){
        int ID = Btn.getId();
        if(ID == R.id.a_1||ID == R.id.a_2||ID == R.id.a_3){
            scoreA = scoreA +2;
            score = scoreA;
            display(R.id.team_a_point);
        }
        else{
            scoreB = scoreB +2;
            score = scoreB;
            display(R.id.team_b_point);
        }

    }

    public void addOne(View Btn){
        int ID = Btn.getId();
        if(ID == R.id.a_1||ID == R.id.a_2||ID == R.id.a_3){
            scoreA = scoreA +1;
            score = scoreA;
            display(R.id.team_a_point);
        }
        else{
            scoreB = scoreB +1;
            score = scoreB;
            display(R.id.team_b_point);
        }
    }

    public void restart(View view){
        score = 0;
        scoreA = 0;
        scoreB = 0;
        display(R.id.team_b_point);
        display(R.id.team_a_point);
    }


}
