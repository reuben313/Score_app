package com.epic.score_app.serviceslayer;

import com.epic.score_app.serviceslayer.team.TeamService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

public class NotificationProvider extends Service {







@Override
public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return  binder;
}
	

public class MyBinder extends Binder {
	public NotificationProvider getService() {
return NotificationProvider.this;
}
}
private final IBinder binder = new MyBinder();



public void test(Bundle b,Handler handler){
	
	   
	
	   TeamService  teamservice = new TeamService();
	   teamservice.setHandler(handler);
	   teamservice.execute(b);
	
}
}
