package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.PersonalInfoIF;
import henu.bean.Admin;
import henu.bean.Student;
import henu.bean.Teacher;
import henu.others.myClass;
import henu.util.DbcpPool;

public class PersonalInfoImpl implements PersonalInfoIF{

	@Override
	public Student getStudentInfo(String sid) {
		String sql = "SELECT * FROM student WHERE sid='"+sid+"'";
		ResultSet rs = null;
		rs = DbcpPool.executeQuery(sql);
		Student stu = new Student();
		try {
			while(rs.next())
			{
				stu.setSid(rs.getString("sid")); 
				stu.setSname(rs.getString("sname"));
				stu.setSex(rs.getString("sex"));
				myClass mc = new myClass();
				stu.setClassname(mc.getClassName(rs.getInt("classid")));
				if(rs.getString("phone")==null)
				    stu.setPhone("");
				else
					stu.setPhone(rs.getString("phone"));
				
				if(rs.getString("address")==null)
				    stu.setAddress("");
				else
					stu.setAddress(rs.getString("address"));
				
				if(rs.getString("birthdate")==null)
				    stu.setBirthdate("");
				else
					stu.setBirthdate(rs.getString("birthdate"));
				
				if(rs.getString("email")==null)
				    stu.setEmail("");
				else
					stu.setEmail(rs.getString("email"));
	       }
			DbcpPool.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		return stu;
	}

	@Override
	public Teacher getTeacherInfo(String tid) {
		String sql = "SELECT * FROM teacher WHERE tid='"+tid+"'";
		ResultSet rs = null;
		rs = DbcpPool.executeQuery(sql);
		Teacher stu = new Teacher();
		try {
			while(rs.next())
			{
				stu.setTid(rs.getString("tid")); 
				stu.setTname(rs.getString("Tname"));
				stu.setSex(rs.getString("sex"));
				if(rs.getString("phone")==null)
				    stu.setPhone("");
				else
					stu.setPhone(rs.getString("phone"));
				
				if(rs.getString("address")==null)
				    stu.setAddress("");
				else
					stu.setAddress(rs.getString("address"));
				
				if(rs.getString("birthdate")==null)
				    stu.setBirthdate("");
				else
					stu.setBirthdate(rs.getString("birthdate"));
				
				if(rs.getString("email")==null)
				    stu.setEmail("");
				else
					stu.setEmail(rs.getString("email"));
	       }
			DbcpPool.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return stu;
	}

	@Override
	public Admin getAdminInfo(String aid) {
		String sql = "SELECT * FROM administrator WHERE aid='"+aid+"'";
		ResultSet rs = null;
		rs = DbcpPool.executeQuery(sql);
		Admin stu = new Admin();
		try {
			while(rs.next())
			{
				stu.setAid(rs.getString("aid")); 
				stu.setAname(rs.getString("aname"));
				stu.setSex(rs.getString("sex"));
				if(rs.getString("phone")==null)
				    stu.setPhone("");
				else
					stu.setPhone(rs.getString("phone"));
				
				if(rs.getString("address")==null)
				    stu.setAddress("");
				else
					stu.setAddress(rs.getString("address"));
				
				if(rs.getString("birthdate")==null)
				    stu.setBirthdate("");
				else
					stu.setBirthdate(rs.getString("birthdate"));
				
				if(rs.getString("email")==null)
				    stu.setEmail("");
				else
					stu.setEmail(rs.getString("email"));
	       }
			DbcpPool.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return stu;
	}

	@Override
	public boolean updateStudentInfo(Student s) {
		String sql = "UPDATE student SET sex=?,phone=?,address=?,birthdate=?,email=? WHERE sid=?";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
        int result = 0;
		try {
			ps.setString(1, s.getSex());
			ps.setString(2, s.getPhone());
			ps.setString(3, s.getAddress());
			ps.setString(4, s.getBirthdate());
			ps.setString(5, s.getEmail());
			ps.setString(6, s.getSid());
			result = ps.executeUpdate();
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

	@Override
	public boolean updateAdminInfo(Admin a) {
		String sql = "UPDATE administrator SET sex=?,phone=?,address=?,birthdate=?,email=? WHERE aid=?";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
        int result = 0;
		try {
			ps.setString(1, a.getSex());
			ps.setString(2, a.getPhone());
			ps.setString(3, a.getAddress());
			ps.setString(4, a.getBirthdate());
			ps.setString(5, a.getEmail());
			ps.setString(6, a.getAid());
			result = ps.executeUpdate();
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

	@Override
	public boolean updateTeacherInfo(Teacher t) {
		String sql = "UPDATE teacher SET sex=?,phone=?,address=?,birthdate=?,email=? WHERE tid=?";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
        int result = 0;
		try {
			ps.setString(1, t.getSex());
			ps.setString(2, t.getPhone());
			ps.setString(3, t.getAddress());
			ps.setString(4, t.getBirthdate());
			ps.setString(5, t.getEmail());
			ps.setString(6, t.getTid());
			result = ps.executeUpdate();
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
