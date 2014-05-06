package com.epic.score_app.services.league;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.epic.score_app.model.Group;
import com.epic.score_app.model.Match;
import com.epic.score_app.services.EntityFactory;
import com.epic.score_app.services.JsonService;
import com.epic.score_app.services.ServiceProvider;

public class LeagueGateway extends JsonService{

	public LeagueGateway(Handler h) {
		super(h);
	
	}
	
	public void getMatches(Bundle receivedBundle)
	{
		EntityFactory factory = new EntityFactory();
		JSONArray values = null;
		int compid= receivedBundle.getInt("compid");
		int groupid= receivedBundle.getInt("groupid");
		
		
		
		String link= ServiceProvider.Host+"match.php?compid="+receivedBundle;
		values=GetData(link);
		ArrayList<Match> matches=   factory.getMatches(values);
		
		Message msg = new Message();
		msg.what= ServiceProvider.getmatches_response;
		msg.obj=matches;
		handler.sendMessage(msg);
		
		
		
		
	}
	
	public void getGroups(int competationID)
	{
		EntityFactory factory = new EntityFactory();
		JSONArray values = null;
		String link= ServiceProvider.Host+"group.php?compid="+competationID;
		values=GetData(link);
		ArrayList<Group> matches=   factory.getGroups(values);
		
		Message msg = new Message();
		msg.what= ServiceProvider.getmatches_response;
		msg.obj=matches;
		handler.sendMessage(msg);
		
	}
	
	
	

}
