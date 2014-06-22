package com.epic.score_app.serviceslayer.image;

import Utils.Tuple;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.epic.score_app.cache.InternalStorageGateway;
import com.epic.score_app.cache.interfaces.Ichacheable;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Team;

public class ImageServicee extends AsyncTask<Ichacheable, String, Void>  {
	   private Context context;
	   private Handler handler;
	   private InternalStorageGateway gateway;
		public ImageServicee(Context c) {
			this.context=c;
		}
		
		
		
		@Override
		protected Void doInBackground(Ichacheable... urls) {
			gateway = new InternalStorageGateway(context);
			
			for (Ichacheable icache : urls) {
				Bitmap bit=null;
			bit=	gateway.getImage(icache);
			if(bit!=null){
			
			sendMsg(icache.getCacheName(),bit);
			}
				
			}
			return null;
		}
		
		private void sendMsg(String filename,Bitmap view)
		{
			Message msg = new Message();
			msg.what= ServiceProvider.getImage_response;
			msg.obj=view;
			handler.sendMessage(msg);
			
		   
		    	
		   
			
			
		}
		
		public void setHandler(Handler handler){
			this.handler= handler;
			
		}
		
		/*
		 * 	Message msg = new Message();
			msg.what= ServiceProvider.getImage_response;
			msg.obj=bmp;
			handler.sendMessage(msg);
		 * 
		 * 
		 * */

	}
