package henu.others;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import henu.bean.Paper;
import henu.util.DbcpPool;

public class CalcTime {
	long min;
	long s;
    public long getmin(int paperid)
    {
    	 subtract(paperid);
    	 return min;
    }
    
    public long getseconds(int paperid)
    {
    	subtract(paperid);
   	    return s;
    }
    
    public void subtract(int paperid)
    {
    	String sql = null;
		sql = "select * from paper where paperid="+paperid+"";
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now1 = new Date(); 
		String nowtime = dateFormat.format( now1 ); 
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			String endtime = dateFormat.format( rs.getTimestamp("endtime") );
			 Date end = dateFormat.parse(endtime);
			 Date now = dateFormat.parse(nowtime);
			   long l = end.getTime() - now.getTime();
			   long day=l/(24*60*60*1000);
			   long hour=(l/(60*60*1000)-day*24);
			   min=((l/(60*1000))-day*24*60-hour*60);
			   s=(l/1000-day*24*60*60-hour*60*60-min*60);
			   min = min + hour*60;
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		
    }
}
