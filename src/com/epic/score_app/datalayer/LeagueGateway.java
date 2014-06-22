package com.epic.score_app.datalayer;

import java.util.ArrayList;

import org.json.JSONArray;

import android.hardware.Camera.Face;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.epic.score_app.serviceslayer.JsonService;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Group;
import domainmodel.Match;
import domainmodel.Stadium;
import domainmodel.Standing;

public class LeagueGateway extends JsonService{

	public LeagueGateway(Handler h) {
		super(h);
	
	}
	
	public ArrayList<Match> getMatches(int compid)
	{
		EntityFactory factory = new EntityFactory();
		JSONArray values = null;
		String link= ServiceProvider.Host+"/match.php";
		values=GetData(link);
		ArrayList<Match> matches=   factory.getMatches(values);
		
		
		
		
		return matches;
		
	}
	
	public ArrayList<Match> getMatchesByGroupId(int gid)
	{
		EntityFactory factory = new EntityFactory();
		JSONArray values = null;
		
		String link= ServiceProvider.Host+"/match.php?gid="+gid;
		
		
		
		
		
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
	
	
	public ArrayList<Stadium> getStadiums(int competationId){
		ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
		JSONArray array = null;
		String link= ServiceProvider.Host+"/stadiums.php";
		EntityFactory factory = new EntityFactory();
		array=GetData(link);
		stadiums= factory.getStaduims(array);
		
		return stadiums;
		
		
	}
	
	
	public ArrayList<Standing> getStandings(int compId){
		ArrayList<Standing> standings = new ArrayList<Standing>();
		JSONArray array = null;
		String link= ServiceProvider.Host+"/stats.php";
		EntityFactory factory = new EntityFactory();
		array=GetData(link);
		standings = factory.getStandings(array);
	
        return standings;	
	}
	
	
	

}
