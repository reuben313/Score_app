package com.epic.score_app.serviceslayer;

import Utils.Tuple;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.image.ImageService;
import com.epic.score_app.serviceslayer.image.ImageServicee;
import com.epic.score_app.serviceslayer.interfaces.IServiceProvider;
import com.epic.score_app.serviceslayer.league.LeagueService;
import com.epic.score_app.serviceslayer.team.TeamService;
import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.MemoryCache;

import domainmodel.Team;

public class ServiceProvider implements IServiceProvider {
	public static final String Host="http://scoreapp.freeiz.com";

	public static final int getPlayers = 1;
	public static final int getPlayers_response = 11;

	public static final int getPlayer = 2;
	public static final int getPlayer_response = 22;

	public static final int getLazyPlayer = 3;
	public static final int getLazyPlayer_response = 33;

	public static final int getTeams = 4;
	public static final int getTeams_response = 44;

	public static final int getmatches = 5;
	public static final int getmatches_response = 55;

	public static final int getGroup = 6;
	public static final int getGroup_response = 66;

	public static final int getRawQuery = 7;
	public static final int getRawQuery_response = 77;

	public static final int getTeamPlayers = 8;
	public static final int getTeamPlayers_response = 88;

	public static final int getNews = 9;
	public static final int getNews_response = 99;

	public static final int getWallOf = 10;
	public static final int getWallOf_response = 1010;

	public static final int getImage= 11;
	public static final int getImage_response = 1111;
	
	public static final int getStadiums= 12;
	public static final int getStadiums_response = 1212;
	
	public static final int getStandings=13;
	public static final int getStandings_response=1313;
	
	public static final int getNewsDescription=14;
	public static final int getNewsDescription_response=1414;



	private TeamService teamservice= null;
	private LeagueService leagueservice= null;
	private GlobalService globalService=null;
	private ImageService imageservice = null;
	private ImageServicee imageservicee = null;





	private static ServiceProvider instance=null;

	


	public static ServiceProvider getInsance(){
		if (instance==null) {
			instance= new ServiceProvider();
		}


		return instance;
	}





	@Override
	public void getData(Bundle bundle, Handler handler) {
		unPackBundle(bundle,handler);

	}


	private void unPackBundle(Bundle b,Handler handler){

		int requestCode=	b.getInt("requestcode");

		switch (requestCode) {
		case getPlayers:
			teamservice = new TeamService();
			teamservice.setHandler(handler);
			teamservice.execute(b);
			break;

		case getmatches:
			leagueservice = new LeagueService();
			leagueservice.setHandler(handler);
			leagueservice.execute(b);
			break;

		case getTeams:
			teamservice = new TeamService();
			teamservice.setHandler(handler);
			teamservice.execute(b);
			break;

		case getLazyPlayer:
			teamservice = new TeamService();
			teamservice.setHandler(handler);
			teamservice.execute(b);
			break;

		case getGroup:
			leagueservice = new LeagueService();
			leagueservice.setHandler(handler);
			leagueservice.execute(b);
			break;

		case getTeamPlayers:
			teamservice = new TeamService();
			teamservice.setHandler(handler);
			teamservice.execute(b);
			break;

		case getNews:
			globalService = new GlobalService();
			globalService.setHandler(handler);
			globalService.execute(b);
			break;

		case getWallOf:
			globalService = new GlobalService();
			globalService.setHandler(handler);
			globalService.execute(b);
			break;
			
			
			
		case getStadiums:
			leagueservice = new LeagueService();
			leagueservice.setHandler(handler);
			leagueservice.execute(b);
			break;
			
		case getStandings:
			leagueservice = new LeagueService();
			leagueservice.setHandler(handler);
			leagueservice.execute(b);
			break;
		case getNewsDescription:
			globalService = new GlobalService();
			globalService.setHandler(handler);
			globalService.execute(b);
			break;

		default:
			break;
		}

	}


	public void getImageFromUrl(String link, final ImageView image)
	{




		Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (msg.what==ServiceProvider.getImage_response) {

					Bitmap b= (Bitmap) msg.obj;

					image.setImageBitmap(b);

				}

			};

		};
		/*
		  imageservice = new ImageService();
	  	  imageservice.setHandler(handler);
	  	  Bundle b = new Bundle();
	  	  b.putString("link", link);
	  	  imageservice.execute(b);
		 */
	}





	public void getImageFromUrl(final String link,
			final LruCache<String, Bitmap> mMemoryCache, final Tuple<Team, ImageView> teamTuple) {

		Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (msg.what==ServiceProvider.getImage_response) {

					Bitmap b= (Bitmap) msg.obj;
					if(b!=null){
						teamTuple.getSecond().setImageBitmap(b);
						try{
							String key="flag_"+teamTuple.getFirst().getName().toLowerCase()+".png";
							mMemoryCache.put(key, b);
							Log.i("putting in cahche ",key);
						}catch(NullPointerException exe){
							Log.i("putting in cahche error", "",exe);
						}

					}
				}   

			};

		};
		Context c = teamTuple.getSecond().getContext();

		imageservice = new ImageService(c);
		imageservice.setHandler(handler);


		imageservice.execute(new Tuple<String, Team>(link,teamTuple.getFirst()));


	}



public void getImageFromUrll(
		final LruCache<String, Bitmap> mMemoryCache, final Tuple<Ichacheable, ImageView> teamTuple) {

	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==ServiceProvider.getImage_response) {

				Bitmap b= (Bitmap) msg.obj;
				if(b!=null){
					teamTuple.getSecond().setImageBitmap(b);
					try{
						String key=teamTuple.getFirst().getCacheName();
						mMemoryCache.put(key, b);
						Log.i("putting in cahche ",key);
					}catch(NullPointerException exe){
						Log.i("putting in cahche error", "",exe);
					}

				}
			}   

		};

	};
	Context c = teamTuple.getSecond().getContext();

	imageservicee = new ImageServicee(c);
	imageservicee.setHandler(handler);


	imageservicee.execute(teamTuple.getFirst());


}


}


