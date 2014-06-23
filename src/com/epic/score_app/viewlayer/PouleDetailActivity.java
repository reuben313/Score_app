package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TableLayout;
import android.widget.TextView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Group;
import domainmodel.Standing;

public class PouleDetailActivity extends Activity {
	ArrayList<Standing> standings = new ArrayList<Standing>();
	TableLayout table = null;
	TextView land1, land2, land3, land4, win1, win2, win3, win4, draw1, draw2, draw3, draw4, lose1, lose2, lose3, lose4, point1, point2, point3, point4 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poule_detail_view);
		Bundle b = getIntent().getExtras();
		Group g = (Group) b.getSerializable("group");
		Bundle requestBundle = new Bundle();
		requestBundle.putInt("requestcode", ServiceProvider.getStandingsByGroepID);
		requestBundle.putInt("group_id",(int) g.getGroupId());
		table = (TableLayout)findViewById(R.id.Table1);
		
		land1 = (TextView)findViewById(R.id.textView7);
		win1 = (TextView)findViewById(R.id.textView8);
		draw1 = (TextView)findViewById(R.id.textView9);
		lose1= (TextView)findViewById(R.id.textView10);
		point1 = (TextView)findViewById(R.id.textView11);
		
		land2 = (TextView)findViewById(R.id.textView12);
		win2 = (TextView)findViewById(R.id.textView13);
		draw2 =(TextView)findViewById(R.id.textView14);
		lose2 = (TextView)findViewById(R.id.textView15);
		point2 = (TextView)findViewById(R.id.textView16);
		
		land3 = (TextView)findViewById(R.id.textView17);
		win3 = (TextView)findViewById(R.id.textView18);
		draw3 =(TextView)findViewById(R.id.textView19);
		lose3= (TextView)findViewById(R.id.textView20);
		point3 = (TextView)findViewById(R.id.textView21);
		
		land4 = (TextView)findViewById(R.id.textView22);
		win4 = (TextView)findViewById(R.id.textView23);
		draw4 =(TextView)findViewById(R.id.textView24);
		lose4 = (TextView)findViewById(R.id.textView25);
		point4 = (TextView)findViewById(R.id.textView26);
		
		TextView p = (TextView)findViewById(R.id.textView1);
		p.setText(g.getName());

	}
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==ServiceProvider.getStandingsByGroepID_response) {
				standings=(ArrayList<Standing>) msg.obj;
			
				land1.setText(standings.get(0).getTeam().getName());
				land2.setText(standings.get(1).getTeam().getName());
				land3.setText(standings.get(2).getTeam().getName());
				land4.setText(standings.get(3).getTeam().getName());
				
				win1.setText(standings.get(0).getWin());
				win2.setText(standings.get(1).getWin());
				win3.setText(standings.get(2).getWin());
				win4.setText(standings.get(3).getWin());
				
				draw1.setText(standings.get(0).getDraw());
				draw2.setText(standings.get(1).getDraw());
				draw3.setText(standings.get(2).getDraw());
				draw4.setText(standings.get(3).getDraw());
				
				lose1.setText(standings.get(0).getLose());
				lose2.setText(standings.get(1).getLose());
				lose3.setText(standings.get(2).getLose());
				lose4.setText(standings.get(3).getLose());
				
				point1.setText(standings.get(0).getPoint());
				point2.setText(standings.get(1).getPoint());
				point3.setText(standings.get(2).getPoint());
				point4.setText(standings.get(3).getPoint());
				}
		};
	};
}

