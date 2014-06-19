package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.adapters.PlayerItemAdapter;

import domainmodel.Player;

public class SpelerActivity extends Activity {
	private ArrayList<Player> spelers= new ArrayList<Player>();
	private PlayerItemAdapter adapter;
	private ListView spelers_list;
	private int offset=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speler);
		adapter = new PlayerItemAdapter(this, spelers);
		spelers_list=(ListView) findViewById(R.id.lijstplayers);
		spelers_list.setAdapter(adapter);
		spelers_list.setDivider(new ColorDrawable(0xff444444));
		spelers_list.setDividerHeight(1);
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
		b.putInt("requestcode", ServiceProvider.getPlayers);
		b.putInt("limit", 0);
		b.putInt("offset", 0);
		ServiceProvider.getInsance().getData(b, playershandler);
	}

	private Handler playershandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ServiceProvider.getPlayers_response:
				spelers= (ArrayList<Player>) msg.obj;
				adapter.addAll(spelers);
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
				  if (spelers_list.getAdapter() == null)
				        return ;

				    if (spelers_list.getAdapter().getCount() == 0)
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
			
			ServiceProvider.getInsance().getData(b,playershandler);

		}


		private OnItemClickListener onplayerClick= new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {

				Player selectedPlayer= adapter.getItem(pos);
				Intent intent = new Intent(SpelerActivity.this,ViewPlayer.class);
				Bundle b = new Bundle();
				b.putParcelable("player", selectedPlayer);
				intent.putExtras(b);

				startActivity(intent);




			}


		};
}
		
