package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Player;
import domainmodel.Team;

public class ViewTeam extends ActionBarActivity {
	private Team team;
	private String nm;
	ArrayList<Player> pls;
	private TextView name, players;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_team);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		team= (Team)b.getSerializable("team");
		name = (TextView) findViewById(R.id.name);
		players = (TextView) findViewById(R.id.players);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	@Override
	protected void onStart() {
		Bundle b = new Bundle();
	
		b.putInt("requestcode", ServiceProvider.getLazyPlayer);
		b.putLong("team_id", team.getTeamId());
		ServiceProvider.getInsance().getData(b,handler);
		
		
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.view_team, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			 switch (msg.what) {
			case ServiceProvider.getLazyPlayer_response:
			
				Team temp = (Team) msg.obj;
				
				nm = team.getName();
				pls = team.getPlayers();
				
				if(nm == "null"){
					nm = "GEEN TEAM BEKEND";
					name.setText(nm);
				}else{
				name.setText(nm);
			  }			
				break;

			default:
				break;
			}
			
		};
	};
	
	
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_view_team,
					container, false);
			return rootView;
		}
	}

}
