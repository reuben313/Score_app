package com.epic.score_app.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.epic.score_app.R;
import com.epic.score_app.services.NotificationProvider;


public class MainActivity extends ActionBarActivity {
 private Context context=this;
 private Button viewPlayerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
  
     
     viewPlayerButton= (Button)findViewById(R.id.viewplayer_button);
     viewPlayerButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		 Intent io = new Intent(context, ViewPlayersActivity.class);
		 startActivity(io);
			
		}
	});
     
    }
    @Override
    	protected void onStart() {
    	 Context context= getApplicationContext();
		    Intent i= new Intent(context, NotificationProvider.class);
	    	startService(i);
	    	Log.i("Service Started", "OK");
    		super.onStart();
    	}
    
    @Override
    protected void onResume() {
    	
    	
    	super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
           
            return rootView;
        }
    }
    
    
    
    
   
}
