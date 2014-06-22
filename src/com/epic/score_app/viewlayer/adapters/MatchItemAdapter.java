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

import domainmodel.Match;
import domainmodel.News;
import domainmodel.Player;
import domainmodel.Team;

public class MatchItemAdapter extends ArrayAdapter<Match> {
	private List<Match> match;
	private Activity context;
	private LayoutInflater inflator;
	

	public MatchItemAdapter(Activity context,List<Match> match) {
		super(context, R.layout.view_wedstrijd_item, match);
		this.context=context;
		this.match=match;
		 inflator = this.context.getLayoutInflater();
	
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view =null;
	 view=	inflator.inflate(R.layout.view_wedstrijd_item, null);
	Match _match = match.get(position);
	
	TextView matchdatum  = (TextView)view.findViewById(R.id.matchdatum);
	TextView tijd= (TextView)view.findViewById(R.id.tijd);
	TextView thuis = (TextView)view.findViewById(R.id.thuisploeg);
	TextView uit = (TextView)view.findViewById(R.id.uitploeg);
	matchdatum.setText(_match.getMatchDate());
	tijd.setText(_match.getBeginsAt());
	Team teamhome = _match.getTeamHome();
	Team teamuit = _match.getTeamVisitor();
	thuis.setText(teamhome.getName());
	uit.setText(teamuit.getName());
		
		 
		 
		 
		 
		
		
		
		
		return view;
	}
	
	
	
	
}
