package com.epic.score_app.viewlayer;



import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.view.R.id;
import com.epic.score_app.view.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private ImageButton teams, spelers, wedstrijden, poule, nieuws, media, stadions, settings;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();

		teams = (ImageButton) findViewById(R.id.Button01);
		spelers = (ImageButton) findViewById(R.id.Button02);
		wedstrijden = (ImageButton) findViewById(R.id.Button03);
		poule = (ImageButton) findViewById(R.id.Button04);
		nieuws = (ImageButton) findViewById(R.id.Button05);
		media = (ImageButton) findViewById(R.id.Button06);
		stadions = (ImageButton) findViewById(R.id.Button07);
		settings = (ImageButton) findViewById(R.id.Button08);
	

		teams.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TeamActivity.class);
				startActivity(i);		
			}			
		});

		spelers.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SpelerActivity.class);
				startActivity(i);		
			}			
		});
		
		wedstrijden.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, WedstrijdenActivity.class);
				startActivity(i);		
			}			
		});
		
		poule.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, PouleActivity.class);
				startActivity(i);		
			}			
		});
		
		nieuws.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, NieuwsActivity.class);
				startActivity(i);		
			}			
		});
		
		media.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MediaActivity.class);
				startActivity(i);		
			}			
		});
		
		stadions.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, StadiumActivity.class);
				startActivity(i);		
			}			
		});
		
		settings.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(i);		
			}			
		});


	

	}
	
	@Override
	protected void onStart() {
		
		super.onStart();
		
	}

//	is niet nodig omdat er een instellingen knop is, staat ook niet mooi.
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	
	

}
