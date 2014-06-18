package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.view.R.id;
import com.epic.score_app.view.R.layout;
import com.epic.score_app.viewlayer.adapters.TeamItemAdapter;

import domainmodel.Team;
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


public class TeamActivity extends Activity {
	private ArrayList<Team> teams= new ArrayList<Team>();
	private TeamItemAdapter adapter;
	private ListView teams_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
		adapter = new TeamItemAdapter(this, teams);
		teams_list=(ListView) findViewById(R.id.lijstspelers);
		teams_list.setAdapter(adapter);
		teams_list.setDivider(new ColorDrawable(0xff444444));
		teams_list.setDividerHeight(1);
		setupActionBar();
	}

	@Override
	protected void onStart() {
		loadTeams();
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

	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.team, menu);
	//		return true;
	//	}
	//
	//	@Override
	//	public boolean onOptionsItemSelected(MenuItem item) {
	//		switch (item.getItemId()) {
	//		case android.R.id.home:
	//			// This ID represents the Home or Up button. In the case of this
	//			// activity, the Up button is shown. Use NavUtils to allow users
	//			// to navigate up one level in the application structure. For
	//			// more details, see the Navigation pattern on Android Design:
	//			//
	//			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
	//			//
	//			NavUtils.navigateUpFromSameTask(this);
	//			return true;
	//		}
	//		return super.onOptionsItemSelected(item);
	//	}

	public void loadTeams(){
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getTeams);
		b.putInt("limit", 20);
		b.putInt("offset", 0);
		ServiceProvider.getInsance().getData(b, handler);
	}

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ServiceProvider.getTeams_response:
				teams= (ArrayList<Team>) msg.obj;
				adapter.addAll(teams);
				break;
			default:
				break;
			}
		}
	};	
}
