package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.adapters.StadiumItemAdapter;

import domainmodel.Stadium;

public class StadiumActivity extends Activity {
	private ArrayList<Stadium> stadiums= new ArrayList<Stadium>();
	private StadiumItemAdapter adapter;
	private ListView stadion_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station);
		adapter = new StadiumItemAdapter(this, stadiums);
		stadion_list=(ListView) findViewById(R.id.lijststadion);
		stadion_list.setAdapter(adapter);
		stadion_list.setDivider(new ColorDrawable(0xff444444));
		stadion_list.setDividerHeight(1);
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
	
	//service provider moet eerst gefixed worden
//	public void loadStadions(){
//		Bundle b = new Bundle();
//		b.putInt("requestcode", ServiceProvider.getStadiums);
//		b.putInt("limit", 20);
//		b.putInt("offset", 0);
//		ServiceProvider.getInsance().getData(b, handler);
//	}
//	
//	private Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case ServiceProvider.getStadiums_response:
//				stadiums= (ArrayList<Stadium>) msg.obj;
//				adapter.addAll(stadiums);
//				break;
//			default:
//				break;
//			}
//		}
//	};
}
