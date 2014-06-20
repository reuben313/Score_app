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
import com.epic.score_app.viewlayer.adapters.PouleAdapter;

import domainmodel.Group;

public class PouleActivity extends Activity {
	private ArrayList<Group> poule= new ArrayList<Group>();
	private PouleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poule);
		// Show the Up button in the action bar.
		
		adapter = new PouleAdapter(this, poule);
		ListView list = (ListView)findViewById(R.id.ListView1);
		list.setAdapter(adapter);
		list.setDivider(new ColorDrawable(0xff444444));
		list.setDividerHeight(1);
		
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.poule, menu);
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
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getGroup); 
		b.putInt("compid", 1);
		ServiceProvider.getInsance().getData(b, poulehandler);
	}
	
	
	private Handler poulehandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ServiceProvider.getGroup_response:
				ArrayList<Group> poulelist = (ArrayList<Group>)msg.obj;
				adapter.addAll(poulelist);
				break;
			default:
				break;
			}
		}
	};

}
