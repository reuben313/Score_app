package com.epic.score_app.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcelable;
import android.util.Log;

import com.epic.score_app.model.Group;
import com.epic.score_app.model.Match;
import com.epic.score_app.model.Player;
import com.epic.score_app.model.Team;

public class EntityFactory {

	
	
	public List<Parcelable> getPackages(String code,JSONArray jarr){
		
		ArrayList<Parcelable> return_value = new ArrayList<Parcelable>();
		
		
		
		
		return getParcablePlayers(jarr);
	}
	
	
	
	private List<Parcelable> getParcablePlayers(JSONArray jarr){
		
		for (int i = 0; i < jarr.length(); i++) {
		try {
			JSONObject obj = jarr.getJSONObject(i);
		
		    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		  }
		
		
		return null;
	}
	
	
	public ArrayList<Player> getPlayers(JSONArray arr){
		
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < arr.length(); i++) {
			try {
				JSONObject obj = arr.getJSONObject(i);
				JSONObject pl = obj.getJSONObject("player");
				//"0":"1","PLAYER_ID":"1","1":"AZZEDINE","FIRST_NAME":"AZZEDINE","2":"DOUKHA","LAST_NAME":"DOUKHA","3":"ALGERIA","NATIONALITY":"ALGERIA"}}
				long  player_id= pl.getLong("PLAYER_ID");
				String name= pl.getString("FIRST_NAME");
				String lastname= pl.getString("LAST_NAME");
				String nationality=pl.getString("NATIONALITY");
				Player player = new Player();
				player.setName(name);
				player.setLastname(lastname);
				player.setNationality(nationality);
				player.setPlayer_id(player_id);
				players.add(player);
				
				
			
			    } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			  }
			
		
		
		
		return players;
	}



