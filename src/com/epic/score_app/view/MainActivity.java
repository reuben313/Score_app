package com.epic.score_app.view;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private Button settings;
	private ImageButton teams, spelers, wedstrijden, poule, nieuws;


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
		settings = (Button) findViewById(R.id.Button08);
	

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
		
		settings.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(i);		
			}			
		});


	

	}

//	is niet nodig omdat er een instellingen knop is, staat ook niet mooi.
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
