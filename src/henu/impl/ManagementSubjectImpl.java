package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.ManagementSubjectIF;
import henu.bean.ChoiceQuestion;
import henu.bean.Subject;
import henu.others.subject;
import henu.util.DbcpPool;

public class ManagementSubjectImpl implements ManagementSubjectIF{

	@Override
	public boolean addsubject(Subject sub) {
		String sql = "INSERT INTO subject (subjectname)VALUES('"+sub.getSubjectname()+"')";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int result = 0 ;
		try {
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
	public List<Subject> findsubject() {
		String sql = null;
		sql = "select * from subject";
		List<Subject> list = new ArrayList<Subject>();
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			Subject sj = new Subject();
			sj.setSubjectid(rs.getInt("subjectid"));
			sj.setSubjectname(rs.getString("subjectname"));
			list.add(sj);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public boolean deletesubject(int subjectid) {
		int result = 0;
		String sql = "delete from subject where subjectid="+subjectid+"";
		result = DbcpPool.executeUpdate(sql);
		if(result>0)
			return true;
		else 
		    return false;
	}

	@Override
	public boolean updatesubject(Subject sub) {
		String sql = "UPDATE subject SET subjectname=? WHERE subjectid="+sub.getSubjectid()+"";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
        int result = 0;
		try {
			result = ps.executeUpdate();
			ps.setString(1, sub.getSubjectname());
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
