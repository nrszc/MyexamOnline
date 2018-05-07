package henu.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.MyMistakeIF;
import henu.bean.Errors;
import henu.bean.Mistakes;
import henu.util.DbcpPool;

public class MyMistakeImpl implements MyMistakeIF{

	@Override
	public List<Errors> getmistakeList(String sid) {
		String sql = "select distinct paperid from mistakes where sid='"+sid+"'";
		ResultSet rs = null;		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Errors> list = new ArrayList<Errors>();
		try {
			rs=DbcpPool.executeQuery(sql);

			while(rs.next())
			{
				String sql1 = "select * from paper b,class c,subject d,teacher e,grade f"
						+ " where b.classid=c.classid and b.subjectid=d.subjectid"
						+ " and b.tid=e.tid and b.paperid=f.paperid and "
						+ " b.paperid="+rs.getInt("paperid")+" and f.sid='"+sid+"'";
				rs1=DbcpPool.executeQuery(sql1);
				while(rs1.next()){
				Errors s = new Errors();
				s.setPaperid(rs1.getInt("b.paperid"));
				s.setPapername(rs1.getString("b.papername"));
				String endtime = dateFormat.format( rs1.getTimestamp("b.endtime") );
				String starttime = dateFormat.format( rs1.getTimestamp("b.starttime") );
				s.setEndtime(endtime);
				s.setStarttime(starttime);
		        s.setClassname(rs1.getString("c.classname"));
		        s.setScore(rs1.getInt("f.score"));
		        s.setSubjectname(rs1.getString("d.subjectname"));
		        s.setTname(rs1.getString("e.tname"));
		        String sql2 = "select count(*) from mistakes where "
		        		+ "paperid="+rs.getInt("paperid")+" and sid='"+sid+"'";
		        rs2=DbcpPool.executeQuery(sql2);

		        while(rs2.next()){
		        s.setMistakessum(rs2.getInt("count(*)"));
		        }
	            list.add(s);
				rs2.close();
				}
				rs1.close();
			}
			rs.close();
			System.out.println("===========77");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public List<Mistakes> Mistakesc(String sid, int paperid) {
		int r=0;
		String sql = "select * from mistakes a,choicequestion b where a.paperid="+paperid+" "
				+ "and a.sid='"+sid+"' and a.qid=b.c_id and a.questiontype=0";
		ResultSet rs = null;		
		List<Mistakes> list = new ArrayList<Mistakes>();
		try {
			rs=DbcpPool.executeQuery(sql);
			while(rs.next())
			{
				Mistakes s = new Mistakes();
				
				s.setC_id(rs.getInt("b.c_id"));
				s.setC_question(rs.getString("b.c_question"));
				s.setC_choiceA(rs.getString("b.c_choiceA"));
				s.setC_choiceB(rs.getString("b.c_choiceB"));
				s.setC_choiceC(rs.getString("b.c_choiceC"));
				s.setC_choiceD(rs.getString("b.c_choiceD"));
				s.setC_answer(rs.getString("b.c_answer"));
				if(rs.getString("a.misanswer")==null||rs.getString("a.misanswer").equals(""))
					s.setMisanswer("您没有作答");
				else 
				    s.setMisanswer(rs.getString("a.misanswer"));
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
	public List<Mistakes> Mistakesf(String sid, int paperid) {
		int r=1;
		String sql = "select * from mistakes a, fillquestion b where "
				+ "a.paperid="+paperid+" "
				+ "and a.sid='"+sid+"' and a.qid=b.f_id and a.questiontype=1 ";
		ResultSet rs = null;		
		List<Mistakes> list = new ArrayList<Mistakes>();
		try {
			rs=DbcpPool.executeQuery(sql);
			while(rs.next())
			{
				Mistakes s = new Mistakes();
				
				s.setF_id(rs.getInt("b.f_id"));
				s.setF_question(rs.getString("b.f_question"));
				s.setF_answer(rs.getString("b.f_answer"));
				if(rs.getString("a.misanswer")==null||rs.getString("a.misanswer").equals(""))
					s.setMisanswer("您没有作答");
				else 
				    s.setMisanswer(rs.getString("a.misanswer"));
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
	public List<Mistakes> Mistakesj(String sid, int paperid) {
		int r=2;
		String sql = "select * from mistakes a, judgequestion b where "
				+ "a.paperid="+paperid+" "
				+ "and a.sid='"+sid+"' and a.qid=b.j_id and a.questiontype=2 ";
		ResultSet rs = null;		
		List<Mistakes> list = new ArrayList<Mistakes>();
		try {
			rs=DbcpPool.executeQuery(sql);
			while(rs.next())
			{
				Mistakes s = new Mistakes();
				s.setJ_id(rs.getInt("b.j_id"));
				s.setJ_question(rs.getString("b.j_question"));
				if(rs.getInt("b.j_answer")==0)
				s.setJ_answer("×");
				else if(rs.getInt("b.j_answer")==1)
				s.setJ_answer("√");
				if(rs.getString("a.misanswer")==null||rs.getString("a.misanswer").equals(""))
					s.setMisanswer("您没有作答");
				else if(rs.getString("a.misanswer").equals("1"))
				    s.setMisanswer("√");
				else 
					s.setMisanswer("×");
	            list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

}
