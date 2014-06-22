package com.epic.score_app.cache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.epic.score_app.cache.interfaces.Ichacheable;

import domainmodel.Team;
import Utils.Tuple;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class InternalStorageGateway {

	private Context context;
   public InternalStorageGateway(Context activity) {
	this.context=activity;
}
	
	
	public  Bitmap getImage(Tuple<String,Team> teamTuple)
	{
		Bitmap bitmap = null;
		
		String filename= "flag_"+teamTuple.getSecond().getName().toLowerCase()+".png";
		
		File file = new File(context.getFilesDir(),filename );
		if (file.isFile()) {
			Log.i("getting from the storage", filename);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			 bitmap = BitmapFactory.decodeFile(file.getPath(),options);
		}else{
			Log.i("Calling from web", teamTuple.getFirst());
			bitmap= getImagefromUrl(teamTuple);
			
			
		}
		
		
		return bitmap;
	}
	
	
	private Bitmap getImagefromUrl(Tuple<String,Team> teamTuple)
	{
		 URL url;
		 Bitmap bmp=null;
		try {
			url = new URL(teamTuple.getFirst());
			 try {
				 bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				 
				 if(bmp!=null){
					 String filename= "flag_"+teamTuple.getSecond().getName().toLowerCase()+".png";
					 Thread t = new Thread(writeToInternalStorage(filename, bmp));
					 t.run();
					 
				 }
					
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bmp;
	}
	
	
	public Runnable writeToInternalStorage(final String filename,final Bitmap bmp)
	{
		return new Runnable() {
			
			@Override
			public void run() {
				

				try {
				 
					
				
				
				  
				  FileOutputStream out=context.openFileOutput(filename, Context.MODE_PRIVATE);;
				  try {
				       
				         bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
				  } catch (Exception e) {
					  Log.i("Writing to files","fail",e);
					  e.printStackTrace();
				  } finally {
				         try{
				             out.close();
				         } catch(Throwable ignore) {}
				  }
				} catch (Exception e) {
				  e.printStackTrace();
				}
				Log.i("Writing to files","Succes :"+filename);
				
			}
		};
		
	}


	public Bitmap getImage(Ichacheable icache) {
		{
			Bitmap bitmap = null;
			
			String filename= icache.getCacheName();
			
			File file = new File(context.getFilesDir(),filename );
			if (file.isFile()) {
				Log.i("getting from the storage", filename);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				 bitmap = BitmapFactory.decodeFile(file.getPath(),options);
			}else{
				Log.i("Calling from web", icache.getUrl());
				bitmap= getImagefromUrl(icache);
				
				
			}
			
			
			return bitmap;
	}
	
	
	
}
	
	
	private Bitmap getImagefromUrl(Ichacheable icache)
	{
		 URL url;
		 Bitmap bmp=null;
		try {
			url = new URL(icache.getUrl());
			 try {
				 bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				 
				 if(bmp!=null){
					 String filename= icache.getCacheName();
					 Thread t = new Thread(writeToInternalStorage(filename, bmp));
					 t.run();
					 
				 }
					
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bmp;
	}
	
}
