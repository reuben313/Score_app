package com.epic.score_app.viewlayer;

import com.epic.score_app.view.R;
import com.epic.score_app.view.R.layout;
import com.epic.score_app.view.R.menu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreenActivity extends Activity {

	// Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    private ProgressBar spinner;
    
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
 
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
                //spinner.setVisibility(View.GONE);
            }
        }, SPLASH_TIME_OUT);
    }
 
}