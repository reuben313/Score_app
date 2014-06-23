package com.epic.score_app.viewlayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.epic.score_app.view.R;

import domainmodel.Group;

public class PouleDetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poule_detail_view);
		Bundle b = getIntent().getExtras();
		Group g = (Group) b.getSerializable("group");
		
		TextView p = (TextView)findViewById(R.id.textView1);
		p.setText(g.getName());
	}
}
