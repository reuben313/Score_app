package domainmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.epic.score_app.cache.interfaces.Ichacheable;



public class Team implements Serializable,Ichacheable {
    /**
     * @attribute
     */
    private String name;
    private long teamId;
	private ArrayList<Player> players = new ArrayList<Player>();
    
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	public void addPlayers(ArrayList<Player> players2) {
		this.setPlayers(players2);
		
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	@Override
	public String getCacheName() {
		// TODO Auto-generated method stub
		return "flag_"+getName()+".jpg";
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return  "http://scoreapp.freeiz.com/img/"+getName()+".png";
	}
	
}
