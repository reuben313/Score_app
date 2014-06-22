package com.epic.score_app.viewlayer.adapters;

import java.util.ArrayList;
import java.util.List;

import Utils.Tuple;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.SpelerActivity;
import com.epic.score_app.viewlayer.ViewPlayer;

import domainmodel.Group;
import domainmodel.Match;
import domainmodel.News;
import domainmodel.Player;
import domainmodel.Team;

public class MatchItemAdapter extends ArrayAdapter<Match> {
	private List<Match> match;
	private Activity context;
	private LayoutInflater inflator;
	private LruCache<String, Bitmap> mMemoryCache;
	

	public MatchItemAdapter(Activity context,List<Match> match) {
		super(context, R.layout.view_wedstrijd_item, match);
		this.context=context;
		this.match=match;
		inflator = this.context.getLayoutInflater();
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
		View view =null;
		view=	inflator.inflate(R.layout.view_wedstrijd_item, null);
		Match _match = match.get(position);
		
       //	ArrayList<Team> teams = _match.getGroupTeams();
       	
		TextView matchdatum  = (TextView)view.findViewById(R.id.matchdatum);
		TextView tijd= (TextView)view.findViewById(R.id.tijd);
		TextView thuis = (TextView)view.findViewById(R.id.thuisploeg);
		TextView uit = (TextView)view.findViewById(R.id.uitploeg);
		ImageView vlag1 = (ImageView)view.findViewById(R.id.team1_flag);
		ImageView vlag2 = (ImageView)view.findViewById(R.id.team2_flag);
		
		
		matchdatum.setText(_match.getMatchDate());
		tijd.setText(_match.getBeginsAt());
		Team teamhome = _match.getTeamHome();
		Team teamuit = _match.getTeamVisitor();
		thuis.setText(teamhome.getName());
		uit.setText(teamuit.getName());

		loadFlagImage(teamuit,vlag1);
		loadFlagImage(teamhome,vlag2);
        return view;
	}

	private void loadFlagImage(Ichacheable teamhome, ImageView flag) {
		Bitmap b = getBitmapFromMemCache(teamhome.getCacheName());
		if (b==null) {
			ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(teamhome, flag));
			
		}else{
			flag.setImageBitmap(b);
		}
		
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
