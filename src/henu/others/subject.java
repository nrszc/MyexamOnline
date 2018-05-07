package henu.others;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import henu.bean.Admin;
import henu.bean.Subject;
import henu.util.DbcpPool;

public class subject {
	public List<Subject> getSubject() {
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
	
	public String getSubjectName(int subjectid)
	{
		String sql = null;
		sql = "select * from subject where subjectid="+subjectid+"";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			sb = rs.getString("subjectname");
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
