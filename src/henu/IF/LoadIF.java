package henu.IF;

import java.io.FileNotFoundException;
import java.util.List;

import henu.bean.ChoiceQuestion;
import henu.bean.DetailStatistics;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;

public interface LoadIF {
	public boolean saveCQ(ChoiceQuestion CQ, int subjectid); 
	public boolean saveFQ(FillQuestion FQ, int subjectid); 
	public boolean saveJQ(JudgeQuestion JQ, int subjectid); 
	public List<DetailStatistics> saveExcel(int paperid);
}
