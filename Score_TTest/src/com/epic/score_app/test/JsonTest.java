
package com.epic.score_app.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.json.JSONArray;

import android.os.Bundle;
import android.os.Handler;

import com.epic.score_app.datalayer.EntityFactory;
import com.epic.score_app.datalayer.GlobalGateway;
import com.epic.score_app.datalayer.LeagueGateway;
import com.epic.score_app.datalayer.TeamGateway;

import domainmodel.Group;
import domainmodel.Match;
import domainmodel.News;
import domainmodel.Player;
import domainmodel.Stadium;
import domainmodel.Team;
import domainmodel.Wallof;

public class JsonTest extends TestCase {
private Handler h;
	public JsonTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		
	}
	
public void	testGetPlayers()
{
ArrayList<Player> players = new ArrayList<Player>();
	EntityFactory factory = new EntityFactory();
JSONArray values = null;
TeamGateway teamgatway = new TeamGateway(null);
String link="http://scoreapp.freeiz.com/user.php?lim=20&offset=0";
values= teamgatway.GetData(link);
players= factory.getPlayers(values);
assertTrue(players.size()==20);
}


public void testGetPlayerComplete(){
	Player p = null;
	TeamGateway gateway = new TeamGateway(null);
	p=gateway.getLazyPlayer(1);
	assertTrue(!p.getHeight().equalsIgnoreCase(""));
	}



public void testgetPlayersByTeamId()
{
	TeamGateway gateway = new TeamGateway(null);
	ArrayList<Team> teams = new ArrayList<Team>();
	for (int i = 1; i < 33; i++) {
		Team t = new Team();
		ArrayList<Player> players = new ArrayList<Player>();
		players= gateway.getPlayers(i);
		t.addPlayers(players);
		teams.add(t);
	   }
	
	assertTrue(teams.size()==32);
	
	for (Team t : teams) {
		assertTrue(t.getPlayers().size()!=0);
	}
	
}


public void testGetatendingTeam(){
	
	TeamGateway gateway = new TeamGateway(null);
	ArrayList<Player> players = new ArrayList<Player>();
	players= gateway.getPlayersByUserID(124);
	assertTrue(1l==players.get(0).getAttendingTeams().get(0).getTeamId());
	
	
	
	
	
}



public void	testGetTeams()
{
ArrayList<Team> teams = new ArrayList<Team>();
TeamGateway gateway = new TeamGateway(null);
teams= gateway.getTeams(20,0,0);
assertTrue(teams.size()==32);

}


public void testGetmatches(){
	ArrayList<Match> matches = new ArrayList<Match>();
	LeagueGateway gateway= new LeagueGateway(null);
	Bundle receivedBundle = new Bundle();
	int groupId= 0;
	receivedBundle.putInt("compid", 1);
	
	
	matches=gateway.getMatches(receivedBundle);
	assertTrue(matches.size()==48);
	}

public void testGetGroups(){
	
	ArrayList<Group> groeps = new ArrayList<Group>();
	LeagueGateway gateway  = new LeagueGateway(null);
    groeps=	gateway.getGroups(1);
    assertTrue(groeps.size()==8);
    for (Group group : groeps) {
	assertTrue(group.getGroupTeams().size()==4);
   }
  }

public void testGetNews(){
	GlobalGateway gateway = new GlobalGateway(null);
	ArrayList<News> news  = new ArrayList<News>();
	news =gateway.getNews();
	assertTrue(news.size()>1);
	}

public void testWallof(){
	GlobalGateway gateway = new GlobalGateway(null);
	ArrayList<Wallof> wallofs  = new ArrayList<Wallof>();
	wallofs =gateway.getWallofs();
	assertTrue(wallofs.size()>0);

}

public void testUpdate_w(){
	GlobalGateway gateway = new GlobalGateway(null);
	
	boolean hit= true;
	Wallof ofs ;
	ArrayList<Wallof> wallofs  = new ArrayList<Wallof>();
	wallofs =gateway.getWallofs();
	for (Wallof wallof : wallofs) {
		ofs=gateway.update_Wallof(wallof.getWallofID(),hit);
		int _like= wallof.getLikes();
		int like= ofs.getLikes();
		int verschill= like-_like;
		assertTrue(verschill==1);
	}
	
	
	
	
}


public void testGetStaduims()
{
	GlobalGateway gateway = new GlobalGateway(null);
	ArrayList<Stadium> staduims = new ArrayList<Stadium>();
	


}


}
