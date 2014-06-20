package com.epic.score_app.viewlayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Player;

public class ViewPlayer extends ActionBarActivity {
 private Player player;
 private String vn, an, nat, lf, gb, ps, hg, wg, ft;
 private TextView voornaam, achternaam, nationaliteit, leeftijd, 
 geboortedatum, positie, height, weight, foot;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_player);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		player= (Player)b.getSerializable("player");
		voornaam = (TextView) findViewById(R.id.voornaam);
		achternaam = (TextView) findViewById(R.id.achternaam);
		nationaliteit = (TextView) findViewById(R.id.nationality);
		voornaam.setText(player.getName());
		achternaam.setText(player.getLastname());
		nationaliteit.setText(player.getNationality());
		
		leeftijd = (TextView) findViewById(R.id.leeftijd);
		geboortedatum = (TextView) findViewById(R.id.date_of_birth);
		positie = (TextView) findViewById(R.id.position);
		height = (TextView) findViewById(R.id.height);
		weight = (TextView) findViewById(R.id.weight);
		foot = (TextView) findViewById(R.id.foot);
	
		
				
		
		

	
		
	}
	
	@Override
		protected void onStart() {
			Bundle b = new Bundle();
		
			b.putInt("requestcode", ServiceProvider.getLazyPlayer);
			b.putLong("player_id", player.getPlayer_id());
			ServiceProvider.getInsance().getData(b,handler);
			
			
			super.onStart();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.view_player, menu);
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
			
				Player temp = (Player) msg.obj;
				lf=temp.getAge();
				gb=temp.getDateOfbirth();
				ft=temp.getFoot();
				hg=temp.getHeight();
				wg=temp.getWeight();
			
			
				
				ps=temp.getPosition();
			
			
				leeftijd.setText(temp.getAge());
				geboortedatum.setText(gb);
				positie.setText(ps);
				height.setText(hg);
				weight.setText(wg);
				foot.setText(ft);
				Log.i("speler lazy succes", temp.getFoot());
		//hier moet je de andere atributen can de speler toevoegrn
				
				break;

			default:
				break;
			}
			
		};
	};
	
}
