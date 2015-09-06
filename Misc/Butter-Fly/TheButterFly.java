package com.example.jack.butterfly;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class ButterFly extends ActionBarActivity {

    boolean flap = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_fly);
        final ImageView theButterFly = (ImageView) findViewById(R.id.theButterFlyImage);
        TimerTask theTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        flap = !flap;
                        if (flap) {
                            theButterFly.setImageResource(R.drawable.butterfly1);
                        } else {
                            theButterFly.setImageResource(R.drawable.butterfly2);
                        }
                    }
                });
            }
        };
        Timer theTimer = new Timer();
        theTimer.scheduleAtFixedRate(theTask, 0, 100);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_butter_fly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
