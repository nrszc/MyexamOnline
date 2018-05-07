package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.MakePaperIF;
import henu.bean.ChoiceQuestion;
import henu.bean.Paper;
import henu.others.subject;
import henu.util.DbcpPool;

public class MakePaperImpl implements MakePaperIF {

	@Override
	public boolean makePaper(Paper p) {
		p = makeChoiceQuestion(p);
		p = makeFillQuestion(p);
		p = makeJudgeQuestion(p);
		String sql = "INSERT INTO paper (starttime,endtime,subjectid,papername,"
				+ "c1,c2,c3,c4,c5,c6,c7,c8,c9,c10"
				+ ",f1,f2,f3,f4,f5,j1,j2,j3,j4,j5,tid,classid) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, p.getStarttime());
			ps.setString(2, p.getEndtime());
			ps.setInt(3, p.getSubjectid());
			ps.setString(4, p.getPapername());
			ps.setInt(5, p.getC1());
			ps.setInt(6, p.getC2());
			ps.setInt(7, p.getC3());
			ps.setInt(8, p.getC4());
			ps.setInt(9, p.getC5());
			ps.setInt(10, p.getC6());
			ps.setInt(11, p.getC7());
			ps.setInt(12, p.getC8());
			ps.setInt(13, p.getC9());
			ps.setInt(14, p.getC10());
			ps.setInt(15, p.getF1());
			ps.setInt(16, p.getF2());
			ps.setInt(17, p.getF3());
			ps.setInt(18, p.getF4());
			ps.setInt(19, p.getF5());
			ps.setInt(20, p.getJ1());
			ps.setInt(21, p.getJ2());
			ps.setInt(22, p.getJ3());
			ps.setInt(23, p.getJ4());
			ps.setInt(24, p.getJ5());
			ps.setString(25, p.getTid());
			ps.setInt(26, p.getClassid());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbcpPool.close();
		if(result>0)
		return true;
		else 
		return false;
	}
	
	private Paper makeJudgeQuestion(Paper p) {
		String sql = null;
		sql ="SELECT j_id FROM judgequestion "
				+ "WHERE j_subjectid="+p.getSubjectid()+" and j_id >= (SELECT floor(RAND() * "
				+ "(SELECT MAX(j_id) FROM judgequestion)))  "
				+ "ORDER BY j_id LIMIT 5";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			rs.next();
			p.setJ1(rs.getInt("j_id"));
			rs.next();
			p.setJ2(rs.getInt("j_id"));
			rs.next();
			p.setJ3(rs.getInt("j_id"));
			rs.next();
			p.setJ4(rs.getInt("j_id"));
			rs.next();
			p.setJ5(rs.getInt("j_id"));
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return p;
	}

	private Paper makeFillQuestion(Paper p) {
		String sql = null;
		sql ="SELECT f_id FROM fillquestion "
				+ "WHERE f_subjectid="+p.getSubjectid()+" and f_id >= (SELECT floor(RAND() * "
				+ "(SELECT MAX(f_id) FROM fillquestion)))  "
				+ "ORDER BY f_id LIMIT 5";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			rs.next();
			p.setF1(rs.getInt("f_id"));
			rs.next();
			p.setF2(rs.getInt("f_id"));
			rs.next();
			p.setF3(rs.getInt("f_id"));
			rs.next();
			p.setF4(rs.getInt("f_id"));
			rs.next();
			p.setF5(rs.getInt("f_id"));
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return p;
	}

	public Paper makeChoiceQuestion(Paper p)
	{
		String sql = null;
		sql ="SELECT c_id FROM choicequestion "
				+ "WHERE c_subjectid="+p.getSubjectid()+" and c_id >= (SELECT floor(RAND() * "
				+ "(SELECT MAX(c_id) FROM choicequestion)))  "
				+ "ORDER BY c_id LIMIT 10";
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			rs.next();
			p.setC1(rs.getInt("c_id"));
			rs.next();
			p.setC2(rs.getInt("c_id"));
			rs.next();
			p.setC3(rs.getInt("c_id"));
			rs.next();
			p.setC4(rs.getInt("c_id"));
			rs.next();
			p.setC5(rs.getInt("c_id"));
			rs.next();
			p.setC6(rs.getInt("c_id"));
			rs.next();
			p.setC7(rs.getInt("c_id"));
			rs.next();
			p.setC8(rs.getInt("c_id"));
			rs.next();
			p.setC9(rs.getInt("c_id"));
			rs.next();
			p.setC10(rs.getInt("c_id"));
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return p;
	}
	
	

}
