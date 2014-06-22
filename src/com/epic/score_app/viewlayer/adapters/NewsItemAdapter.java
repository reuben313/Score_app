package com.epic.score_app.viewlayer.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.epic.score_app.view.R;
import com.epic.score_app.viewlayer.SpelerActivity;
import com.epic.score_app.viewlayer.ViewPlayer;

import domainmodel.News;
import domainmodel.Player;

public class NewsItemAdapter extends ArrayAdapter<News> {
	private List<News> news;
	private Activity context;
	private LayoutInflater inflator;
	

	public NewsItemAdapter(Activity context,List<News> news) {
		super(context, R.layout.view_player_item, news);
		this.context=context;
		this.news=news;
		 inflator = this.context.getLayoutInflater();
	
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view =null;
	 view=	inflator.inflate(R.layout.view_news_item, null);
	News _news = news.get(position);
	
	TextView title  = (TextView)view.findViewById(R.id.news_Title);
	TextView pubdate= (TextView)view.findViewById(R.id.news_pubdate);
	title.setText(_news.getTitle());
	pubdate.setText(_news.getPubdate());
		
		 
		 
		 
		 
		
		
		
		
		return view;
	}
	
	
	
	
}
