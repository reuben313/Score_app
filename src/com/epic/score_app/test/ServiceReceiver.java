package com.epic.score_app.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class ServiceReceiver extends BroadcastReceiver{

	private Handler handler;
	
	public ServiceReceiver(Handler handle){
		
		this.handler=handle;
		
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
	}

}
