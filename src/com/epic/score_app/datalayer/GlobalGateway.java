package com.epic.score_app.datalayer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.JSONArray;

import android.os.Bundle;
import android.os.Handler;

import com.epic.score_app.serviceslayer.JsonService;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.News;
import domainmodel.Wallof;

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
	
	
	public News getNewsDescription(long news_id) {
	       News news= new News();
		   String link=ServiceProvider.Host+"/news.php?nid="+news_id;
	       JSONArray values = null;
	       values=GetData(link);
	       news=  factory.getNewsDescription(values);
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
	
	
	public Wallof update_Wallof(long id, boolean hit)
	{
		JSONArray jvalues = new JSONArray();
		Wallof temp = new Wallof();
		
		String link = ServiceProvider.Host+"/update.php";
		LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
		values.put("wid", id+"");
		if (hit) {
			values.put("like", "");
		}else{
			values.put("dislike", "");
		}
	  jvalues=	sendData(link, values);
	 temp=  factory.getWallofs(jvalues).get(0);
		
		
		return temp;
		
		
	}

	public ArrayList<Wallof> getWallofs(Bundle receivedBundle) {
	   JSONArray values = new JSONArray();
		Object wall_id= receivedBundle.get("wall_id");
	  String link=ServiceProvider.Host+"/";
	   if(wall_id==null)
	   {
		   link+="wallof.php";
		   
	   }else{
		   long id = (Long) wall_id;
		   link+="wallof.php?wallid="+id;
		   
	   }
		
	   
	   values= GetData(link);
	   
	   return factory.getWallofs(values);
	}
	
	
	
	
	
	

}
