package henu.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.ReleaseHistoryIF;
import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.bean.Paper;
import henu.others.myClass;
import henu.others.myTeacher;
import henu.others.subject;
import henu.util.DbcpPool;

public class ReleaseHistoryImpl implements ReleaseHistoryIF{

	@Override
	public List<Paper> findpaperHistory(String tid) {
		String sql = null;
		sql = "select * from paper where tid='"+tid+"'";
		List<Paper> list = new ArrayList<Paper>();
		myClass mc = new myClass();
		subject sj = new subject();
		myTeacher mt = new myTeacher();
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			Paper p = new Paper();
			p.setClassid(rs.getInt("classid"));
			p.setClassname(mc.getClassName(rs.getInt("classid")));
			String endtime = dateFormat.format( rs.getTimestamp("endtime") );
			String starttime = dateFormat.format( rs.getTimestamp("starttime") );
			p.setEndtime(endtime);
			p.setStarttime(starttime);
			p.setPapername(rs.getString("papername"));
			p.setPaperid(rs.getInt("paperid"));
			p.setSubjectid(rs.getInt("subjectid"));
			p.setSubjectname(sj.getSubjectName(rs.getInt("subjectid")));
			p.setTid(tid);
			p.setTeachername(mt.getTeacherName(tid));
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

	@Override
	public List<ChoiceQuestion> getChoiceQPaper(int paperid) {
		String sql = null;
		sql = "select * from paper,choicequestion where (c_id=c1 or c_id=c2 or "
				+ "c_id=c3 or c_id=c4 or c_id=c5 or c_id=c6 or "
				+ "c_id=c7 or c_id=c8 or c_id=c9 or c_id=c10) and paperid="+paperid+" order by c_id";
		List<ChoiceQuestion> list = new ArrayList<ChoiceQuestion>();
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			ChoiceQuestion cq = new ChoiceQuestion();
			cq.setC_question(rs.getString("c_question"));
			cq.setC_choiceA(rs.getString("c_choiceA"));
			cq.setC_choiceB(rs.getString("c_choiceB"));
			cq.setC_choiceC(rs.getString("c_choiceC"));
			cq.setC_choiceD(rs.getString("c_choiceD"));
			list.add(cq);
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
	public List<FillQuestion> getFillQPaper(int paperid) {
		String sql = null;
		sql = "select * from paper,fillquestion where (f_id=f1 or f_id=f2 or "
				+ "f_id=f3 or f_id=f4 or f_id=f5) and paperid="+paperid+" order by f_id";
		List<FillQuestion> list = new ArrayList<FillQuestion>();
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			FillQuestion fq = new FillQuestion();
			fq.setF_question(rs.getString("f_question"));
			list.add(fq);
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
	public List<JudgeQuestion> getJudgeQPaper(int paperid) {
		String sql = null;
		sql = "select * from paper,judgequestion where (j_id=j1 or j_id=j2 or "
				+ "j_id=j3 or j_id=j4 or j_id=j5) and paperid="+paperid+" order by j_id";
		List<JudgeQuestion> list = new ArrayList<JudgeQuestion>();
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			JudgeQuestion jq = new JudgeQuestion();
			jq.setJ_question(rs.getString("j_question"));
			list.add(jq);
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
