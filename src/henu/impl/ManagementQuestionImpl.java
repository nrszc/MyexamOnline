package henu.impl;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.ManagementQuestionIF;
import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.others.subject;
import henu.util.DbcpPool;

public class ManagementQuestionImpl implements ManagementQuestionIF{


	@Override
	public boolean addchoiceQuestion(ChoiceQuestion cq) {
		String sql = "INSERT INTO choicequestion (c_question,c_choiceA,"
				+ "c_choiceB,c_choiceC"
				+ ",c_choiceD,c_answer,c_subjectid) "
				+ "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, cq.getC_question());
			ps.setString(2, cq.getC_choiceA());
			ps.setString(3, cq.getC_choiceB());
			ps.setString(4, cq.getC_choiceC());
			ps.setString(5, cq.getC_choiceD());
			ps.setString(6, cq.getC_answer());
			ps.setInt(7, cq.getC_subjectid());
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

	@Override
	public boolean addfillQuestion(FillQuestion fq) {
		String sql = "INSERT INTO fillquestion (f_question,f_answer,"
				+ "f_subjectid) "
				+ "VALUES (?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, fq.getF_question());
			ps.setString(2, fq.getF_answer());
			ps.setInt(3, fq.getF_subjectid());
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

	@Override
	public boolean addjudgeQuestion(JudgeQuestion jq) {
		String sql = "INSERT INTO judgequestion (j_question,j_answer,"
				+ "j_subjectid) "
				+ "VALUES (?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int result = 0 ;
		try {
			ps.setString(1, jq.getJ_question());
			ps.setInt(2, jq.getJ_answer());
			ps.setInt(3, jq.getJ_subjectid());
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

	@Override
	public List<ChoiceQuestion> findchoiceQuestion(String subjectid) {
		String sql = null;
		if(subjectid==null) //判断题型
			sql = "select * from choicequestion";
		else 
			sql = "select * from choicequestion where c_subjectid='"+subjectid+"'";
		List<ChoiceQuestion> list = new ArrayList<ChoiceQuestion>();
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			ChoiceQuestion cq = new ChoiceQuestion();
			cq.setC_question(rs.getString("c_question"));
			cq.setC_choiceA(rs.getString("c_choiceA"));
			cq.setC_choiceB(rs.getString("c_choiceB"));
			cq.setC_choiceC(rs.getString("c_choiceC"));
			cq.setC_choiceD(rs.getString("c_choiceD"));
			cq.setC_id(rs.getInt("c_id"));
			cq.setC_answer(rs.getString("c_answer"));
			cq.setC_subjectid(rs.getInt("c_subjectid"));
			subject sj = new subject();
			cq.setC_subjectname(sj.getSubjectName(rs.getInt("c_subjectid")));
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
	public boolean deletechoiceQuestion(String c_id) {
		int result = 0;
		String sql = "delete from choicequestion where c_id='"+c_id+"'";
		result = DbcpPool.executeUpdate(sql);
		if(result>0)
			return true;
		else 
		    return false;
	}

	@Override
	public boolean modifychoiceQuestion(ChoiceQuestion cq) {
		String sql = null;
		sql = "update choicequestion set c_question=?,c_choiceA=?,c_choiceB=?,"
				+"c_choiceC=?,c_choiceD=?,c_answer=? where c_id=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, cq.getC_question());
		ps.setString(2, cq.getC_choiceA());
		ps.setString(3, cq.getC_choiceB());
		ps.setString(4, cq.getC_choiceC());
		ps.setString(5, cq.getC_choiceD());
		ps.setString(6, cq.getC_answer());
		ps.setInt(7, cq.getC_id());
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

	@Override
	public List<FillQuestion> findfillQuestion(String subjectid) {
		String sql = null;
		if(subjectid==null) //判断题型
			sql = "select * from fillquestion";
		else 
			sql = "select * from fillquestion where f_subjectid='"+subjectid+"'";
		List<FillQuestion> list = new ArrayList<FillQuestion>();
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			FillQuestion fq = new FillQuestion();
			fq.setF_question(rs.getString("f_question"));
			fq.setF_id(rs.getInt("f_id"));
			fq.setF_answer(rs.getString("f_answer"));
			fq.setF_subjectid(rs.getInt("f_subjectid"));
			subject sj = new subject();
			fq.setF_subjectname(sj.getSubjectName(rs.getInt("f_subjectid")));
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
	public boolean deletefillQuestion(String f_id) {
		int result = 0;
		String sql = "delete from fillquestion where f_id='"+f_id+"'";
		result = DbcpPool.executeUpdate(sql);
		if(result>0)
			return true;
		else 
		    return false;
	}

	@Override
	public boolean modifyfillQuestion(FillQuestion fq) {
		String sql = null;
		sql = "update fillquestion set f_question=?,"
				+"f_answer=? where f_id=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, fq.getF_question());
		ps.setString(2, fq.getF_answer());
		ps.setInt(3, fq.getF_id());
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

	@Override
	public List<JudgeQuestion> findjudgeQuestion(String subjectid) {
		String sql = null;
		if(subjectid==null) //判断题型
			sql = "select * from judgequestion";
		else 
			sql = "select * from judgequestion where j_subjectid='"+subjectid+"'";
		List<JudgeQuestion> list = new ArrayList<JudgeQuestion>();
		ResultSet rs = null;
        String sb = "";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			JudgeQuestion jq = new JudgeQuestion();
			jq.setJ_question(rs.getString("j_question"));
			jq.setJ_id(rs.getInt("j_id"));
			jq.setJ_answer(rs.getInt("j_answer"));
			jq.setJ_subjectid(rs.getInt("j_subjectid"));
			subject sj = new subject();
			jq.setJ_subjectname(sj.getSubjectName(rs.getInt("j_subjectid")));
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

	@Override
	public boolean deletejudgeQuestion(String j_id) {
		int result = 0;
		String sql = "delete from judgequestion where j_id='"+j_id+"'";
		result = DbcpPool.executeUpdate(sql);
		if(result>0)
			return true;
		else 
		    return false;
	}

	@Override
	public boolean modifyjudgeQuestion(JudgeQuestion jq) {
		String sql = null;
		sql = "update judgequestion set j_question=?,"
				+"j_answer=? where j_id=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, jq.getJ_question());
		ps.setInt(2, jq.getJ_answer());
		ps.setInt(3, jq.getJ_id());
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
