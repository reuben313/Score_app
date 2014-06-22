package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
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
import com.epic.score_app.viewlayer.adapters.MatchItemAdapter;
import com.epic.score_app.viewlayer.adapters.PlayerItemAdapter;

import domainmodel.Match;
import domainmodel.Player;

public class WedstrijdenActivity extends Activity {
	private ArrayList<Match> match= new ArrayList<Match>();
	private MatchItemAdapter adapter;
	private ListView wedstrijden_lijst;
	private int offset=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wedstrijden);
		adapter = new MatchItemAdapter(this, match);
		wedstrijden_lijst=(ListView) findViewById(R.id.ListViewMatch);
		wedstrijden_lijst.setAdapter(adapter);
		wedstrijden_lijst.setDivider(new ColorDrawable(0xff444444));
		wedstrijden_lijst.setDividerHeight(1);
		//wedstrijden_lijst.setOnItemClickListener(onplayerClick);
		setupActionBar();
	}
	
	
	@Override
	protected void onStart() {
		loadMatches();
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
		getMenuInflater().inflate(R.menu.wedstrijden, menu);
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
	
	
	
	public void loadMatches(){
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getmatches);
		b.putInt("limit", 20);
		b.putInt("offset", 0);
		b.putInt("compid", 1);
		ServiceProvider.getInsance().getData(b, matchhandler);
	}

	private Handler matchhandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ServiceProvider.getmatches_response:
				
				match= (ArrayList<Match>) msg.obj;
				
				adapter.addAll(match);
				offset+=20;
				break;
			default:
				break;
			}
		}
	};	


}
