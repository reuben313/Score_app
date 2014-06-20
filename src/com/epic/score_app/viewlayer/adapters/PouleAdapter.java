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

import domainmodel.Group;
import domainmodel.Team;

public class PouleAdapter extends ArrayAdapter<Group> {

	private final Activity context;
	private List<Group> list;
	private LruCache<String, Bitmap> mMemoryCache;
	
	public PouleAdapter(Activity context, List<Group> lis) {

		super(context, R.layout.view_poule, lis);
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
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;

		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.view_poule, null);
		Group g = list.get(position);
		ArrayList<Team> teams = g.getGroupTeams();
		TextView team1 = (TextView)view.findViewById(R.id.textView1);
		TextView team2 = (TextView)view.findViewById(R.id.textView2);
		TextView team3 = (TextView)view.findViewById(R.id.textView3);
		TextView team4 = (TextView)view.findViewById(R.id.textView4);
		ImageView vlag1 = (ImageView)view.findViewById(R.id.imageView1);
		ImageView vlag2 = (ImageView)view.findViewById(R.id.imageView2);
		ImageView vlag3 = (ImageView)view.findViewById(R.id.imageView3);
		ImageView vlag4 = (ImageView)view.findViewById(R.id.imageView4);
		
		team1.setText(teams.get(0).getName());
		team2.setText(teams.get(1).getName());
		team3.setText(teams.get(2).getName());
		team4.setText(teams.get(3).getName());
		
		Team t1 = teams.get(0);
		Team t2 = teams.get(1);
		Team t3 = teams.get(2);
		Team t4 = teams.get(3);
		
		 Tuple<Team, ImageView> teamTuple1 = new Tuple<Team, ImageView>(t1, vlag1);
		 Tuple<Team, ImageView> teamTuple2 = new Tuple<Team, ImageView>(t2, vlag2);
		 Tuple<Team, ImageView> teamTuple3 = new Tuple<Team, ImageView>(t3, vlag3);
		 Tuple<Team, ImageView> teamTuple4 = new Tuple<Team, ImageView>(t4, vlag4);
		String link1 =chekifnotvalid(t1);
		String link2 =chekifnotvalid(t2);
		String link3 =chekifnotvalid(t3);
		String link4 =chekifnotvalid(t4);
			
			  Bitmap bitmap1 = getBitmapFromMemCache("flag_"+t1.getName()+".png");
			  Bitmap bitmap2 = getBitmapFromMemCache("flag_"+t2.getName()+".png");
			  Bitmap bitmap3 = getBitmapFromMemCache("flag_"+t3.getName()+".png");
			  Bitmap bitmap4 = getBitmapFromMemCache("flag_"+t4.getName()+".png");
			  
		        if (bitmap1==null) {
		        	 ServiceProvider.getInsance().getImageFromUrl(link1,mMemoryCache, teamTuple1);
		        } if(bitmap2==null){
		        	 ServiceProvider.getInsance().getImageFromUrl(link2,mMemoryCache, teamTuple2);
		        } if(bitmap3==null){
		        	 ServiceProvider.getInsance().getImageFromUrl(link3,mMemoryCache, teamTuple3);
		        } if(bitmap4==null){
		        	 ServiceProvider.getInsance().getImageFromUrl(link4,mMemoryCache, teamTuple4);
				}else{
					
					vlag1.setImageBitmap(bitmap1);
					vlag2.setImageBitmap(bitmap2);
					vlag3.setImageBitmap(bitmap3);
					vlag4.setImageBitmap(bitmap4);
					Log.i("getting from cash", "yes");
				}
		return view;
	}
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
	    if (getBitmapFromMemCache(key) == null) {
	        mMemoryCache.put(key, bitmap);
	    }
	}

	public Bitmap getBitmapFromMemCache(String key) {
	    return mMemoryCache.get(key);
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
