package com.epic.score_app.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epic.score_app.model.News;
import com.epic.score_app.view.R;

public class NewsItemAdapter extends ArrayAdapter<News> {
	private List<News> news;
	private Activity context;
	

	public NewsItemAdapter(Activity context,List<News> news) {
		super(context, R.layout.view_player_item, news);
		this.context=context;
		this.news=news;
	
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    
		ImageView icon ;
		TextView title ;
		TextView underTitle;
		
		 LayoutInflater inflator = context.getLayoutInflater();
		
		
		
		
		return super.getView(position, convertView, parent);
	}

}
