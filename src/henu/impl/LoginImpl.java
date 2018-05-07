package henu.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import henu.IF.LoginIF;
import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.util.DbcpPool;

public class LoginImpl implements LoginIF{

	@Override
	public int studentLogin(Student std) {
		 String sql = null;
   		 sql = "select count(*) from student where sid='"+std.getSid()+"' and pwd ='"+std.getPwd()+"'";
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
 			return 1;
 		else
   	        return 0;
	}

	@Override
	public int adminLogin(Admin ad) {
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
			return 1;
		else
  	        return 0;
	}

	@Override
	public int teacherLogin(Teacher tc) {
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
			return 1;
		else
  	        return 0;
	}

}
