package com.epic.score_app.temp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class ServiceReceiver extends BroadcastReceiver{

	private Handler handler;
	
	public ServiceReceiver(Handler handle){
		
		this.handler=handle;
		//hello
		
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
	}

}
