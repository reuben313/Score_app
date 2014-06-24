package com.epic.score_app.viewlayer.adapters;

import java.util.List;

import Utils.Tuple;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;
import com.epic.score_app.view.R;

import domainmodel.Standing;
import domainmodel.Team;

public class StandingItemAdapter extends ArrayAdapter<Standing> {
   private List<Standing> standings;
   private LruCache<String, Bitmap> mMemoryCache;
   private Activity context;
   private TextView teamname,teamwin,teamlose,teamdraw,teampoints;
   private ImageView flg;
	public StandingItemAdapter(Activity context,List<Standing> list) {
		super(context,R.layout.standings_item,list);
		this.standings=list;
		this.context = context;
		
		init();
	}
	private void init() {
		  final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		    // Use 1/8th of the available memory for this memory cache.
		    final int cacheSize = maxMemory / 8;

		    mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
		        @Override
		        protected int sizeOf(String key, Bitmap bitmap) {
		            // The cache size will be measured in kilobytes rather than
		            // number of items.
		            return bitmap.getByteCount() / 1024;
		        }
		    };
		
	}
	
	
	@Override
		public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;

		LayoutInflater inflator = context.getLayoutInflater();
		view = inflator.inflate(R.layout.standings_item, null);
		Standing s =standings.get(position);
		Team t = s.getTeam();
		flg = (ImageView) view.findViewById(R.id.standing_team_flag);
		teamname=(TextView) view.findViewById(R.id.standing_team_name_label);
		teamwin=(TextView) view.findViewById(R.id.standing_team_win_label);
		
		teamlose=(TextView) view.findViewById(R.id.standing_team_lose_label);
		teamdraw=(TextView) view.findViewById(R.id.standing_team_draw_label);
		teampoints=(TextView) view.findViewById(R.id.standing_team_point_label);
		ServiceProvider.getInsance().getImageFromUrll(mMemoryCache, new Tuple<Ichacheable, ImageView>(t, flg));
		
		teamname.setText(t.getName());
		teamwin.setText(s.getWin()+"");
		teamdraw.setText(s.getDraw()+"");
		teampoints.setText(s.getPoint()+"");
		teamlose.setText(s.getLose()+"");
		
		
		
		
		
	
	
	
	return view;
	}

}
