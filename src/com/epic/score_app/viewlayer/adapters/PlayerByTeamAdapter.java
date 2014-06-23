package com.epic.score_app.viewlayer.adapters;

import java.util.ArrayList;
import java.util.List;

import Utils.Tuple;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Player;
import domainmodel.Team;

public class PlayerByTeamAdapter extends ArrayAdapter<Player>{
	
	
    private final Activity context;
	private List<Player>   list;
	private Team team ;
	private LruCache<String, Bitmap> mMemoryCache;
	
	public PlayerByTeamAdapter(Activity context, List<Player> lis,Team team) {
		
		super(context, R.layout.view_player_item, lis);
		this.context = context;
	    this.list = lis;
	    this.team=team;
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
   	      view = inflator.inflate(R.layout.view_player_item, null);
    		Player p = list.get(position);
    		TextView name = (TextView) view.findViewById(R.id.label_playeritem_name);
    		TextView lastname = (TextView) view.findViewById(R.id.label_playeritem_lastname);
    		TextView nationality = (TextView) view.findViewById(R.id.label_playeritem_nationality);
    		ImageView image = (ImageView) view.findViewById(R.id.image_player);
    		ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(team,image));
            name.setText(p.getName());
            lastname.setText(p.getLastname());
    		nationality.setText(p.getNationality());
    	   		
    	
    		 
    		 
    	 
    	return view;
    }


	public void refresh(ArrayList<Player> array) {
		list.addAll(array);
		this.notifyDataSetChanged();
		
	}



}
