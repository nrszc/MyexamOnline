package henu.others;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import henu.bean.Classbean;
import henu.util.DbcpPool;

public class myClass {
	public List<Classbean> getmyClass() {
		String sql = null;
		sql = "select * from class";
		List<Classbean> list = new ArrayList<Classbean>();
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			Classbean c = new Classbean();
			c.setClassid(rs.getInt("classid"));
			c.setClassname(rs.getString("classname"));
			list.add(c);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}
	
	public String getClassName(int classid)
	{
		String sql = null;
		sql = "select * from class where classid="+classid+"";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			sb = rs.getString("classname");
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