	public Player getLazyPlayer(JSONArray values) {
		Player player = new Player();
		try {
			JSONObject obj = values.getJSONObject(0);
			JSONObject pl = obj.getJSONObject("playercomplete");
			//{"playercomplete":{"0":"5 AUGUST 1986","DATE_OF_BIRTH":"5 AUGUST 1986","1":"27","AGE":"27","2":"ALGERIA","COUNTRY_OF_BIRTH":"ALGERIA","3":"CHETTIA","PLACE_OF_BIRTH":"CHETTIA","4":"GOALKEEPER","POSITION":"GOALKEEPER","5":"188 CM","HEIGHT":"188 CM","6":"80 KG","WEIGHT":"80 KG","7":"RIGHT","FOOT":"RIGHT"}}]
		
			String dateofbirth= pl.getString("DATE_OF_BIRTH");
			String position= pl.getString("POSITION");
			String height=pl.getString("HEIGHT");
			
		
		
		player.setDateOfbirth(dateofbirth);
		player.setPosition(position);
		player.setHeight(height);
		
		
			
			
		
		    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		return player;
	}



	public ArrayList<Match> getMatches(JSONArray values) {
		ArrayList<Match> matches = new ArrayList<Match>();
		try {
			for (int i = 0; i < values.length(); i++) {
				Match m = new Match();
				Team t1 = new Team();
				Team t2 = new Team();
				Group g = new Group();
				
				JSONObject obj = values.getJSONObject(i);
				JSONObject jsonM= obj.getJSONObject("match");
				String team1 = jsonM.getString("teamA");
				String team2 = jsonM.getString("Teamb");
				String dayofmatch = jsonM.getString("MATCHDAY");
				String group = jsonM.getString("group_name");
				String beginsAt = jsonM.getString("BEGINSAT");
				long team_homeID= jsonM.getLong("TEAM_HOME");
				long team_visitorID= jsonM.getLong("TEAM_HOME");
				long group_id= jsonM.getLong("GID");
				
				t1.setName(team1);
				t1.setTeamId(team_homeID);
				
				t2.setName(team2);
				t2.setTeamId(team_visitorID);
				
				m.setTeamHome(t1);
				m.setTeamVisitor(t2);
				
				g.setName(group);
				g.setGroupId(group_id);
				
				m.setGroup(g);
				m.setMatchDate(dayofmatch);
				m.setBeginsAt(beginsAt);
				
				matches.add(m);
				
				
				
				
				
				
				
				
			}
			
			
			
		} catch (Exception e) {
			
		}
		
		
		return matches;
	}



	public ArrayList<Group> getGroups(JSONArray values) {
		
		LinkedHashMap<Long, Group> groups =new  LinkedHashMap<Long, Group>(); 
		try {
			for (int i = 0; i < values.length(); i++) {
				
				
				
				
				JSONObject obj = values.getJSONObject(i);
				JSONObject jsonM= obj.getJSONObject("group");
				String team1 = jsonM.getString("teamName");
				
				String group = jsonM.getString("groupName");
			    long team_homeID= jsonM.getLong("TEAM_ID");
			    long group_id= jsonM.getLong("GID");
				//"0":"SPAIN","teamName":"SPAIN","1":"5","TEAM_ID":"5","2":"2","GID":"2","3":"Group B","groupName":"Group B"}}
				Team t1 = new Team();
				t1.setName(team1);
				t1.setTeamId(team_homeID);
				
				
				if (groups.get(group_id)==null) {
					Group temp= new Group();
					temp.setGroupId(group_id);
					temp.setName(group);
					
					temp.addTeam(t1);
					groups.put(group_id, temp);
					 
				}
				else{
						
					groups.get(group_id).addTeam(t1);
	              				
					
				}
				}
			
			
			
		} catch (Exception e) {
			
		}
		return new ArrayList<Group>(groups.values());
	}
	
	
	
	public Group getGroup(JSONArray values) {
		Group g = new Group();
		try {
			for (int i = 0; i < values.length(); i++) {
				
				Team t1 = new Team();
				
				
				
				JSONObject obj = values.getJSONObject(i);
				JSONObject jsonM= obj.getJSONObject("group");
				String team1 = jsonM.getString("teamName");
				
				
				String group = jsonM.getString("groupName");
			    long team_homeID= jsonM.getLong("TEAM_ID");
			    long group_id= jsonM.getLong("GID");
				//"0":"SPAIN","teamName":"SPAIN","1":"5","TEAM_ID":"5","2":"2","GID":"2","3":"Group B","groupName":"Group B"}}
				
				t1.setName(team1);
				t1.setTeamId(team_homeID);
				
				
				
			
				
				g.setName(group);
				g.setGroupId(group_id);
				
				
				g.addTeam(t1);
			
				
				
				
				
				
				
				
			}
			
			
			
		} catch (Exception e) {
			
		}
		return g;
	}



	public ArrayList<Team> getTeams(JSONArray values) {
		//{"team":{"0":"1","TEAM_ID":"1","1":"BRAZIL","NAME":"BRAZIL"}},{"team":{"0":"2","TEAM_ID":"2","1":"CROATIA","NAME":"CROATIA"}}
		ArrayList<Team> teams = new ArrayList<Team>();
	
	
		try {
			for (int i = 0; i < values.length(); i++) {
			JSONObject obj;
			    obj = values.getJSONObject(i);
				Team t = new Team();
			    JSONObject jsonM= obj.getJSONObject("team");
				String teamname = jsonM.getString("NAME");
		        long teamId= jsonM.getLong("TEAM_ID");
		        t.setName(teamname);
		        t.setTeamId(teamId);
		        teams.add(t);
		       
			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		
		return teams;
	}



	public ArrayList<Player> getPlayersWithTeamID(JSONArray values) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < values.length(); i++) {
			try {
				JSONObject obj = values.getJSONObject(i);
				JSONObject pl = obj.getJSONObject("player");
				//"0":"1","PLAYER_ID":"1","1":"AZZEDINE","FIRST_NAME":"AZZEDINE","2":"DOUKHA","LAST_NAME":"DOUKHA","3":"ALGERIA","NATIONALITY":"ALGERIA"}}
				long  player_id= pl.getLong("PLAYER_ID");
				String name= pl.getString("FIRST_NAME");
				String lastname= pl.getString("LAST_NAME");
				String nationality=pl.getString("NATIONALITY");
				Player player = new Player();
				player.setName(name);
				player.setLastname(lastname);
				player.setNationality(nationality);
				player.setPlayer_id(player_id);
				Team t = new Team();
				long teamId = pl.getLong("TEAM_ID");
				t.setTeamId(teamId);
				player.getAttendingTeams().add(t);
				
				
				players.add(player);
				
				
			
			    } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			  }
			
		
		
		
		return players;
		
	}
	}
	
	
	
	
	

