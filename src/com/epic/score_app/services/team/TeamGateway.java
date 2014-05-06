package com.epic.score_app.services.team;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.epic.score_app.model.Match;
import com.epic.score_app.model.Player;
import com.epic.score_app.model.Team;
import com.epic.score_app.services.EntityFactory;
import com.epic.score_app.services.JsonService;
import com.epic.score_app.services.ServiceProvider;

public class TeamGateway extends JsonService {
    private EntityFactory factory= new EntityFactory();
	public TeamGateway(Handler h) {
		super(h);
		
	}
	
	public void sth(Handler han) {
		handler=han;
	}

	public JSONArray getPlayers(Integer... params) {
		Log.i("getPlayers ", "Begin");
		StringBuilder builder = new StringBuilder();
		builder.append("lim=");
		builder.append(params[0]);
		builder.append("&");
		builder.append("offset=");
		
		builder.append(params[1]);
		
		String link = ServiceProvider.Host+"/user.php?"+builder.toString();
		Log.i("Test",""+link);
		JSONArray values = null;
		
			 values = GetData(link);
			 
			 
			 //http://scoreapp.freeiz.com/user.php?lim=10&offset=3
			//int len= values.length();
			//for (int i = 0; i < len; i++) {
				
				//	JSONObject obj = values.getJSONObject(i);
					//String name= obj.getString("FIRST_NAME");
				//	String lastname= obj.getString("LAST_NAME");
					//String nation= obj.getString("NATIONALITY");
					//Log.i("LOCAL ", String.format("name : %s lastname : %s nat : %s", name,lastname,nation));
                //    Player p1 = new Player();
                //    p1.setName(name);
                //    p1.setLastname(lastname);
                //    res.add(p1);
			//}
		Message msg = new Message();
		msg.what=ServiceProvider.getPlayers_response;
		ArrayList<Player> pls= factory.getPlayers(values);
		msg.obj=pls;
		Log.i("sending the data to handler ",pls.size()+"");
		handler.sendMessage(msg);
		
		return  values;
		
	}

	

	public void getMatcheslp(int... params) {
		int howmany= params[0];
		int offset=params[1];
		String link = ServiceProvider.Host+"/matches.php?limit="+howmany+"&"+"offset="+offset;
		JSONArray values = null;
		
		 values = GetData(link);
		 Message msg = new Message();
			msg.what=ServiceProvider.getmatches_response;
			ArrayList<Match> matches= factory.getMatches(values);
			msg.obj=matches;
			Log.i("sending the data to handler ","lazyPlayer");
			handler.sendMessage(msg);
		 }
    
	

	public void getLazyPlayer(int playerId) {
		//http://localhost/scoreapp/usercomplete.php?users=1
		//http://scoreapp.freeiz.com/usercomplete.php?users=1
		//usercomplete.php?users=1
		String link = ServiceProvider.Host+"/usercomplete.php?users="+playerId;
		
		JSONArray values = null;
		
	    values = GetData(link);
			 
	    Message msg = new Message();
		msg.what=ServiceProvider.getLazyPlayer_response;
		Player player= factory.getLazyPlayer(values);
		msg.obj=player;
		Log.i("sending the data to handler ","lazyPlayer");
		handler.sendMessage(msg);
	}
	
	public void getTeams(int... params){
	    JSONArray values= null;
		try{
		int limit= params[0];
		int offset=params[1];
		int teamid =params[2];
		String link=ServiceProvider.Host+"/team.php?teams=true&limit="+limit+"&offset="+offset;
		values= GetData(link);
		ArrayList<Team> teams =factory.getTeams(values);
		Message msg = new Message();
		msg.what=ServiceProvider.getTeams_response;
		msg.obj=teams;
		handler.sendMessage(msg);
		
		
		}catch(Exception ex)
		{
			
			
		}
		
	}
		
	

}
