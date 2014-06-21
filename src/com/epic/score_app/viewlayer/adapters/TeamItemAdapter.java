package com.epic.score_app.viewlayer.adapters;

import java.util.ArrayList;
import java.util.List;

import Utils.Tuple;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Team;

public class TeamItemAdapter extends ArrayAdapter<Team>{
	private final Activity context;
	private List<Team>   list;
	private LruCache<String, Bitmap> mMemoryCache;

	public TeamItemAdapter (Activity context, List<Team> lis) {
		super(context, R.layout.view_player_item, lis);
		this.context = context;
		this.list = lis; 
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
	public View getView(int position, View convertView, ViewGroup parent) {
		View view= null;
		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.team_player_item, null);
		Team t = list.get(position);
		TextView name = (TextView) view.findViewById(R.id.label_teamitem_name);
		name.setText(t.getName());
        ImageView flag=(ImageView) view.findViewById(R.id.image_player);

        Tuple<Team, ImageView> teamTuple = new Tuple<Team, ImageView>(t, flag);
		String link =chekifnotvalid(t);
		//String link_ ="http://flagspictures.org/photo/icons/normal/300/Brazil.png";
        Bitmap bitmap = getBitmapFromMemCache("flag_"+t.getName()+".png");
        if (bitmap==null) {
        	 ServiceProvider.getInsance().getImageFromUrl(link,mMemoryCache, teamTuple);
        	
		}else{
			
			flag.setImageBitmap(bitmap);
			Log.i("getting from cash", "yes");
		}
		return view;

	}

	public void refresh(ArrayList<Team> array) {
		list.addAll(array);
		this.notifyDataSetChanged();
	}
		
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
	    if (getBitmapFromMemCache(key) == null) {
	        mMemoryCache.put(key, bitmap);
	    }
	}

	public Bitmap getBitmapFromMemCache(String key) {
	    return mMemoryCache.get(key.toLowerCase());
	}
	
	public String chekifnotvalid(Team land)
	{
		String linkhost = "http://scoreapp.freeiz.com/img/"+land.getName()+".png";
		String link = "http://scoreapp.freeiz.com/img/"+land.getName()+".png";
		//String link_ ="http://flagspictures.org/photo/icons/normal/300/Brazil.png";
		String s = land.getName();
		
		if (s.equals("bosnia-herzegovina")||s.equals("costa-rica")||s.equals("cote-divoire")||s.equals("korea-republic")||s.equals("united-states")) {
			
			return link;
		}else{
			return linkhost;
			
		}
		
	}
	
	
}
