package com.epic.score_app.model;

import java.util.Date;



public class News {
	//[{"news":{"0":"1","NEWS_ID":"1","1":"test","TITLE":"test","2":"test content ","content":"test content ","3":"ere",
	//"title link":"ere","4":"1","content_photo_id":"1","5":"0000-00-00 00:00:00","datum":"0000-00-00 00:00:00"}}]
	private long news_id;
	private String title;
	private String content;
	private String titlephotoLink;
	private int contentphotoid;
	private Date datum;
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
	public String getTitlephotoLink() {
		return titlephotoLink;
	}
	public void setTitlephotoLink(String titlephotoLink) {
		this.titlephotoLink = titlephotoLink;
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
	
}
