package com.epic.score_app.services.team;


public class GameMatchFilter {

	
	public enum MATCHSTATUS { PLAYED , NOTPLAYED };
	public enum ORDERBY{DATE,TEAM,STADUIM,GROUP};
	
	public MATCHSTATUS matchStatus= null;
	public ORDERBY[] orderBys=null;
	public String rawFilter = null;
	public int matchId=-1;
	public String status_column="status";
	
	
	public String getFilterQuery(){
		
		StringBuilder result = new StringBuilder("");
		if (rawFilter!=null) {
			
			result.append(rawFilter);
			
			orderBys= null;
			matchStatus=null;
			
		}
		
		
			
			
		
		
		if (matchStatus!=null) {
		if (matchId!=-1) {
			result.append("where ");	
		    String res=	between(new Object[]{"match_id="+matchId,"match_status="+matchStatus.toString()}, "and");
		    result.append(res);
			
		}else{
			
			
		} 	
		
		
		
		}else{
			
			if (matchId!=-1) {
				result.append("where match_id="+matchId);
			}
			
		}
		
		
		
		
		
		
		if(orderBys!=null){
			
		
			result.append("orderby");
			result.append(between(orderBys,","));
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		return result.toString();
	}
	
	
	
	public static  String between(Object[] t,String dat){
		
		return recurBetween(t,0,dat);


		}



		public static String recurBetween(Object[] t , int pos,String dat){
			 String current = null;
			 
		  try{
			
			  current=  t[pos].toString();
			  
			  t[pos+1].toString();
			  
			 return current+dat+" "+recurBetween(t, pos+1,dat);
			  
		  }catch(Exception rt) {


		   return current;
			  
		  }
	
	
		}	
}
