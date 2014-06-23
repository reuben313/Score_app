package com.epic.score_app.view;

import java.util.ArrayList;
import java.util.ListIterator;

import Utils.Tuple;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Wallof;

public class WallOfActivity extends ActionBarActivity {
    private TextView likes,dislikes;
    private ImageButton like_button,dislike_button,leftarrow,rightarrow;
    private ImageView mainview;
    private LruCache<String, Bitmap> mMemoryCache;
    private  int screenWidth;
    private  int screenHeight;
 
    private ListIterator<Wallof> cursor;
    private Wallof buffer;
    private Wallof _buffer=buffer;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wall_of);
		likes = (TextView) findViewById(R.id.wallof_like_label);
		dislikes = (TextView) findViewById(R.id.wallof_dislike_label);
		like_button=(ImageButton) findViewById(R.id.wallof_like_button);
		like_button=(ImageButton) findViewById(R.id.wallof_like_button);
		leftarrow = (ImageButton) findViewById(R.id.wall_of_left_arrow);
		leftarrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				loadPrevious();
				
			}
		});
		rightarrow = (ImageButton) findViewById(R.id.wall_of_right_arrow);
		rightarrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				loadNext();
				
			}
		});
		mainview = (ImageView) findViewById(R.id.wallof_image);
		Bundle b = new Bundle();
		b.putInt("requestcode", ServiceProvider.getWallOf);
		ServiceProvider.getInsance().getData(b, wallofHandler);
		init();
		getscreenDetails();

		
	}
	
	
	private void getscreenDetails() {
		 Resources res = this.getResources();  //  Load the resources

		    //  Get available screen size
		    Display display = getWindowManager().getDefaultDisplay();
		    int screenWidth = display.getWidth();
		    int screenHeight = display.getHeight();
		    
		
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
				cursor=walls.listIterator();
				buffer=cursor.next();
				loadImage();
				
				
				
			}
			
			
			
		};
		
	};
	
	
	
	public void loadNext(){
		if(cursor.hasNext()){
			
			buffer= cursor.next();
			Wallof _pre= buffer;
				if(_pre.equals(buffer)&&cursor.hasNext())
				{
					buffer= cursor.next();
				}
			
			loadImage();
			
		}else{
			Toast.makeText(getApplicationContext(), "end of the list", Toast.LENGTH_LONG).show();
		}
		
	}

	public void loadPrevious(){
		if (cursor.hasPrevious()) {
			Wallof _pre;
			buffer=cursor.previous();
			 _pre= buffer;
				if(_pre.equals(buffer)&&cursor.hasPrevious())
				{
					buffer= cursor.previous();
				}
			Log.i("load previos", "yeahhhhhh");
			loadImage();
		} else {
			Toast.makeText(getApplicationContext(), "end of the list", Toast.LENGTH_LONG).show();
		}
	}
	
	
	
	public void loadImage()
	{
		dislikes.setText(buffer.getDislikes()+"");
		likes.setText(buffer.getLikes()+"");
		System.out.println(buffer.getUrl());
		ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(buffer,mainview ));
		int y = (int)(screenHeight*0.75);
		int x = (int)(screenWidth*0.75);
		mainview.setMaxHeight(y);
		mainview.setMaxWidth(x);
		
	}



}
