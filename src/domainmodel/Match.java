package domainmodel;




public class Match {
    /**
     * @attribute
     */
    

    /**
     * @attribute
     */
   private Team teamVisitor;

    /**
     * @attribute
     */
    private Team teamHome;
    
    private long matchId;
    private Group group;
    private String beginsAt;
    private String matchDate;
	public enum JSONVALUE{TEAM_HOME,TEAM_VISITOR,MATCHID,GROUP,BEGINSAT,MATCHDATE};
    public Team getTeamVisitor() {
		return teamVisitor;
	}
	public void setTeamVisitor(Team teamVisitor) {
		this.teamVisitor = teamVisitor;
	}
	public Team getTeamHome() {
		return teamHome;
	}
	public void setTeamHome(Team teamHome) {
		this.teamHome = teamHome;
	}
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getBeginsAt() {
		return beginsAt;
	}
	public void setBeginsAt(String beginsAt) {
		this.beginsAt = beginsAt;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	
	
	
}
