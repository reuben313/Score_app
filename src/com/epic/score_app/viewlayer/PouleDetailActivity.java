package com.epic.score_app.viewlayer;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Group;
import domainmodel.Standing;

public class PouleDetailActivity extends Activity {
	ArrayList<Standing> standings = new ArrayList<Standing>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poule_detail_view);
		Bundle b = getIntent().getExtras();
		Group g = (Group) b.getSerializable("group");
		Bundle requestBundle = new Bundle();
		requestBundle.putInt("requestcode", ServiceProvider.getStandingsByGroepID);
		requestBundle.putInt("group_id",(int) g.getGroupId());
		
		TextView land1 = (TextView)findViewById(R.id.textView7);
		
		TextView p = (TextView)findViewById(R.id.textView1);
		p.setText(g.getName());
	}
	
	
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			if (msg.what==ServiceProvider.getStandingsByGroepID_response) {
				standings=(ArrayList<Standing>) msg.obj;
			}
			
		};
	};
}
