package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.ExamOnlineIF;
import henu.IF.ReleaseHistoryIF;
import henu.bean.ChoiceQuestion;
import henu.bean.FillQuestion;
import henu.bean.JudgeQuestion;
import henu.bean.Paper;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class ExamOnline
 */
@WebServlet("/ExamOnline")
public class ExamOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamOnline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try {
				myway(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		public void myway(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException
		{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String method = request.getParameter("method");
			switch(method){
			     case "examList"  : examList(request, response);   break;
			     case "enterExam" : enterExam(request, response);  break;
			     case "countScore": countScore(request, response); break;
			}
		}

	private void countScore(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		    ExamOnlineIF eo = DaoFactory.getExamOnlineDaoInstance();
		    int paperid = Integer.parseInt(request.getParameter("paperid"));
			String sid = (String)request.getSession().getAttribute("sid");
		    String Q[] = new String[25];
			System.out.println(sid);
		    for(int i=1;i<21;i++){
			   Q[i] = request.getParameter(i+"");
		    }
            int Scoce = eo.getScore(Q,paperid,sid);
    		PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('请到个人成绩查看成绩!');</script>"); 
    		out.println("<script>window.location.href='/MyexamOnline/student/studentHome.jsp'</script>");

		}

	private void enterExam(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
			int paperid = Integer.parseInt(request.getParameter("paperid"));
			String sid = (String)request.getSession().getAttribute("sid");
		    ExamOnlineIF eo = DaoFactory.getExamOnlineDaoInstance();
			int result = eo.checkState(paperid,sid);
    		PrintWriter   out   =   response.getWriter(); 
		    if(result==1)
		    {
		    	out.println("<script>alert('本次考试您已经考过，不能再考一次!');history.back();</script>"); 
		    }
		    else if(result==2)
		    {
		    	out.println("<script>alert('考试时间已过，下次要来早一点哦!');</script>"); 
		    	examList(request, response);
		    }
		    else{
			ReleaseHistoryIF rh = DaoFactory.getReleaseHistoryDaoInstance();
			List<ChoiceQuestion> choiceQPaper = rh.getChoiceQPaper(paperid);
			request.getSession().setAttribute("choiceQPaper", choiceQPaper);
			List<FillQuestion> fillQPaper = rh.getFillQPaper(paperid);
			request.getSession().setAttribute("fillQPaper", fillQPaper);
			List<JudgeQuestion> judgeQPaper = rh.getJudgeQPaper(paperid);
			request.getSession().setAttribute("judgeQPaper", judgeQPaper);
			request.getSession().setAttribute("paperid", request.getParameter("paperid"));
			response.sendRedirect("student/enterExam.jsp");
		    }
		}

	private void examList(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String sid = (String)request.getSession().getAttribute("sid");
			ExamOnlineIF eo = DaoFactory.getExamOnlineDaoInstance();
			List<Paper> list = eo.examlist(sid);
			request.getSession().setAttribute("paperList", list);
    		PrintWriter   out   =   response.getWriter(); 
    		out.println("<script>window.location.href='/MyexamOnline/student/examList.jsp'</script>");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
