package com.epic.score_app.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.epic.score_app.ViewPlayer;
import com.epic.score_app.adapters.PlayerItemAdapter;
import com.epic.score_app.model.Player;
import com.epic.score_app.services.NotificationProvider;
import com.epic.score_app.services.ServiceProvider;

public class ViewPlayersActivity extends ActionBarActivity {
private NotificationProvider s;

private int beginp=0;
private int offset=0;
private long begin;
private long end;
private ListView listOfPlayers;
private ArrayList<Player> players= new ArrayList<Player>();
private PlayerItemAdapter adapter;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_player);
		
	
		listOfPlayers = (ListView) findViewById(R.id.playerView);
		adapter = new PlayerItemAdapter(this, players);
	    listOfPlayers.setAdapter(adapter);
		
   
        listOfPlayers.setOnScrollListener(onScrollistener);
        listOfPlayers.setOnItemClickListener(onplayerClick);
		
	
	}
	@Override
		protected void onStart() {
		
		loadNextPlayers();
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
	
	
	
	
	 @Override
	  protected void onResume() {
	    
	 super.onResume();
	 Intent intent= new Intent(ViewPlayersActivity.this, NotificationProvider.class);
	    bindService(intent, mConnection,
	        Context.BIND_AUTO_CREATE);   
	 
		
	   
		
	    
	    
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    unbindService(mConnection);
	  }
	
	
	 private ServiceConnection mConnection = new ServiceConnection() {

		    public void onServiceConnected(ComponentName className, 
		        IBinder binder) {
		      NotificationProvider.MyBinder b = (NotificationProvider.MyBinder) binder;
		      s = b.getService();
		      Log.i("JAMAMI", "conected");
		    }

		    public void onServiceDisconnected(ComponentName className) {
		      s = null;
		    }
		  };



	
	
	@SuppressLint("NewApi")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case ServiceProvider.getPlayers_response:
				ArrayList<Player> array= (ArrayList<Player>) msg.obj;
				
				end=System.currentTimeMillis();
				offset+=20;
			    adapter.addAll(array);
				break;

			default:
				break;
			}
			}
		};
		
		
		private  OnScrollListener onScrollistener= new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				  if (listOfPlayers.getAdapter() == null)
				        return ;
				     
				    if (listOfPlayers.getAdapter().getCount() == 0)
				        return ;
				     
				    int l = visibleItemCount + firstVisibleItem;
				    if (l >= totalItemCount) {
				    
				      loadNextPlayers();
				    	
				    }
			
				
			}

			
		};
		
		
		
		
		private void loadNextPlayers() {
			Bundle b = new Bundle();
			b.putInt("requestcode", ServiceProvider.getPlayers);
			b.putInt("limit", 20);
			b.putInt("offset", offset);
			Log.i("VIEWPLAYERACTIVTY", "OFFSET:"+offset);
			begin= System.currentTimeMillis();
			ServiceProvider.getInsance().getData(b,handler);
			
		}
		
		
		private OnItemClickListener onplayerClick= new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				
				Player selectedPlayer= adapter.getItem(pos);
				Intent intent = new Intent(ViewPlayersActivity.this,ViewPlayer.class);
				Bundle b = new Bundle();
				b.putParcelable("player", selectedPlayer);
				intent.putExtras(b);
				
				startActivity(intent);
				
				
				
				
			}
			
			
		};
	

}
