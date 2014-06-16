package com.epic.score_app.serviceslayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
		}return res;
		}
    
    
    public JSONArray sendData(String link,LinkedHashMap<String, String> valuePair)
    {     StringBuilder builder = new StringBuilder();
    	  JSONArray jsonArray=new JSONArray();    	
    	  HttpClient client = new DefaultHttpClient();
    	  HttpPost post = new HttpPost(link);
    	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	  for (String key : valuePair.keySet()) {
			
    		  urlParameters.add(new BasicNameValuePair(key, valuePair.get(key)));
    		  
		}
    	  
    	  try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  
    	  try {
              HttpResponse response = client.execute(post);
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
    }




