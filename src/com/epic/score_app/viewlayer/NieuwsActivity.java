package com.epic.score_app.viewlayer;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;
import com.epic.score_app.view.R.id;
import com.epic.score_app.view.R.layout;
import com.epic.score_app.view.R.menu;
import com.epic.score_app.viewlayer.adapters.NewsItemAdapter;

import domainmodel.News;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("NewApi")
public class NieuwsActivity extends Activity {
	private 	NewsItemAdapter adapter;
	

	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nieuws);
		List<News> news = new ArrayList<News>();
	 adapter = new NewsItemAdapter(this,news );
	ListView list =(ListView)	findViewById(R.id.news_list);
	list.setOnItemClickListener(onNiewsClick);
	list.setAdapter(adapter);
	Bundle b = new Bundle();
	b.putInt("requestcode", ServiceProvider.getNews);
	b.putInt("compid", 1);
	ServiceProvider.getInsance().getData(b, niewsHandler);
	
        

       
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nieuws, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	private Handler niewsHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==ServiceProvider.getNews_response) {
				 ArrayList<News> newss = new ArrayList<News>();
			newss=(ArrayList<News>)	msg.obj;
			Log.i("news received", newss.size()+"");
			adapter.addAll(newss);
			}
			
		};
	};
	
	
	private OnItemClickListener onNiewsClick= new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {

			News selectedniews= adapter.getItem(pos);
			
			Intent intent = new Intent(NieuwsActivity.this,NewsDescription.class);
			Bundle b = new Bundle();
			//hotfix
			b.putSerializable("news", selectedniews);
			
			intent.putExtras(b);

			startActivity(intent);

		}


	};



}
