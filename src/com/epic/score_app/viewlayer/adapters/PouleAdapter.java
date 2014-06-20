package com.epic.score_app.viewlayer.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epic.score_app.view.R;

import domainmodel.Group;
import domainmodel.Team;

public class PouleAdapter extends ArrayAdapter<Group> {

	private final Activity context;
	private List<Group> list;

	public PouleAdapter(Activity context, List<Group> lis) {

		super(context, R.layout.view_poule, lis);
		this.context = context;
		this.list = lis;

	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;

		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.view_poule, null);
		Group g = list.get(position);
		ArrayList<Team> teams = g.getGroupTeams();
		TextView team1 = (TextView)view.findViewById(R.id.textView1);
		TextView team2 = (TextView)view.findViewById(R.id.textView2);
		TextView team3 = (TextView)view.findViewById(R.id.textView3);
		TextView team4 = (TextView)view.findViewById(R.id.textView4);
		
		team1.setText(teams.get(0).getName());
		team2.setText(teams.get(1).getName());
		team3.setText(teams.get(2).getName());
		team4.setText(teams.get(3).getName());
		
		return view;
	}
}
