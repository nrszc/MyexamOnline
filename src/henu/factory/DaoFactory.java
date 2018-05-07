package henu.factory;

import henu.IF.ExamOnlineIF;
import henu.IF.LoadIF;
import henu.IF.LoginIF;
import henu.IF.MakePaperIF;
import henu.IF.ManagementClassIF;
import henu.IF.ManagementQuestionIF;
import henu.IF.ManagementStudentIF;
import henu.IF.ManagementSubjectIF;
import henu.IF.ManagementTeacherIF;
import henu.IF.MyMistakeIF;
import henu.IF.ReleaseHistoryIF;
import henu.IF.StatisticsIF;
import henu.IF.MyScoreIF;
import henu.IF.PersonalInfoIF;
import henu.IF.UpdatePWIF;
import henu.impl.ExamOnlineImpl;
import henu.impl.LoadImpl;
import henu.impl.LoginImpl;
import henu.impl.MakePaperImpl;
import henu.impl.ManagementClassImpl;
import henu.impl.ManagementQuestionImpl;
import henu.impl.ManagementStudentImpl;
import henu.impl.ManagementSubjectImpl;
import henu.impl.ManagementTeacherImpl;
import henu.impl.MyMistakeImpl;
import henu.impl.ReleaseHistoryImpl;
import henu.impl.StatisticsImpl;
import henu.impl.MyScoreImpl;
import henu.impl.PersonalInfoImpl;
import henu.impl.UpdatePWIpml;

public class DaoFactory {
	public static LoginIF getUserDaoInstance()
	{
		return new LoginImpl();
	}
	
	public static ManagementQuestionIF getTeacherDaoInstance()
	{
		return new ManagementQuestionImpl();
	}
	
	public static MakePaperIF getMakePaperDaoInstance()
	{
		return new MakePaperImpl();
	}
	
	public static ReleaseHistoryIF getReleaseHistoryDaoInstance()
	{
		return new ReleaseHistoryImpl();
	}
	
	public static StatisticsIF getStatisticsDaoInstance()
	{
		return new StatisticsImpl();
	}
	
	public static UpdatePWIF getUpdatePWDaoInstance()
	{
		return new UpdatePWIpml();
	}
	
	public static ManagementSubjectIF getManagementSubjectDaoInstance()
	{
		return new ManagementSubjectImpl();
	}
	
	public static ManagementTeacherIF getManagementTeacherDaoInstance()
	{
		return new ManagementTeacherImpl();
	}
	
	public static ManagementClassIF getManagementClassDaoInstance()
	{
		return new ManagementClassImpl();
	}
	
	public static ManagementStudentIF getManagementStudentDaoInstance()
	{
		return new ManagementStudentImpl();
	}
	
	public static ExamOnlineIF getExamOnlineDaoInstance()
	{
		return new ExamOnlineImpl();
	}
	
	public static MyScoreIF getMyScoreDaoInstance()
	{
		return new MyScoreImpl();
	}
	
	public static PersonalInfoIF getPersonalInfoDaoInstance()
	{
		return new PersonalInfoImpl();
	}
	
	public static MyMistakeIF getMyMistakeDaoInstance()
	{
		return new MyMistakeImpl();
	}
	
	public static LoadIF getLoadDaoInstance()
	{
		return new LoadImpl();
	}
}
