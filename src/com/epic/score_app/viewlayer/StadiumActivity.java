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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.adapters.StadiumItemAdapter;

import domainmodel.Stadium;

public class StadiumActivity extends Activity {
	private ArrayList<Stadium> stadiums= new ArrayList<Stadium>();
	private StadiumItemAdapter adapter;
	private ListView stadion_list;
	private int offset = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station);
		adapter = new StadiumItemAdapter(this, stadiums);
		stadion_list=(ListView) findViewById(R.id.lijststadion);
		stadion_list.setAdapter(adapter);
		stadion_list.setDivider(new ColorDrawable(0xff444444));
		stadion_list.setDividerHeight(1);
		stadion_list.setOnItemClickListener(onstadiumClick);
		setupActionBar();
	}
	
	@Override
	protected void onStart() {
		loadStadium();
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
	
	public void loadStadium(){
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getStadiums);
		b.putInt("limit", 20);
		b.putInt("offset", 0);
		b.putInt("compid", 1);
		ServiceProvider.getInsance().getData(b, stadiumhandler);
	}

	private Handler stadiumhandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ServiceProvider.getStadiums_response:
				stadiums= (ArrayList<Stadium>) msg.obj;
				adapter.addAll(stadiums);
				offset+=20;
				break;
			default:
				break;
			}
		}
	};
	
	
	private OnItemClickListener onstadiumClick= new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {

			Stadium selectedStadium= adapter.getItem(pos);
			Log.i("selected stadium id", selectedStadium.getStadium_id()+"");
			Intent intent = new Intent(StadiumActivity.this,ViewStadium.class);
			Bundle b = new Bundle();
			//hotfix
			b.putSerializable("stadium", selectedStadium);
			//b.putParcelable("player", selectedPlayer);
			intent.putExtras(b);

			startActivity(intent);

		}


	};
}
