package com.epic.score_app.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;





public class Player  implements Parcelable{
  
  
	
	private String lastname;
	private long player_id;
	private String nationality;
	private String dateOfbirth;
	private String height;
	private String position;
	private ArrayList<Team> attendingTeams = new ArrayList<Team>();
	

  
   public String getHeight() {
		return height;
	}


	public String getPosition() {
		return position;
	}


public long getPlayer_id() {
		return player_id;
	}


	public void setPlayer_id(long player_id) {
		this.player_id = player_id;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


private Integer jerseynumber;

   
    private String name;

    
	public Player(){
		
	}
	

	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Integer getJerseynumber() {
		return jerseynumber;
	}


	public void setJerseynumber(Integer jerseynumber) {
		this.jerseynumber = jerseynumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public ArrayList<Team> getAttendingTeams(){
		
		
		
		return attendingTeams;
	}
	
	
    public static final Parcelable.Creator<Player> CREATOR = new Creator<Player>() { 

  public Player createFromParcel(Parcel source) { 

      Player mplayer = new Player(); 
      mplayer.setPlayer_id(source.readLong());
      mplayer.setName(source.readString());
      mplayer.setLastname(source.readString());
      mplayer.setNationality(source.readString());

      
 

      return mplayer; 
  }

@Override
public Player[] newArray(int arg0) {
	// TODO Auto-generated method stub
	return new Player[arg0];
}
  }; 


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(player_id);
		dest.writeString(name);
		dest.writeString(lastname);
	    dest.writeString(nationality);
		
		
	}


	

	public void setPosition(String position) {
		this.position=position;
		
	}


	public void setHeight(String height) {
		// TODO Auto-generated method stub
		this.height=height;
	}


	public String getDateOfbirth() {
		return dateOfbirth;
	}


	public void setDateOfbirth(String dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}


	public void setAttendingTeams(ArrayList<Team> attendingTeams) {
		this.attendingTeams = attendingTeams;
	}
	
	
}

