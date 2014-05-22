package com.epic.score_app.view;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button teams, spelers, wedstrijden, poule, settings, nieuws;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		teams = (Button) findViewById(R.id.Button01);
		spelers = (Button) findViewById(R.id.Button02);
		wedstrijden = (Button) findViewById(R.id.Button03);
		poule = (Button) findViewById(R.id.Button04);
		nieuws = (Button) findViewById(R.id.Button05);
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
