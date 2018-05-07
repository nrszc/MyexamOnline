package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import henu.IF.UpdatePWIF;
import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.util.DbcpPool;

public class UpdatePWIpml implements UpdatePWIF{

	@Override
	public int updatetPW(Teacher tc) {
		String sql = null;
  		 sql = "select count(*) from teacher where tid='"+tc.getTid()+"' and pwd ='"+tc.getPwd()+"'";
  	     ResultSet rs = DbcpPool.executeQuery(sql);
  	     int count = 0;
  	     try {
			if(rs.next())
			{
				count = rs.getInt("count(*)");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbcpPool.close();
		if(count>0)
		{
			sql = "update teacher set pwd=? where tid=?";
		    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		    int result = 0;
		    try {
			ps.setString(1, tc.getNewpwd());
			ps.setString(2, tc.getTid());
			result = ps.executeUpdate();
			ps.close();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    DbcpPool.close();
		   if(result>0)
		   return 2;
		   else 
		   return 1;
		}
		else
  	        return 0;	
	}

	@Override
	public int updatesPW(Student sd) {
		String sql = null;
 		 sql = "select count(*) from student where sid='"+sd.getSid()+"' and pwd ='"+sd.getPwd()+"'";
 	     ResultSet rs = DbcpPool.executeQuery(sql);
 	     int count = 0;
 	     try {
			if(rs.next())
			{
				count = rs.getInt("count(*)");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbcpPool.close();
		if(count>0)
		{
			sql = "update student set pwd=? where sid=?";
		    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		    int result = 0;
		    try {
			ps.setString(1, sd.getNewpwd());
			ps.setString(2, sd.getSid());
			result = ps.executeUpdate();
			ps.close();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    DbcpPool.close();
		   if(result>0)
		   return 2;
		   else 
		   return 1;
		}
		else
 	        return 0;	
	}

	@Override
	public int updateaPW(Admin ad) {
		String sql = null;
 		 sql = "select count(*) from administrator where aid='"+ad.getAid()+"' and pwd ='"+ad.getPwd()+"'";
 	     ResultSet rs = DbcpPool.executeQuery(sql);
 	     int count = 0;
 	     try {
			if(rs.next())
			{
				count = rs.getInt("count(*)");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbcpPool.close();
		if(count>0)
		{
			sql = "update administrator set pwd=? where aid=?";
		    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		    int result = 0;
		    try {
			ps.setString(1, ad.getNewpwd());
			ps.setString(2, ad.getAid());
			result = ps.executeUpdate();
			ps.close();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    DbcpPool.close();
		   if(result>0)
		   return 2;
		   else 
		   return 1;
		}
		else
 	        return 0;	
	}

}
