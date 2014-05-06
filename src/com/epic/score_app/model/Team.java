package com.epic.score_app.model;

import java.util.ArrayList;



public class Team {
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
	
}
