package com.epic.score_app.serviceslayer.league;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Message;

import com.epic.score_app.datalayer.LeagueGateway;
import com.epic.score_app.serviceslayer.RequestService;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Group;
import domainmodel.Match;
import domainmodel.Stadium;




public class LeagueService extends RequestService{

	@Override
	protected void executeRequest(Bundle receivedBundle) {
		     LeagueGateway gateway = new LeagueGateway(handler);
			 int requestCode= receivedBundle.getInt("requestcode");
			
			switch (requestCode) {
			case ServiceProvider.getmatches:
				ArrayList<Match> matches = new ArrayList<Match>();
				int groupId= 0;
				int compid=receivedBundle.getInt("compid");
				groupId=receivedBundle.getInt("group_id");
				gateway.getMatches(receivedBundle);
				Message msg = new Message();
				msg.what= ServiceProvider.getmatches_response;
				msg.obj=matches;
				handler.sendMessage(msg);
			break;
			
			case ServiceProvider.getGroup:
				 compid=receivedBundle.getInt("compid");
				
				ArrayList<Group> groups=  gateway.getGroups(compid);
				
				Message msggroup = new Message();
				msggroup.what= ServiceProvider.getGroup_response;
				msggroup.obj=groups;
				handler.sendMessage(msggroup);
				
				
			break;
			
			case ServiceProvider.getStadiums:
				 compid=receivedBundle.getInt("compid");
				
				ArrayList<Stadium> staduims = new ArrayList<Stadium>();
				Stadium sta = new Stadium();
				sta.setName("Sheik Ali staduim");
				
				
				Message msgstaduims = new Message();
				msgstaduims.what= ServiceProvider.getStadiums_response;
				msgstaduims.obj=staduims;
				handler.sendMessage(msgstaduims);
				
				
			break;
				
				

			default:
				break;
			}
		
	}

}
