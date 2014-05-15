package com.epic.score_app.model;

import java.util.Date;



public class News {
	
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
