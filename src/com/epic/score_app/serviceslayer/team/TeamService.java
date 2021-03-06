package com.epic.score_app.serviceslayer.team;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.epic.score_app.datalayer.TeamGateway;
import com.epic.score_app.serviceslayer.RequestService;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Player;
import domainmodel.Team;

 

public class TeamService  extends RequestService{


protected void executeRequest(Bundle receivedBundle) {
	    TeamGateway gateway=null; 
		int requestCode= receivedBundle.getInt("requestcode");
		int limit;
		int offset;
		switch (requestCode) {
		case ServiceProvider.getPlayers:
			ArrayList<Player> players = new ArrayList<Player>();
			gateway = new TeamGateway(handler);
			limit= receivedBundle.getInt("limit");
			offset= receivedBundle.getInt("offset");
			players =  gateway.getPlayers(limit,offset);
			Message msg = new Message();
			msg.what=ServiceProvider.getPlayers_response;
			msg.obj=players;
			Log.i("sending the data to handler ",players.size()+"");
			handler.sendMessage(msg);
		
			
			
			
			break;
			
			
	
			
		case ServiceProvider.getLazyPlayer:
			gateway = new TeamGateway(handler);
			int playerId= (int)receivedBundle.getLong("player_id");
			Log.i("getting lazy from gateway", " with "+playerId);
			Player player=	gateway.getLazyPlayer(playerId);
			Message msglazyplayer = new Message();
			msglazyplayer.what=ServiceProvider.getLazyPlayer_response;
			 
			msglazyplayer.obj=player;
			Log.i("sending the data to handler ",player.getAge());
			handler.sendMessage(msglazyplayer);
			
		break;
		
		case ServiceProvider.getTeams:
			ArrayList<Team> teams = new ArrayList<Team>();
			gateway = new TeamGateway(handler);
		
			 limit= receivedBundle.getInt("limit");
			 offset=receivedBundle.getInt("offset");
			int teamid =0;
				teamid=	receivedBundle.getInt("teamid");
			if (teamid == 0) {
			teams=	gateway.getTeams(limit,offset);
			
			}else{
			teams=gateway.getTeams(limit,offset,teamid);
			}
			
			Message msgteam = new Message();
			msgteam.what=ServiceProvider.getTeams_response;
			msgteam.obj=teams;
			handler.sendMessage(msgteam);
			
			
		break;
		
		
		case ServiceProvider.getTeamPlayers:
			gateway = new TeamGateway(handler);
			long team_id=receivedBundle.getLong("team_id");
			ArrayList<Player> team_players = new ArrayList<Player>();
			team_players=gateway.getPlayers((int)team_id);
			Message msgstandings = new Message();
			msgstandings.what= ServiceProvider.getTeamPlayers_response;
			msgstandings.obj= team_players;
			handler.sendMessage(msgstandings);
			break;

		default:
			break;
		}
		
		
	
		
	}


	@Override
	 protected void onPostExecute(Void result) {
        Log.i("DONEE", "DONEEEEEEEEEEEEEE");
		 
     }



	
}
