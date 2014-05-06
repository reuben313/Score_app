package com.epic.score_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.epic.score_app.model.Player;
import com.epic.score_app.services.ServiceProvider;

public class ViewPlayer extends ActionBarActivity {
 private Player player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_player);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		player= b.getParcelable("player");
		
		

	
		
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
				Toast.makeText(ViewPlayer.this, "response recieved", Toast.LENGTH_LONG).show();
				Player temp = (Player) msg.obj;
				
				
				break;

			default:
				break;
			}
			
		};
	};
	
}
