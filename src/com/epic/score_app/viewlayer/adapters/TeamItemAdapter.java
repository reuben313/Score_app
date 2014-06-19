package com.epic.score_app.viewlayer.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Team;

public class TeamItemAdapter extends ArrayAdapter<Team>{
	private final Activity context;
	private List<Team>   list;

	public TeamItemAdapter (Activity context, List<Team> lis) {
		super(context, R.layout.view_player_item, lis);
		this.context = context;
		this.list = lis; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view= null;
		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.team_player_item, null);
		Team t = list.get(position);
		TextView name = (TextView) view.findViewById(R.id.label_teamitem_name);
		name.setText(t.getName());
        
	    ImageView flag=(ImageView) view.findViewById(R.id.image_player);
		
	
		ServiceProvider.getInsance().getImageFromUrl("https://lh3.ggpht.com/awFplteC-a5YwV0vNAhbnypE8rR4WffRiyM5kgzOyKgM1cDQyttyhAp0fenJ4PO73no=w300", flag);
		
		
		
		return view;
	}

	public void refresh(ArrayList<Team> array) {
		list.addAll(array);
		this.notifyDataSetChanged();
	}
}
