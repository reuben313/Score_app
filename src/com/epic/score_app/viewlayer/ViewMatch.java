package com.epic.score_app.viewlayer;

import com.epic.score_app.view.R;
import com.epic.score_app.view.R.layout;
import com.epic.score_app.view.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewMatch extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_match);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_match, menu);
		return true;
	}

}
