package com.epic.score_app.datalayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcelable;
import android.util.JsonReader;
import android.util.Log;
import domainmodel.Group;
import domainmodel.Match;
import domainmodel.Match.MATCH_STATUS;
import domainmodel.News;
import domainmodel.Player;
import domainmodel.Stadium;
import domainmodel.Standing;
import domainmodel.Team;
import domainmodel.Wallof;

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
			String age=pl.getString("AGE");
			String foot = pl.getString("FOOT");
			String weight= pl.getString("WEIGHT");
			
			
		
		
		player.setDateOfbirth(dateofbirth);
		player.setPosition(position);
		player.setHeight(height);
		player.setAge(age);
		player.setFoot(foot);
		player.setWeight(weight);
		
		
		
			
			
		
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
				//ST","9":"3","team1_result":"3","10":"1","team2_result":"1","11":"played","match_status":"played"}
				int team1result = jsonM.getInt("team1_result");
				int team2result = jsonM.getInt("team2_result");
				String status = jsonM.getString("match_status");
				m.setTeam_home_result(team1result);
				m.setTeam_visitor_result(team2result);
			    m.setMatchStatus(status.equalsIgnoreCase("played")?  MATCH_STATUS.PLAYED:MATCH_STATUS.NOTPLAYED);
				
				
				
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



	public ArrayList<News> getNews(JSONArray values) {
		ArrayList<News> newslist = new ArrayList<News>();
		try {
		for (int i = 0; i < values.length(); i++) {
			//[{"news":{"0":"1","NEWS_ID":"1","1":"test","TITLE":"test","2":"test content ","content":"test content ","3":"ere",
			//"title link":"ere","4":"1","content_photo_id":"1","5":"0000-00-00 00:00:00","datum":"0000-00-00 00:00:00"}}]
			
				JSONObject newsContainer = values.getJSONObject(i);
				JSONObject jnews = newsContainer.getJSONObject("news");
				News  news= new News();
				long news_id;
				String title;
				String photoLink;
				
			    
			    news_id=jnews.getLong("news_id");
			    title= jnews.getString("title");
			   
			    photoLink=jnews.getString("link");
			    
			    
			    
			    String pubdate=jnews.getString("pubdate");
			 
			    
			    news.setTitle(title);
			   news.setPubdate(pubdate);
			   news.setPhotoLink(photoLink);
			   
			   
			    news.setNews_id(news_id);
			  
			   
			    newslist.add(news);
				
				
				
			} 
		
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newslist;
	}
	
	public News getNewsDescription(JSONArray values) {
		News news= new News();
		try {
		for (int i = 0; i < values.length(); i++) {
			//[{"news":{"0":"1","NEWS_ID":"1","1":"test","TITLE":"test","2":"test content ","content":"test content ","3":"ere",
			//"title link":"ere","4":"1","content_photo_id":"1","5":"0000-00-00 00:00:00","datum":"0000-00-00 00:00:00"}}]
			
				JSONObject newsContainer = values.getJSONObject(i);
				JSONObject jnews = newsContainer.getJSONObject("news");
				
			
				String content;
				long news_id=jnews.getLong("news_id");
				
			    
			   
			    content= jnews.getString("desc");
			  
			    
			    
			    
			
			    
			  
			    news.setContent(content);
			    news.setNews_id(news_id);
			   
			
		
			   
			   
				
				
				
			} 
		
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}
	
	
	
	



	public ArrayList<Wallof> getWallofs(JSONArray values) {
		ArrayList<Wallof> wallofs = new ArrayList<Wallof>();
		try{
		for (int i = 0; i < values.length(); i++) {
			JSONObject obj = values.getJSONObject(i);
			JSONObject wallof = obj.getJSONObject("wallof");
			Wallof wlof = new Wallof();
			//[{"wallof":{"0":"1","wall_of_fame_id":"1","1":"0","photo_id":"0","2":"0","like":"0","3":"0","dislike":"0"}}] 
			long wallofID=wallof.getLong("wall_of_fame_id");
			   int likes=wallof.getInt("like");
			   int dislikes=wallof.getInt("dislike");
			   int photoId=wallof.getInt("photo_id");
			 
			   String photolink= wallof.getString("photolink");
			   
			wlof.setWallofID(wallofID);
			wlof.setDislikes(dislikes);
			wlof.setLikes(likes);
			wlof.setPhotoId(photoId);
			wlof.setPhotolink(photolink);
			wallofs.add(wlof);
			
		}
		
		}catch(Exception ex)
		{}
		
		return wallofs;
	}


public ArrayList<Stadium> getStaduims(JSONArray values){
	ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
	try{
		for (int i = 0; i < values.length(); i++) {
			JSONObject obj = values.getJSONObject(i);
			JSONObject jstadium = obj.getJSONObject("stadium");
			Stadium stadium = new Stadium();
			String name =  jstadium.getString("name");
			String description = jstadium.getString("desc");
			String link = jstadium.getString("link");
			
			stadium.setName(name);
			stadium.setDescription(description);
			stadium.setPhoto_link(link);
			stadiums.add(stadium);
		
			
		}
		
		}catch(Exception ex)
		{}
	
	
	return stadiums;
}



public ArrayList<Standing> getStandings(JSONArray values) {
	ArrayList<Standing> standings = new ArrayList<Standing>();
	try{
		for (int i = 0; i < values.length(); i++) {
			JSONObject obj = values.getJSONObject(i);
			JSONObject jstanding = obj.getJSONObject("stats");
			Standing standing = new Standing();
			Team team = new Team();
			//{"0":"1","team_id":"1","1":"0","win":"0","2":"0","lose":"0","3":"0","draw":"0","4":"0","points":"0","5":"1","comp_id":"1","6":"BRAZIL","team_name":"BRAZIL"}},{"stats":
			long team_id = jstanding.getLong("team_id");
			String teamName= jstanding.getString("team_name");
			int win =  jstanding.getInt("win");
			 int lose = jstanding.getInt("lose");
			 int draw = jstanding.getInt("draw");
			 int points = jstanding.getInt("points");
			 int compid = jstanding.getInt("comp_id");
			 team.setTeamId(team_id);
			 team.setName(teamName);
			 standing.setTeam(team);
			 standing.setWin(win);
			 standing.setLose(lose);
			 standing.setPoint(points);
			 standing.setDraw(draw);
			 standings.add(standing);
		
			
		}
		
		}catch(Exception ex)
		{}
	
	
	
	
	return standings;
}

	}


	
	
	
	
	

