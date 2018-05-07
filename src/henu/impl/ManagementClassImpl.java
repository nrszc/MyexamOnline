package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.ManagementClassIF;
import henu.bean.Classbean;
import henu.others.myClass;
import henu.util.DbcpPool;

public class ManagementClassImpl implements ManagementClassIF{

	@Override
	public boolean addclass(Classbean mc) {
		String sql = "insert into class(classname,major,college,grade) values(?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, mc.getClassname());
			ps.setString(2, mc.getMajor());
			ps.setString(3, mc.getCollege());
			ps.setString(4, mc.getGrade());
			r=ps.executeUpdate();
			ps.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		if(r==0)
			return false;
		else 
			return true;
	}

	@Override
	public List<Classbean> findclass() {
		String sql = "select * from class ";
		ResultSet rs = DbcpPool.executeQuery(sql);
		List<Classbean> list = new ArrayList<Classbean>();
		try {
			while(rs.next())
			{
				Classbean s = new Classbean();
				s.setClassid(Integer.parseInt(rs.getString("classid")));
				s.setClassname(rs.getString("classname"));
				s.setMajor(rs.getString("major"));
				s.setCollege(rs.getString("college"));
				s.setGrade(rs.getString("grade"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public boolean deleteclass(int classid) {
		String sql = "DELETE FROM class WHERE classid= " + classid + "";
		int result = 0 ;
		result = DbcpPool.executeUpdate(sql);
		DbcpPool.close();
		if(result>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateclass(Classbean mc) {
		String sql = null;
		sql = "update class set classname=?,"
				+"major=?, college=?,grade=? where classid=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		
		ps.setString(1, mc.getClassname());
		ps.setString(2, mc.getMajor());
		ps.setString(3, mc.getCollege());
		ps.setString(4, mc.getGrade());
		ps.setInt(5, mc.getClassid());
		result = ps.executeUpdate();
		ps.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    DbcpPool.close();
	   
	   if(result>0)
	       return true;
	   else 
	       return false;
	}

}
