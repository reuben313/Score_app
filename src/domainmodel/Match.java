package domainmodel;




public class Match {
    /**
     * @attribute
     */
    

    /**
     * @attribute
     */
   private Team teamVisitor;
   public int getTeam_visitor_result() {
		return team_visitor_result;
	}
	public void setTeam_visitor_result(int team_visitor_result) {
		this.team_visitor_result = team_visitor_result;
	}
	public int getTeam_home_result() {
		return team_home_result;
	}
	public void setTeam_home_result(int team_home_result) {
		this.team_home_result = team_home_result;
	}
	public MATCH_STATUS getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(MATCH_STATUS matchStatus) {
		this.matchStatus = matchStatus;
	}
private int team_visitor_result;

    /**
     * @attribute
     */
    private Team teamHome;
    private int team_home_result;
    
    public enum MATCH_STATUS{PENALTY,TEAMVISITORWINS,TEAMHOMEWINS,DRAW,NOTPLAYED,PLAYED};
    
    private long matchId;
    private Group group;
    private String beginsAt;
    private String matchDate;
    private MATCH_STATUS matchStatus;
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
