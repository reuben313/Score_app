package domainmodel;



import java.io.Serializable;

import com.epic.score_app.cache.interfaces.Ichacheable;





public class Stadium implements Ichacheable, Serializable {
	private String name;
	private String description;
	private String photo_link;
	private int competition_id;
	private long stadium_id;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto_link() {
		return photo_link;
	}
	public void setPhoto_link(String photo_link) {
		this.photo_link = photo_link;
	}
	public int getCompetition_id() {
		return competition_id;
	}
	public void setCompetition_id(int competition_id) {
		this.competition_id = competition_id;
	}
	@Override
	public String getCacheName() {
		// TODO Auto-generated method stub
		return "stadium_" + getStadium_id() + ".png";
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return getPhoto_link();
	}
	public long getStadium_id() {
		return stadium_id;
	}
	public void setStadium_id(long stadium_id) {
		this.stadium_id = stadium_id;
	}
	
}