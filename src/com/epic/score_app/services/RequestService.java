package com.epic.score_app.services;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public abstract class RequestService extends AsyncTask<Bundle, String, Void> {

	protected Handler handler;
	
	
	public RequestService(){
    }
	
	public RequestService(Handler h){
    this.handler=h;
	
	}
	
	@Override
	protected Void doInBackground(Bundle... params) {
		
		Bundle receivedBundle = params[0];
		
		
		
		
		 
		  executeRequest(receivedBundle);
		return null;
	}
	
	public void setHandler(Handler handler2) {
		this.handler=handler2;
			
		}
	
	
	
	abstract protected void executeRequest(Bundle receivedBundle);
	
	
	
}
