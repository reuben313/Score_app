package domainmodel;

import java.io.Serializable;
import java.util.Date;

import com.epic.score_app.cache.interfaces.Ichacheable;



public class News implements Serializable,Ichacheable {
	
	private long news_id;
	private String title;
	private String content;
	private String photolink;
	private int contentphotoid;
	private Date datum;
	private String pubdate;
	public long getNews_id() {
		return news_id;
	}
	public void setNews_id(long news_id) {
		this.news_id = news_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhotoLink() {
		return photolink;
	}
	public void setPhotoLink(String titlephotoLink) {
		this.photolink = titlephotoLink;
	}
	public int getContentphotoid() {
		return contentphotoid;
	}
	public void setContentphotoid(int contentphotoid) {
		this.contentphotoid = contentphotoid;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	@Override
	public String getCacheName() {
		
		return "news_"+getTitle()+".png";
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return getPhotoLink();
	}
	
}
