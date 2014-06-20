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

import domainmodel.Player;


public class PlayerItemAdapter extends ArrayAdapter<Player>{
	
	
    private final Activity context;
	private List<Player>   list;
	
	public PlayerItemAdapter(Activity context, List<Player> lis) {
		
		super(context, R.layout.view_player_item, lis);
		this.context = context;
	    this.list = lis;
	    
	}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	 
    	View view= null;
    	
    	  LayoutInflater inflator = context.getLayoutInflater();
   	      view = inflator.inflate(R.layout.view_player_item, null);
    		Player p = list.get(position);
    		TextView name = (TextView) view.findViewById(R.id.label_playeritem_name);
    		TextView lastname = (TextView) view.findViewById(R.id.label_playeritem_lastname);
    		TextView nationality = (TextView) view.findViewById(R.id.label_playeritem_nationality);
    		
            name.setText(p.getName());
            lastname.setText(p.getLastname());
    		nationality.setText(p.getNationality());
    	   		
    	
    		 
    		 
    	 
    	return view;
    }


	public void refresh(ArrayList<Player> array) {
		list.addAll(array);
		this.notifyDataSetChanged();
		
	}



}
