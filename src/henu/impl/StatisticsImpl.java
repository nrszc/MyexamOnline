package henu.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.StatisticsIF;
import henu.bean.DetailStatistics;
import henu.bean.Paper;
import henu.bean.StatisticsTable;
import henu.others.myClass;
import henu.others.myTeacher;
import henu.others.subject;
import henu.util.DbcpPool;

public class StatisticsImpl implements StatisticsIF{

	@Override
	public List<StatisticsTable> getStatistics(String tid) {
		String sql = null;
		sql = "select * from teacher a, paper b, class c, subject e "
				+ "where a.tid=b.tid and b.classid=c.classid and b.subjectid=e.subjectid and a.tid='"+tid+"'";
		List<StatisticsTable> list = new ArrayList<StatisticsTable>();
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			StatisticsTable st = new StatisticsTable();
			st.setPaperid(rs.getInt("b.paperid"));
			st.setPapername(rs.getString("b.papername"));
			st.setClassname(rs.getString("c.classname"));
			String endtime = dateFormat.format( rs.getTimestamp("b.endtime") );
			String starttime = dateFormat.format( rs.getTimestamp("b.starttime") );
			st.setEndtime(endtime);
			st.setStarttime(starttime);
			st.setSubjectname(rs.getString("e.subjectname"));
			st.setTeachername(rs.getString("a.tname"));
			st = StatisticsScore(st);
			list.add(st);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}
	
	private StatisticsTable StatisticsScore(StatisticsTable st)
	{
		String sql = null;
		sql = "select avg(score),min(score),max(score) from grade where paperid="+st.getPaperid()+"";
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			st.setAverscore((rs.getInt("avg(score)")));
			st.setHighestscore((rs.getInt("max(score)")));
			st.setLowestscore((rs.getInt("min(score)")));
		}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return st;
	}

	@Override
	public List<DetailStatistics> getdetailStatistics(int paperid) {
		String sql = null;
		sql = "select * from grade a,student b "
				+ "where a.sid=b.sid and a.paperid="+paperid+"";
		List<DetailStatistics> list = new ArrayList<DetailStatistics>();
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			DetailStatistics ds = new DetailStatistics();
			ds.setScore(rs.getInt("a.score"));
			ds.setSid(rs.getString("b.sid"));
			ds.setSname(rs.getString("b.sname"));
			list.add(ds);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}
	

}
