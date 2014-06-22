package com.epic.score_app.serviceslayer;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Message;

import com.epic.score_app.datalayer.GlobalGateway;

import domainmodel.News;
import domainmodel.Wallof;

public class GlobalService extends RequestService{

	@Override
	protected void executeRequest(Bundle receivedBundle) {
	     GlobalGateway gateway = new GlobalGateway(handler);
				 int requestCode= receivedBundle.getInt("requestcode");
				
				switch (requestCode) {
				case ServiceProvider.getNews:
					ArrayList<News> news = new ArrayList<News>();
					
					news=gateway.getNews();
					Message msg = new Message();
					msg.what= ServiceProvider.getNews_response;
					msg.obj=news;
					handler.sendMessage(msg);
				break;
				
				case ServiceProvider.getNewsDescription:
					News news_desc =new News();
			long nid=		receivedBundle.getLong("news_id");
					news_desc=gateway.getNewsDescription(nid);
					Message msgNewsDescription = new Message();
					msgNewsDescription.what= ServiceProvider.getNewsDescription_response;
					msgNewsDescription.obj=news_desc;
					handler.sendMessage(msgNewsDescription);
				break;
				
				case ServiceProvider.getWallOf:
					 
					
					ArrayList<Wallof> wallofs=  gateway.getWallofs(receivedBundle);
					
					Message msgwallof = new Message();
					msgwallof.what= ServiceProvider.getWallOf_response;
					msgwallof.obj=wallofs;
					handler.sendMessage(msgwallof);
					
					
				break;
					
					

				default:
					break;
				}
		
	}

}
