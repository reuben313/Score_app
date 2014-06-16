package com.epic.score_app.datalayer;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.epic.score_app.serviceslayer.JsonService;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Group;
import domainmodel.Match;

public class LeagueGateway extends JsonService{

	public LeagueGateway(Handler h) {
		super(h);
	
	}
	
	public ArrayList<Match> getMatches(Bundle receivedBundle)
	{
		EntityFactory factory = new EntityFactory();
		JSONArray values = null;
		int compid= receivedBundle.getInt("compid");
		//int groupid= receivedBundle.getInt("groupid");
		
		
		
		String link= ServiceProvider.Host+"/match.php?compid="+compid;
		values=GetData(link);
		ArrayList<Match> matches=   factory.getMatches(values);
		
		
		
		
		return matches;
		
	}
	
	public ArrayList<Group> getGroups(int competationID)
	{
		EntityFactory factory = new EntityFactory();
		JSONArray values = null;
		String link= ServiceProvider.Host+"/group.php?compid="+competationID;
		values=GetData(link);
		ArrayList<Group> matches=   factory.getGroups(values);
		return matches;
		
	}
	
	
	

}
