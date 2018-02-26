package com.example.android.wash_dry_timer;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;


import android.widget.TextView;
import android.widget.Toast;

import com.example.android.wash_dry_timer.R;

import java.util.Timer;

import static com.example.android.wash_dry_timer.R.id.dryView;
import static com.example.android.wash_dry_timer.R.id.washView;


public class MainActivity extends Activity {
    //shows the washer's countdown
    TextView washCounter;

    //shows the dryer's countdown
    TextView dryCounter;

    //trigger the washer's countdown
    TextView washText;

    //trigger the dryer's countdown
    TextView dryText;

    //a Timer for washer to operates
    Timer washTmr;
    //a Timer for dryer to operates
    Timer dryTmr;

    //boolean that control if the timer should be canceled
    Boolean isCanceled;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the starting UI
        this.washCounter = (TextView) findViewById(R.id.washCounter);
        this.dryCounter = (TextView) findViewById(R.id.dryCounter);
        this.washText = (TextView) findViewById(washView);
        this.dryText = (TextView) findViewById(dryView);
        this.isCanceled = false;
        washTmr =  new Timer("timerForWasher", true);
        dryTmr = new Timer("timerForDrier", true);

        //temporary counter method
        washText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if(millisUntilFinished == 0)
                            return;
                        else if(isCanceled){
//                            Toast.makeText(getApplicationContext(),"isCanceled",
//                                    Toast.LENGTH_SHORT).show();
                            cancel();
                            return;
                        }

                        washCounter.setText("seconds remaining: " + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        Alert().show();
                        washCounter.setText("done!");
                    }
                }.start();
                washCounter.setText(R.string.show_time);


            }
        });

        dryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 500) {
                    public void onTick(long millisUntilFinished) {
                        if(millisUntilFinished == 0)
                            return;
                        else if(isCanceled){
//                            Toast.makeText(getApplicationContext(),"isCanceled",
//                                    Toast.LENGTH_SHORT).show();
                            cancel();
                            return;
                        }
                        dryCounter.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        Alert().show();
                        dryCounter.setText("done!");
                    }
                }.start();

                dryCounter.setText(R.string.show_time);
            }
        });

        //another way to do it
//        washView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                washTmr.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//
//
//                    }
//                }, 1000);
//
//            }
//        });
//
//        //another way to do it
//        dryView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dryTmr.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        //make a count sown timer when task is run
//                        Toast.makeText(getApplicationContext(),"DRY",
//                                Toast.LENGTH_SHORT).show();
//                        countingSwitch("dry");
//                    }
//                },0);
//            }
//        });
    }

    //handling button click
    public void onClick(View v){

        switch(v.getId()) {
            //if is reset button
            case R.id.resetBtn:
                Toast.makeText(getApplicationContext(),"this is reset Btn",
                        Toast.LENGTH_SHORT).show();
                this.isCanceled = true;
                this.reset();

                break;
            //if is pause button
            case R.id.pauseBtn:
                Toast.makeText(getApplicationContext(),"this is pause Btn",
                        Toast.LENGTH_SHORT).show();
                this.isCanceled = true;

                break;
        }
    }

    //reset the timer seen
    public void reset(){
        isCanceled = false;
        washCounter.setText(R.string.show_time);
        dryCounter.setText(R.string.show_time);
    }

    public AlertDialog Alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.time_on);
        // Create the AlertDialog object and return it
        return builder.create();
    }

//    public void countingSwitch(String i) {
//
//        switch(i) {
//            case "wash":
//                new CountDownTimer(10000, 1000) {
//                    public void onTick(long millisUntilFinished) {
//                        if (millisUntilFinished == 0)
//                            return;
//                        washCounter.setText("seconds remaining: " + millisUntilFinished / 1000);
//                    }
//
//                    public void onFinish() {
//                        washCounter.setText("done!");
//                    }
//                }.start();
//                break;
//            case "dry":
//                new CountDownTimer(10000, 1000) {
//                    public void onTick(long millisUntilFinished) {
//                        if (millisUntilFinished == 0)
//                            return;
//                        dryCounter.setText("seconds remaining: " + millisUntilFinished / 1000);
//                    }
//
//                    public void onFinish() {
//                        dryCounter.setText("done!");
//                    }
//                }.start();
//                break;
//        }
//    }

}
