package com.epic.score_app.services.team;

import com.epic.score_app.services.RequestService;
import com.epic.score_app.services.ServiceProvider;

import android.os.Bundle;
import android.util.Log;

 

public class TeamService  extends RequestService{


protected void executeRequest(Bundle receivedBundle) {
	    TeamGateway gateway=null; 
		int requestCode= receivedBundle.getInt("requestcode");
		int limit;
		int offset;
		switch (requestCode) {
		case ServiceProvider.getPlayers:
			gateway = new TeamGateway(handler);
			 limit= receivedBundle.getInt("limit");
			 offset= receivedBundle.getInt("offset");
			gateway.getPlayers(limit,offset);
			
		
			
			
			
			break;
			
			
	
			
		case ServiceProvider.getLazyPlayer:
			gateway = new TeamGateway(handler);
			int playerId= receivedBundle.getInt("player_id");
			gateway.getLazyPlayer(playerId);
			
		break;
		
		case ServiceProvider.getTeams:
			gateway = new TeamGateway(handler);
		
			 limit= receivedBundle.getInt("limit");
			 offset=receivedBundle.getInt("offset");
			int teamid =0;
				teamid=	receivedBundle.getInt("teamid");
			if (teamid == 0) {
				gateway.getTeams(limit,offset);
			}else{
				gateway.getTeams(limit,offset,teamid);
			}
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
