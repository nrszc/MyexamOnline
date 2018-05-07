package henu.others;

import java.sql.ResultSet;

import henu.util.DbcpPool;

public class myPaper {
	public String getPaperName(int paperid)
	{
		String sql = null;
		sql = "select * from paper where paperid="+paperid+"";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			sb = rs.getString("papername");
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return sb;
	}
}
