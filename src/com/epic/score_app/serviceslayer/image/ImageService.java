package com.epic.score_app.serviceslayer.image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import Utils.Tuple;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.epic.score_app.cache.InternalStorageGateway;
import com.epic.score_app.serviceslayer.RequestService;
import com.epic.score_app.serviceslayer.ServiceProvider;

import domainmodel.Team;

public class ImageService extends AsyncTask<Tuple<String,Team>, String, Void>  {
   private Context context;
   private Handler handler;
   private InternalStorageGateway gateway;
	public ImageService(Context c) {
		this.context=c;
	}
	
	
	
	@Override
	protected Void doInBackground(Tuple<String,Team>... urls) {
		gateway = new InternalStorageGateway(context);
		
		for (Tuple<String,Team> tuple : urls) {
			Bitmap bit=null;
		bit=	gateway.getImage(tuple);
		if(bit!=null){
		
		sendMsg("flag_"+tuple.getSecond().getName().toLowerCase()+".png",bit);
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
