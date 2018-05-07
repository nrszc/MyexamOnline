package henu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.LoadIF;
import henu.bean.ChoiceQuestion;
import henu.bean.DetailStatistics;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.util.DbcpPool;

public class LoadImpl implements LoadIF{

	@Override
	public boolean saveCQ(ChoiceQuestion CQ, int subjectid) {
		String sql_save = " INSERT INTO choicequestion(c_question,c_choiceA,c_choiceB,c_choiceC,c_choiceD,c_answer,c_subjectid) VALUES(?,?,?,?,?,?,?)";  
        int i = 0;  
        try {  
        	PreparedStatement prep = null;  
        	prep = DbcpPool.executePreparedStatement(sql_save);
            prep.setString(1, CQ.getC_question());  
            prep.setString(2, CQ.getC_choiceA());  
            prep.setString(3, CQ.getC_choiceB());  
            prep.setString(4, CQ.getC_choiceC());  
            prep.setString(5, CQ.getC_choiceD());  
            prep.setString(6, CQ.getC_answer());  
            prep.setInt(7, subjectid);  
            i  = prep.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
        	DbcpPool.close();
        }  
        if(i>0)  
           return true;  
        else 
           return false; 
	}

	@Override
	public boolean saveFQ(FillQuestion FQ, int subjectid) {
		String sql_save = " INSERT INTO fillquestion(f_question,f_answer,f_subjectid) VALUES(?,?,?)";  
        int i = 0;  
        try {  
        	PreparedStatement prep = null;  
        	prep = DbcpPool.executePreparedStatement(sql_save);
            prep.setString(1, FQ.getF_question());  
            prep.setString(2, FQ.getF_answer());  
            prep.setInt(3, subjectid);  
            i  = prep.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
        	DbcpPool.close();
        }  
        if(i>0)  
           return true;  
        else 
           return false;
	}

	@Override
	public boolean saveJQ(JudgeQuestion JQ, int subjectid) {
		String sql_save = " INSERT INTO judgequestion(j_question,j_answer,j_subjectid) VALUES(?,?,?)";  
        int i = 0;  
        try {  
        	PreparedStatement prep = null;  
        	prep = DbcpPool.executePreparedStatement(sql_save);
            prep.setString(1, JQ.getJ_question());  
            prep.setInt(2, JQ.getJ_answer());  
            prep.setInt(3, subjectid);  
            i  = prep.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
        	DbcpPool.close();
        }  
        if(i>0)  
           return true;  
        else 
           return false;
	}

	@Override
	public List<DetailStatistics> saveExcel(int paperid) {
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
