package domainmodel;

import com.epic.score_app.cache.interfaces.Ichacheable;

public class Wallof implements Ichacheable{
  private long wallofID;
  private int likes;
  private int dislikes;
  private int photoId;
  private String photolink;
  public enum HIT {LIKE,DISLIKE};

  
  
public long getWallofID() {
	return wallofID;
}
public void setWallofID(long wallofID) {
	this.wallofID = wallofID;
}
public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
public int getDislikes() {
	return dislikes;
}
public void setDislikes(int dislikes) {
	this.dislikes = dislikes;
}
public int getPhotoId() {
	return photoId;
}
public void setPhotoId(int photoId) {
	this.photoId = photoId;
}
public String getPhotolink() {
	return photolink;
}
public void setPhotolink(String photolink) {
	this.photolink = photolink;
}
@Override
public String getCacheName() {
	// TODO Auto-generated method stub
	return "wallof_"+wallofID+".png";
}
@Override
public String getUrl() {
	// TODO Auto-generated method stub
	return photolink;
}
}
