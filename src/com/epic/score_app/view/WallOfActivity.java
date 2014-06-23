package com.epic.score_app.view;

import java.util.ArrayList;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Wallof;
import Utils.Tuple;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WallOfActivity extends ActionBarActivity {
    private TextView likes,dislikes;
    private ImageButton like_button,dislike_button;
    private ImageView mainview;
    private LruCache<String, Bitmap> mMemoryCache;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wall_of);
		likes = (TextView) findViewById(R.id.wallof_like_label);
		dislikes = (TextView) findViewById(R.id.wallof_dislike_label);
		like_button=(ImageButton) findViewById(R.id.wallof_like_button);
		like_button=(ImageButton) findViewById(R.id.wallof_like_button);
		mainview = (ImageView) findViewById(R.id.wallof_image);
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getWallOf);
		ServiceProvider.getInsance().getData(b, wallofHandler);
		init();

		
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
		getMenuInflater().inflate(R.menu.wall_of, menu);
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
	
	
	
	private Handler wallofHandler = new Handler(){
		public void handleMessage(Message msg)
		{
			if (msg.what==ServiceProvider.getWallOf_response) {
				ArrayList<Wallof> walls = new ArrayList<Wallof>();
				walls=(ArrayList<Wallof>) msg.obj;
				Wallof w = walls.get(0);
				dislikes.setText(w.getDislikes()+"");
				likes.setText(w.getLikes()+"");
				ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(w,mainview ));
				
			}
			
			
			
		};
		
	};



}
