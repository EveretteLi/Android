package com.example.android.gravetytest;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;

public class gyroscope extends Activity implements SensorEventListener {
    //a TextView
    private TextView showing;
    //the Sensor Manager
    private SensorManager sManager;
    private TextView direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        //get the TextView from the layout file
        showing = (TextView) findViewById(R.id.tester);

        //get a hook to the sensor service
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        direction = (TextView) findViewById(R.id.direction);

    }


    //when this Activity starts
    @Override
    protected void onResume()
    {
        super.onResume();
        /*register the sensor listener to listen to the gyroscope sensor, use the
        callbacks defined in this class, and gather the sensor information as quick
        as possible*/
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    //When this Activity isn't visible anymore
    @Override
    protected void onStop()
    {
        //unregister the sensor listener
        sManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        //Do nothing.
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        //if sensor is unreliable, return void
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }

        //else it will output the Roll, Pitch and Yawn values
        showing.setText(" X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
                " Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
                " Z (Yaw) :"+ Float.toString(event.values[0]));
        //x for freeze
        if(Math.abs(event.values[2]) - Math.abs(event.values[1])>1 && Math.abs(event.values[2]) -
                Math.abs(event.values[0])>1){
            //if x is largest change color to blue
            View background = findViewById(R.id.activity_gyroscope);
            background.setBackgroundColor(Color.BLUE);
            direction.setText("KEEP\nTHERE");
        }
        //y for back and forward: y>0 back y<0 forward
        else if(Math.abs(event.values[1]) - Math.abs(event.values[2])>1 && Math.abs(event.values[1]) -
                Math.abs(event.values[0])>1 && event.values[1]>0){
            //if y is largest change color to red
            View background = findViewById(R.id.activity_gyroscope);
            background.setBackgroundColor(Color.RED);
            direction.setText("FLY\nBACK");
        }
        else if(Math.abs(event.values[1]) - Math.abs(event.values[2])>1 && Math.abs(event.values[1]) -
                Math.abs(event.values[0])>1 && event.values[1]<0){
            //if y is largest change color to red
            View background = findViewById(R.id.activity_gyroscope);
            background.setBackgroundColor(Color.RED);
            direction.setText("FLY\nFORWARD");
        }
        //z for left and right:
        else if(Math.abs(event.values[0]) - Math.abs(event.values[2])>1 && Math.abs(event.values[0]) -
                Math.abs(event.values[1])>1 && event.values[0]<0){
            //if z is largest change color to green
            View background = findViewById(R.id.activity_gyroscope);
            background.setBackgroundColor(Color.GREEN);
            direction.setText("TURN\nRIGHT");
        }
        else if(Math.abs(event.values[0]) - Math.abs(event.values[2])>1 && Math.abs(event.values[0]) -
                Math.abs(event.values[1])>1 && event.values[0]>0){
            //if z is largest change color to green
            View background = findViewById(R.id.activity_gyroscope);
            background.setBackgroundColor(Color.GREEN);
            direction.setText("TURN\nLEFT");
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int action = MotionEventCompat.getActionMasked(event);
//
//        switch (action) {
//            case (MotionEvent.ACTION_DOWN):
//                Toast.makeText(getApplicationContext(), "step pedal",
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case (MotionEvent.ACTION_SCROLL):
//                Toast.makeText(getApplicationContext(), "pedal pressed",
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            case (MotionEvent.ACTION_UP):
//                Toast.makeText(getApplicationContext(), "loose pedal",
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onTouchEvent(event);
//        }
//    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN)
                    Toast.makeText(getApplicationContext(),"Gas > <",Toast.LENGTH_SHORT ).show();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN)
                    Toast.makeText(getApplicationContext(),"Brake = =",Toast.LENGTH_SHORT ).show();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
