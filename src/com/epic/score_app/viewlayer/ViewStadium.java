package com.epic.score_app.viewlayer;

import Utils.Tuple;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.LruCache;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.News;
import domainmodel.Stadium;

public class ViewStadium extends Activity {
	 private TextView title;
	    private ImageView photo;
	    private ListView desc;
		private LruCache<String, Bitmap> mMemoryCache;
		ArrayAdapter<String> arrayAdapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_description);
		init();
		
		Intent intent= getIntent();
		Bundle b = intent.getExtras();
		Stadium stadium = (Stadium) b.getSerializable("stadium");
		
		title=(TextView)findViewById(R.id.news_desc_title);
		photo=(ImageView) findViewById(R.id.news_desc_image);
		title.setText(stadium.getName());
		
		ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(stadium, photo));
		desc=(ListView) findViewById(R.id.news_desc_description);
		String arr[] = {""};
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
		desc.setAdapter(arrayAdapter);
		Bundle sendBundle = new Bundle();
		sendBundle.putInt("requestcode", ServiceProvider.getStadiums);
		sendBundle.putLong("stadium_id", stadium.getStadium_id());
		ServiceProvider.getInsance().getData(sendBundle,stadiumDescHandler );

	}

	private void init() {
		   final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		    // Use 1/8th of the available memory for this memory cache.
		    final int cacheSize = maxMemory / 8;

		    mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
		        @Override
		        protected int sizeOf(String key, Bitmap bitmap) {
		            // The cache size will be measured in kilobytes rather than
		            // number of items.
		            return bitmap.getByteCount() / 1024;
		        }
		    };
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_stadium, menu);
		return true;
	}

	
	private Handler stadiumDescHandler = new Handler(){
		public void handleMessage(android.os.Message msg) 
		{
		if(msg.what==ServiceProvider.getStadiums_response){
			Stadium stadium = (Stadium) msg.obj;
			
			String paragraph[]= stadium.getDescription().split("<p>");
			arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, paragraph);
			desc.setAdapter(arrayAdapter);
			
			
			
			
		}
			
		};
	};
}
