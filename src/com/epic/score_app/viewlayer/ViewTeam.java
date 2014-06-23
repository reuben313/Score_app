package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.adapters.PlayerItemAdapter;

import domainmodel.Player;
import domainmodel.Team;

public class ViewTeam extends ActionBarActivity {
	private Team team;
	private String nm;
	private ArrayList<Player> pls= new ArrayList<Player>();
	private TextView name, players;
	private PlayerItemAdapter adapter;
	private ListView lijstvanSpelers;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_team);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		team= (Team)b.getSerializable("team");
		name = (TextView) findViewById(R.id.name);
		players = (TextView) findViewById(R.id.players);
		lijstvanSpelers = (ListView) findViewById(R.id.player_by_team_list);
       adapter = new PlayerItemAdapter(this, pls);
       lijstvanSpelers.setAdapter(adapter);
       lijstvanSpelers.setDivider(new ColorDrawable(0xff444444));
       lijstvanSpelers.setDividerHeight(1);		
	}
	
	@Override
	protected void onStart() {
		Bundle b = new Bundle();
	
		b.putInt("requestcode", ServiceProvider.getTeamPlayers);
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
			case ServiceProvider.getTeamPlayers_response:
			  ArrayList<Player> spelers = new ArrayList<Player>();
				spelers = (ArrayList<Player>) msg.obj;
				adapter.addAll(spelers);
				Log.i("players received", spelers.size()+"");
				
					
				break;

			default:
				break;
			}
			
		};
	};
	
	
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	

}
