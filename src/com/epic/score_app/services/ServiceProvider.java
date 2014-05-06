package com.epic.score_app.services;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.epic.score_app.interfaces.IServiceProvider;
import com.epic.score_app.services.league.LeagueService;
import com.epic.score_app.services.team.TeamService;

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
	public static final int getmaGroup_response = 66;
	
	public static final int getRawQuery = 7;
	public static final int getRawQuery_response = 77;
	
	public static final int getTeamPlayers = 8;
	public static final int getTeamPlayers_response = 88;
	
	
	
	private TeamService teamservice= null;
   private LeagueService leagueservice= null;

	
	

	
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
   	   leagueservice = new LeagueService();
   	   leagueservice.setHandler(handler);
   	   leagueservice.execute(b);
   	break;
	
	
	

	default:
		break;
	}
		
	}
	
	
	}
