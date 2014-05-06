package com.epic.score_app.services;

import android.os.Bundle;

import com.epic.score_app.services.league.LeagueGateway;




public class LeagueService extends RequestService{

	@Override
	protected void executeRequest(Bundle receivedBundle) {
		     LeagueGateway gateway = new LeagueGateway(handler);
			 int requestCode= receivedBundle.getInt("requestcode");
			
			switch (requestCode) {
			case ServiceProvider.getmatches:
				int groupId= 0;
				int compid=receivedBundle.getInt("compid");
				groupId=receivedBundle.getInt("group_id");
				gateway.getMatches(receivedBundle);
			break;
			
			case ServiceProvider.getGroup:
				 compid=receivedBundle.getInt("compid");
				gateway.getGroups(compid);
			break;
				
				

			default:
				break;
			}
		
	}

}
