package com.epic.score_app.viewlayer;

import Utils.Tuple;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.News;

public class NewsDescription extends ActionBarActivity {
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
		News news = (News) b.getSerializable("news");
		title=(TextView)findViewById(R.id.news_desc_title);
		photo=(ImageView) findViewById(R.id.news_desc_image);
		title.setText(news.getTitle());
		ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(news, photo));
		desc=(ListView) findViewById(R.id.news_desc_description);
		String arr[] = {""};
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
		desc.setAdapter(arrayAdapter);
		Bundle sendBundle = new Bundle();
		sendBundle.putInt("requestcode", ServiceProvider.getNewsDescription);
		sendBundle.putLong("news_id", news.getNews_id());
		ServiceProvider.getInsance().getData(sendBundle,newsDescHandler );

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
		getMenuInflater().inflate(R.menu.news_description, menu);
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

private Handler newsDescHandler = new Handler(){
	public void handleMessage(android.os.Message msg) 
	{
	if(msg.what==ServiceProvider.getNewsDescription_response){
		News news = (News) msg.obj;
		
		String paragraph[]= news.getContent().split("<p>");
		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, paragraph);
		desc.setAdapter(arrayAdapter);
		
		
		
		
	}
		
	};
};

}
