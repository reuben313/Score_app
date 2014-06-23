package domainmodel;

import java.io.Serializable;
import java.util.ArrayList;



public class Group implements Serializable {
    /**
     * @attribute
     */
    private String name;
    private long groupId;
    private ArrayList<Match> groupMatches =new ArrayList<Match>();	
    private ArrayList<Team> groupTeams =new ArrayList<Team>();	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public void addTeam(Team t1) {
		groupTeams.add(t1);
		
	}
	public ArrayList<Match> getGroupMatches() {
		return groupMatches;
	}
	public void setGroupMatches(ArrayList<Match> groupMatches) {
		this.groupMatches = groupMatches;
	}
	public ArrayList<Team> getGroupTeams() {
		return groupTeams;
	}
	public void setGroupTeams(ArrayList<Team> groupTeams) {
		this.groupTeams = groupTeams;
	}
	
}
