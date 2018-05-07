package henu.others;

import java.sql.ResultSet;

import henu.util.DbcpPool;

public class myTeacher {
	public String getTeacherName(String tid)
	{
		String sql = null;
		sql = "select * from teacher where tid='"+tid+"'";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			sb = rs.getString("tname");
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
