package com.epic.score_app.temp;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Handler;

import com.epic.score_app.model.News;
import com.epic.score_app.model.Wallof;
import com.epic.score_app.services.EntityFactory;
import com.epic.score_app.services.JsonService;
import com.epic.score_app.services.ServiceProvider;

public class GlobalGateway extends JsonService{
 private EntityFactory factory = new EntityFactory();
	public GlobalGateway(Handler h) {
		super(h);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<News> getNews() {
       ArrayList<News> news= new ArrayList<News>();
	   String link=ServiceProvider.Host+"/news.php";
       JSONArray values = null;
       values=GetData(link);
       news=  factory.getNews(values);
       return news;
	}
	
	
	public ArrayList<Wallof> getWallofs()
	{
		ArrayList<Wallof> wallofs = new ArrayList<Wallof>();
		String link=ServiceProvider.Host+"/wallof.php";
		JSONArray values = GetData(link);
		wallofs=factory.getWallofs(values);
		
		
		
		return wallofs;
	}
	
	
	
	
	
	

}
