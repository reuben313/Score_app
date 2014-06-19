package com.epic.score_app.serviceslayer.image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;

import com.epic.score_app.serviceslayer.RequestService;
import com.epic.score_app.serviceslayer.ServiceProvider;

public class ImageService extends RequestService {

	@Override
	protected void executeRequest(Bundle receivedBundle) {
		String link = receivedBundle.getString("link");
		 URL url;
		 Bitmap bmp=null;
		 
		try {
			url = new URL(link);
			 try {
				 bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Message msg = new Message();
		msg.what= ServiceProvider.getImage_response;
		msg.obj=bmp;
		handler.sendMessage(msg);
		

	}

}
