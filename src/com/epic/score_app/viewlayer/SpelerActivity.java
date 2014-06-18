package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.adapters.PlayerItemAdapter;


import domainmodel.Player;

public class SpelerActivity extends Activity {
	private ArrayList<Player> spelers= new ArrayList<Player>();
	private PlayerItemAdapter adapter;
	private ListView spelers_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speler);
		adapter = new PlayerItemAdapter(this, spelers);
		spelers_list=(ListView) findViewById(R.id.lijstplayers);
		spelers_list.setAdapter(adapter);
		setupActionBar();
	}

	
	@Override
	protected void onStart() {
		loadPlayers();
		super.onStart();
	}
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speler, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void loadPlayers(){
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getPlayer);
		b.putInt("limit", 20);
		b.putInt("offset", 0);
		ServiceProvider.getInsance().getData(b, playerhandler);
	}

	private Handler playerhandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ServiceProvider.getPlayer_response:
				spelers= (ArrayList<Player>) msg.obj;
				adapter.addAll(spelers);
				break;
			default:
				break;
			}
		}
	};	

}
