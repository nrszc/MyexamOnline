package henu.impl;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import henu.IF.ExamOnlineIF;
import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.bean.Paper;
import henu.util.DbcpPool;

public class ExamOnlineImpl implements ExamOnlineIF{

	@Override
	public List<Paper> examlist(String sid) {
		String sql = null;
		sql = "select * from paper a,class b,subject c,teacher d,student e"
				+ " where e.sid='"+sid+"' and a.classid = b.classid and "
						+ "a.subjectid = c.subjectid and a.tid = d.tid "
						+ "and b.classid = e.classid order by a.starttime desc";
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			Paper p = new Paper();
			p.setClassname(rs.getString("b.classname"));
			String endtime = dateFormat.format( rs.getTimestamp("a.endtime") );
			String starttime = dateFormat.format( rs.getTimestamp("a.starttime") );
			p.setEndtime(endtime);
			p.setStarttime(starttime);
			p.setPapername(rs.getString("a.papername"));
			p.setPaperid(rs.getInt("a.paperid"));
			p.setSubjectname(rs.getString("c.subjectname"));
			p.setTeachername(rs.getString("d.tname"));
			p.setState(compTime(starttime,endtime));
			list.add(p);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	private String compTime(String starttime, String endtime) throws ParseException {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式 
		String nowtime = dateFormat.format( now ); 
		Date a = dateFormat.parse(starttime);  
        Date b = dateFormat.parse(endtime); 
        Date c = dateFormat.parse(nowtime); 
        //Date类的一个方法，如果a早于b返回true，否则返回false  
        if(b.before(c))  
            return "已截止";  
        else if(c.before(a))  
            return "等待考试";  
        else
        	return "正在考试";
	}

	@Override
	public int getScore(String[] Q, int paperid,String sid) {
		int choiceScore = 0,fillScore = 0,judgeScore = 0,Score=0;
		choiceScore = choiceQScore(Q, paperid,sid);
		fillScore = fillQScore(Q, paperid,sid);
		judgeScore = judgeQScore(Q, paperid,sid);
		System.out.println(choiceScore+" "+fillScore+" "+judgeScore);
		Score = choiceScore + fillScore + judgeScore;
		String sql = "INSERT INTO grade (sid,score,paperid) "
				+ "VALUES (?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		try {
			ps.setString(1, sid);
			ps.setInt(2, Score);
			ps.setInt(3, paperid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbcpPool.close();
		return Score;
	}

	private int judgeQScore(String[] q, int paperid,String sid) {
		String sql = null;
		sql = "select * from paper,judgequestion where (j_id=j1 or j_id=j2 or "
				+ "j_id=j3 or j_id=j4 or j_id=j5) and paperid="+paperid+" order by j_id";
		ResultSet rs = null;
		int Score = 0;
		int i=16;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			if(rs.getString("j_answer").equals(q[i]))
			    Score = Score + 5;
			else
			{
				updateJQMistake(rs.getInt("j_id"),q[i],paperid,sid);
			}
			i++;
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return Score;
	}

	private int fillQScore(String[] q, int paperid,String sid) {
		String sql = null;
		sql = "select * from paper,fillquestion where (f_id=f1 or f_id=f2 or "
				+ "f_id=f3 or f_id=f4 or f_id=f5) and paperid="+paperid+" order by f_id";
		ResultSet rs = null;
		int Score = 0;
		int i=11;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			if(rs.getString("f_answer").equals(q[i]))
			    Score = Score + 5;
			else
			{
				updateFQMistake(rs.getInt("f_id"),q[i],paperid,sid);
			}
			i++;
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return Score;
	}

	private int choiceQScore(String[] q, int paperid,String sid) {
		String sql = null;
		int Score = 0;
		sql = "select * from paper,choicequestion where (c_id=c1 or c_id=c2 or "
				+ "c_id=c3 or c_id=c4 or c_id=c5 or c_id=c6 or "
				+ "c_id=c7 or c_id=c8 or c_id=c9 or c_id=c10) and paperid="+paperid+" ";
		ResultSet rs = null;
		int i=1;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			if(rs.getString("c_answer").equals(q[i]))
			    Score = Score + 5;
			else
			{
				updateCQMistake(rs.getInt("c_id"),q[i],paperid,sid);
			}
			i++;
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return Score;
	}

	private void updateCQMistake(int qid, String q, int paperid, String sid) {
		String sql = "INSERT INTO mistakes (sid,questiontype,"
				+ "qid,misanswer"
				+ ",paperid) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		try {
			ps.setString(1, sid);
			ps.setInt(2, 0);
			ps.setInt(3, qid);
			ps.setString(4, q);
			ps.setInt(5, paperid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateFQMistake(int qid, String q, int paperid, String sid) {
		String sql = "INSERT INTO mistakes (sid,questiontype,"
				+ "qid,misanswer"
				+ ",paperid) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		try {
			ps.setString(1, sid);
			ps.setInt(2, 1);
			ps.setInt(3, qid);
			ps.setString(4, q);
			ps.setInt(5, paperid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateJQMistake(int qid, String q, int paperid, String sid) {
		String sql = "INSERT INTO mistakes (sid,questiontype,"
				+ "qid,misanswer"
				+ ",paperid) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		try {
			ps.setString(1, sid);
			ps.setInt(2, 2);
			ps.setInt(3, qid);
			ps.setString(4, q);
			ps.setInt(5, paperid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int checkState(int paperid, String sid) throws ParseException {
		String sql = null;
		sql = "select count(*) from grade where paperid="+paperid+" and sid='"+sid+"'";
		ResultSet rs = null;
		int count = 0;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			count = rs.getInt("count(*)");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			rs.last();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(count>0)
			return 1;
		sql = "select endtime from paper where paperid="+paperid+"";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endtime="";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			endtime = dateFormat.format( rs.getTimestamp("endtime") );
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		Date now = new Date(); 
		String nowtime = dateFormat.format( now );  
        Date a = dateFormat.parse(endtime); 
        Date b = dateFormat.parse(nowtime); 
        //Date类的一个方法，如果a早于b返回true，否则返回false  
        if(a.before(b))  
            return 2;  
        else
        	return 0;
	}
	
	

}
