package com.epic.score_app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.Handler;



public abstract class JsonService {

	
	protected Handler handler;
	
	public JsonService(Handler h){
		
		this.handler=h;
		
	}
	
	
	public JSONArray GetData(String link){
		JSONArray jsonArray=new JSONArray();
		StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
       
        HttpGet httpGet = new HttpGet(link);
        try {
          HttpResponse response = client.execute(httpGet);
          StatusLine statusLine = response.getStatusLine();
          int statusCode = statusLine.getStatusCode();
          if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
              
            	if (isnothost(line)) {
					builder.append(line);
				}
            }
          } else {
           // Log.e(ParseJSON.class.toString(), "Failed to download file");
          }
        } catch (ClientProtocolException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        
        try {
  	       jsonArray = new JSONArray(builder.toString());
  	    
  	      
  	    } catch (Exception e) {
  	      e.printStackTrace();
  	    }


		
		
		
		return jsonArray;
		
		
		
	}
	
	
	
    public boolean isnothost(String s){
    	
    	boolean  res= true;
    	if (s.equals("<!-- Hosting24 Analytics Code -->")||s.equals("<!-- End Of Analytics Code -->")||s.equals("<script type=\"text/javascript\" src=\"http://stats.hosting24.com/count.php\"></script>")) {
			res=false;
		}
    	
    	
			
return res;
		}
    	
}
