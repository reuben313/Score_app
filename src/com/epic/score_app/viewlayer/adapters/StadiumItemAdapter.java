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

import domainmodel.Stadium;

public class StadiumItemAdapter extends ArrayAdapter<Stadium> {
	private final Activity context;
	private List<Stadium> list;

	public StadiumItemAdapter(Activity context, ArrayList<Stadium> lis) {
		super(context, R.layout.view_player_item, lis);
		this.context = context;
		this.list = lis; 
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view= null;
		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.stadium_item, null);
		Stadium s = list.get(position);
		TextView name = (TextView) view.findViewById(R.id.label_stadiumitem_name);
		name.setText(s.getName());
		return view;
	}
	
	public void refresh(ArrayList<Stadium> array) {
		list.addAll(array);
		this.notifyDataSetChanged();
	}
}
